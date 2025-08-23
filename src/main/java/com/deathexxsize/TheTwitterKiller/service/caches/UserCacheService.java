package com.deathexxsize.TheTwitterKiller.service.caches;

import com.deathexxsize.TheTwitterKiller.dto.userDTOs.UserCacheDTO;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.userMappers.UserCacheMapper;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final RedisTemplate<String, Object> template;
    private ValueOperations<String, Object> valueOps;
    private final UserRepository userRepo;
    private final UserCacheMapper userCacheMapper;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        valueOps = template.opsForValue();
    }

    public User getUserOrLoad(int userId) {
        String key = "user:" + userId;
        Object cached = valueOps.get(key);
        if (cached != null) {
            UserCacheDTO dto = objectMapper.convertValue(cached, UserCacheDTO.class);
            System.out.println("user cache");
            return userCacheMapper.toUser(dto);
        }
        User user = userRepo.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        UserCacheDTO userDto =  userCacheMapper.toUserCacheDTO(user);
        valueOps.set(key, userDto, 5, TimeUnit.MINUTES);
        return user;
    }
}
