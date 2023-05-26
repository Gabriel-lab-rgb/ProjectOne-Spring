package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "amigos")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"image","roles","posts","seguidores","siguiendo"})
    @ManyToOne
    private Usuario follower;

    @JsonIgnoreProperties({"image","roles","posts","seguidores","siguiendo"})
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
