package com.deathexxsize.TheTwitterKiller.mapper;

import com.deathexxsize.TheTwitterKiller.dto.FollowDTO;
import com.deathexxsize.TheTwitterKiller.model.Follow;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    @Named("toFollowersDTO")
    @Mapping(target = "username", source = "follower.username")
    FollowDTO toFollowersDTO(Follow follow);
    @IterableMapping(qualifiedByName = "toFollowersDTO")
    List<FollowDTO> toFollowersDTOs(List<Follow> followers);

    @Named("toFollowingDTO")
    @Mapping(target = "username", source = "following.username")
    FollowDTO toFollowingDTO(Follow follow);
    @IterableMapping(qualifiedByName = "toFollowingDTO")
    List<FollowDTO> toFollowingDTOs(List<Follow> followers);
}
