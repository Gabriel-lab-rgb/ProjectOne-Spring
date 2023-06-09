package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date fecha;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sala_user",
            joinColumns = @JoinColumn(name = "sala_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @JsonIgnoreProperties({"comentarios","posts","seguidores","siguiendo","roles","email"})
    public List<Usuario> getUsuarios() {
        return usuarios;
    }


    public void agregarUsuario(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        usuarios.add(usuario);
    }
}
