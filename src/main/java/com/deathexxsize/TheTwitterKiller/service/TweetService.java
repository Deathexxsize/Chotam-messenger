package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.AllCommentsResponse;
import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentDTO;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.AllTweetsResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.exception.TweetNotFoundException;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.AllCommentsMapper;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.CreateCommentMapper;
import com.deathexxsize.TheTwitterKiller.mapper.tweetMappers.TweetMapper;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.CommentRepository;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        Tweet tweet = tweetRepo.getTweetById(id)
                .orElseThrow(() -> new TweetNotFoundException("tweet not found"));
        return tweetMapper.toAllTweetsDTO(tweet);
    }

    public List<TweetFeedResponse> getDailyNews(String username) {
        LocalDateTime since = LocalDateTime.now().minusHours(24);
        return tweetRepo.findRecentTweetsFromFollowing(username, since);
    }

    public String deleteTweet(int id, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + " not found"));

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
