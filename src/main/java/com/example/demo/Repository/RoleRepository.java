package com.example.demo.Repository;


import com.example.demo.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {
        Optional<Rol> findByNombre(String name);
    }

