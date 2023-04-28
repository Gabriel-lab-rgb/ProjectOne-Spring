package com.example.demo.Service;

import com.example.demo.Entity.Post;
import com.example.demo.Entity.TypePost;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class TypeService {


    @Autowired
    TypeRepository typeRepository;
    public TypePost loadTypePost(String nombre){

        TypePost typePost =  typeRepository.findByNombre(nombre)
                .orElseThrow(() -> new TypePostNotExistException(nombre));

        return typePost;
    }

    @ResponseStatus(HttpStatus.CONFLICT) // 409
    class TypePostNotExistException extends RuntimeException {
        public TypePostNotExistException(String meetingId) {
            super("Meeting " + meetingId + " does not exist.");
        }
    }


}

