package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author
 */
public class Discusion {
    private ObjectId idNoticia;
    private String categoria;
    private Date fecha_publicacion;
    private Date ultima_modificacion;
    private String contenido;
    private String url_img;

    public Discusion() {
    }
    
    public Discusion(String categoria, Date fecha_publicacion) {
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getUrl_img() {
        return url_img;
    }

    @Override
    public String toString() {
        return "Noticia{" + "categoria=" + categoria + ", fecha_publicacion=" + fecha_publicacion + ", contenido=" + contenido + '}';
    }
    
}
