package com.example.demo.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        Set<GrantedAuthority> authorities = usuario
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(),
                usuario.getPassword(),
                authorities);
    }

    public Usuario loadUser(String username){
      Usuario usuario=userRepository.findByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
        return usuario;
    }

    public Usuario loadUserById(long id){
        Usuario usuario=userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + id));
        return usuario;
    }
}


