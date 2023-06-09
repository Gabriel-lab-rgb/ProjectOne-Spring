package com.example.demo.module;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.demo.Entity.Mensaje;
import com.example.demo.Entity.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocketService {

    /*
    public void sendMessage(String room, String eventName, SocketIOClient senderClient, String message) {
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName,
                        new Mensaje(MessageType.SERVER, message));
            }
        }
    }*/

}