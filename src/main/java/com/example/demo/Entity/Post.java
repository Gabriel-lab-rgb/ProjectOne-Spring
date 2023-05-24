package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties({"comentarios","posts"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="comunidad_id")
    private Comunidad comunidad;

    @Column(nullable = false)
    private Date fecha;
    @JsonIgnoreProperties("posts")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="type_post_id", nullable=false)
    private TypePost type;

    @Column(nullable = false)
    private String texto;

    @OneToMany(mappedBy="post")
    private Set<Images> images;

    private String video;
    private String gif;



   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPostShared", referencedColumnName = "id")
    private Post PostShared;
    @OneToOne(mappedBy = "post")
    private Post post;*/
    @JsonIgnore
    @OneToMany(mappedBy="post",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Comentario> comentarios;
    @JsonIgnoreProperties("post")
    @OneToMany(mappedBy="post",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<LikePost> likePosts;

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

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TypePost getType() {
        return type;
    }

    public void setType(TypePost type) {
        this.type = type;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Set<Images> getImages() {
        return images;
    }
/*
    public Images addImage(Images image)
    {
        if (!this.images.contains(image)) {
            this.images = (Set<Images>) image;
            image.setPost(this);
        }

        return this.images;
    }*/

    public void setImages(Set<Images> images) {
        this.images = images;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
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


}
