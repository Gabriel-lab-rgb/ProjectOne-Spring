package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


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
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Rol> roles;

    @JsonIgnoreProperties({"comentarios,usuario"})
    @OneToMany(mappedBy="usuario",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Post> posts;
    @JsonIgnore
    @OneToMany(mappedBy="usuario",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Comentario> comentarios;
    @JsonIgnore
    @OneToMany(mappedBy="usuario",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<LikePost> likePosts;

    @JsonIgnoreProperties({"followed"})
    @OneToMany(mappedBy="followed",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Follow> seguidores;

    @JsonIgnoreProperties({"follower"})
    @OneToMany(mappedBy="follower",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Follow> siguiendo;


    @ManyToMany(mappedBy = "usuarios")
    private Set<Sala> salas;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<LikePost> getLikePosts() {
        return likePosts;
    }

    public void setLikePosts(Set<LikePost> likePosts) {
        this.likePosts = likePosts;
    }

    public Set<Follow> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Set<Follow> seguidores) {
        this.seguidores = seguidores;
    }

    public Set<Follow> getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(Set<Follow> siguiendo) {
        this.siguiendo = siguiendo;
    }


    /*
    public Post getUltimoPost() {
        if (posts != null && !posts.isEmpty()) {
            return Collections.max(posts, Comparator.comparing(Post::getFecha));
        }
        return null;
    }*/

}
