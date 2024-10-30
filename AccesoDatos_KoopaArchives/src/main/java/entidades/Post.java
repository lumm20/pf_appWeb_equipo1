package entidades;

import java.util.Date;

/**
 *
 * @author karim
 */
public abstract class Post {
    protected String categoria;
    protected Date fechaCreacion;
    protected Date ultimaModificacion;
    protected String numPost;

    public Post() {
    }

    public Post(String categoria, Date fechaPublicacion) {
        this.categoria = categoria;
        this.fechaCreacion = fechaPublicacion;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getNumPost() {
        return numPost;
    }

    public void setNumPost(String numPost) {
        this.numPost = numPost;
    }
}
