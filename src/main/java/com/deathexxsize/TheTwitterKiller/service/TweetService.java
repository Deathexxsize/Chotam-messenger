package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentDTO;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.AllTweetsResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.exception.AccountDeactivatedException;
import com.deathexxsize.TheTwitterKiller.exception.TweetNotFoundException;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.AllCommentsMapper;
import com.deathexxsize.TheTwitterKiller.mapper.tweetMappers.TweetMapper;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.CommentRepository;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepo;
    private final UserRepository userRepo;
    private final TweetMapper tweetMapper;
    private final CommentRepository commentRepo;
    private final AllCommentsMapper allCommentsMapper;

    public CreateTweetResponse createPost(int userId , String title, String content) {
        User user = userRepo.findById(userId)
                .orElseThrow(() ->new UserNotFoundException("User not found"));

        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }

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

        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }

        List<Tweet> tweets = user.getTweets();
        return tweetMapper.toAllTweetsDTO(tweets);
    }

    public AllTweetsResponse getUserTweet(String username, int tweetId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " not found"));

        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }

        Tweet tweet = tweetRepo.getTweetById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("tweet not found"));

        return tweetMapper.toAllTweetsDTO(tweet);
    }

    public List<TweetFeedResponse> getDailyNews(String username) {
        LocalDateTime since = LocalDateTime.now().minusHours(24);
        return tweetRepo.findRecentTweetsFromFollowing(username, since);
    }

    public String deleteTweet(int id, int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }

        tweetRepo.deleteById(id);
        return "tweet deleted";
    }

    public List<CommentDTO> getComments(int tweetId) {
        Tweet tweet = tweetRepo.getTweetById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("tweet not found"));

        List<Comment> comments = commentRepo.findByTweetId(tweetId);
        return allCommentsMapper.toAllCommentsResponse(comments);
    }

}
