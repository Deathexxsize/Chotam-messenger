package com.deathexxsize.TheTwitterKiller.mapper;

import com.deathexxsize.TheTwitterKiller.dto.authDTOs.RegisterDTO;
import com.deathexxsize.TheTwitterKiller.dto.TweetDTO;
import com.deathexxsize.TheTwitterKiller.dto.UserProfileDTO;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TweetDTO.class)
public interface UserMapper {
    User toUser(RegisterDTO registerDTO);
    RegisterDTO toRegisterDTO(User user);
    @Mapping(target = "followers", expression = "java(user.getFollowers().size())")
    @Mapping(target = "following", expression = "java(user.getFollowing().size())")
    UserProfileDTO toUserProfileDTO(User user);
}
