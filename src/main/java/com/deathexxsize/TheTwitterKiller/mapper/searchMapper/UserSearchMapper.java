package com.deathexxsize.TheTwitterKiller.mapper.searchMapper;

import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchResponse;
import com.deathexxsize.TheTwitterKiller.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSearchMapper {

    @Mapping(source = "id", target = "userId")
    UserSearchResponse toUserSearchResponse(User user);

    List<UserSearchResponse> toUserSearchResponse(List<User> users);
}
