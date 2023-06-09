package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Mensaje")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties({"comentarios","posts","seguidores","siguiendo","roles","email"})
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "sala_id",nullable = false)
    private Sala sala;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private Date fecha;

    private MessageStatus status;


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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public enum MessageStatus {
        RECIBIDO, ENTREGADO
    }



}
