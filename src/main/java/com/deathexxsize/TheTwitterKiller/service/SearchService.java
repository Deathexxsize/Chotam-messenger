package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchRequest;
import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchResponse;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.searchMapper.UserSearchMapper;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepo;
    private final UserSearchMapper userSearchMapper;

    public List<UserSearchResponse> userSearch(UserSearchRequest userSearchRequest) {
        List<User> users = userRepo.findByUsernameStartingWithIgnoreCase(userSearchRequest.getUsername());

        if (users.isEmpty()) throw new UserNotFoundException("User not found");

        return userSearchMapper.toUserSearchResponse(users);
    }
}
