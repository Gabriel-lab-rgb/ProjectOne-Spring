package com.example.demo.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class CreatePost {
    private String username;
    private String tipo;
    private String texto;
    private MultipartFile media;
}
