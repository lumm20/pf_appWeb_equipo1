package fachadas;

import daos.INoticiaDAO;
import daos.IPublicacionDAO;
import daos.NoticiaDAO;
import daos.PublicacionDAO;
import entidades.FiltroNoticia;
import entidades.Noticia;
import entidades.Publicacion;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la interfaz IFacadePost que proporciona una capa de fachada
 * para interactuar con la capa de datos de publicaciones y noticias.
 *
 * @author karim
 */
public class FacadePost implements IFacadePost {

    private INoticiaDAO noticiaDAO;
    private IPublicacionDAO publicacionDAO;

    /**
     * Constructor que inicializa las instancias de INoticiaDAO y
     * IPublicacionDAO.
     */
    public FacadePost() {
        this.noticiaDAO = new NoticiaDAO();
        this.publicacionDAO = new PublicacionDAO();
    }

    /**
     * Registra una nueva noticia en el sistema.
     *
     * @param noticia Noticia a registrar.
     * @param contenido Contenido de la noticia.
     */
    @Override
    public Noticia registrarNoticia(Noticia noticia) throws PersistenciaException {
        try {
            return noticiaDAO.registrarNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     * Busca una noticia en el sistema según los criterios de búsqueda
     * proporcionados.
     *
     * @param notica Criterios de búsqueda para la noticia.
     * @return Noticia encontrada o null si no se encuentra.
     */
    @Override
    public Noticia buscarNoticia(Noticia notica) {
        try {

            return noticiaDAO.buscarNoticia(notica);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Actualiza una noticia existente en el sistema.
     *
     * @param noticia Noticia a actualizar.
     */
    @Override
    public void actualizarNoticia(Noticia noticia) {
        try {
            noticiaDAO.actualizarNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Registra una nueva publicación en el sistema.
     *
     * @param publicacion Publicación a registrar.
     */
    @Override
    public void registrarPublicacion(Publicacion publicacion) {
        try {
            publicacionDAO.publicarNuevaPublicacion(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Busca todas las publicaciones en el sistema.
     *
     * @return Lista de publicaciones encontradas.
     */
    @Override
    public List<Publicacion> buscarPublicaciones() {
        return publicacionDAO.buscarPublicaciones();
    }

    /**
     * Busca todas las publicaciones en el sistema.
     *
     * @param publicacion
     * @return Lista de publicaciones encontradas.
     */
    @Override
    public List<Publicacion> buscarPublicacionesPorCategoria(Publicacion publicacion) {
        try {
            return publicacionDAO.buscarPublicacionesPorCategoria(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Actualiza una publicación existente en el sistema.
     *
     * @param publicacion Publicación a actualizar.
     */
    @Override
    public void actualizarPublicacion(Publicacion publicacion) {
        try {
            publicacionDAO.actualizarPublicacion(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Busca una publicación en el sistema según los criterios de búsqueda
     * proporcionados.
     *
     * @param publicacion Criterios de búsqueda para la publicación.
     * @return Publicación encontrada o null si no se encuentra.
     */
    @Override
    public Publicacion buscarPublicacion(Publicacion publicacion) {
        try {
            return publicacionDAO.buscarPublicacion(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Elimina una publicación del sistema.
     *
     * @param publicacion Publicación a eliminar.
     */
    @Override
    public void eliminarPublicacion(Publicacion publicacion) {
        publicacionDAO.eliminarPublicacion(publicacion);
    }

    /**
     * Elimina una noticia del sistema.
     *
     * @param noticia Noticia a eliminar.
     */
    @Override
    public boolean eliminarNoticia(Noticia noticia) {
        return noticiaDAO.eliminarNoticia(noticia);
    }

    @Override
    public List<Noticia> buscarNoticias(FiltroNoticia filtro) throws PersistenciaException {
        return noticiaDAO.buscarNoticias(filtro);
    }

    @Override
    public boolean anclarNoticia(Noticia noticia) {
        try {
            return noticiaDAO.anclarNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean desanclarNoticia(Noticia noticia) {
        try {
            return noticiaDAO.desanclarNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
