package com.deathexxsize.TheTwitterKiller.mapper;

import com.deathexxsize.TheTwitterKiller.dto.TweetDTO;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    TweetDTO toTweetDTO(Tweet tweet);
}
