package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.exception.InvalidVerificationCodeException;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity< ? > getMyProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getUserProfile(userPrincipal.getId()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity< ? > getUserProfile(@PathVariable int userId ) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity< ? > getFollowers(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity< ? > getFollowing(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getFollowing(userId));
    }

    @PatchMapping("/edit")
    public ResponseEntity< ? > editData(
            @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Map<String, Object> edits
            ) {
        return ResponseEntity.ok( userService.editUserData(userPrincipal.getId(), edits));
    }

    @DeleteMapping("/delete")
    public ResponseEntity< ? > deleteProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.deleteProfile(userPrincipal.getId()));
    }



}
