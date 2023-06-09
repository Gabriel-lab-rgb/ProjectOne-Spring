package com.example.demo.module;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.demo.Entity.Mensaje;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SocketModule {

  /*  private final SocketService socketService;*/
/*
    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Mensaje.class, onChatReceived());

    }*/
/*
    private DataListener<Mensaje> onChatReceived() {
        return (client, data, ackSender) -> {
            log.debug(data.toString());
            socketService.sendMessage(data.getRoom(),"get_message", client, data.getMessage());
        };
    }*/

    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            log.debug("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> log.debug("Client[{}] - Disconnected from socket", client.getSessionId().toString());
    }

}