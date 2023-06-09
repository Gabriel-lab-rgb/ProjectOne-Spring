package com.example.demo.Repository;

import com.example.demo.Entity.Mensaje;
import com.example.demo.Entity.Sala;
import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {

    Optional<List<Mensaje>> findBySala(Sala sala);
    Optional<List<Mensaje>> findBySalaAndUsuario(Sala sala, Usuario usuario);
    Optional<List<Mensaje>> findBySalaOrderByFechaDesc(Sala sala);
}

