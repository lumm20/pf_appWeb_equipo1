package filtros;

import entidades_beans.UsuarioBean;
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
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author karim
 */
@WebFilter("/private/*")
public class FiltroAutenticacion implements Filter {

    private static final boolean debug = true;
    private static final String[] urlPublicas = {"/inicio","/Usuario","/Noticia",
        "/Harcodear","/CargarNoticia","/inicio.jsp","/register.jsp","/login.jsp",
        "/buscadorNoticias.jsp","/noticia.jsp"};
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public FiltroAutenticacion() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroAutenticacion:DoBeforeProcessing");
        }

    }
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroAutenticacion:DoAfterProcessing");
        }

    }

    //Verifica que haya una sesion activa y que el usuario  se encuentre autenticado
    private boolean estaLogueado(HttpSession session) {
        boolean logueado = (session != null && session.getAttribute("usuario") != null);

        return logueado;
    }

    private boolean esAdmin(HttpSession session){
        UsuarioBean user = (UsuarioBean)session.getAttribute("usuario");
        
        return user.getRol().equalsIgnoreCase("admin");
    }
    
    private boolean esURLPublica(String uri, String contextPath) {
        String ruta = uri.substring(contextPath.length());
        for (String url : urlPublicas) {
            if (ruta.startsWith(url)) {
                return true;
            }
        }
        return false;
    }

    // Obtiene la ruta a la que desea acceder
    private String getRutaSolicitada(HttpServletRequest httpRequest) {
        String uriSolicitada = httpRequest.getRequestURI();
        String ruta = uriSolicitada.substring(httpRequest.getContextPath().length());
        return ruta;
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setHeader("Expires", "0");
        
        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        System.out.println("context path: "+contextPath);
        boolean urlPublica = esURLPublica(uri,contextPath);
        
        if(urlPublica){
            chain.doFilter(request, response);
            return;
        }
        
        HttpSession session = httpRequest.getSession(false);
        
        if (session != null && session.getAttribute("usuario") != null) {
            UsuarioBean user  = (UsuarioBean)session.getAttribute("usuario");
            
            if(uri.contains("/private/admin/")){
                if(validateRole(user)){
                    chain.doFilter(request, response);
                }else
                    httpRequest.getRequestDispatcher("/error401.jsp").
                            forward(httpRequest, httpResponse);
            }else if(uri.contains("/private/normal/")){
                if(user.getRol().equalsIgnoreCase("Normal"))
                    chain.doFilter(request, response);
                else
                    httpRequest.getRequestDispatcher("/error401.jsp").
                            forward(httpRequest, httpResponse);
            }else if(uri.contains("/private/")){
                chain.doFilter(request, response);
            }
                
        }else{
            session = httpRequest.getSession();
            session.setAttribute("urlOriginal", uri);
            httpResponse.sendRedirect("/login.jsp");
        }

    }

    private boolean validateRole(UsuarioBean user){
        return user.getRol().equalsIgnoreCase("admin");
    }
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FiltroAutenticacion:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroAutenticacion()");
        }
        StringBuffer sb = new StringBuffer("FiltroAutenticacion(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
