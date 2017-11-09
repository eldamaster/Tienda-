package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alumno
 */
public class Conexion {
    Connection conn;   // su importacion es:   import java.sql.Connection;:
    String resp;
    
    
    
    public String Conectar()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");  // el nombre del conector
           // su importacion es:   import java.sql.Connection;:
           
           //CONECTARSE A LA BASE DE DATOS Y SI LO LOGRA LA CONEXION SE GUARDA EN conn
           conn = DriverManager.getConnection("jdbc:mysql://localhost/tienda","root","");  //locasion de labase de datos, usuario y contraseña
            return "Se conectó a la base de Tienda";
        }
        catch(Exception e)
        {
            return "Ocurrio un erro al momento de conectarse: "+e;
        }
    }
    
    public Connection getConexion()
    {
        return conn;
    }
    
}
