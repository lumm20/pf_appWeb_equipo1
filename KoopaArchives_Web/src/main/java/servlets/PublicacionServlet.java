package servlets;

import entidades_beans.ContenidoBean;
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
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import objetosNegocio.IPostBO;
import objetosNegocio.PostBO;

/**
 *
 * @author karim
 */
@WebServlet(name = "PublicacionServlet", urlPatterns = {"/Publicacion"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 10,     // 10 MB
    maxRequestSize = 1024 * 1024 * 50   // 50 MB
)
public class PublicacionServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        
        if(username == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else{
            PostBean post = new PostBean();
            
            UsuarioBean usuario = new UsuarioBean();
            usuario.setUsername(username);
            post.setPublicador(usuario);
            System.out.println("usuario bean : "+usuario);
            String cat = request.getParameter("category");
            post.setCategoria(cat);
            ContenidoBean contenido = new ContenidoBean();
            String descripcion = request.getParameter("description");
            
            System.out.println("categoria y descripcion: "+cat +", "+ descripcion);
            if (descripcion != null) {
                contenido.setDescripcion(descripcion);
            }
            
//            Part imgPost = request.getPart("image");
//            String fileName = Paths.get(imgPost.getSubmittedFileName()).getFileName().toString();
//            String dir = "C:/Users/luiis/Desktop/uploads";
//            File uploadDir = new File(dir);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//            
//            String filePath = dir+File.separator +fileName;
//            imgPost.write(filePath);
//            
//            contenido.setUrlImg(fileName);
//           
//            post.setContenido(contenido);

            Date fechaCreacion = Calendar.getInstance().getTime();
            post.setFechaCreacion(fechaCreacion);
            
            IPostBO postBO = new PostBO();
            if (postBO.subirPublicacion(post)) {
                //session.setAttribute("newPost", post);
                
                response.sendRedirect(request.getContextPath() + "/inicio.jsp");
            }
            
//            // Procesa el archivo cargado
//            for (Part part : request.getParts()) {
//                // Obtén el nombre del archivo
//                String fileName = extractFileName(part);
//                if (fileName != null && !fileName.isEmpty()) {
//                    // Guarda el archivo en la carpeta destino
//                    String filePath = dir + File.separator + fileName;
//                    part.write(filePath);
//                    System.out.println("Archivo subido exitosamente: " + fileName);
//                }
//            }
            
        }
    }
    

    // Método para extraer el nombre del archivo desde el encabezado Content-Disposition
    private String extractFileName(Part part) {
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
