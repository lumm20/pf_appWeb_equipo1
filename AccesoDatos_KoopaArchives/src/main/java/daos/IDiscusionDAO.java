/*
 * IDiscusionDAO.java
 */
package daos;

import entidades.Discusion;
import entidades.FiltroNoticia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Juventino López García - 00000248547 - 28/10/2024
 */
public interface IDiscusionDAO {

    public void publicarNuevaDiscusion(Discusion discusion) throws PersistenciaException;

    public Discusion buscarDiscusion(Discusion discusion) throws PersistenciaException;

    public List<Discusion> buscarDiscusiones(FiltroNoticia noticia) throws PersistenciaException;

}
