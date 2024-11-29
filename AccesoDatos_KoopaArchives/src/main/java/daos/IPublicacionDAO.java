/*
 * IPublicacionDAO.java
 */
package daos;

import entidades.Comentario;
import entidades.Publicacion;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define los métodos para interactuar con la capa de datos de
 * publicaciones.
 *
 * @author Juventino López García - 00000248547 - 28/10/2024.
 */
public interface IPublicacionDAO {

    /**
     * Publica una nueva publicación en la base de datos.
     *
     * @param publicacion Publicación a publicar.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public void publicarNuevaPublicacion(Publicacion publicacion) throws PersistenciaException;

    /**
     * Busca una publicación en la base de datos según los criterios de búsqueda
     * proporcionados.
     *
     * @param publicacion Criterios de búsqueda para la publicación.
     * @return Publicación encontrada o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public Publicacion buscarPublicacion(Publicacion publicacion) throws PersistenciaException;

    /**
     * Busca publicaciones en la base de datos según los criterios de búsqueda
     * proporcionados.
     *
     * @param publicacion Criterios de búsqueda para las publicaciones.
     * @return Lista de publicaciones encontradas.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public List<Publicacion> buscarPublicacionesPorCategoria(Publicacion publicacion) throws PersistenciaException;
   
    /**
     * Busca todas las publicaciones en la base de datos.
     *
     * @return Lista de publicaciones encontradas.
     */
    public List<Publicacion> buscarPublicaciones();

    /**
     * Actualiza una publicación existente en la base de datos.
     *
     * @param publicacion Publicación a actualizar.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public void actualizarPublicacion(Publicacion publicacion) throws PersistenciaException;

    /**
     * Actualiza los likes  que tiene una publicacion.
     * Se puede sumar y restar likes
     * @param publicacion Publicacion a actualizar
     * @return true si se actualizo correctamente
     */
    public boolean actualizarLikesPublicacion(Publicacion publicacion);
    public boolean agregarComentarioPublicacion(Publicacion publicacion, Comentario comentario);
    public boolean removerComentarioPublicacion(Publicacion publicacion, Comentario comentario);
    
    /**
     * Elimina una publicación de la base de datos.
     *
     * @param publicacion Publicación a eliminar.
     */
    public void eliminarPublicacion(Publicacion publicacion);
}
