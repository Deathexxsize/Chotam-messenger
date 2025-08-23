package com.deathexxsize.TheTwitterKiller.mapper.userMappers;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.RegisterRequest;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetDTO;
import com.deathexxsize.TheTwitterKiller.dto.userDTOs.UserProfileResponse;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TweetDTO.class)
public interface UserMapper {
    User toUser(RegisterRequest registerRequest);
    RegisterRequest toRegisterDTO(User user);
    @Mapping(target = "followers", expression = "java(user.getFollowers().size())")
    @Mapping(target = "following", expression = "java(user.getFollowing().size())")
    UserProfileResponse toUserProfileDTO(User user);
}
