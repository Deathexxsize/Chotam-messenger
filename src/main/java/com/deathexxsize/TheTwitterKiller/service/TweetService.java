package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentDTO;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.AllTweetsResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.exception.AccountDeactivatedException;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.AllCommentsMapper;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.CommentCacheMapper;
import com.deathexxsize.TheTwitterKiller.mapper.tweetMappers.TweetMapper;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.CommentRepository;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
import com.deathexxsize.TheTwitterKiller.service.caches.CommentCacheService;
import com.deathexxsize.TheTwitterKiller.service.caches.TweetCacheService;
import com.deathexxsize.TheTwitterKiller.service.caches.UserCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepo;
    private final TweetMapper tweetMapper;
    private final CommentRepository commentRepo;
    private final AllCommentsMapper allCommentsMapper;
    private final UserCacheService userCacheService;
    private final TweetCacheService tweetCacheService;
    private final CommentCacheService commentCacheService;

    public CreateTweetResponse createPost(int userId , String title, String content) {
        User user = userCacheService.getUserOrLoad(userId);
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

    public List<AllTweetsResponse> getAllUserTweets(int userId) {
        User user = userCacheService.getUserOrLoad(userId);
        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }
        List<Tweet> tweets = tweetCacheService.getUserTweetsOrLoad(userId);
        return tweetMapper.toAllTweetsDTO(tweets);
    }

    public AllTweetsResponse getUserTweet(int userId, int tweetId) {
        User user = userCacheService.getUserOrLoad(userId);
        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }
        Tweet tweet = tweetCacheService.getTweetOrLoad(tweetId);
        return tweetMapper.toAllTweetsDTO(tweet);
    }

    public List<TweetFeedResponse> getDailyNews(String username) {
        LocalDateTime since = LocalDateTime.now().minusHours(24); // ! в кэш, но потом
        return tweetRepo.findRecentTweetsFromFollowing(username, since);
    }

    public String deleteTweet(int tweetId, int userId) {
        User user = userCacheService.getUserOrLoad(userId);
        if (!user.isEnabled()) {
            throw new AccountDeactivatedException("Account deactivated");
        }
        tweetRepo.deleteById(tweetId);
        tweetCacheService.evictTweet(tweetId, userId);
        return "tweet deleted";
    }

    public List<CommentDTO> getComments(int tweetId) {
        Tweet tweet = tweetCacheService.getTweetOrLoad(tweetId);

        List<Comment> comments = commentCacheService.getCommentsOrLoad(tweetId);
        return allCommentsMapper.toAllCommentsResponse(comments);
    }

}
