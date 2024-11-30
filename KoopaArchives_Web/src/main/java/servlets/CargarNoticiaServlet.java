/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objetosNegocio.INoticiaBO;
import objetosNegocio.NoticiaBO;

/**
 *
 * @author karim
 */
@WebServlet(name = "CargarNoticiaServlet", urlPatterns = {"/CargarNoticia"})
public class CargarNoticiaServlet extends HttpServlet {
private INoticiaBO noticiaBO = new NoticiaBO();
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CargarNoticiaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CargarNoticiaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
         String idNoticia = request.getParameter("id");
        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo(idNoticia);
        System.out.println("codigo noticia: "+bean);
        NoticiaBean noticiaEncontrada = noticiaBO.buscarNoticia(bean);
        System.out.println("noticia encontrada: "+noticiaEncontrada.getTitulo());
        if (noticiaEncontrada != null) {
            String[] parrafos = noticiaEncontrada.getTexto().split("\n");
            ImagenBean imagen = noticiaEncontrada.getImagen();

            request.setAttribute("noticia", noticiaEncontrada);
            request.setAttribute("tipoArchivo", imagen.getTipoImagen());
            request.setAttribute("nombreArchivo", imagen.getNombreArchivo());
            request.setAttribute("url", imagen.getUrl());

            request.setAttribute("parrafos", parrafos);
            request.getRequestDispatcher("/noticia.jsp").forward(request, response);
        } else {
            // Handle case where no news article is found
            response.sendRedirect("error404");
        }
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
