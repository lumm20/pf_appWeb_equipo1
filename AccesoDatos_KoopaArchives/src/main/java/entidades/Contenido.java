package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase que representa el contenido de una noticia
 *
 * @author karim
 */
public class Contenido {

    private ObjectId idContenido;
    private String descripcion;
    private String titulo;
    private List<Subtema> subtemas;
    private String urlImg;

    /**
     * Constructor por defecto.
     */
    public Contenido() {
    }

    /**
     * Constructor que inicializa el contenido con una descripción, título,
     * subtemas y URL de imagen.
     *
     * @param descripcion Descripción del contenido.
     * @param titulo Título del contenido.
     * @param subtemas Subtemas del contenido.
     * @param urlImg URL de la imagen del contenido.
     */
    public Contenido(String descripcion, String titulo, List<Subtema> subtemas, String urlImg) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.subtemas = subtemas;
        this.urlImg = urlImg;
    }

    /**
     * Obtiene el identificador del contenido.
     *
     * @return Identificador del contenido.
     */
    public ObjectId getIdContenido() {
        return idContenido;
    }

    /**
     * Establece el identificador del contenido.
     *
     * @param idContenido Identificador del contenido.
     */
    public void setIdContenido(ObjectId idContenido) {
        this.idContenido = idContenido;
    }

    /**
     * Obtiene la descripción del contenido.
     *
     * @return Descripción del contenido.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del contenido.
     *
     * @param descripcion Descripción del contenido.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el título del contenido.
     *
     * @return Título del contenido.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del contenido.
     *
     * @param titulo Título del contenido.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene los subtemas del contenido.
     *
     * @return Subtemas del contenido.
     */
    public List<Subtema> getSubtemas() {
        return subtemas;
    }

    /**
     * Establece los subtemas del contenido.
     *
     * @param subtemas Subtemas del contenido.
     */
    public void setSubtemas(List<Subtema> subtemas) {
        this.subtemas = subtemas;
    }

    /**
     * Obtiene la URL de la imagen del contenido.
     *
     * @return URL de la imagen del contenido.
     */
    public String getUrlImg() {
        return urlImg;
    }

    /**
     * Establece la URL de la imagen del contenido.
     *
     * @param urlImg URL de la imagen del contenido.
     */
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    /**
     * Retorna una representación en cadena del contenido.
     *
     * @return Representación en cadena del contenido.
     */
    @Override
    public String toString() {
        return "Contenido{" + "descripcion=" + descripcion + ", titulo=" + titulo + ", subtemas=" + subtemas + '}';
    }

}
