package com.example.demo.Controllers;

import com.example.demo.Entity.Follow;
import com.example.demo.Form.UpdateUser;
import com.example.demo.Form.addFollow;
import com.example.demo.Repository.*;


import com.example.demo.Service.FollowNotExistsException;
import com.example.demo.Service.UserService;
import com.example.demo.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;




    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public Usuario loadUsuario(@PathVariable("username") String username){
        return userService.loadUser(username);
    }

  /*  @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        fileUploadService.uploadFile(file);
    }*/

    @RequestMapping(value="/{username}/image", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImageUser(@PathVariable("username") String username, @RequestParam("file") MultipartFile file){

        Usuario usuario=userService.loadUser(username);
        usuario.setImage(fileUploadService.uploadFile(file).getOriginalFilename());
        userRepository.save(usuario);

        return new ResponseEntity<>("User updated image successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/{username}/update", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable("username") String username,@ModelAttribute UpdateUser updateUser){

        Usuario usuario=userService.loadUser(username);
        usuario.setEmail(updateUser.getEmail());
        usuario.setPassword(updateUser.getPassword());
        usuario.setUsername(updateUser.getUsername());
        userRepository.save(usuario);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public List<UsuarioSummary> searchUsers(@RequestParam("cadena") String cadena) {
        List<UsuarioSummary> users = userService.loadUserByUsernameContaining(cadena);
        return users;
    }

    @RequestMapping(value = "/follow/add", method = RequestMethod.POST)
    public ResponseEntity<?> followUser(@ModelAttribute addFollow addFollow) {

        Usuario follower = userService.loadUser(addFollow.getFollower());
        Usuario followed = userService.loadUserById(addFollow.getFollowed());
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowed(followed);
        followRepository.save(follow);

        return new ResponseEntity<>("Usuario seguido exitosamente", HttpStatus.OK);
    }

    @RequestMapping(value = "/follow/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFollowUser(@PathVariable("follower") String user_follower,
                                              @PathVariable("followed") String user_followed) {

        Usuario follower= userService.loadUser(user_follower);
        Usuario followed=userService.loadUser(user_followed);
        Follow follow = followRepository.findByFollowerAndFollowed(follower,followed).orElseThrow(() -> new FollowNotExistsException());
        followRepository.delete(follow);

        return new ResponseEntity<>("Seguidor eliminado exitosamente", HttpStatus.OK);
    }

}
