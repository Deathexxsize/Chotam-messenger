package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{authorId}")
    public ResponseEntity< ? > subscribe(
            @PathVariable int authorId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        return ResponseEntity.ok(followService.subscribe(authorId, userPrincipal.getId()));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity< ? > unsubscribe(
            @PathVariable int authorId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        return ResponseEntity.ok(followService.unsubscribe(authorId, userPrincipal.getId()));
    }
}
