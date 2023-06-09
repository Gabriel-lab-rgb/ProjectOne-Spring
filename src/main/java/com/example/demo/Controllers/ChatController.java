package com.example.demo.Controllers;

import com.example.demo.Entity.*;
import com.example.demo.Form.createMensaje;
import com.example.demo.Form.createSala;
import com.example.demo.Repository.MensajeRepository;
import com.example.demo.Repository.SalaRepository;
import com.example.demo.Service.FollowNotExistsException;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @RequestMapping(value="/salas", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearSala(@ModelAttribute createSala createSala){

        java.util.Date d = new java.util.Date();
        Usuario emisor= userService.loadUser(createSala.getUsername1());
        Usuario receptor= userService.loadUser(createSala.getUsername2());

        String salaId = null;

        List<Sala> salasEmisor = salaRepository.findByUsuariosIn(Collections.singletonList(emisor));
        List<Sala> salasReceptor = salaRepository.findByUsuariosIn(Collections.singletonList(receptor));

        for (Sala salaEmisor : salasEmisor) {
            for (Sala salaReceptor : salasReceptor) {
                if (salaEmisor.getId()==salaReceptor.getId()) {
                    salaId = String.valueOf(salaEmisor.getId());
                    break;
                }
            }
            if (salaId != null) {
                break;
            }
        }

        if (salaId != null) {

            Sala sala=salaRepository.findById(Long.parseLong(salaId)).orElseThrow(() -> new FollowNotExistsException());;
            Mensaje nuevoMensaje = new Mensaje();
            nuevoMensaje.setMensaje(createSala.getMensaje());
            nuevoMensaje.setUsuario(emisor);
            nuevoMensaje.setSala(sala);
            nuevoMensaje.setFecha(new java.sql.Date(d.getTime()));
            mensajeRepository.save(nuevoMensaje);


        } else {
            Sala nuevaSala = new Sala();
            nuevaSala.agregarUsuario(emisor);
            nuevaSala.agregarUsuario(receptor);
            nuevaSala.setFecha(new java.sql.Date(d.getTime()));
            salaRepository.save(nuevaSala);

            // Crear el mensaje y asociarlo a la nueva sala
            Mensaje nuevoMensaje = new Mensaje();
            nuevoMensaje.setMensaje(createSala.getMensaje());
            nuevoMensaje.setUsuario(emisor);
            nuevoMensaje.setSala(nuevaSala);
            nuevoMensaje.setFecha(new java.sql.Date(d.getTime()));
            mensajeRepository.save(nuevoMensaje);

        }

        return new ResponseEntity<>("Sala o mensaje creado exitosamente", HttpStatus.OK);
    }

    @MessageMapping("/app/send/message")
    //@RequestMapping(value="/salas/mensajes", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void crearMensaje(@Payload createMensaje createMensaje){

        java.util.Date d = new java.util.Date();
        Sala sala=salaRepository.findById(createMensaje.getSala_id()).orElseThrow(() -> new FollowNotExistsException());
        Usuario emisor= userService.loadUser(createMensaje.getEmisor());

        Mensaje nuevoMensaje = new Mensaje();
        nuevoMensaje.setMensaje(createMensaje.getMensaje());
        nuevoMensaje.setUsuario(emisor);
        nuevoMensaje.setSala(sala);
        nuevoMensaje.setFecha(new java.sql.Date(d.getTime()));

        mensajeRepository.save(nuevoMensaje);

        messagingTemplate.convertAndSendToUser(String.valueOf(createMensaje.getSala_id()),"/queue/messages", new ChatNotification(
                nuevoMensaje.getId(),
                nuevoMensaje.getUsuario().getUsername(),
                nuevoMensaje.getMensaje()));
        //return new ResponseEntity<>("Mensaje Creaated successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/salas/{salaId}/mensajes", method = RequestMethod.GET)
    public List<Mensaje> obtenerMensajes(@PathVariable("salaId") long id) {

        Sala sala=salaRepository.findById(id).orElseThrow(() -> new FollowNotExistsException());
        List<Mensaje> mensajes=mensajeRepository.findBySalaOrderByFechaDesc(sala).orElseThrow(() -> new FollowNotExistsException());

        return mensajes;
    }

    @RequestMapping(value="/{username}/salas", method = RequestMethod.GET)
    public List<Sala> obtenerSalas(@PathVariable("username") String username) {

        Usuario usuario= userService.loadUser(username);
        List<Sala> salas=salaRepository.findByUsuarios(usuario).orElseThrow(() -> new FollowNotExistsException());

        return salas;
    }

}
