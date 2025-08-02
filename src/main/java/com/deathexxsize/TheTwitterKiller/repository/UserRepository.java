package com.deathexxsize.TheTwitterKiller.repository;

import com.deathexxsize.TheTwitterKiller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    void deleteByUsername(String username);
}
