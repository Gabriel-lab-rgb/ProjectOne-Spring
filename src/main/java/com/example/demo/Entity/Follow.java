package com.example.demo.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario follower;

    @ManyToOne
    private Usuario followed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getFollower() {
        return follower;
    }

    public void setFollower(Usuario follower) {
        this.follower = follower;
    }

    public Usuario getFollowed() {
        return followed;
    }

    public void setFollowed(Usuario followed) {
        this.followed = followed;
    }
}