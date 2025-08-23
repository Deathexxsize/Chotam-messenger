package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.exception.AlreadyLikedException;
import com.deathexxsize.TheTwitterKiller.exception.LikeNotFoundException;
import com.deathexxsize.TheTwitterKiller.model.Like;
import com.deathexxsize.TheTwitterKiller.model.LikeId;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.LikeRepository;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepo;
    private final UserRepository userRepo;
    private final TweetRepository tweetRepo;

    public String putLike(int tweetId, int userId) {
        User user = userRepo.getReferenceById(userId);
        Tweet tweet = tweetRepo.getReferenceById(tweetId);

        LikeId likeId = new LikeId(userId, tweetId);

        if (likeRepo.existsById(likeId)) {
            throw new AlreadyLikedException("Tweet already liked by user");
        }

        Like like = new Like();
        like.setId(likeId);
        like.setUser(user);
        like.setTweet(tweet);
        like.setCreatedAt(LocalDateTime.now());
        likeRepo.save(like);
        return "You've been liked";
    }

    public String removeLike(int tweetId, int userId) {
        LikeId likeId = new LikeId(userId, tweetId);

        if (!likeRepo.existsById(likeId)) {
            throw new LikeNotFoundException("Like not found for tweetId: " + tweetId + " and authorId: " + userId);
        }

        likeRepo.deleteById(likeId);
        return "Like removed successfully.";
    }

}
