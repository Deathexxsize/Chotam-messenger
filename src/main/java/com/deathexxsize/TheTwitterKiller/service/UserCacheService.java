package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final RedisTemplate<Integer, User> template;
    private ValueOperations<Integer, User> valueOps;
    private final UserRepository userRepo;

    @PostConstruct
    public void init() {
        valueOps = template.opsForValue();
    }

    public User getOrLoad(int userId) {
        User user;
        user = valueOps.get(userId);
        if (user == null) {
            user = userRepo.getUserById(userId)
                    .orElseThrow(() -> new UserNotFoundException("user not found"));
            valueOps.setIfAbsent(userId, user, 10, TimeUnit.MINUTES);
            return user;
        }
        System.out.println("cashe");
        return user;
    }


}
