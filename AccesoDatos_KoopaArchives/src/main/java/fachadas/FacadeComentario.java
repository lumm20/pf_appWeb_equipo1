package fachadas;

import daos.ComentarioDAO;
import daos.IComentarioDAO;
import entidades.Comentario;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class FacadeComentario implements IFacadeComentario{
    private IComentarioDAO comentarioDAO;
    
    public FacadeComentario() {
        this.comentarioDAO = new ComentarioDAO();
    }
    
    
    @Override
    public Comentario registrarComentario(Comentario comentario) {
        try {
            return comentarioDAO.registrarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Comentario registrarComentarioPublicacion(Comentario comentario) {
        try {
            return comentarioDAO.agregarComentarioPublicacion(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Comentario buscarComentario(Comentario Comentario) {
        try {
            return comentarioDAO.buscarComentario(Comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean eliminarComentario(Comentario comentario) {
        try {
            return comentarioDAO.eliminarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    @Override
    public boolean anclarComentario(Comentario comentario) {
        try {
            return comentarioDAO.anclarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean editarComentario(Comentario comentario) {
        try {
            return comentarioDAO.editarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia) {
        try {
            return comentarioDAO.obtenerComentariosPorNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean desanclarComentario(Comentario comentario) {
        try {
            return comentarioDAO.desanclarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Comentario> obtenerComentariosDestacadosPorNoticia(Noticia noticia) {
        try {
            return comentarioDAO.obtenerComentariosDestacadosPorNoticia(noticia);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Comentario> obtenerComentarios(List<String> id) {
        try {
            return comentarioDAO.obtenerComentarios(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
