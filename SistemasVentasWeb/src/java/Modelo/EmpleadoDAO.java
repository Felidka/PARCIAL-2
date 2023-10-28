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
public class EmpleadoDAO {

    Conexion cn = new Conexion();
    Connection cone;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Empleado validar(Empleado item) {
        System.out.println("entro en validar");
        Empleado pem = new Empleado();
        String sql = "select * from empleado where User=? and contrasena=? ";
        try {
            System.out.println("entro en validar try");
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.setString(1, item.getUser());
            ps.setString(2, item.getContrasena());
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("entro en validar while");
                pem.setId(rs.getInt("IdEmpleado"));
                pem.setUser(rs.getString("User"));
                pem.setDni(rs.getString("Dni"));
                pem.setNom(rs.getString("Nombres"));
                //pem.setContrasena(rs.getString("Contrasena"));
                System.out.println(pem.getDni());
            }
            cone.close();

        } catch (SQLException e) {
            System.out.println("Usuario no encontrado" + e.getMessage());
        }
        System.out.println("atras "+pem);

        return pem;
    }

    public List Listar() {
        String sql = "select * from empleado";
        List<Empleado> lista = new ArrayList<>();
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                em.setContrasena(rs.getString(7));
                lista.add(em);
            }
        } catch (Exception e) {
            System.out.println("No se puede listar a los empleados");
        }
        return lista;
    }

    public int agregar(Empleado em) {
        String sql = "insert into empleado(Dni,Nombres,Telefono,Estado,User,Contrasena)values(?,?,?,?,?,?)";
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setString(6, em.getContrasena());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se puede agregar a los empleados:"+e.getMessage());
        }
        return r;
    }

    public Empleado listarId(int id) {
        Empleado emp = new Empleado();
        String sql = "select * from empleado where IdEmpleado=" + id;
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));
                emp.setContrasena(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println("No se puede listar a los ids");

        }
        return emp;
    }

    public int actualizar(Empleado em) {
        String sql = "update empleado set Dni=?,Nombres=?,Telefono=?,Estado=?,User=?,Contrasena=? where IdEmpleado=?";
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setString(6, em.getContrasena());
            ps.setInt(7, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se puede actualizar a los empleados");
        }
        return r;
    }

    public void delete(int id) {
        String sql = "delete from empleado where IdEmpleado=" + id;
        try {
            cone = cn.ConexionMethod();
            ps = cone.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se puede eliminar a los empleados");

        }
    }
}
