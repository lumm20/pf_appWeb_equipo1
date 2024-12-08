package servlets;

import entidades_beans.ImagenBean;
import entidades_beans.UsuarioBean;
import entidades_beans.UsuarioRegistroBean;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.Date;
import objetosNegocio.IUsuarioBO;
import objetosNegocio.UsuarioBO;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author karim
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/Usuario"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
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

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.invalidate();
        response.sendRedirect(request.getContextPath() + "login.jsp");
        //response.sendRedirect(request.getContextPath() + "/login.jsp");

    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsuarioBean aux = new UsuarioBean();
        aux.setUsername(request.getParameter("username"));
        aux.setPassword(request.getParameter("password"));

        IUsuarioBO usuarioBO = new UsuarioBO();
        UsuarioBean usuario = usuarioBO.iniciarSesion(aux);

        if (usuario != null) {
            // Crear sesión
            HttpSession sesion = request.getSession();
            boolean esAdmin = usuario.getRol().equals("Admin");
//            System.out.println(esAdmin);
            sesion.setAttribute("usuario", usuario);
            sesion.setAttribute("rol", usuario.getRol());
            sesion.setAttribute("urlPerfil", usuario.getImagen().getUrl());
            sesion.setAttribute("tipoArchivo", usuario.getImagen().getTipoImagen());
            sesion.setAttribute("nombreArchivo", usuario.getImagen().getNombreArchivo());
            
            sesion.setMaxInactiveInterval(30 * 60); // 30 minutos
            
            String urlOriginal = (String)sesion.getAttribute("urlOriginal");
            if(urlOriginal != null){
                sesion.removeAttribute("urlOriginal");
                response.sendRedirect(urlOriginal);
            }else{
                response.sendRedirect(request.getContextPath()+"inicio");
            }

            // Redirigir a página de inicio
            response.sendRedirect(request.getContextPath() + "Noticia?action=cargarInicio");
        } else {
            request.setAttribute("error", "Datos incorrector intentelo de nuevo");
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsuarioRegistroBean aux = new UsuarioRegistroBean();
        aux.setUsername(request.getParameter("username"));
        aux.setEmail(request.getParameter("email"));

        IUsuarioBO usuarioBO = new UsuarioBO();

        String msjError = "";
        String errorUsername = "";
        String errorEmail = "";
        UsuarioRegistroBean usuarioExistente = usuarioBO.existeUsuario(aux);
        if (usuarioExistente == null) {
            aux.setNombre(request.getParameter("nombre"));
            aux.setGenero(request.getParameter("genero"));
            aux.setApellidoMaterno(request.getParameter("apellido-materno"));
            aux.setApellidoPaterno(request.getParameter("apellido-paterno"));
            aux.setPassword(request.getParameter("password"));
            aux.setImagen(leerImagen(request));


            if (!usuarioBO.registrarUsuario(aux)) {
                msjError = "Hubo un error al registrar el usuario. Intentelo de nuevo";
                request.setAttribute("error", msjError);
            }

            request.setAttribute("success", "Se registro correctamente, inicie sesión");

        } else {
            if (usuarioExistente.getUsername().equals(aux.getUsername())) {
                errorUsername = "Ya existe un usuario con ese username. Ingrese un username diferente";
                request.setAttribute("usernameExistente", errorUsername);
            }
            if (usuarioExistente.getEmail().equals(aux.getEmail())) {
                errorEmail = "Ya existe un usuario con ese email. Ingrese un email diferente";
                request.setAttribute("emailExistente", errorEmail);
            }

        }
        //si hubo algun error, se mantiene en la pagina de registro y se envia el mensaje de error capturado
        request.getRequestDispatcher("/register.jsp").forward(request, response);

    }

    private ImagenBean leerImagen(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("img-perfil");

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
