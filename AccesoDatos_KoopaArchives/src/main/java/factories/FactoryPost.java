package factories;

import entidades.Noticia;
import entidades.Post;
import entidades.Publicacion;

/**
 * Clase de fábrica que crea instancias de los diferentes tipos de Post
 *
 * @author karim
 */
public class FactoryPost {

    /**
     * Constante que representa el tipo de post "Publicación".
     */
    public static final int PUBLICACION = 1;

    /**
     * Constante que representa el tipo de post "Noticia".
     */
    public static final int NOTICIA = 2;

    /**
     * Crea una instancia de Post según el tipo de post especificado.
     *
     * @param tipoPost Tipo de post a crear (PUBLICACION o NOTICIA).
     * @return Instancia de Post creada.
     * @throws IllegalArgumentException Si el tipo de post no es válido.
     */
    public static Post crearPost(int tipoPost) {
        switch (tipoPost) {
            case PUBLICACION -> {
                return new Publicacion();  // Retorna una instancia de Publicacion
            }
            case NOTICIA -> {
                return new Noticia();      // Retorna una instancia de Noticia
            }
            default ->
                throw new IllegalArgumentException("Tipo de Post no válido: " + tipoPost);
        }
    }
}
