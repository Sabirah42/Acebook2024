package com.makersacademy.acebook.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.Collections;
import  javax.persistence.Column;

@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;

    @Column(name="avatar_id")
    private Long avatarId;


    public User() {
        this.enabled = true;
    }

    public User(String username, String password, Long avatarId) {
        this.username = username;
        this.password = password;
        this.avatarId = avatarId;
        this.enabled = true;
    }

    public User(String username, String password, boolean enabled, Long avatarId) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.avatarId = avatarId;
    }

    public Long getId() {
        return id;
    }


    // methods for the spring sec userDetails class which we need for getting user ids etc. I have no idea how it works...
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Long getAvatarId() {
        return avatarId;
    }
    public void setAvatarId(Long AvatarId) {
        this.avatarId = avatarId;
    }
}
