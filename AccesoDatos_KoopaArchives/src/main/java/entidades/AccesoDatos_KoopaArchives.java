/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package entidades;

import conexion.Conexion;
import conexion.IConexion;
import daos.INoticiaDAO;
import daos.NoticiaDAO;
import excepciones.PersistenciaException;
import fachadas.FacadePost;
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
        
//        Subtema subtema1 = new Subtema("Subtitulo1", "Descripcion 1", "Img 1");
//        Subtema subtema2 = new Subtema("Subtitulo2", "Descripcion 2", "Img 2");
//        Subtema subtema3 = new Subtema("Subtitulo3", "Descripcion 3", "Img 3");
//        Subtema subtema4 = new Subtema("Subtitulo4", "Descripcion 4", "Img 4");
//        
//        List<Subtema> lista1 = Arrays.asList(subtema1,subtema2);
//        List<Subtema> lista2 = Arrays.asList(subtema4,subtema2,subtema3);
//        
//        Contenido contenido1 = new Contenido("Texto 1", "Titulo 1", lista2, "Png 1");
//        Contenido contenido2 = new Contenido("Texto 2", "Titulo 2", lista2, "Png 2");
//        Contenido contenido3 = new Contenido("Texto 2", "Titulo 3", lista1, "Png 3");
//        
        Noticia noticia1 = new Noticia("Zelda", new Date());
        Noticia noticia2 = new Noticia("Mario", new Date());
        Noticia noticia3 = new Noticia("Metroid", new Date());
//        
//        INoticiaDAO noticiasDAO = new NoticiaDAO();
//
//        noticiasDAO.publicarNuevaNoticia(noticia1, contenido1);
//        noticiasDAO.publicarNuevaNoticia(noticia2, contenido2);
//        noticiasDAO.publicarNuevaNoticia(noticia3, contenido3);

//             // Crear un objeto Calendar
//        Calendar calendar = Calendar.getInstance();
//        
//        // Establecer la fecha: 26 de octubre de 2024
//        calendar.set(2024, Calendar.OCTOBER, 25, 0, 0, 0); // Mes es cero-indexado
//        calendar.set(Calendar.MILLISECOND, 0); // Asegurarse de que los milisegundos son cero
//
//        // Obtener la fecha como un objeto Date
//        Date fecha1 = calendar.getTime();
//         // Crear un objeto Calendar
//        Calendar calendar2 = Calendar.getInstance();
//        
//        // Establecer la fecha: 26 de octubre de 2024
//        calendar2.set(2024, Calendar.OCTOBER, 26, 0, 0, 0); // Mes es cero-indexado
//        calendar2.set(Calendar.MILLISECOND, 0); // Asegurarse de que los milisegundos son cero
//
//        // Obtener la fecha como un objeto Date
//        Date fecha2 = calendar2.getTime();
//
//        
//        FiltroNoticia filtro1 = new FiltroNoticia(fecha1, fecha2, null, null);
//
//        List<Noticia> listaNoticias = noticiasDAO.buscarNoticias(filtro1);
//        
//        for (Noticia noticia : listaNoticias) {
//            System.out.println(noticia);

//        Noticia buscar = new Noticia();
//        buscar.setNumPost("N1730221440");
//        
        IFacadePost post = new FacadePost();
        post.registrarNoticia(noticia1, new Contenido("Lala", "Splattonn", null, ""));
        post.registrarNoticia(noticia2, new Contenido("Lala", "Link", null, ""));
        post.registrarNoticia(noticia3, new Contenido("Lala", "Mario", null, ""));
        post.eliminarNoticia(new Noticia("N1730221440"));
//        Noticia noticia = post.buscarNoticia(buscar);
//        System.out.println(noticia);
//        noticia.getContenido().setDescripcion("El mejor cantante de mexico");
//        noticia.getContenido().setTitulo("Luis Miguel");
//        
//        post.actualizarNoticia(noticia);
//        System.out.println(post.buscarNoticia(noticia));

//            Publicacion publi1 = (Publicacion) FactoryPost.crearPost(FactoryPost.PUBLICACION);
//            Publicacion publi2 = (Publicacion) FactoryPost.crearPost(FactoryPost.PUBLICACION);
//            Publicacion publi3 = (Publicacion) FactoryPost.crearPost(FactoryPost.PUBLICACION);
            
//            publi1.setContenido("Aqui estuvo mario2");
//            publi1.setFechaCreacion(new Date());
//            publi1.setCategoria("Mario2");
//            publi1.setUrlImg("Mario2.png");
//            
//            publi2.setContenido("Aqui estuvo kirby2");
//            publi2.setFechaCreacion(new Date());
//            publi2.setCategoria("Kirby2");
//            publi2.setUrlImg("Kirby2.png");
//            
//            publi3.setContenido("Aqui estuvo link2");
//            publi3.setFechaCreacion(new Date());
//            publi3.setCategoria("Link2");
//            publi3.setUrlImg("Link2.png");
//            
////            IFacadePost post = new FacadePost();
//            post.registrarPublicacion(publi1);
//            post.registrarPublicacion(publi2);
//            post.registrarPublicacion(publi3);
            Publicacion buscar = new Publicacion();
            buscar.setNumPost("N961075187");
//            post.actualizarPublicacion(publi3);
//
            System.out.println(post.buscarPublicacion(buscar));
//            for(Publicacion publi : post.buscarPublicaciones()){
//                System.out.println(publi);
//            }
//        }
    }
}
