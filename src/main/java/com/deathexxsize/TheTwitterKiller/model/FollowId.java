package com.deathexxsize.TheTwitterKiller.model;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FollowId implements Serializable {
    private Integer followerId;
    private Integer followingId;
}
