package com.deathexxsize.TheTwitterKiller.repository;

import com.deathexxsize.TheTwitterKiller.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTweetId(int tweetId);
}
