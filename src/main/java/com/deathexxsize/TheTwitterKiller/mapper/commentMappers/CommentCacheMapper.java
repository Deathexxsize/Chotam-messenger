package com.deathexxsize.TheTwitterKiller.mapper.commentMappers;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentCacheDTO;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentCacheMapper {

    // Entity -> DTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "tweetId", source = "tweet.id")
    @Mapping(target = "authorId", source = "user.id")          // user в сущности Comment
    @Mapping(target = "authorUsername", source = "user.username")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "content", source = "content")
    CommentCacheDTO toCommentCacheDTO(Comment comment);

    List<CommentCacheDTO> toCommentCacheDTOs(List<Comment> comments);

    // DTO -> Entity
    @Mapping(target = "tweet", expression = "java(mapTweet(dto.tweetId()))")
    @Mapping(target = "user", expression = "java(mapUser(dto.authorId(), dto.authorUsername()))")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "content", source = "content")
    Comment toComment(CommentCacheDTO dto);

    List<Comment> toComments(List<CommentCacheDTO> dtos);

    // Вспомогательные мапперы
    default User mapUser(Integer id, String username) {
        if (id == null) return null;
        User u = new User();
        u.setId(id);
        u.setUsername(username);
        return u;
    }

    default Tweet mapTweet(Integer id) {
        if (id == null) return null;
        Tweet t = new Tweet();
        t.setId(id);
        return t;
    }
}


