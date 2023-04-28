package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Repository
public interface FileUploadService {


    MultipartFile uploadFile(MultipartFile file);
}
