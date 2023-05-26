package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity

@Table(name = "likepost")
public class LikePost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties({"image","roles","posts","seguidores","siguiendo"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;
    @JsonIgnore
    @Column(nullable = false)
    private Date fecha;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
