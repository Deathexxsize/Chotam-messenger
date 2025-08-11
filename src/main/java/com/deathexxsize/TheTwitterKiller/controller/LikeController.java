package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{tweetId}")
    public ResponseEntity< ? > putLike(
            @PathVariable int tweetId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(likeService.putLike(tweetId, userPrincipal.getId()));
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity< ? > removeLike(
            @PathVariable int tweetId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(likeService.removeLike(tweetId, userPrincipal.getId()));
    }
}
