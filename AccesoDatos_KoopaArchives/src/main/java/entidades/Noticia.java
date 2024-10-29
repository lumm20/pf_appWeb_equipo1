package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author karim
 */
public class Noticia {
    private ObjectId idNoticia;
    private String categoria;
    private Date fecha_publicacion;
    private Date ultima_modificacion;
    private boolean anclada;
    private ObjectId idContenido;
    private Contenido contenido;

    public Noticia() {
    }

    
    
    public Noticia(String categoria, Date fecha_publicacion) {
        this.categoria = categoria;
        this.fecha_publicacion = fecha_publicacion;
    }

    
    
    
    public ObjectId getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(ObjectId idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Date getUltima_modificacion() {
        return ultima_modificacion;
    }

    public void setUltima_modificacion(Date ultima_modificacion) {
        this.ultima_modificacion = ultima_modificacion;
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
        return "Noticia{" + "categoria=" + categoria + ", fecha_publicacion=" + fecha_publicacion + ", contenido=" + contenido + '}';
    }
    
    
    
    
}
