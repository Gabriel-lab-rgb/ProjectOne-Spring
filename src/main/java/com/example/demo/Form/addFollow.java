package com.example.demo.Form;

import com.example.demo.Entity.Usuario;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class addFollow {

    private long id;
    private String follower;
    private long followed;


}
