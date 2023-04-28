package com.example.demo.dao;

import com.example.demo.utils.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class daoImpl implements dao{


    Connection c;

    @Override
    public boolean verificarUsuario(String usuario,String password) {
        boolean encontrado=false;
        String sql ="SELECT * FROM usuario WHERE username='prueba' AND password='prueba'";
try{
    c= Conexion.getConnection();

    Statement sqlStatement = c.createStatement();
    ResultSet set = sqlStatement.executeQuery(sql);

    if(set.next()){
        encontrado=true;
    }

} catch (SQLException e) {
    throw new RuntimeException(e);
} catch (IOException e) {
    throw new RuntimeException(e);
}

        return encontrado;
    }
}
