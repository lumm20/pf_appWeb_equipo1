package entidades;

/**
 * Clase que representa un subtema de un contenido.
 *
 * Un subtema es una sección dentro de un contenido que puede tener un
 * subtítulo, descripción y URL de imagen.
 *
 * @author karim
 */
public class Subtema {
    private Image imagen;
    private String subtitulo;
    private String descripcion;
    private String url_img;

    /**
     * Constructor por defecto.
     */
    public Subtema() {
    }

    /**
     * Constructor que inicializa el subtema con un subtítulo, descripción y URL
     * de imagen.
     *
     * @param subtitulo Subtítulo del subtema.
     * @param descripcion Descripción del subtema.
     * @param url_img URL de la imagen del subtema.
     */
    public Subtema(String subtitulo, String descripcion, String url_img) {
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        this.url_img = url_img;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el subtítulo del subtema.
     *
     * @return Subtítulo del subtema.
     */
    public String getSubtitulo() {
        return subtitulo;
    }

    /**
     * Establece el subtítulo del subtema.
     *
     * @param subtitulo Subtítulo del subtema.
     */
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    /**
     * Obtiene la descripción del subtema.
     *
     * @return Descripción del subtema.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del subtema.
     *
     * @param descripcion Descripción del subtema.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la URL de la imagen del subtema.
     *
     * @return URL de la imagen del subtema.
     */
    public String getUrl_img() {
        return url_img;
    }

    /**
     * Establece la URL de la imagen del subtema.
     *
     * @param url_img URL de la imagen del subtema.
     */
    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    /**
     * Retorna una representación en cadena del subtema.
     *
     * @return Representación en cadena del subtema.
     */
    @Override
    public String toString() {
        return "Subtema{" + "subtitulo=" + subtitulo + ", descripcion=" + descripcion + ", url_img=" + url_img + '}';
    }

}
