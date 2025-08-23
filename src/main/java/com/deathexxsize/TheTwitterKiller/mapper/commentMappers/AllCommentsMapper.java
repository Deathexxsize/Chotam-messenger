package com.deathexxsize.TheTwitterKiller.mapper.commentMappers;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentDTO;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AllCommentsMapper {

    @Mapping(source = "id", target = "commentId")                  // comment.id -> commentId
    @Mapping(source = "createdAt", target = "timestamp")           // comment.createdAt -> timestamp
    @Mapping(source = "user.username", target = "author")          // user.username -> author
    @Mapping(source = "content", target = "content")               // comment.content -> content
    CommentDTO toCommentDTO(Comment comment);

    List<CommentDTO> toAllCommentsResponse(List<Comment> comments);
}

