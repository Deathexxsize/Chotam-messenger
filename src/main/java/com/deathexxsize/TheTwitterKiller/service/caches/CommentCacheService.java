package com.deathexxsize.TheTwitterKiller.service.caches;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentCacheDTO;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.CommentCacheMapper;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import com.deathexxsize.TheTwitterKiller.repository.CommentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CommentCacheService {

    private final RedisTemplate<String, Object> template;
    private ValueOperations<String, Object> valueOps;
    @PostConstruct
    public void init() {
        valueOps = template.opsForValue();
    }
    private final ObjectMapper objectMapper;
    private final CommentCacheMapper commentCacheMapper;
    private final CommentRepository commentRepo;


    public List<Comment> getCommentsOrLoad(int tweetId) {
        String key = "tweet:" + tweetId + ":comments";

        Object cached = valueOps.get(key);
        if (cached != null) {
            List<CommentCacheDTO> dtoList = objectMapper.convertValue(
                    cached,
                    new TypeReference<List<CommentCacheDTO>>() {}
            );
            System.out.println("comment cache");
            return commentCacheMapper.toComments(dtoList);
        }
        List<Comment> comments = commentRepo.findByTweetId(tweetId);
        List<CommentCacheDTO> commentsDto = commentCacheMapper.toCommentCacheDTOs(comments);
        valueOps.set(key, commentsDto, 5, TimeUnit.MINUTES);
        return comments;
    }

    public void evictComments(int tweetId) {
        template.delete("tweet:" + tweetId + ":comments");
    }
}
