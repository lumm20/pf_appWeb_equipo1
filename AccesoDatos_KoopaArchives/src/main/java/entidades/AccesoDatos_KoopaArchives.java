package entidades;

import daos.ComentarioDAO;
import daos.NoticiaDAO;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author karim
 */
public class AccesoDatos_KoopaArchives {

    public static void main(String[] args) throws PersistenciaException {
        Noticia n = new Noticia();
        n.setCodigo("N6770884989");
        NoticiaDAO dao = new NoticiaDAO();
        System.out.println(dao.desanclarNoticia(n));
//        boolean resultado = dao.(new Noticia("N1376744126"));
//        System.out.println(resultado);
        
//        ComentarioDAO dao = new ComentarioDAO();
//        Comentario aux = new Comentario();
////        aux.setAutor("zoro");
////        aux.setContenido("holi");
////        aux.setCodigoNoticia("N6770884989");
//        List<Comentario> lista = dao.obtenerComentariosDestacadosPorNoticia(new Noticia("N6770884989"));
//        System.out.println(lista);
        
//        Comentario c =     dao.registrarComentario(aux);
//        System.out.println(c);
      
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
