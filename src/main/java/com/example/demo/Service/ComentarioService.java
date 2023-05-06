package com.example.demo.Service;


import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.Post;
import com.example.demo.Repository.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    public Comentario loadComentario(long id){

        Comentario comment=comentarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotExistsException(id));

        return comment;
    }

    public List<Comentario> loadComentariosByPost(Post post){
        List<Comentario> comentarioList=comentarioRepository.findByPost(post)
                .orElseThrow(() -> new ObjectNotExistsException(post.getId()));
        return comentarioList;
    }
}
