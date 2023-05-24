package com.example.demo.Repository;

import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.LikePost;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    @Override
    Optional<LikePost> findById(Long aLong);

    Optional<LikePost>findByPostAndUsuario(Post post, Usuario usuario);


}
