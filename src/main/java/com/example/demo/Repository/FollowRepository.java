package com.example.demo.Repository;

import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.Follow;
import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findById(long id);


}
