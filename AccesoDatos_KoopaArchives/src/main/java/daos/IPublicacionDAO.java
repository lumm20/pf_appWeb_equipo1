/*
 * IPublicacionDAO.java
 */
package daos;

import entidades.Publicacion;
import entidades.FiltroNoticia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Juventino López García - 00000248547 - 28/10/2024
 */
public interface IPublicacionDAO {

    public void publicarNuevaPublicacion(Publicacion publicacion) throws PersistenciaException;
    public Publicacion buscarPublicacion(Publicacion publicacion) throws PersistenciaException;
    public List<Publicacion> buscarPublicaciones(Publicacion publicacion) throws PersistenciaException;
    public List<Publicacion> buscarPublicaciones();
    public void actualizarPublicacion(Publicacion publicacion) throws PersistenciaException;
    public void eliminarPublicacion(Publicacion publicacion);

}
