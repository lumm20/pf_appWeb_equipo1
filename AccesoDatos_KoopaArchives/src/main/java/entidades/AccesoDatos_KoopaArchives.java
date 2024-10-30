package entidades;

import conexion.Conexion;
import conexion.IConexion;
import daos.INoticiaDAO;
import daos.NoticiaDAO;
import daos.UsuarioNormalDAO;
import excepciones.PersistenciaException;
import fachadas.FacadeComentario;
import fachadas.FacadePost;
import fachadas.FachadaUsuarios;
import fachadas.IFacadeComentario;
import fachadas.IFacadePost;
import factories.FactoryPost;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author karim
 */
public class AccesoDatos_KoopaArchives {

    public static void main(String[] args) throws PersistenciaException {
        IFacadePost post = new FacadePost();
        IFacadeComentario reply = new FacadeComentario();
        FachadaUsuarios user = new FachadaUsuarios();
        
//        user.registrarUsuario(new Usuario("Karim", "Franco", false));
//        user.registrarUsuario(new Usuario("Victor", "Encinas", false));
//        user.registrarUsuario(new Usuario("Laura", "Arredondo", true));
//        user.registrarUsuario(new Usuario("Luis", "Miguel", false));

//        Comentario comentario = new Comentario("Me cai jaja", new Noticia("N5526980303"), new Usuario("N9708244311"));
//        Comentario comentario2 = new Comentario("Me levante jaja", new Noticia("N5526980303"), new Usuario("N9708244311"));
//        
//        reply.registrarComentario(comentario);
//        reply.registrarComentario(comentario2);
        List<Comentario> lista = reply.obtenerComentariosPorNoticia(new Noticia("N5526980303"));
        System.out.println(lista.size());
       for(Comentario comentario: lista){
           System.out.println(comentario);
       }
    }
}
