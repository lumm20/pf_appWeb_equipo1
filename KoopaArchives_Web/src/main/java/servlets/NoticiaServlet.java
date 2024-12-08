package servlets;

import com.google.gson.Gson;
import entidades_beans.FiltroBean;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private Gson gson = new Gson();
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
//        System.out.println("Hola desde servlet noticia");
        if (action == null) {
            response.sendRedirect("error404.jsp");
            return;
        }

        switch (action) {
            case "cargarInicio" ->
                cargarNoticia(request, response);
            case "publicarNoticia" ->
                publicarNoticia(request, response);
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

        cargarNoticia(request, response);
//       
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
//        System.out.println("Hola desde servlet noticia");
        if (action == null) {
            response.sendRedirect("error404.jsp");
            return;
        }

        switch (action) {
            case "desanclar" ->
                desanclar(request, response);
            case "destacar" ->
                destacar(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Convertir JSON a objeto Java usando Gson
        NoticiaBean datosRecibidos = gson.fromJson(reader, NoticiaBean.class);
        System.out.println(datosRecibidos);
        boolean comentarioCreado = noticiaBO.eliminarNoticia(datosRecibidos);
//        
        if (comentarioCreado) {
            response.setStatus(HttpServletResponse.SC_OK); // 200

        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }
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

        UsuarioBean usuario = new UsuarioBean();
        usuario.setUsername("Monkey D. Luffy");
        aux.setAutor(usuario);
        ImagenBean imagen = leerImagen(request);
        aux.setDestacada(destacado);
        aux.setImagen(imagen);

        NoticiaBean noticiaPublicada = noticiaBO.publicarNoticia(aux);

        if (noticiaPublicada != null) {

            cargarNoticia(request, response);
        } else {
            response.sendRedirect("error404.jsp");
        }

    }

    private void destacar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Configurar respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Convertir JSON a objeto Java usando Gson
        NoticiaBean datosRecibidos = gson.fromJson(reader, NoticiaBean.class);
        boolean noticiaAnclada = noticiaBO.anclar(datosRecibidos);

        if (noticiaAnclada) {
            System.out.println("Noticia anclada con id: " + datosRecibidos.getCodigo());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }
    }

    private void desanclar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Configurar respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Convertir JSON a objeto Java usando Gson
        NoticiaBean datosRecibidos = gson.fromJson(reader, NoticiaBean.class);
        boolean noticiaDesanclada = noticiaBO.desanclar(datosRecibidos);

        if (noticiaDesanclada) {
            System.out.println("Noticia desanclada con id: " + datosRecibidos.getCodigo());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }
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

    private void cargarNoticia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FiltroBean filtroNormal = new FiltroBean();
        filtroNormal.setInicio(true);
        filtroNormal.setDestacada(false);

        FiltroBean filtroDestacada = new FiltroBean();
        filtroDestacada.setInicio(true);
        filtroDestacada.setDestacada(true);

        List<NoticiaBean> noticiasNormales = noticiaBO.buscarNoticias(filtroNormal);
        List<NoticiaBean> noticiasDestacadas = noticiaBO.buscarNoticias(filtroDestacada);

//            for (NoticiaBean noticia : noticiasDestacadas) {
//        if (noticia.getImagen() != null) {
//            System.out.println("Imagen: " + noticia.getImagen());
//            System.out.println("Tipo de Archivo: " + noticia.getImagen().getTipoImagen());
//            System.out.println("URL: " + noticia.getImagen().getUrl());
//        }
//    }
        request.setAttribute("destacadas", noticiasDestacadas);
        request.setAttribute("normales", noticiasNormales);
        request.getRequestDispatcher("inicio.jsp")
                .forward(request, response);
    }

}
