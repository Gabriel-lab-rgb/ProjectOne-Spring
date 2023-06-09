package com.example.demo.Repository;

import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.Images;
import com.example.demo.Entity.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {


    Optional<Comentario> findById(long id);
    Optional <List<Comentario>> findByPostOrderByFecha(Post post);
}
