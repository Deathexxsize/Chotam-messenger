package com.deathexxsize.TheTwitterKiller.mapper.userMappers;

import com.deathexxsize.TheTwitterKiller.dto.userDTOs.UserCacheDTO;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCacheMapper {
    User toUser(UserCacheDTO userCacheDTO);
    UserCacheDTO toUserCacheDTO(User user);
}
