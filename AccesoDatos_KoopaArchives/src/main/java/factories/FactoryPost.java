package factories;

import entidades.Noticia;
import entidades.Post;
import entidades.Publicacion;

/**
 *
 * @author karim
 */
public class FactoryPost {

    public static final int PUBLICACION = 1;
    public static final int NOTICIA = 2;

    public static Post crearPost(int tipoPost) {
        switch (tipoPost) {
            case PUBLICACION:
                return new Publicacion();  // Retorna una instancia de Publicacion
            case NOTICIA:
                return new Noticia();      // Retorna una instancia de Noticia
            default:
                throw new IllegalArgumentException("Tipo de Post no válido: " + tipoPost);
        }
    }
}
