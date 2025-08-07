package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.exception.AccountDeactivatedException;
import com.deathexxsize.TheTwitterKiller.model.Follow;
import com.deathexxsize.TheTwitterKiller.model.FollowId;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.FollowRepository;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepo;
    private final FollowRepository followRepo;

    public String subscribe(int authorId, String username) {
        User author = userRepo.getReferenceById(authorId);
        isEnable(author);
        User user = userRepo.getReferenceByUsername(username);

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
    public String unsubscribe(int authorId, String username) {
        User author = userRepo.getReferenceById(authorId);
        isEnable(author);
        User user = userRepo.getReferenceByUsername(username);

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
