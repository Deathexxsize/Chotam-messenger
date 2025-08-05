package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentRequest;
import com.deathexxsize.TheTwitterKiller.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{tweetId}/{userId}")
    public ResponseEntity< ? > writeComment(
            @PathVariable int tweetId,
            @PathVariable int userId,
            @RequestBody CreateCommentRequest createRequest
            ) {
        return ResponseEntity.ok(commentService.createComment(tweetId, userId, createRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity< ? > deleteComment(@PathVariable int commentId) {
        return ResponseEntity.ok(commentService.removeComment(commentId));
    }
}
