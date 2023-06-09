package com.example.demo.Repository;

import com.example.demo.Entity.Sala;
import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsernameOrEmail(String username, String email);
    Optional<Usuario> findByUsername(String username);
    /*@Query("SELECT u.id,u.username,u.image FROM usuario u WHERE u.username LIKE %:username%")*/
    Optional<List<UsuarioSummary>> findByUsernameStartingWith(@Param("username") String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


}

