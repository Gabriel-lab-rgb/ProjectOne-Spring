package com.example.demo.Repository;


import com.example.demo.Entity.Follow;
import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findById(long id);

    Optional<Follow> findByFollowerAndFollowed(Usuario follower, Usuario followed);


}
