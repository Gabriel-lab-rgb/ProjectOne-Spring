package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="comunidad_id")
    private Comunidad comunidad;

    @Column(nullable = false)
    private Date fecha;

    @ManyToOne
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

    @OneToMany(mappedBy="post")
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy="post")
    private Set<LikePost> likePosts;
}
