package daos;

import entidades.FiltroNoticia;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define los métodos para interactuar con la capa de datos de
 * noticias.
 *
 * @author karim
 */
public interface INoticiaDAO {

    /**
     * Publica una nueva noticia en la base de datos.
     *
     * @param noticia Noticia a publicar.
     * @param contenido Contenido de la noticia.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public Noticia registrarNoticia(Noticia noticia) throws PersistenciaException;

    /**
     * Busca una noticia en la base de datos según los criterios de búsqueda
     * proporcionados.
     *
     * @param noticia Criterios de búsqueda para la noticia.
     * @return Noticia encontrada o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public Noticia buscarNoticia(Noticia noticia) throws PersistenciaException;

    /**
     * Busca noticias en la base de datos según los criterios de búsqueda
     * proporcionados.
     *
     * @param noticia Criterios de búsqueda para las noticias.
     * @return Lista de noticias encontradas.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public List<Noticia> buscarNoticias(FiltroNoticia noticia) throws PersistenciaException;

    /**
     * Busca todas las noticias en la base de datos.
     *
     * @return Lista de noticias encontradas.
     */
    public List<Noticia> buscarNoticias();
    
    public List<Noticia> buscarNoticiasDestacadas();

    /**
     * Ancla una noticia en la base de datos.
     *
     * @param noticia Noticia a anclar.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public boolean anclarNoticia(Noticia noticia) throws PersistenciaException;
    public boolean desanclarNoticia(Noticia noticia) throws PersistenciaException;

    /**
     * Actualiza una noticia existente en la base de datos.
     *
     * @param noticia Noticia a actualizar.
     * @throws PersistenciaException Si ocurre un error al interactuar con la
     * base de datos.
     */
    public void actualizarNoticia(Noticia noticia) throws PersistenciaException;

    /**
     * Elimina una noticia de la base de datos.
     *
     * @param noticia Noticia a eliminar.
     */
    public boolean eliminarNoticia(Noticia noticia);
}
