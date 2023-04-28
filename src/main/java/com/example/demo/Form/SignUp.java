package com.example.demo.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUp {


    private String username;
    private String email;
    private String password;
    private MultipartFile file;
}
