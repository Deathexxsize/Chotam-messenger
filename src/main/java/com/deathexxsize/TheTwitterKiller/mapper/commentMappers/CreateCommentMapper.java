package com.deathexxsize.TheTwitterKiller.mapper.commentMappers;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentResponse;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateCommentMapper {

    @Mapping(source = "user.username", target = "author")
    @Mapping(source = "tweet.id", target = "tweetId")
    @Mapping(source = "createdAt", target = "timestamp")
    @Mapping(source = "comment.id", target = "commentId")
    CreateCommentResponse toCreateCommentResponse(Comment comment);
}
