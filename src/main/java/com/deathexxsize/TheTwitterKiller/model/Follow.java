package com.deathexxsize.TheTwitterKiller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "Follows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

    @EmbeddedId
    private FollowId id;

    @MapsId("followerId")
    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User follower;

    @MapsId("followingId")
    @ManyToOne
    @JoinColumn(name = "following_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User following;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
