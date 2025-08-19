package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.exception.AccountDeactivatedException;
import com.deathexxsize.TheTwitterKiller.model.Follow;
import com.deathexxsize.TheTwitterKiller.model.FollowId;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepo;
    private final UserCacheService userCacheService;

    public String subscribe(int authorId, int userId) {
        User author = userCacheService.getOrLoad(authorId);
        isEnable(author);
        User user = userCacheService.getOrLoad(userId);

        if (user.getId().equals(authorId)) { // что бы юзер не подписался сам на себя
            throw new IllegalArgumentException("You cannot subscribe to yourself");
        }

        FollowId followId = new FollowId(user.getId(), authorId);

        Follow follow = new Follow();
        follow.setId(followId);
        follow.setFollower(user);
        follow.setFollowing(author);
        follow.setCreatedAt(LocalDateTime.now());

        followRepo.save(follow);
        return "You have successfully subscribed to " + author.getUsername();
    }

    @Transactional
    public String unsubscribe(int authorId, int userId) {
        User author = userCacheService.getOrLoad(authorId);
        isEnable(author);
        User user = userCacheService.getOrLoad(userId);

        followRepo.deleteByFollowerIdAndFollowingId(user.getId(), authorId);

        return "You have unsubscribed from " + author.getUsername();
    }

    private boolean isEnable(User user) {
        if (user.isEnabled() == true) {
            return true;
        } else {
            throw new AccountDeactivatedException("Account is deactivated");
        }
    }

}
