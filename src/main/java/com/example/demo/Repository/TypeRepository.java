package com.example.demo.Repository;

import com.example.demo.Entity.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<TypePost, Long> {


    Optional<TypePost> findByNombre(String nombre);
}
