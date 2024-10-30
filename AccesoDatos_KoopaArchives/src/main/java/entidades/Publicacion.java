package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author 
 */
public class Publicacion extends Post{
    private ObjectId _id;
    private String contenido;
    private String urlImg;

    public Publicacion() {
    }

    public Publicacion(String categoria, Date fechaPublicacion) {
        super(categoria, fechaPublicacion);
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    @Override
    public String toString() {
        return "Publicacion{" + "_id=" + _id + ", contenido=" + contenido + ", urlImg=" + urlImg + ", fechaCreacion=" + fechaCreacion + ", ultimaModificacion=" + ultimaModificacion+ '}';
    }

}