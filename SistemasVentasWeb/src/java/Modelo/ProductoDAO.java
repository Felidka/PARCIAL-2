/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asanc
 */
public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection cone;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public List Listar() {
        String sql = "select * from producto";
        List<Producto> lista = new ArrayList<>();
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt(1));
                pro.setNom(rs.getString(2));
                pro.setPre(rs.getString(3));
                pro.setStock(rs.getString(4));
                pro.setEstado(rs.getString(5));
                lista.add(pro);
            }
        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(Producto pro) {
        String sql = "insert into producto(Nombres,Precio,Stock,Estado)values(?,?,?,?)";
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setString(2, pro.getPre());
            ps.setString(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;
    }

    public Producto listarId(int id) {
        Producto pro = new Producto();
        String sql = "select * from producto where IdProducto=" + id;
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setNom(rs.getString(2));
                pro.setPre(rs.getString(3));
                pro.setStock(rs.getString(4));
                pro.setEstado(rs.getString(5));                

            }
        } catch (Exception e) {

        }
        return pro;
    }
    
    public int actualizar(Producto pro) {
        String sql = "update producto set Nombres=?,Precio=?,Stock=?,Estado=? where IdProducto=?";
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setString(2, pro.getPre());
            ps.setString(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setInt(5, pro.getId());
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;
    }
    
     public void delete(int id) {
        String sql = "delete from producto where IdCliente=" + id;
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }
}
