package entidades;

import daos.ComentarioDAO;
import daos.NoticiaDAO;
import excepciones.PersistenciaException;
import java.util.List;

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
