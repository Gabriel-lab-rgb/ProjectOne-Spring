package com.example.demo.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class CreatePost {
    private String username;
    private String comunidad;
    private String type;
    private String texto;
    private ArrayList<MultipartFile> images;
    private MultipartFile gif;
    private MultipartFile video;
}
