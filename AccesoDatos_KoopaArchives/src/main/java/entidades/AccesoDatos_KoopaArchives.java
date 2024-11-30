package entidades;

import daos.ComentarioDAO;
import daos.NoticiaDAO;
import excepciones.PersistenciaException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author karim
 */
public class AccesoDatos_KoopaArchives {

    public static void main(String[] args) throws PersistenciaException {
      
        ComentarioDAO daoC = new ComentarioDAO();
//        Comentario comentario = new Comentario();
//        comentario.setContenido("Mi primer comentario");
//        comentario.setIdUsuario("luisaFernanda12");
////        
//        Comentario nuevo = daoC.agregarComentario(comentario);
//        if(nuevo != null){
//            System.out.println("comentario: "+nuevo.getIdComentario());
//        }
//        Comentario com = daoC.obtenerComentarioPorCodigo("N4912012504");
//        if(com != null){
//            System.out.println("usuario; "+com.getUsuario());
//            System.out.println("imagen "+com.getUsuario().getImagen().getNombreArchivo());
//        }
        
//        NoticiaDAO dao = new NoticiaDAO();
//        Noticia noticiaCambiar  = dao.buscarNoticia(new Noticia("N6077345630"));
//
//        
//        noticiaCambiar.setDestacada(true);
//        noticiaCambiar.setTitulo("Titulo cambiado");
//        dao.actualizarNoticia(noticiaCambiar);
//        System.out.println(dao.buscarNoticia(new Noticia("N6077345630")));
    }
}
