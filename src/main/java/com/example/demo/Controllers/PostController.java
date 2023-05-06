package com.example.demo.Controllers;

import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.Images;
import com.example.demo.Entity.Usuario;
import com.example.demo.Form.CreateComentario;
import com.example.demo.Form.CreatePost;
import com.example.demo.Repository.*;
import com.example.demo.Service.ComentarioService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.TypeService;
import com.example.demo.Service.UserService;
import com.example.demo.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Autowired
    ComentarioService comentarioService;
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;


    @RequestMapping(value="/create", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> CreatePost(@ModelAttribute CreatePost createPost){

        Post post = new Post();
        post.setUsuario(userService.loadUser(createPost.getUsername()));
        post.setType(typeService.loadTypePost(createPost.getType()));

        switch (createPost.getType()){

            case "Video":
                post.setVideo(fileUploadService.uploadFile(createPost.getVideo()).getOriginalFilename());
                break;
            case "Gif":
                post.setGif(fileUploadService.uploadFile(createPost.getGif()).getOriginalFilename());
                break;
        }
        java.util.Date d = new java.util.Date();
        post.setFecha(new java.sql.Date(d.getTime()));
        post.setTexto(createPost.getTexto());


        postRepository.save(post);
        if(createPost.getType().equals("Images")){
            if(createPost.getImages().size()>0){
                for(MultipartFile f: createPost.getImages()){
                    Images image=new Images();
                    image.setNombre(fileUploadService.uploadFile(f).getOriginalFilename());
                    image.setPost(post);
                    imageRepository.save(image);
                }
            }
        }

        return new ResponseEntity<>("Post registered successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/update", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> UpdatePost(){

        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeletePost(@PathVariable("id") long id){

        Post post=postService.loadPost(id);
        postRepository.delete(post);

        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
    @RequestMapping(value="/comments/{id}", method = RequestMethod.GET)
    public List<Comentario> ShowComments(@PathVariable("id") long id){
         Post post=postService.loadPost(id);
        return comentarioService.loadComentariosByPost(post);
    }


}
