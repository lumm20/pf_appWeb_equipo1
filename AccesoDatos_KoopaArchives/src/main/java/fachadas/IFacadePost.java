package fachadas;

import entidades.Contenido;
import entidades.Noticia;
import entidades.Publicacion;
import java.util.List;


/**
 *
 * @author karim
 */
public interface IFacadePost {
    public void registrarNoticia(Noticia noticia, Contenido contenido);
    public Noticia buscarNoticia(Noticia notica);
    public List<Noticia> buscarNoticias();
    public void actualizarNoticia(Noticia noticia);
    public void eliminarNoticia(Noticia noticia);
    
    
    
    public void registrarPublicacion(Publicacion publicacion);
    public Publicacion buscarPublicacion(Publicacion publicacion);
    public List<Publicacion> buscarPublicaciones();
    public void actualizarPublicacion(Publicacion publicacion);
    public void eliminarPublicacion(Publicacion publicacion);
    
    
}
