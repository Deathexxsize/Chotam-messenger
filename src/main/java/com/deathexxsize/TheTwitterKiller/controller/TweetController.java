package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetRequest;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    @PostMapping()
    public ResponseEntity< ? > createTweet(
            @RequestBody CreateTweetRequest createTweetRequest,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        return ResponseEntity.ok(tweetService.createPost(userPrincipal.getId(), createTweetRequest.getTitle(), createTweetRequest.getContent()));
    }

    @GetMapping("/{username}/tweets")
    public ResponseEntity< ? > getAllTweets(@PathVariable String username) {
        return ResponseEntity.ok(tweetService.getAllUserTweets(username));
    }

    @GetMapping("/{username}/{tweetId}")
    public ResponseEntity< ? > getTweet(@PathVariable String username, @PathVariable int tweetId) {
        return ResponseEntity.ok(tweetService.getUserTweet(username, tweetId));
    }

    @GetMapping("/feed")
    public ResponseEntity< ? > getNewsFeed(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<TweetFeedResponse> feed = tweetService.getDailyNews(userPrincipal.getUsername());
        return ResponseEntity.ok(feed);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity< ? > deleteTweet(@PathVariable int tweetId , @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(tweetService.deleteTweet(tweetId, userPrincipal.getId()));
    }

    @GetMapping("/{tweetId}/comments")
    public ResponseEntity< ? > getAllComments(@PathVariable int tweetId) {
        return ResponseEntity.ok(tweetService.getComments(tweetId));
    }
}
