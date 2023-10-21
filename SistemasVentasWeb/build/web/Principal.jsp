<%-- 
    Document   : Principal
    Created on : 5 Oct 2023, 19:41:28
    Author     : asanc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./Bootstrap/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <a class="navbar-brand" href="#">Navbar</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home </a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="Controlador?menu=Producto" target="myFrame">Producto</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="Controlador?menu=Empleado&accion=Listar" target="myFrame" >Empleado</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="Controlador?menu=Cliente" target="myFrame" >Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="Controlador?menu=NuevaVenta&accion=default" target="myFrame" >Nueva Venta</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown">
                <button class="btn btn-outline-light dropdown-toggle" type="button" id="drodownMenu" data-toggle="dropdown" aria-expanded="false">
                    ${usuario.getNom()}
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">
                    ${usuario.getUser()}                    
                    </a>
                    <a class="dropdown-item" href="#">User@gmai.com</a>
                    <div class="dropdown-divider"></div>
                    <form action="Validar" method="POST"> 
                        <button name="accion" value="Salir" class="dropdown-item" href="#">Salir</button>
                    </from>
                </div>
            </div> 
        </nav>
                    <div class="m-4" style="height: 550px;">
                        <iframe name="myFrame" style="height: 100%; width: 100%; border:none"></iframe>
                        
                    </div>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    </body>
</html>
