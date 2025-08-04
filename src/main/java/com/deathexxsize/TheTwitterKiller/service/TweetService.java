package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.AllTweetsResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.tweetMappers.TweetMapper;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TweetMapper tweetMapper;

    public CreateTweetResponse createPost(String username, String title, String content) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->new UserNotFoundException(username +" not found"));

        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setTitle(title);
        tweet.setContent(content);

        tweetRepo.save(tweet);
        return tweetMapper.toCreateTweetDTO(tweet);
    }

    public List<AllTweetsResponse> getAllUserTweets(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " not found"));

        List<Tweet> tweets = user.getTweets();
        return tweetMapper.toAllTweetsDTO(tweets);
    }

    public AllTweetsResponse getUserTweet(String username, int id) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " not found"));

        Tweet tweet = tweetRepo.getTweetById(id);
        return tweetMapper.toAllTweetsDTO(tweet);
    }

    public List<TweetFeedResponse> getDailyNews(String username) {
        LocalDateTime since = LocalDateTime.now().minusHours(24);
        return tweetRepo.findRecentTweetsFromFollowing(username, since);
    }

    public String deletePost(int id, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " not found"));

        tweetRepo.deleteById(id);
        return "tweet deleted";
    }
}
