package com.example.demo.Entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "likepost")
public class LikePost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

    @Column(nullable = false)
    private Date fecha;
}
