package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties({"comentarios","posts"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String texto;


    @JsonIgnoreProperties("comentarios")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comentario_id", referencedColumnName = "id")
    private Comentario comentario;*/

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
