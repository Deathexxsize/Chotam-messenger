package com.deathexxsize.TheTwitterKiller.mapper.commentMappers;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.AllCommentsResponse;
import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentDTO;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AllCommentsMapper {

    @Mapping(source = "comment.id", target = "commentId")
    @Mapping(source = "comment.createdAt", target = "timestamp")
    @Mapping(source = "user.username", target = "author")
    CommentDTO toCommentDTO(Comment comment);

    List<CommentDTO> toAllCommentsResponse(List<Comment> comments);
}
