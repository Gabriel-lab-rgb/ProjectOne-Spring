package com.example.demo.Service;

import com.example.demo.Repository.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class FileUploadServiceImpl implements FileUploadService {

   private final  String RUTA="C:\\Users\\Nadia-PC\\IdeaProjects\\demo\\public\\img";

    @Override
    public MultipartFile uploadFile(MultipartFile file) {

        try {
            byte[]  data = file.getBytes();
            Path destinationFile = Paths.get(RUTA, file.getOriginalFilename());
            Files.write(destinationFile, data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }



}


