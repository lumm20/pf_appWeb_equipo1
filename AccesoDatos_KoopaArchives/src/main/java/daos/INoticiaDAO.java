package daos;

import entidades.Contenido;
import entidades.FiltroNoticia;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author karim
 */
public interface INoticiaDAO {
    public void publicarNuevaNoticia(Noticia noticia, Contenido contenido) throws PersistenciaException;
    public Noticia buscarNoticia(Noticia noticia) throws PersistenciaException;
    public List<Noticia> buscarNoticias(FiltroNoticia noticia) throws PersistenciaException;
    public List<Noticia> buscarNoticias();
    public void anclarNoticia(Noticia noticia) throws PersistenciaException;
    public void actualizarNoticia(Noticia noticia) throws PersistenciaException;
    public void eliminarNoticia(Noticia noticia);
}
