/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Producto buscar(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where idproducto=" + id;
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPre(rs.getString(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto:"+e.getMessage());
        }
        return p;
    }

    public int actualizarstock(int id, int stock) {

        String sql = "update producto set stock=? where idproducto=?";
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del producto:" + e.getMessage());

        }
        return r;
    }

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
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
                lista.add(pro);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar producto:"+e.getMessage());

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
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar producto:"+e.getMessage());

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
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));

            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos por id:"+e.getMessage());

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
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setInt(5, pro.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto:" + e.getMessage());
        }
        return r;
    }

    public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto:" + e.getMessage());
        }
    }
}
