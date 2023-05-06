package com.example.demo.Controllers;


import com.example.demo.Entity.Comentario;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.Usuario;
import com.example.demo.Form.CreateComentario;
import com.example.demo.Form.UpdateComentario;
import com.example.demo.Repository.ComentarioRepository;
import com.example.demo.Service.ComentarioService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    ComentarioService comentarioService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<?> createComentarioPost(@ModelAttribute CreateComentario createComentario){

        Usuario usuario=userService.loadUser(createComentario.getUsername());
        Post post=postService.loadPost(createComentario.getPost_id());
        Comentario comentario=new Comentario();
        comentario.setUsuario(usuario);
        comentario.setPost(post);
        comentario.setTexto(createComentario.getTexto());
        java.util.Date d = new java.util.Date();
        comentario.setFecha(new java.sql.Date(d.getTime()));

        comentarioRepository.save(comentario);



        return new ResponseEntity<>("Comentario created successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateComentarioPost(@PathVariable("id") long id, @ModelAttribute UpdateComentario updateComentario){

        Comentario comentario=comentarioService.loadComentario(id);
        comentario.setTexto(updateComentario.getTexto());
        comentarioRepository.save(comentario);


        return new ResponseEntity<>("Comentario updated successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComentarioPost(@PathVariable("id") long id){

        Comentario comentario=comentarioService.loadComentario(id);
        comentarioRepository.delete(comentario);

        return new ResponseEntity<>("Comentario deleted successfully", HttpStatus.OK);
    }


}
