package com.example.demo.Repository;

import com.example.demo.Entity.Follow;
import com.example.demo.Entity.Sala;
import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {


   Optional<Sala> findById(long id);
   List<Sala> findByUsuariosIn(List<Usuario> usuarios);



   Optional<List<Sala>> findByUsuarios(Usuario usuario);
}
