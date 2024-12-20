package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import entidades_beans.ComentarioBean;
import entidades_beans.ImagenBean;
import entidades_beans.PostBean;
import entidades_beans.UsuarioBean;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import objetosNegocio.IPostBO;
import objetosNegocio.PostBO;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author karim
 */
@WebServlet(name = "PublicacionServlet", urlPatterns = {"/private/Publicacion"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 10,     // 10 MB
    maxRequestSize = 1024 * 1024 * 50   // 50 MB
)
public class PublicacionServlet extends HttpServlet {
    private final int LIKE = 1;
    private final int DISLIKE = 2;
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
        
        if(action == null){
        }else switch (action) {
            case "reaccion" -> {actualizarLikes(request, response);
            }
            case "comentario" -> {actualizarComentarios(request, response);
            }
            case "publicar"->{}
            default -> {
            }
        }
        
    }

    private void actualizarLikes(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException{
        String codigoPost = request.getParameter("codigoPost");
        if (codigoPost != null) {
            IPostBO postBO = new PostBO();
            PostBean post = new PostBean();
            post.setCodigo(codigoPost);
            String likes = request.getParameter("likes");
            post.setLikes(Integer.parseInt(likes));
            postBO.actualizarReacciones(post);
        }
       
    }
    
    private void actualizarComentarios(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException{
        String codigoPost = request.getParameter("codigoPost");
        if(codigoPost != null){
            IPostBO postBO = new PostBO();
            PostBean post = new PostBean();
            post.setCodigo(codigoPost);
            
            String texto = request.getParameter("texto");
            HttpSession session = request.getSession(false);
            UsuarioBean autor = (UsuarioBean)session.getAttribute("usuario");
            ComentarioBean comentario = new ComentarioBean();
            comentario.setContenido(texto);
            comentario.setAutor(autor.getUsername());
            
            if(postBO.actualizarComentarios(post, comentario, PostBO.AGREGAR_COMENTARIO)){
                request.setAttribute("fechaPublicacion", dateToString(new Date()));
                request.setAttribute("comentario", comentario);
                
                request.getRequestDispatcher("/private/publicacionEspecifica.jsp").forward(request, response);
                
            }else{
                request.setAttribute("error", "Hubo un error al subir el comentario");
                request.getRequestDispatcher("/private/publicacionEspecifica.jsp").forward(request, response);
            }
        }
        
    }
    
    private void buscarPublicaciones(HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException{
        IPostBO postBO = new PostBO();
        boolean flag;
        List<PostBean> posts = postBO.buscarPublicaciones();
        HttpSession session = request.getSession(false);
        if (posts != null) {
            if (session != null && session.getAttribute("usuario") != null) {
                try {
                    List<Map<String, Object>> mapeo = mapearPublicaciones(posts);
                    session.setAttribute("listaPublicaciones", mapeo);
                    //request.setAttribute("listaPublicaciones", mapeo);
                    response.sendRedirect("/private/publicaciones.jsp");
//                    request.getRequestDispatcher("/private/publicaciones.jsp").forward(request, response);
                    flag = false;
                } catch (Exception e) {
                    Logger.getLogger(PublicacionServlet.class.getName()).log(Level.SEVERE, null, e);
                    flag = true;
                }
            } else {
                flag = true;
            }

        } else {
            flag = true;
        }
        if (flag) {
            request.setAttribute("error", "La pagina no se pudo cargar correctamente");
            request.getRequestDispatcher("/errorConsulta.jsp").forward(request, response);
        }
    }
    
    private void buscarPublicacion(HttpServletRequest request, HttpServletResponse response, String postId )throws ServletException, IOException{
        IPostBO postBO = new PostBO();
        PostBean bean = new PostBean();
        bean.setCodigo(postId);
        PostBean publicacion = postBO.buscarPublicacion(bean);
        
        if(publicacion != null){
            String date = dateToString(publicacion.getFechaCreacion());
            
            ImagenBean imagen = publicacion.getImagen();
            ImagenBean iconoPublicador = publicacion.getAutor().getImagen();
            request.setAttribute("iconoAutor", iconoPublicador);
            request.setAttribute("imagenPost", imagen);
            request.setAttribute("fechaPost", date);
            request.setAttribute("cantComentarios", publicacion.getComentarios().size());
            if(publicacion.getTexto()!= null){
                String txt[] = publicacion.getTexto().split("\n");
                List parrafos = Arrays.asList(txt);
                request.setAttribute("parrafos", parrafos);
            }
            
            request.setAttribute("post", publicacion);

            request.getRequestDispatcher("/private/publicacionEspecifica.jsp").forward(request, response);
        }else{
            request.setAttribute("error", "La pagina no se pudo cargar correctamente");
            request.getRequestDispatcher("/errorConsulta.jsp").forward(request, response);
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
        String action = request.getParameter("post");
        
        if(action == null){
            buscarPublicaciones(request, response);
        }else{
            buscarPublicacion(request, response, action);
        }
    }

    private List<Map<String,Object>> mapearPublicaciones(List<PostBean> posts){
        List<Map<String, Object>> publicaciones = new ArrayList<>();

        Map<String, Object> mapeoPublicacion;
        for (PostBean post : posts) {
            mapeoPublicacion = new HashMap<>();
            mapeoPublicacion.put("post", post);
            String date = dateToString(post.getFechaCreacion());
            mapeoPublicacion.put("fechaCreacion", date);
            ImagenBean imagen = post.getImagen();
            ImagenBean iconoPublicador = post.getAutor().getImagen();
            
            if (imagen != null) {
                String base64Image = Base64.getEncoder().encodeToString(imagen.getImageBytes());
                mapeoPublicacion.put("imagenPost", base64Image);
                mapeoPublicacion.put("tipoArchivoPost", imagen.getTipoImagen());
                mapeoPublicacion.put("nombreArchivoPost", imagen.getNombreArchivo());
            }
            
            if (iconoPublicador != null) {
                String base64Image = Base64.getEncoder().encodeToString(iconoPublicador.getImageBytes());
                mapeoPublicacion.put("iconoPublicador", base64Image);
                mapeoPublicacion.put("tipoArchivoIcon", iconoPublicador.getTipoImagen());
                mapeoPublicacion.put("nombreArchivoIcon", iconoPublicador.getNombreArchivo());
            }
            
            publicaciones.add(mapeoPublicacion);
        }
        return publicaciones;
        
    }
    
    private String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
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
        String action = request.getParameter("action");
        
        if(action != null){
            switch(action){
                case "comentario"-> actualizarComentarios(request, response);
                case "publicar"-> nuevoPost(request, response);
                case "reaccion"-> actualizarLikes(request, response);
                case "eliminarPost"-> borrarPost(request, response);
                case "editarPost"-> editarPost(request, response);
            }
        }
    }
    
    private void editarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        IPostBO postBO = new PostBO();
        String codigo = request.getParameter("codigoPost");
        String contenido = request.getParameter("contenido");
        
        System.out.println("prueba de editar");
        System.out.println("codigo: "+codigo);
        System.out.println("contenido: "+contenido);
        PostBean post = new PostBean();
        post.setCodigo(codigo);
        post.setTexto(contenido);
        
        if(!postBO.actualizarPublicacion(post, PostBO.ACTUALIZAR_CONTENIDO)){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"No se pudo editar el post\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"mensaje\": \"Post editado con éxito\"}");
        }
    }
    
    private void borrarPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        IPostBO postBO = new PostBO();
        String codigo = request.getParameter("codigoPost");
        
        PostBean post = new PostBean();
        post.setCodigo(codigo);
        
        if(!postBO.eliminarPublicacion(post)){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"No se pudo eliminar el post\"}");
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"mensaje\": \"Post eliminado con éxito\"}");
        }
    }
    
    private void nuevoPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        PostBean post = new PostBean();
        HttpSession session = request.getSession(false);
        UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");

        post.setAutor(user);
        String cat = request.getParameter("category");
        post.setCategoria(cat);
        String descripcion = request.getParameter("description");

        if (descripcion != null) {
            post.setTexto(descripcion);
        }
        post.setImagen(leerImagen(request));

        Date fechaCreacion = Calendar.getInstance().getTime();
        post.setFechaCreacion(fechaCreacion);

        IPostBO postBO = new PostBO();
        PostBean postNuevo = postBO.subirPublicacion(post);
        
        if (postNuevo != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            //session.setAttribute("resultado", "Publicacion realizada con exito");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"codigo\":\"" + postNuevo.getCodigo() + "\", \"mensaje\":\"Post creado exitosamente\"}");
            //response.sendRedirect("/private/publicaciones.jsp");
        }else{
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"No se pudo crear el post\"}");
        }
    }
    
    private void guardarImg(Part filePart){
        try {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String dir = "D:/uploads";
            File uploadDir = new File(dir);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            String filePath = dir + File.separator + fileName;
            filePart.write(filePath);
            System.out.println("se guardo imagen en carpeta");
        } catch (IOException ex) {
            Logger.getLogger(PublicacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ImagenBean leerImagen(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("image-post");

        if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
            return null;
        }

        String contentType = filePart.getContentType();
        if (!contentType.startsWith("image/")) {
            return null;
        }
        guardarImg(filePart);
        
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

    // Método para extraer el nombre del archivo desde el encabezado Content-Disposition
    private String extractFileName2(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
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
