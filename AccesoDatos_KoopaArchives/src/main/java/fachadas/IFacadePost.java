package fachadas;

import entidades.Contenido;
import entidades.Noticia;
import entidades.Publicacion;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define los métodos para interactuar con la capa de datos de
 * publicaciones y noticias.
 *
 * @author karim
 */
public interface IFacadePost {

    /**
     * Registra una nueva noticia en el sistema.
     *
     * @param noticia Noticia a registrar.
     * @param contenido Contenido de la noticia.
     */
    public void registrarNoticia(Noticia noticia, Contenido contenido)throws PersistenciaException;

    /**
     * Busca una noticia en el sistema según los criterios de búsqueda
     * proporcionados.
     *
     * @param noticia Criterios de búsqueda para la noticia.
     * @return Noticia encontrada
     */
    public Noticia buscarNoticia(Noticia noticia);

    /**
     * Busca todas las noticias en el sistema.
     *
     * @return Lista de noticias encontradas.
     */
    public List<Noticia> buscarNoticias();

    /**
     * Actualiza una noticia existente en el sistema.
     *
     * @param noticia Noticia a actualizar.
     */
    public void actualizarNoticia(Noticia noticia);

    /**
     * Elimina una noticia del sistema.
     *
     * @param noticia Noticia a eliminar.
     */
    public void eliminarNoticia(Noticia noticia);

    /**
     * Registra una nueva publicación en el sistema.
     *
     * @param publicacion Publicación a registrar.
     */
    public void registrarPublicacion(Publicacion publicacion)throws PersistenciaException;

    /**
     * Busca una publicación en el sistema según los criterios de búsqueda
     * proporcionados.
     *
     * @param publicacion Criterios de búsqueda para la publicación.
     * @return Publicación encontrada
     */
    public Publicacion buscarPublicacion(Publicacion publicacion);

    /**
     * Busca todas las publicaciones en el sistema.
     *
     * @return Lista de publicaciones encontradas.
     */
    public List<Publicacion> buscarPublicaciones();

    /**
     * Actualiza una publicación existente en el sistema.
     *
     * @param publicacion Publicación a actualizar.
     */
    public void actualizarPublicacion(Publicacion publicacion);

    /**
     * Elimina una publicación del sistema.
     *
     * @param publicacion Publicación a eliminar.
     */
    public void eliminarPublicacion(Publicacion publicacion);
}
