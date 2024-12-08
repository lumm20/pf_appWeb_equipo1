
import entidades.Comentario;
import entidades_beans.ComentarioBean;
import entidades_beans.NoticiaBean;
import entidades_beans.UsuarioBean;
import objetosNegocio.ComentarioBO;
import objetosNegocio.IComentarioBO;
import objetosNegocio.IUsuarioBO;
import objetosNegocio.UsuarioBO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author karim
 */
public class prueba {
    public static void main(String[] args) {
        IComentarioBO bo = new ComentarioBO();
        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo("N6077345630");
        for(ComentarioBean comentario :bo.obtenerComentariosPorNoticia(bean)){
            System.out.println(comentario);
        
    }
    }
   
}
