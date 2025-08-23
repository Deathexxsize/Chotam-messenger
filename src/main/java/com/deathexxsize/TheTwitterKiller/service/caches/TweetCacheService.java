package com.deathexxsize.TheTwitterKiller.service.caches;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetCacheDTO;
import com.deathexxsize.TheTwitterKiller.exception.TweetNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.tweetMappers.TweetCacheMapper;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
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
public class TweetCacheService {

    private final RedisTemplate<String, Object> template;
    private ValueOperations<String, Object> valueOps;
    private final TweetRepository tweetRepo;
    private final TweetCacheMapper tweetCacheMapper;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        valueOps = template.opsForValue();
    }

    public Tweet getTweetOrLoad(int tweetId) {
        String key = "tweet:" + tweetId;

        Object cached = valueOps.get(key);
        if (cached != null) {
            TweetCacheDTO dto = objectMapper.convertValue(cached, TweetCacheDTO.class);
            System.out.println("tweet cache");
            return tweetCacheMapper.toTweet(dto);
        }
        Tweet tweet = tweetRepo.getTweetById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
        TweetCacheDTO dto = tweetCacheMapper.toTweetCache(tweet);
        valueOps.set(key, dto, 5, TimeUnit.MINUTES);

        return tweet;
    }

    public List<Tweet> getUserTweetsOrLoad(int userId) {
        String key = "user:" + userId + ":tweets";

        Object cached = valueOps.get(key);
        if (cached != null) {
            List<TweetCacheDTO> dtoList = objectMapper.convertValue(
                    cached,
                    new TypeReference<List<TweetCacheDTO>>() {}
            );
            System.out.println("tweet cache");
            return tweetCacheMapper.toTweetsFromCache(dtoList);
        }
        List<Tweet> tweets = tweetRepo.findAllByUserId(userId);
        List<TweetCacheDTO> dtoList = tweetCacheMapper.toTweetCacheDTOs(tweets);
        valueOps.set(key, dtoList, 5, TimeUnit.MINUTES);
        return tweets;
    }

    public void evictTweet(int tweetId, int userId) {
        template.delete("tweet:" + tweetId);
        template.delete("user:" + userId + ":tweets");
    }
}

