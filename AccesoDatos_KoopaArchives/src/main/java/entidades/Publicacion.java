package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * Representa una publicación en la aplicación.
 *
 * @author
 */
public class Publicacion extends Post {

    private ObjectId _id;
    private String contenido;
    private String urlImg;

    /**
     * Constructor por defecto.
     */
    public Publicacion() {
    }

    /**
     * Constructor que establece la categoría y la fecha de publicación.
     *
     * @param categoria Categoría de la publicación.
     * @param fechaPublicacion Fecha de publicación.
     */
    public Publicacion(String categoria, Date fechaPublicacion) {
        super(categoria, fechaPublicacion);
    }

    /**
     * Obtiene el identificador único de la publicación.
     *
     * @return Identificador único de la publicación.
     */
    public ObjectId getId() {
        return _id;
    }

    /**
     * Establece el identificador único de la publicación.
     *
     * @param _id Identificador único de la publicación.
     */
    public void setId(ObjectId _id) {
        this._id = _id;
    }

    /**
     * Obtiene el contenido de la publicación.
     *
     * @return Contenido de la publicación.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido de la publicación.
     *
     * @param contenido Contenido de la publicación.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene la URL de la imagen asociada a la publicación.
     *
     * @return URL de la imagen asociada a la publicación.
     */
    public String getUrlImg() {
        return urlImg;
    }

    /**
     * Establece la URL de la imagen asociada a la publicación.
     *
     * @param urlImg URL de la imagen asociada a la publicación.
     */
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    /**
     * Retorna una representación en cadena de la publicación.
     *
     * @return Representación en cadena de la publicación.
     */
    @Override
    public String toString() {
        return "Publicacion{" + "_id=" + _id + ", contenido=" + contenido + ", urlImg=" + urlImg + ", fechaCreacion=" + fechaCreacion + ", ultimaModificacion=" + ultimaModificacion + '}';
    }

}
