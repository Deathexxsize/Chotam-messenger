package com.deathexxsize.TheTwitterKiller.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LikeId implements Serializable {
    private Integer userId;
    private Integer tweetId;
}
