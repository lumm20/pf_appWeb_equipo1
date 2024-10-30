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

        // user.registrarUsuario(new Usuario("Karim", "Franco", false));
        // user.registrarUsuario(new Usuario("Victor", "Encinas", false));
        // user.registrarUsuario(new Usuario("Laura", "Arredondo", true));
        // user.registrarUsuario(new Usuario("Luis", "Miguel", false));

        // Comentario comentario = new Comentario("Me cai jaja", new
        // Noticia("N5526980303"), new Usuario("N9708244311"));
        // Comentario comentario2 = new Comentario("Me levante jaja", new
        // Noticia("N5526980303"), new Usuario("N9708244311"));
        // List<Comentario> lista = reply.obtenerComentariosPorNoticia(new
        // Noticia("N5526980303")); 
        // reply.registrarComentario(comentario);
        // reply.registrarComentario(comentario2);
        System.out.println(lista.size());
        for (Comentario comentario : lista) {
            System.out.println(comentario);
        }
        // List<Subtema> lista1 = Arrays.asList(subtema1,subtema2);
        // List<Subtema> lista2 = Arrays.asList(subtema4,subtema2,subtema3);
        //
        // Contenido contenido1 = new Contenido("Texto 1", "Titulo 1", lista2, "Png 1");
        // Contenido contenido2 = new Contenido("Texto 2", "Titulo 2", lista2, "Png 2");
        // Contenido contenido3 = new Contenido("Texto 2", "Titulo 3", lista1, "Png 3");
        //
        // Noticia noticia1 = new Noticia("Zelda", new Date());
        // Noticia noticia2 = new Noticia("Mario", new Date());
        // Noticia noticia3 = new Noticia("Metroid", new Date());
        ////
        INoticiaDAO noticiasDAO = new NoticiaDAO();
        ////
        // noticiasDAO.publicarNuevaNoticia(noticia1, contenido1);
        // noticiasDAO.publicarNuevaNoticia(noticia2, contenido2);
        // noticiasDAO.publicarNuevaNoticia(noticia3, contenido3);

        // Crear un objeto Calendar
        Calendar calendar = Calendar.getInstance();

        // Establecer la fecha: 26 de octubre de 2024
        calendar.set(2024, Calendar.OCTOBER, 25, 0, 0, 0); // Mes es cero-indexado
        calendar.set(Calendar.MILLISECOND, 0); // Asegurarse de que los milisegundos son cero

        // Obtener la fecha como un objeto Date
        Date fecha1 = calendar.getTime();
        // Crear un objeto Calendar
        Calendar calendar2 = Calendar.getInstance();

        // Establecer la fecha: 26 de octubre de 2024
        calendar2.set(2024, Calendar.OCTOBER, 26, 0, 0, 0); // Mes es cero-indexado
        calendar2.set(Calendar.MILLISECOND, 0); // Asegurarse de que los milisegundos son cero

        // Obtener la fecha como un objeto Date
        Date fecha2 = calendar2.getTime();

        FiltroNoticia filtro1 = new FiltroNoticia(fecha1, fecha2, null, null);

        List<Noticia> listaNoticias = noticiasDAO.buscarNoticias(filtro1);

        for (Noticia noticia : listaNoticias) {
            System.out.println(noticia);
        }

        // Usuario admin1 = new Usuario("1234", "luisa", "morales", "noseTengoSuenio");
        Usuario normal1 = new Usuario("0001233", "fernanda", "espinoza", "mequiero_Dormir");

        UsuarioNormalDAO dao = new UsuarioNormalDAO();

        normal1 = dao.buscarUsuario(normal1);
        System.out.println(normal1);
        System.out.println("contra " + normal1.getContra());
        normal1.printObjectId();

        if (dao.iniciarSesion(normal1))
            System.out.println("logged");
        else
            System.out.println("not logged");
        // admin1 = dao.agregarUsuario(admin1, true);
        // System.out.println("admin agregado: "+admin1);
        // normal1 = dao.agregarUsuario(normal1, false);
        // System.out.println("normal agregado: "+normal1);
       

    }
}
