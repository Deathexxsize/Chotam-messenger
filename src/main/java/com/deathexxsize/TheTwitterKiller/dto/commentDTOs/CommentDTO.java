package com.deathexxsize.TheTwitterKiller.dto.commentDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int commentId;
    private String author;
    private String content;
    private LocalDateTime timestamp;
}
