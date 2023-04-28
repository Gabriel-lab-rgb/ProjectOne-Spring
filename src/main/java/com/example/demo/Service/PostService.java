package com.example.demo.Service;

import com.example.demo.Repository.PostRepository;
import com.example.demo.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public Post loadPost(long id){

        Post post =  postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotExistsException(id));

        return post;
    }

   /* public List<Post> loadPostByUser(int id){

List<Post> posts =  postRepository.findByUsuario(id)
        .orElseThrow(() -> new ObjectNotExistsException(id));

        return posts;
    }*/



}



