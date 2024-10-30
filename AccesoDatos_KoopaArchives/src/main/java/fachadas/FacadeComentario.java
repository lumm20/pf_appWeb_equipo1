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
    public void registrarComentario(Comentario comentario) {
        try {
            comentarioDAO.registrarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
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
    public void eliminarComentario(Comentario comentario) {
        try {
            comentarioDAO.eliminarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void anclarComentario(Comentario comentario) {
        try {
            comentarioDAO.anclarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editarComentario(Comentario comentario) {
        try {
            comentarioDAO.editarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
