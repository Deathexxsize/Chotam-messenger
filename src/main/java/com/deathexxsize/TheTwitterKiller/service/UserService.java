package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.FollowDTO;
import com.deathexxsize.TheTwitterKiller.dto.UserProfileResponse;
import com.deathexxsize.TheTwitterKiller.exception.AccountDeactivatedException;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.FollowMapper;
import com.deathexxsize.TheTwitterKiller.mapper.UserMapper;
import com.deathexxsize.TheTwitterKiller.model.Follow;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final FollowMapper followMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public UserProfileResponse getUserProfile(int userId) {
        User user = userRepo.findById(userId)
                        .orElseThrow(() ->new UserNotFoundException("User not found"));
        isEnable(user);

        return userMapper.toUserProfileDTO(user);
    }

    public List<FollowDTO> getFollowers(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->new UserNotFoundException(username +" not found"));
        List<Follow> followers = user.getFollowers();

        return followMapper.toFollowersDTOs(followers);
    }

    public List<FollowDTO> getFollowing(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->new UserNotFoundException(username +" not found"));
        List<Follow> following = user.getFollowing();

        return followMapper.toFollowingDTOs(following);
    }

    public String editUserData(int userId, Map<String, Object> edits) {
        User user = userRepo.findById(userId)
                .orElseThrow(() ->new UserNotFoundException("User not found"));

        edits.forEach((key, value) -> {
            switch (key) {
                case "username"-> user.setUsername(String.valueOf(value));
                case "email" -> user.setEmail(String.valueOf(value));
                case "password" -> user.setPassword(encoder.encode((String.valueOf(value))));
                default -> throw new UsernameNotFoundException("Filed " + key + " cannot be updated");
            }
        });

        userRepo.save(user);
        return "Success";
    }

    public String deleteProfile(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() ->new UserNotFoundException("User not found"));
        user.setEnabled(false);
        userRepo.save(user);
        return "Account is deactivated";
    }

    private boolean isEnable(User user) {
        if (user.isEnabled() == true) {
            return true;
        } else {
            throw new AccountDeactivatedException("Account is deactivated");
        }
    }

}
