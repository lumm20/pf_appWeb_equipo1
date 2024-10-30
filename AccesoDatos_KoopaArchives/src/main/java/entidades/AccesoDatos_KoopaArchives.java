/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package entidades;

import conexion.Conexion;
import conexion.IConexion;
import daos.INoticiaDAO;
import daos.IUsuarioDAO;
import daos.NoticiaDAO;
import daos.UsuarioNormalDAO;
import excepciones.PersistenciaException;
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
//        Noticia noticia1 = new Noticia("Zelda", new Date());
//        Noticia noticia2 = new Noticia("Mario", new Date());
//        Noticia noticia3 = new Noticia("Metroid", new Date());
////        
        INoticiaDAO noticiasDAO = new NoticiaDAO();
////
//        noticiasDAO.publicarNuevaNoticia(noticia1, contenido1);
//        noticiasDAO.publicarNuevaNoticia(noticia2, contenido2);
//        noticiasDAO.publicarNuevaNoticia(noticia3, contenido3);

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
        
        normal1= dao.buscarUsuario(normal1);
        System.out.println(normal1);
        System.out.println("contra "+normal1.getContra());
        normal1.printObjectId();
        
        if(dao.iniciarSesion(normal1))
            System.out.println("logged");
        else System.out.println("not logged");
//        admin1 = dao.agregarUsuario(admin1, true);
//        System.out.println("admin agregado: "+admin1);
//        normal1 = dao.agregarUsuario(normal1, false);
//        System.out.println("normal agregado: "+normal1);
    }
}
