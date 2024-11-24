/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import entidades_beans.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import objetosNegocio.IUsuarioBO;
import objetosNegocio.UsuarioBO;

/**
 *
 * @author karim
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/Usuario"})
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("error404.jsp");
            return;
        }

        switch (action) {
            case "registrar" ->
                registrarUsuario(request, response);
            case "iniciarSesion" ->
                iniciarSesion(request, response);
            case "cerrarSesion" ->
                cerrarSesion(request, response);

        }
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

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {

    }
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/inicio.jsp");
    
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsuarioBean aux = new UsuarioBean();
        aux.setUsername(request.getParameter("username"));
        aux.setPassword(request.getParameter("password"));
        
        IUsuarioBO usuarioBO = new UsuarioBO();
        UsuarioBean usuario = usuarioBO.iniciarSesion(aux);
        
        if(usuario != null){
            // Crear sesión
            HttpSession sesion = request.getSession();
            
            // Guardar datos en la sesión
            sesion.setAttribute("usuario", usuario);
            
            // Opcional: establecer tiempo máximo de sesión (en segundos)
            sesion.setMaxInactiveInterval(30*60); // 30 minutos
            
            // Redirigir a página de inicio
            response.sendRedirect(request.getContextPath() + "/inicio.jsp");
        }else{
            request.setAttribute("error", "Datos incorrector intentelo de nuevo");
            request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
        }
    }
}
