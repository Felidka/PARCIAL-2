/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asanc
 */
public class Controlador extends HttpServlet {

    Empleado emp = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();

    Cliente cli = new Cliente();
    ClienteDAO cdao = new ClienteDAO();

    Producto pro = new Producto();
    ProductoDAO prodao = new ProductoDAO();

    int ide;
    int idc;
    int idp;

    List<Venta> lista = new ArrayList<>();

    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;

    String numeroserie;
    VentaDAO vdao = new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Empleado us = (Empleado) sesion.getAttribute("usuario");
        if (us != null) {

            if (menu.equals("Principal")) {
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if (menu.equals("Empleado")) {
                switch (accion) {
                    case "Listar":
                        List lista = edao.Listar();
                        request.setAttribute("empleados", lista);
                        break;
                    case "Agregar":
                        String dni = request.getParameter("txtDni");
                        String nom = request.getParameter("txtNombres");
                        String tel = request.getParameter("txtTel");
                        String user = request.getParameter("txtUser");
                        String contrasena = asegurarClave(request.getParameter("txtContrasena"));
                        String estado;
                        if(request.getParameter("txtEstado").equals("Activo")){
                            estado = "1";
                        }else{
                            estado = "2";
                        }
                        emp.setDni(dni);
                        emp.setNom(nom);
                        emp.setTel(tel);
                        emp.setEstado(estado);
                        emp.setUser(user);
                        emp.setContrasena(contrasena);
                        System.out.println(contrasena);
                        edao.agregar(emp);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        ide = Integer.parseInt(request.getParameter("id"));
                        Empleado e = edao.listarId(ide);
                        request.setAttribute("empleado", e);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1 = request.getParameter("txtDni");
                        String nom1 = request.getParameter("txtNombres");
                        String tel1 = request.getParameter("txtTel");
                        String estado1;
                        String user1 = request.getParameter("txtUser");
                        String contrasena1 = asegurarClave(request.getParameter("txtContrasena"));
                        if(request.getParameter("txtEstado").equals("Activo")){
                            estado1 = "1";
                        }else{
                            estado1 = "2";
                        }
                        emp.setDni(dni1);
                        emp.setNom(nom1);
                        emp.setTel(tel1);
                        emp.setEstado(estado1);
                        emp.setUser(user1);
                        emp.setContrasena(contrasena1);
                        emp.setId(ide);
                        edao.actualizar(emp);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        ide = Integer.parseInt(request.getParameter("id"));
                        edao.delete(ide);
                        request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Empleado.jsp").forward(request,
                        response);
            }
            if (menu.equals("Cliente")) {
                switch (accion) {
                    case "Listar":
                        List lista = cdao.Listar();
                        request.setAttribute("clientes", lista);
                        break;
                    case "Agregar":
                        String dni = request.getParameter("txtDni");
                        String nom = request.getParameter("txtNombres");
                        String direc = request.getParameter("txtDir");
                        String estado;
                        if(request.getParameter("txtEstado").equals("Activo")){
                            estado = "1";
                        }else{
                            estado = "2";
                        }
                        cli.setDni(dni);
                        cli.setNom(nom);
                        cli.setDirec(direc);
                        cli.setEstado(estado);
                        cdao.agregar(cli);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idc = Integer.parseInt(request.getParameter("id"));
                        Cliente cl = cdao.listarId(idc);
                        request.setAttribute("cliente", cl);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String dni1 = request.getParameter("txtDni");
                        String nom1 = request.getParameter("txtNombres");
                        String direc1 = request.getParameter("txtDir");
                        String estado1;
                        if(request.getParameter("txtEstado").equals("Activo")){
                            estado1 = "1";
                        }else{
                            estado1 = "2";
                        }
                        cli.setDni(dni1);
                        cli.setNom(nom1);
                        cli.setDirec(direc1);
                        cli.setEstado(estado1);
                        cli.setId(idc);
                        cdao.actualizar(cli);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        idc = Integer.parseInt(request.getParameter("id"));
                        cdao.delete(idc);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Clientes.jsp").forward(request,
                        response);
            }
            if (menu.equals("Producto")) {
                switch (accion) {
                    case "Listar":
                        List lista = prodao.Listar();
                        request.setAttribute("productos", lista);
                        break;
                    case "Agregar":
                        String nom = request.getParameter("txtNombres");
                        String pre = request.getParameter("txtPre");
                        int stock = Integer.parseInt(request.getParameter("txtStock"));
                        String estado;
                        if(request.getParameter("txtEstado").equals("Disponible")){
                            estado = "1";
                        }else{
                            estado = "2";
                        }
                        pro.setNom(nom);
                        pro.setPre(pre);
                        pro.setStock(stock);                      
                        pro.setEstado(estado);
                        prodao.agregar(pro);
                        
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idp = Integer.parseInt(request.getParameter("id"));
                        Producto pr = prodao.listarId(idp);
                        request.setAttribute("producto", pr);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nom1 = request.getParameter("txtNombres");
                        String pre1 = request.getParameter("txtPre");
                        int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                        String estado1;
                        if(request.getParameter("txtEstado").equals("Disponible")){
                            estado1 = "1";
                        }else{
                            estado1 = "2";
                        }
                        pro.setNom(nom1);
                        pro.setPre(pre1);
                        pro.setStock(stock1);
                        pro.setEstado(estado1);
                        pro.setId(idp);
                        prodao.actualizar(pro);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Delete":
                        idp = Integer.parseInt(request.getParameter("id"));
                        prodao.delete(idp);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        System.out.println(idp);
                        break;
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Producto.jsp").forward(request,
                        response);
            }
            if (menu.equals("NuevaVenta")) {
                switch (accion) {
                    case "BuscarCliente":
                        String dni = request.getParameter("codigocliente");
                        cli.setDni(dni);
                        cli = cdao.buscar(dni);
                        request.setAttribute("cli", cli);
                        break;
                    case "BuscarProducto":
                        int id = Integer.parseInt(request.getParameter("codigoproducto"));
                        pro = prodao.listarId(id);
                        request.setAttribute("cli", cli);
                        request.setAttribute("producto", pro);
                        request.setAttribute("lista", lista);
                        request.setAttribute("totalPagar", totalPagar);
                        break;
                    case "Agregar":
                        request.setAttribute("cli", cli);
                        totalPagar = 0.0;
                        item = item + 1;
                        cod = pro.getId();
                        descripcion = request.getParameter("nomproducto");
                        precio = Double.parseDouble(request.getParameter("precio"));
                        cant = Integer.parseInt(request.getParameter("cant"));
                        subtotal = precio * cant;
                        Venta v = new Venta();
                        v.setItem(item);
                        v.setIdproducto(cod);
                        v.setDescripcionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cant);
                        v.setSubtotal(subtotal);
                        lista.add(v);
                        for (int i = 0; i < lista.size(); i++) {
                            totalPagar = totalPagar + lista.get(i).getSubtotal();
                        }
                        request.setAttribute("totalPagar", totalPagar);
                        request.setAttribute("lista", lista);
                        break;
                    case "GenerarVenta":
                        for (int i = 0; i < lista.size(); i++) {
                            int cantidad = lista.get(i).getCantidad();
                            int idproducto = lista.get(i).getIdproducto();
                            ProductoDAO aO = new ProductoDAO();
                            aO.buscar(idproducto);
                            int sac = pro.getStock() - cantidad;
                            aO.actualizarstock(idproducto, sac);
                        }

                        v = new Venta();
                        v.setIdcliente(cli.getId());
                        v.setIdempleado(2);
                        v.setNumserie(numeroserie);
                        java.util.Date ahora = new java.util.Date();
                        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
                        v.setFecha(formateador.format(ahora));
                        v.setMonto(totalPagar);
                        v.setEstado("1");
                        vdao.guardarVenta(v);
                        int idv = Integer.parseInt(vdao.IdVentas());
                        for (int i = 0; i < lista.size(); i++) {
                            v = new Venta();
                            v.setId(idv);
                            v.setIdproducto(lista.get(i).getIdproducto());
                            v.setCantidad(lista.get(i).getCantidad());
                            v.setPrecio(lista.get(i).getPrecio());
                            vdao.guardarDetalleventas(v);
                        }

                        break;
                    default:
                        /*v = new Venta();
                    lista = new ArrayList<>();
                    item = 0;
                    totalPagar = 0.0;*/
                        numeroserie = vdao.GenerarSerie();
                        if (numeroserie == null) {
                            numeroserie = "00000001";
                            request.setAttribute("nserie", numeroserie);
                        } else {
                            int incrementar = Integer.parseInt(numeroserie);
                            GenerarSerie gs = new GenerarSerie();
                            numeroserie = gs.NumeroSerie(incrementar);
                            request.setAttribute("nserie", numeroserie);
                        }
                        request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                }
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private String asegurarClave(String textoClaro) {
        String claveSha = null;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(textoClaro.getBytes());
            claveSha = Base64.getEncoder().encodeToString(sha256.digest());
            System.out.println("Clave sha es: " + claveSha);
            System.out.println("Longitud:" + claveSha.length());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error en instanciar sha256 " + ex.getMessage());
        }
        return claveSha;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
