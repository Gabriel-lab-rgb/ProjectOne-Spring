package com.example.demo.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class LikeNotExistsException extends RuntimeException {

    public LikeNotExistsException(long post_id,long usuario_id){
        super("Like no existe para el post " + post_id + " y usuario " + usuario_id);
    }


}
