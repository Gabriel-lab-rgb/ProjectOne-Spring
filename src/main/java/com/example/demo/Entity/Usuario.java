package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Rol> roles;

    @OneToMany(mappedBy="usuario")
    private Set<Post> posts;

    @OneToMany(mappedBy="usuario")
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy="usuario")
    private Set<LikePost> likePosts;

}
