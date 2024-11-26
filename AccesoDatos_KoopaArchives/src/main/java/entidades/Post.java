package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * Clase abstracta que representa un post en el sistema.
 *
 *
 * @author karim
 */
public abstract class Post {
    private String usernamePublicador;
    protected String categoria;
    protected Date fechaCreacion;
    protected Date ultimaModificacion;
    protected String numPost;
    
    /**
     * Constructor por defecto.
     */
    public Post() {
    }

    /**
     * Constructor que inicializa la categoría y la fecha de creación del post.
     *
     * @param categoria Categoría del post.
     * @param fechaPublicacion Fecha en que se creó el post.
     */
    public Post(String categoria, Date fechaPublicacion) {
        this.categoria = categoria;
        this.fechaCreacion = fechaPublicacion;
    }

    public String getUsernamePublicador() {
        return usernamePublicador;
    }

    public void setUsernamePublicador(String username) {
        this.usernamePublicador = username;
    }

    /**
     * Obtiene la categoría del post.
     *
     * @return Categoría del post.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del post.
     *
     * @param categoria Categoría del post.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la fecha en que se creó el post.
     *
     * @return Fecha en que se creó el post.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha en que se creó el post.
     *
     * @param fechaCreacion Fecha en que se creó el post.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene la fecha en que se modificó por última vez el post.
     *
     * @return Fecha en que se modificó por última vez el post.
     */
    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    /**
     * Establece la fecha en que se modificó por última vez el post.
     *
     * @param ultimaModificacion Fecha en que se modificó por última vez el
     * post.
     */
    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    /**
     * Obtiene el número de post.
     *
     * @return Número de post.
     */
    public String getNumPost() {
        return numPost;
    }

    /**
     * Establece el número de post.
     *
     * @param numPost Número de post.
     */
    public void setNumPost(String numPost) {
        this.numPost = numPost;
    }
}
