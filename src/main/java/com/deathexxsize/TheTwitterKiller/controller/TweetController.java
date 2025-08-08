package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetRequest;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/{username}")
    public ResponseEntity< ? > createTweet(
            @PathVariable String username,
            @RequestBody CreateTweetRequest createTweetRequest
    ) {
        return ResponseEntity.ok(tweetService.createPost(username, createTweetRequest.getTitle(), createTweetRequest.getContent()));
    }

    @GetMapping("/{username}/tweets")
    public ResponseEntity< ? > getAllTweets(@PathVariable String username) {
        return ResponseEntity.ok(tweetService.getAllUserTweets(username));
    }

    @GetMapping("/{username}/{id}")
    public ResponseEntity< ? > getTweet(@PathVariable String username, @PathVariable int id) {
        return ResponseEntity.ok(tweetService.getUserTweet(username, id));
    }

    @GetMapping("/feed")
    public ResponseEntity< ? > getNewsFeed(@AuthenticationPrincipal UserDetails userDetails) {
        List<TweetFeedResponse> feed = tweetService.getDailyNews(userDetails.getUsername());
        return ResponseEntity.ok(feed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity< ? > deleteTweet(@PathVariable int id , @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(tweetService.deleteTweet(id, userDetails.getUsername()));
    }

    @GetMapping("/{tweetId}/comments")
    public ResponseEntity< ? > getAllComments(@PathVariable int tweetId) {
        return ResponseEntity.ok(tweetService.getComments(tweetId));
    }
}
