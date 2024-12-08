package servlets;

import com.google.gson.Gson;
import entidades_beans.ComentarioBean;
import entidades_beans.NoticiaBean;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import objetosNegocio.ComentarioBO;
import objetosNegocio.IComentarioBO;

/**
 *
 * @author karim
 */
@WebServlet(name = "ComentarioServlet", urlPatterns = {"/Comentario"})
public class ComentarioServlet extends HttpServlet {

    private IComentarioBO comentarioBO = new ComentarioBO();
    private Gson gson = new Gson();

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
            out.println("<title>Servlet ComentarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComentarioServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("error404.jsp");
            return;
        }

        switch (action) {
            case "destacados" ->
                cargarComentariosDestacados(request, response);
            case "normales" ->
                cargarComentariosNormales(request, response);

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
        // Configurar respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Convertir JSON a objeto Java usando Gson
        ComentarioBean datosRecibidos = gson.fromJson(reader, ComentarioBean.class);
        ComentarioBean comentarioCreado = comentarioBO.registrarComentario(datosRecibidos);

        if (comentarioCreado != null) {
            response.setStatus(HttpServletResponse.SC_OK);

            String json = gson.toJson(comentarioCreado);

            response.getWriter().write(json);

        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            String jsonRespuesta = gson.toJson(datosRecibidos);
            response.getWriter().write("{}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("error404.jsp");
            return;
        }

        switch (action) {
            case "desanclar" ->
                desanclarComentario(request, response);
            case "destacar" ->
                destacarComentario(request, response);

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
        ComentarioBean datosRecibidos = gson.fromJson(reader, ComentarioBean.class);
        boolean comentarioBorrado = comentarioBO.borrarComentario(datosRecibidos);
        if (comentarioBorrado) {
            System.out.println("Comentario borrado " + datosRecibidos.getIdComentario());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            String jsonRespuesta = gson.toJson(datosRecibidos);
            response.getWriter().write("{}");
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

    private void cargarComentariosNormales(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idNoticia = request.getParameter("codigo");

        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo(idNoticia);

        List<ComentarioBean> comentariosEncontrados = comentarioBO.obtenerComentariosPorNoticia(bean);

        if (comentariosEncontrados != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            Gson gson = new Gson();
            String json = gson.toJson(comentariosEncontrados);

            response.getWriter().write(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{}");
        }
    }

    private void cargarComentariosDestacados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idNoticia = request.getParameter("codigo");
        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo(idNoticia);

        List<ComentarioBean> comentariosEncontrados = comentarioBO.obtenerComentariosDestacadosPorNoticia(bean);

        if (comentariosEncontrados != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            Gson gson = new Gson();
   
            String json = gson.toJson(comentariosEncontrados);

            response.getWriter().write(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{}");
        }
    }

    private void desanclarComentario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Configurar respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Convertir JSON a objeto Java usando Gson
        ComentarioBean datosRecibidos = gson.fromJson(reader, ComentarioBean.class);
        boolean comentarioModificado = comentarioBO.desanclarComentario(datosRecibidos);
        if (comentarioModificado) {
            System.out.println("Desanclado " + datosRecibidos.getIdComentario());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }

    }

    private void destacarComentario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Configurar respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Convertir JSON a objeto Java usando Gson
        ComentarioBean datosRecibidos = gson.fromJson(reader, ComentarioBean.class);
        boolean comentarioModificado = comentarioBO.anclarComentario(datosRecibidos);

        if (comentarioModificado) {
            System.out.println("Anclado " + datosRecibidos.getIdComentario());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Convertir respuesta a JSON y enviar
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }
    }

}
