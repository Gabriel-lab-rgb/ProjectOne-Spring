package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Usuario usuario;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String texto;

    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comentario_id", referencedColumnName = "id")
    private Comentario comentario;*/


}
