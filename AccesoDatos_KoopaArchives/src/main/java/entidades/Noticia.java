package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author karim
 */
public class Noticia extends Post{
    private ObjectId _id;
    private boolean anclada;
    private ObjectId idContenido;
    private Contenido contenido;

    public Noticia() {
    }

    
    
    public Noticia(String numPost) {
        this.numPost = numPost;
    }
    
    

    public Noticia(String categoria, Date fechaPublicacion) {
        super(categoria, fechaPublicacion);
    }
    
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public boolean isAnclada() {
        return anclada;
    }

    public void setAnclada(boolean anclada) {
        this.anclada = anclada;
    }

    public ObjectId getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(ObjectId idContenido) {
        this.idContenido = idContenido;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Noticia{" + "idNoticia=" + _id + ", numPost=" + numPost  + ", categoria=" + categoria  + ", fechaCreacion=" + fechaCreacion + ", ultimaModificacion=" + ultimaModificacion + ", anclada=" + anclada + ", idContenido=" + idContenido + ", contenido=" + contenido + '}';
    }
    
    
}
