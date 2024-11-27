package entidades;

import daos.IUsuarioDAO;
import daos.NoticiaDAO;
import daos.UsuarioDAO;
import excepciones.PersistenciaException;
import fachadas.FacadePost;
import fachadas.IFacadePost;
import factories.FactoryUser;
import java.util.Date;

/**
 *
 * @author karim
 */
public class AccesoDatos_KoopaArchives {

    public static void main(String[] args) throws PersistenciaException {
      
        IFacadePost post = new FacadePost();
        
        for(Noticia noticia : post.buscarNoticias()){
            System.out.println(noticia);
        }

    }
}
