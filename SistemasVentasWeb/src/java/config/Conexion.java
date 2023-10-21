/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asancA
 */
public class Conexion {
    Connection con=null;
    String url = "jdbc:mysql://localhost:3306/bd_ventas";
    String user = "admin";
    String password = "";
    public Connection ConexionMethod(){        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Se conecto la base de datos");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error conectandose");
        }
        return con;
    }
}
