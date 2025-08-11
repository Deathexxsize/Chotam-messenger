package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

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

    @GetMapping("/{username}/followers")
    public ResponseEntity< ? > getFollowers(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowers(username));
    }

    @GetMapping("/{username}/following")
    public ResponseEntity< ? > getFollowing(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowing(username));
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
