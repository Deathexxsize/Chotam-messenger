package com.deathexxsize.TheTwitterKiller.repository;

import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
    Optional<Tweet> getTweetById(Integer id);

    @Query("""
    SELECT new com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse(
        t.id,
        t.user.username,
        t.title,
        t.content,
        COUNT(l.id),
        t.createdAt
    )
    FROM Tweet t
    JOIN t.user u
    JOIN Follow f ON f.following = u
    LEFT JOIN Like l ON l.tweet = t
    WHERE f.follower.username = :username
      AND t.createdAt >= :since
      AND NOT EXISTS (
          SELECT 1 FROM Like liked
          WHERE liked.tweet = t AND liked.user.username = :username
      )
    GROUP BY t.id, t.content, t.createdAt, u.username
    ORDER BY t.createdAt DESC
""")
    List<TweetFeedResponse> findRecentTweetsFromFollowing(
            @Param("username") String username,
            @Param("since") LocalDateTime since
    );

    List<Tweet> findAllByUserId(int userId);
}
