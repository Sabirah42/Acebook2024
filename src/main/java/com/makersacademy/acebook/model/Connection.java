package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long friend_id;

    public Connection(Long user_id, Long friend_id) {
        this.user_id = user_id;
        this.friend_id = friend_id;
    }

    public Long getUserId() {
        return user_id;
    }

    public Long getFriendId() {
        return friend_id;
    }

}
