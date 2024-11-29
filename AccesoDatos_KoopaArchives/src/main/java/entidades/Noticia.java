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
    private String titulo;
    private boolean destacada;
    private String contenido;
    private Image imagen;
    private String autor;

    /**
     * Constructor por defecto.
     */
    public Noticia() {
    }

    /**
     * Constructor que inicializa la noticia con un número de post.
     *
     * @param codigo Número de post.
     */
    public Noticia(String codigo) {
        this.codigo = codigo;
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
     * Indica si la noticia está destacada.
     *
     * @return true si la noticia está destacada, false en caso contrario.
     */
    public boolean isDestacada() {
        return destacada;
    }

    /**
     * Establece si la noticia está destacada.
     *
     * @param destacada true si la noticia está destacada, false en caso contrario.
     */
    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }


    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Noticia{");
        sb.append("_id=").append(_id);
        sb.append(", numPost=").append(codigo);
        sb.append(", descripcion=").append(contenido);
        sb.append(", titulo=").append(titulo);
        sb.append(", destacada=").append(destacada);
        sb.append(", imagen=").append(imagen);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    /**
     * Retorna una representación en cadena de la noticia.
     *
     * @return Representación en cadena de la noticia.
     */
    
}
