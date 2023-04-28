package com.example.demo.Repository;

import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {


    Optional<Comentario> findById(Long aLong);


}
