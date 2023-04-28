package com.example.demo.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class ObjectNotExistsException extends RuntimeException{

    public ObjectNotExistsException(long objectId){
        super("Object " + objectId + " does not exist.");
    }
}
