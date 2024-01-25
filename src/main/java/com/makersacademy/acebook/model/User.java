package com.makersacademy.acebook.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

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

    @ManyToOne
    @JoinColumn(name="avatar_id")
    private Avatar avatar;


    public User() {


        this.enabled = true;
    }

    public User(String username, String password, Long avatarId, Avatar avatar) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.enabled = true;
    }

    public User(String username, String password, boolean enabled, Avatar avatar) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.avatar = avatar;
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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
