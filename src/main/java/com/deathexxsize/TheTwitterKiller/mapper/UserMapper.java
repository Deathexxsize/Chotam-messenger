package com.deathexxsize.TheTwitterKiller.mapper;

import com.deathexxsize.TheTwitterKiller.dto.RegisterDTO;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterDTO registerDTO);
    RegisterDTO toRegisterDTO(User user);
}
