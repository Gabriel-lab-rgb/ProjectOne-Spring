package com.example.demo.Controllers;

import com.example.demo.Entity.*;
import com.example.demo.Form.AddLike;
import com.example.demo.Form.CreatePost;
import com.example.demo.Repository.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    PostRepository postRepository;

    @Autowired
    LikePostRepository likePostRepository;

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
        post.setType(typeService.loadTypePost(createPost.getTipo()));

        switch (createPost.getTipo()){

            case "Video":
                post.setVideo(fileUploadService.uploadFile(createPost.getMedia()).getOriginalFilename());
                break;
            case "Images":
                /*post.setGif(fileUploadService.uploadFile(createPost.getGif()).getOriginalFilename());*/
                break;
        }
        java.util.Date d = new java.util.Date();
        post.setFecha(new java.sql.Date(d.getTime()));
        post.setTexto(createPost.getTexto());


        postRepository.save(post);
       /* if(createPost.getType().equals("Images")){
            if(createPost.getImages().size()>0){
                for(MultipartFile f: createPost.getImages()){
                    Images image=new Images();
                    image.setNombre(fileUploadService.uploadFile(f).getOriginalFilename());
                    image.setPost(post);
                    imageRepository.save(image);
                }
            }
        }*/

        return new ResponseEntity<>("Post registered successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/addLike", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLike(@ModelAttribute AddLike addLike){

        LikePost like=new LikePost();
        like.setPost(postService.loadPost(addLike.getPost_id()));
        like.setUsuario(userService.loadUser(addLike.getUsername()));
        java.util.Date d = new java.util.Date();
        like.setFecha(new java.sql.Date(d.getTime()));
        likePostRepository.save(like);


        return new ResponseEntity<>("Like registered successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/deleteLike", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteLike(@ModelAttribute AddLike addLike){

        Usuario usuario= userService.loadUser(addLike.getUsername());
        Post post=postService.loadPost(addLike.getPost_id());

        LikePost likePost=likePostRepository.findByPostAndUsuario(post,usuario).orElseThrow(() -> new LikeNotExistsException(post.getId(), usuario.getId()));
        likePostRepository.delete(likePost);

        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
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
