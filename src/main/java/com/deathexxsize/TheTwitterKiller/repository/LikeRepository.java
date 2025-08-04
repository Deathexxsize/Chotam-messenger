package com.deathexxsize.TheTwitterKiller.repository;

import com.deathexxsize.TheTwitterKiller.model.Like;
import com.deathexxsize.TheTwitterKiller.model.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

}
