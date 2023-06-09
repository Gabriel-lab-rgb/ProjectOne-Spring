package com.example.demo.Service;

import com.example.demo.Repository.PostRepository;
import com.example.demo.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public Post loadPost(long id){

        Post post =  postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotExistsException(id));

        return post;
    }


}



