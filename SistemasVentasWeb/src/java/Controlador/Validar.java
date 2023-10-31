/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author asanc
 */
public class Validar extends HttpServlet {

    EmpleadoDAO edao = new EmpleadoDAO();
    Empleado em = new Empleado();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String user = request.getParameter("txtuser");
            String pass = asegurarClave(request.getParameter("txtpass"));
            Empleado item = new Empleado();
            item.setUser(user);
            item.setContrasena(pass);
            em = edao.validar(item);
            if (em.getUser() != null) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

                //response.setHeader("Pragma", "no-cache");
                HttpSession sesion = request.getSession();
                System.out.println("Numero de session" + sesion.getId());

                sesion.setAttribute("usuario", em);
                System.out.println("entro en if vlidar");
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                request.setAttribute("usuario", em);

                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                //request.setAttribute("usuario", em);
            } else {

                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        if (accion.equalsIgnoreCase("Salir")) {
            HttpSession sesion = request.getSession();
            sesion.removeAttribute("usuario");
            sesion.invalidate();
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.resetBuffer();
            response.reset();
            request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
        //processRequest(request, response);
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
