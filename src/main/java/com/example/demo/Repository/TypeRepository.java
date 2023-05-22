package com.example.demo.Repository;

import com.example.demo.Entity.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TypeRepository extends JpaRepository<TypePost, Long> {


    Optional<TypePost> findByNombre(String nombre);
}
