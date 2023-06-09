package com.example.demo.Service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class ImageService {

        private static final String IMAGE_DIRECTORY = "C:\\Users\\Nadia-PC\\IdeaProjects\\demo\\public\\img";

        public byte[] getImage(String imageName) throws IOException {

            File file = new File( IMAGE_DIRECTORY + imageName);
            byte[] bytes = new byte[(int) file.length()];

            try (FileInputStream fis = new FileInputStream(file)) {
                int bytesRead = fis.read(bytes);
                if (bytesRead == -1) {
                    // No se pudo leer ningún byte del archivo
                    throw new IOException("No se pudo leer el archivo");
                }
            } catch (IOException e) {
                // Maneja la excepción de lectura del archivo
                e.printStackTrace();
            }

            return bytes;
        }
    }


