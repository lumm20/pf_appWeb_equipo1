package entidades;

import daos.NoticiaDAO;
import excepciones.PersistenciaException;

/**
 *
 * @author karim
 */
public class AccesoDatos_KoopaArchives {

    public static void main(String[] args) throws PersistenciaException {
      
        NoticiaDAO dao = new NoticiaDAO();
        Noticia noticiaCambiar  = dao.buscarNoticia(new Noticia("N6077345630"));

        
        noticiaCambiar.setDestacada(true);
        noticiaCambiar.setTitulo("Titulo cambiado");
        dao.actualizarNoticia(noticiaCambiar);
        System.out.println(dao.buscarNoticia(new Noticia("N6077345630")));
    }
}
