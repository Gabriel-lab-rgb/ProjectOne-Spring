package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "comunidad")
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy="comunidad")
    private Set<Post> posts;
}
