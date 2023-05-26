package com.example.demo.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FollowNotExistsException extends RuntimeException{

    public FollowNotExistsException(){
        super("No existe el registro");
    }
}
