/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import entidades_beans.ImagenBean;
import entidades_beans.UsuarioBean;
import entidades_beans.UsuarioRegistroBean;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import objetosNegocio.UsuarioBO;

/**
 *
 * @author karim
 */
@WebServlet(name = "HarcodearAdminServlet", urlPatterns = {"/Harcodear"})
public class HarcodearAdminServlet extends HttpServlet {

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
        harcodear(response);
        response.sendRedirect("/inicio.jsp");
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

    private void harcodear(HttpServletResponse response) throws IOException {
         try {
            // Obtener la ruta real del archivo en el proyecto
            ServletContext context = getServletContext();
            String rutaArchivo = context.getRealPath("/img/luffy_admin.jpg");
            
            // Alternativa si está en una subcarpeta específica
            // String rutaArchivo = context.getRealPath("/WEB-INF/archivos/nombreArchivo.jpg");
            
            // Verificar que el archivo exista
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Archivo no encontrado");
                return;
            }
            
            // Leer los bytes del archivo
            byte[] archivoBytes = Files.readAllBytes(archivo.toPath());
            
            // Obtener el tipo de contenido
            String contentType = "image/jpeg";
            
            ImagenBean imagenBean = new ImagenBean();
            imagenBean.setImageBytes(archivoBytes);
            imagenBean.setNombreArchivo("luffy_admin.jpg");
            imagenBean.setTipoImagen(contentType);
            imagenBean.setFechaSubida(new Date());

            UsuarioRegistroBean usuario = new UsuarioRegistroBean();
            usuario.setUsername("luffy27");
            usuario.setApellidoMaterno("Monkey");
            usuario.setApellidoPaterno("D.");
            usuario.setEmail("luffy@gmail.com");
            usuario.setPassword("luffy");
            usuario.setGenero("Hombre");
            usuario.setImagen(imagenBean);
            
            UsuarioBO usuarioBO = new UsuarioBO();
            usuarioBO.hardcodearAdmin(usuario);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Error al guardar el archivo: " + e.getMessage());
        }
    }
   
}
