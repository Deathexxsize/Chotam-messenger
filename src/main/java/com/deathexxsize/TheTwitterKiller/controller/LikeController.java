package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired

    private LikeService likeService;

    @PostMapping("/{tweetId}/{userId}")
    public ResponseEntity< ? > putLike(@PathVariable int tweetId, @PathVariable int userId) {
        return ResponseEntity.ok(likeService.putLike(tweetId, userId));
    }

    @DeleteMapping("/{userId}/{tweetId}")
    public ResponseEntity< ? > removeLike(@PathVariable int userId, @PathVariable int tweetId) {
        return ResponseEntity.ok(likeService.removeLike(userId, tweetId));
    }
}
