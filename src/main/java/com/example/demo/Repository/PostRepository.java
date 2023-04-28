package com.example.demo.Repository;

import com.example.demo.Entity.Post;
import com.example.demo.Entity.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>> findByUsuario(long id);
    Optional<Post> findByComunidad(long id);

    Boolean existsByUsuario(long id);
    Boolean existsByComunidad(long id);


}
