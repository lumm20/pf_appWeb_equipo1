
package servlets;

import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;
import entidades_beans.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import objetosNegocio.INoticiaBO;
import objetosNegocio.NoticiaBO;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author karim
 */

@WebServlet(name = "NoticiaServlet", urlPatterns = {"/Noticia"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class NoticiaServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        System.out.println("Hola desde servlet noticia");
        if (action == null) {
            response.sendRedirect("error404.jsp");
            return;
        }

        switch (action) {
            case "publicarNoticia" ->
                publicarNoticia(request, response);
            case "actualizarNoticia" ->
                actualizarNoticia(request, response);
            case "destacarNoticia" ->
                destacarNoticia(request, response);
            case "agregarComentario" ->
                agregarComentario(request, response);
            case "destacarComentario" ->
                destacarComentario(request, response);
            case "borrarComentario" ->
                borrarComentario(request, response);

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
        System.out.println(idNoticia);
        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo(idNoticia);

        NoticiaBean noticiaEncontrada = noticiaBO.buscarNoticia(bean);
        
        if(noticiaEncontrada != null){
            String[] parrafos = noticiaEncontrada.getTexto().split("\n");
            ImagenBean imagen = noticiaEncontrada.getImagen();
            System.out.println("imagen en servlet" + imagen);
            
            request.setAttribute("noticia", noticiaEncontrada);
            request.setAttribute("tipoArchivo", imagen.getTipoImagen());
            request.setAttribute("nombreArchivo", imagen.getNombreArchivo());
            request.setAttribute("url", imagen.getUrl());
            
            request.setAttribute("parrafos", parrafos);
                request.getRequestDispatcher("noticia.jsp").forward(request, response);
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

    private void publicarNoticia(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NoticiaBean aux = new NoticiaBean();
        aux.setTitulo(request.getParameter("titulo"));
        aux.setTexto(request.getParameter("contenido"));
        aux.setCategoria(request.getParameter("categoria"));
        String destacadoString = request.getParameter("destacado");
        boolean destacado = (destacadoString != null);
        aux.setFechaCreacion(new Date());
//        HttpSession sesion = request.getSession(false);
//        UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");

        UsuarioBean usuario = new UsuarioBean();
        usuario.setUsername("Monkey D. Luffy");
        aux.setAutor(usuario);
        ImagenBean imagen = leerImagen(request);
        aux.setDestacada(destacado);
        aux.setImagen(imagen);

        NoticiaBean noticiaPublicada = noticiaBO.publicarNoticia(aux);

        if (noticiaPublicada != null) {

            response.sendRedirect("inicio.jsp");
        } else {
            response.sendRedirect("error404.jsp");
        }

    }

    private void actualizarNoticia(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void destacarNoticia(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void agregarComentario(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void destacarComentario(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void borrarComentario(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private ImagenBean leerImagen(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("imagenPrincipal");

        if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
            return null;
        }

        String contentType = filePart.getContentType();
        if (!contentType.startsWith("image/")) {
            return null;
        }

        InputStream fileContent = filePart.getInputStream();
        byte[] imageBytes = IOUtils.toByteArray(fileContent);

        ImagenBean imagenPerfil = new ImagenBean();
        imagenPerfil.setImageBytes(imageBytes);
        imagenPerfil.setNombreArchivo(extractFileName(filePart));
        imagenPerfil.setTipoImagen(contentType);
        imagenPerfil.setFechaSubida(new Date());
        return imagenPerfil;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "unknown_" + System.currentTimeMillis();
    }
}
