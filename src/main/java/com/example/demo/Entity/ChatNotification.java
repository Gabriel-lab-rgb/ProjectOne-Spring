package com.example.demo.Entity;

public class ChatNotification {
    private long id;
    private String usuario;
    private String contenido;

    public ChatNotification(long id, String usuario, String contenido) {
        this.id = id;
        this.usuario = usuario;
        this.contenido = contenido;
    }
}