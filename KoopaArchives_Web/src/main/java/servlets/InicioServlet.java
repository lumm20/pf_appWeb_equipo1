/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import entidades_beans.FiltroBean;
import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;
import entidades_beans.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.INoticiaBO;
import objetosNegocio.NoticiaBO;

/**
 *
 * @author luisa M
 */
@WebServlet(name = "InicioServlet", urlPatterns = {"/inicio"})
public class InicioServlet extends HttpServlet {

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
        INoticiaBO noticiaBO = new NoticiaBO();
        FiltroBean filtro = new FiltroBean();
        filtro.setDestacada(true);
        filtro.setInicio(true);
        List<NoticiaBean> noticias = noticiaBO.buscarNoticias(filtro);
        
        boolean flag = false;
        
        if (noticias != null && !noticias.isEmpty()) {
            try {
                List<Map<String, Object>> noticiasDestacadas = mapearNoticiasDestacadas(noticias);
                if(!noticiasDestacadas.isEmpty())
                    request.setAttribute("destacadas", noticiasDestacadas);
                List<Map<String, Object>> noticiasNormales = mapearNoticiasNormales(noticias);
                if(!noticiasNormales.isEmpty())
                    request.setAttribute("normales", noticiasNormales);
                System.out.println("se mandaron las noticias");
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } catch (Exception e) {
                Logger.getLogger(InicioServlet.class.getName()).log(Level.SEVERE, null, e);
                flag = true;
            }
        }else{
            flag = true;
            System.out.println("no hay noticias");
        }
        if(flag){
            request.setAttribute("error", "La pagina no se pudo cargar correctamente");
            request.getRequestDispatcher("errorConsulta.jsp").forward(request, response);
            
        }
    }

    private List<Map<String, Object>> mapearNoticiasDestacadas(List<NoticiaBean> noticiasEncontradas){
        List<Map<String, Object>> noticiasMapa = new ArrayList<>();

        Map<String, Object> mapeoNoticia;
        
        for (NoticiaBean noticia : noticiasEncontradas) {
            System.out.println("noticia: "+noticia.getTitulo());
            mapeoNoticia = new HashMap<>();
            String[] parrafos = noticia.getTexto().split("\n");
            ImagenBean imagen = noticia.getImagen();
            if(noticia.isDestacada()){
                mapeoNoticia.put("noticia", noticia);
                mapeoNoticia.put("tipoArchivo", imagen.getTipoImagen());
                mapeoNoticia.put("nombreArchivo", imagen.getNombreArchivo());
                mapeoNoticia.put("url", imagen.getUrl());
                mapeoNoticia.put("parrafos", parrafos);

                noticiasMapa.add(mapeoNoticia);
            }
        }
        
        return noticiasMapa;
    } 
    
    private List<Map<String, Object>> mapearNoticiasNormales(List<NoticiaBean> noticiasEncontradas){
        List<Map<String, Object>> noticiasMapa = new ArrayList<>();

        Map<String, Object> mapeoNoticia;
        
        for (NoticiaBean noticia : noticiasEncontradas) {
            mapeoNoticia = new HashMap<>();
            String[] parrafos = noticia.getTexto().split("\n");
            ImagenBean imagen = noticia.getImagen();
            if(!noticia.isDestacada()){
                mapeoNoticia.put("noticia", noticia);
                mapeoNoticia.put("tipoArchivo", imagen.getTipoImagen());
                mapeoNoticia.put("nombreArchivo", imagen.getNombreArchivo());
                mapeoNoticia.put("url", imagen.getUrl());
                mapeoNoticia.put("parrafos", parrafos);

                noticiasMapa.add(mapeoNoticia);
            }
        }
        
        return noticiasMapa;
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
