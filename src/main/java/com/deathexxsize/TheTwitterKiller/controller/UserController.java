package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity< ? > getMyProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUserProfile(userDetails.getUsername()));
    }

    @GetMapping("/{username}")
    public ResponseEntity< ? > getUserProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfile(username));
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity< ? > getFollowers(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowers(username));
    }

    @GetMapping("/{username}/following")
    public ResponseEntity< ? > getFollowing(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowing(username));
    }

    @PatchMapping("/{username}/edit")
    public ResponseEntity< ? > editData(
            @PathVariable String username, @RequestBody Map<String, Object> edits
            ) {
        return ResponseEntity.ok( userService.editUserData(username, edits));
    }

    @DeleteMapping("/{username}/delete")
    public ResponseEntity< ? > deleteProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteProfile(username));
    }

}
