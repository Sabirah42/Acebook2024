package com.makersacademy.acebook.model;

import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long friend_id;

    public Long getUserId() {
        return user_id;
    }

    public Long getFriendId() {
        return friend_id;
    }

}
