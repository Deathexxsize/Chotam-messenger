package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentRequest;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{tweetId}")
    public ResponseEntity< ? > writeComment(
            @PathVariable int tweetId,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CreateCommentRequest createRequest
            ) {
        return ResponseEntity.ok(commentService.createComment(tweetId, userPrincipal.getId(), createRequest));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity< ? > deleteComment(
            @PathVariable int commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(commentService.removeComment(commentId, userPrincipal.getId()));
    }
}
