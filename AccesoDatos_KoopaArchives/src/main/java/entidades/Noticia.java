package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * Clase que representa una noticia en el sistema
 *
 * @author karim
 */
public class Noticia extends Post {

    private ObjectId _id;
    private boolean anclada;
    private ObjectId idContenido;
    private Contenido contenido;

    /**
     * Constructor por defecto.
     */
    public Noticia() {
    }

    /**
     * Constructor que inicializa la noticia con un número de post.
     *
     * @param numPost Número de post.
     */
    public Noticia(String numPost) {
        this.numPost = numPost;
    }

    /**
     * Constructor que inicializa la categoría y la fecha de publicación de la
     * noticia.
     *
     * @param categoria Categoría de la noticia.
     * @param fechaPublicacion Fecha en que se publicó la noticia.
     */
    public Noticia(String categoria, Date fechaPublicacion) {
        super(categoria, fechaPublicacion);
    }

    /**
     * Obtiene el identificador único de la noticia.
     *
     * @return Identificador único de la noticia.
     */
    public ObjectId getId() {
        return _id;
    }

    /**
     * Establece el identificador único de la noticia.
     *
     * @param _id Identificador único de la noticia.
     */
    public void setId(ObjectId _id) {
        this._id = _id;
    }

    /**
     * Indica si la noticia está anclada.
     *
     * @return true si la noticia está anclada, false en caso contrario.
     */
    public boolean isAnclada() {
        return anclada;
    }

    /**
     * Establece si la noticia está anclada.
     *
     * @param anclada true si la noticia está anclada, false en caso contrario.
     */
    public void setAnclada(boolean anclada) {
        this.anclada = anclada;
    }

    /**
     * Obtiene el identificador del contenido asociado a la noticia.
     *
     * @return Identificador del contenido asociado a la noticia.
     */
    public ObjectId getIdContenido() {
        return idContenido;
    }

    /**
     * Establece el identificador del contenido asociado a la noticia.
     *
     * @param idContenido Identificador del contenido asociado a la noticia.
     */
    public void setIdContenido(ObjectId idContenido) {
        this.idContenido = idContenido;
    }

    /**
     * Obtiene el contenido asociado a la noticia.
     *
     * @return Contenido asociado a la noticia.
     */
    public Contenido getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido asociado a la noticia.
     *
     * @param contenido Contenido asociado a la noticia.
     */
    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    /**
     * Retorna una representación en cadena de la noticia.
     *
     * @return Representación en cadena de la noticia.
     */
    @Override
    public String toString() {
        return "Noticia{" + "idNoticia=" + _id + ", numPost=" + numPost + ", categoria=" + categoria + ", fechaCreacion=" + fechaCreacion + ", ultimaModificacion=" + ultimaModificacion + ", anclada=" + anclada + ", idContenido=" + idContenido + ", contenido=" + contenido + '}';
    }
}
