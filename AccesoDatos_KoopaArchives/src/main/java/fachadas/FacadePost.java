package fachadas;

import daos.INoticiaDAO;
import daos.IPublicacionDAO;
import daos.NoticiaDAO;
import daos.PublicacionDAO;
import entidades.Contenido;
import entidades.Noticia;
import entidades.Publicacion;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class FacadePost implements IFacadePost {

    private INoticiaDAO noticiaDAO;
    private IPublicacionDAO publicacionDAO;

    public FacadePost() {
        this.noticiaDAO = new NoticiaDAO();
        this.publicacionDAO = new PublicacionDAO();
    }

    @Override
    public void registrarNoticia(Noticia noticia, Contenido contenido) {
        try {
            noticiaDAO.publicarNuevaNoticia(noticia, contenido);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Noticia buscarNoticia(Noticia notica) {
        try {
            return noticiaDAO.buscarNoticia(notica);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void actualizarNoticia(Noticia noticia) {
        try {
            noticiaDAO.actualizarNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Noticia> buscarNoticias() {
        return noticiaDAO.buscarNoticias();
    }

    @Override
    public void registrarPublicacion(Publicacion publicacion) {
        try {
            publicacionDAO.publicarNuevaPublicacion(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Publicacion> buscarPublicaciones() {
        return publicacionDAO.buscarPublicaciones();
    }

    @Override
    public void actualizarPublicacion(Publicacion publicacion) {
        try {
            publicacionDAO.actualizarPublicacion(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion publicacion) {
        try {
            return publicacionDAO.buscarPublicacion(publicacion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void eliminarPublicacion(Publicacion publicacion) {
        publicacionDAO.eliminarPublicacion(publicacion);
    }

    @Override
    public void eliminarNoticia(Noticia noticia) {
        noticiaDAO.eliminarNoticia(noticia);
    }
}
