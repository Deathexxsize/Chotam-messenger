package com.deathexxsize.TheTwitterKiller.repository;

import com.deathexxsize.TheTwitterKiller.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
}
