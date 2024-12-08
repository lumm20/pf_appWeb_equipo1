/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtros;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.rmi.server.LogStream.log;

/**
 *
 * @author luisa M
 */
public class FiltroAutenticacion2 implements Filter{
    
    private static final boolean debug = true;
    private static final String PAGINA_PRINCIPAL = "Noticia?action=cargarInicio";
    private static final String LOGIN = "/login.jsp";
    private static final String REGISTRO = "/register.jsp";
    private static final String OPCIONES_USUARIO = "Usuario/*";
    private static final String CREAR_NOTICIAS = "/crearNoticia.jsp";
    private static final String CREAR_POST = "/crearPost.jsp";
    private static final String NOTICIA_DETALLE = "CargarNoticia/*";
    private static final String HARDCODEO_DE_UN_SOLO_TRUCO = "Hardcodear";
    private static final String NOSOTROS = "/nosotros.jsp";

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private static final String[] urlSinSesion = {
        PAGINA_PRINCIPAL,
        NOTICIA_DETALLE,
        HARDCODEO_DE_UN_SOLO_TRUCO,
        LOGIN,
        REGISTRO,
        NOSOTROS,
        OPCIONES_USUARIO
    };
    
    private static final String[] urlAdmin = {
        PAGINA_PRINCIPAL,
        CREAR_NOTICIAS,
        NOTICIA_DETALLE,
        NOSOTROS
    };

    private static final String[] urlNormal = {
        PAGINA_PRINCIPAL,
        CREAR_POST,
        NOTICIA_DETALLE,
        NOSOTROS
    };
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (debug) {
            log("WebAccessControlFilter: Inicializando filtro");
        }
    }
    
    /**
     * Verifica si la ruta solicitada es un recurso estático
     */
    private boolean isStaticResource(String path) {
        return path.endsWith(".js")
                || path.endsWith(".css")
                || path.endsWith(".png")
                || path.endsWith(".jpg")
                || path.endsWith(".jpeg")
                || path.endsWith(".gif")
                || path.endsWith(".svg")
                || path.endsWith(".woff")
                || path.endsWith(".woff2")
                || path.endsWith(".ttf");
    }
    
        /**
     * Verifica si la URL está permitida sin sesión
     */
    private boolean esUrlPermitidaSinSesion(String completePath, String fullPath) {
        return contieneUrl(completePath, urlSinSesion) || contieneUrl(fullPath, urlSinSesion);
    }

    /**
     * Verifica si la URL está permitida para admin
     */
    private boolean esUrlPermitidaAdmin(String completePath, String fullPath) {
        return contieneUrl(completePath, urlAdmin) || contieneUrl(fullPath, urlAdmin);
    }

    /**
     * Verifica si la URL está permitida para usuario normal
     */
    private boolean esUrlPermitidaUsuarioNormal(String completePath, String fullPath) {
        return contieneUrl(completePath, urlNormal) || contieneUrl(fullPath, urlNormal);
    }
    
    /**
     * Método auxiliar para verificar si una URL está en un arreglo de URLs
     * permitidas
     */
    private boolean contieneUrl(String path, String[] urls) {
        if (path == null) {
            return false;
        }

        for (String url : urls) {
            // Soporte para URLs con comodín (*)
            if (url.endsWith("*")) {
                String base = url.substring(0, url.length() - 1);
                if (path.startsWith(base)) {
                    return true;
                }
            } // Coincidencia exacta o con parámetros
            else if (path.equals(url) || path.startsWith(url + "?")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método de log para debug
     */
    private void log(String mensaje) {
        System.out.println("[WebAccessControlFilter] " + mensaje);
    }

    @Override
    public void destroy() {
        if (debug) {
            log("WebAccessControlFilter: Destruyendo filtro");
        }
    }
}
