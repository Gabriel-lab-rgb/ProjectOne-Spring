package com.example.demo.Form;

import lombok.Data;

@Data
public class CreateComentario {
    private long post_id;
    private String username;
    private String comentario;
}
