package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author karim
 */
public class Contenido {
    private ObjectId idContenido;
    private String descripcion;
    private String titulo;
    private List<Subtema> subtemas;
    private String url_img;

    public Contenido() {
    }

    
    
    public Contenido(String descripcion, String titulo, List<Subtema> subtemas, String url_img) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.subtemas = subtemas;
        this.url_img = url_img;
    }

    public ObjectId getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(ObjectId idContenido) {
        this.idContenido = idContenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Subtema> getSubtemas() {
        return subtemas;
    }

    public void setSubtemas(List<Subtema> subtemas) {
        this.subtemas = subtemas;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    @Override
    public String toString() {
        return "Contenido{" + "descripcion=" + descripcion + ", titulo=" + titulo + ", subtemas=" + subtemas + '}';
    }
    
    
    
    
}
