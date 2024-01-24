package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "friend_id")
    private Long friendId;

    public Connection() {}
    public Connection(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFriendId() {
        return friendId;
    }

}
