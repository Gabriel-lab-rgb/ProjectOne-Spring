package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="image_name" ,nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;
}

