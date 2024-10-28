package entidades;

/**
 *
 * @author karim
 */
public class Subtema {
    private String subtitulo;
    private String descripcion;
    private String url_img;

    public Subtema() {
    }

    
    
    public Subtema(String subtitulo, String descripcion, String url_img) {
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        this.url_img = url_img;
    }

    
    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    @Override
    public String toString() {
        return "Subtema{" + "subtitulo=" + subtitulo + ", descripcion=" + descripcion + ", url_img=" + url_img + '}';
    }
    
    
}
