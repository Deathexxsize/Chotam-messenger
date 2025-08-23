package com.deathexxsize.TheTwitterKiller.mapper.commentMappers;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentResponse;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateCommentMapper {

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "user.username", target = "authorId")
    @Mapping(source = "tweet.id", target = "tweetId")
    @Mapping(source = "createdAt", target = "timestamp")
    @Mapping(source = "content", target = "content")
    CreateCommentResponse toCreateCommentResponse(Comment comment);
}

