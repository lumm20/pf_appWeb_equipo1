package entidades_beans;

import java.io.Serializable;

/**
 *
 * @author luisa M
 */
public class SubtemaBean implements Serializable{
    private String subtitulo;
    private String descripcion;
    private ImagenBean imagen;

    public SubtemaBean() {
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

    public ImagenBean getImagen() {
        return imagen;
    }

    public void setImagen(ImagenBean imagen) {
        this.imagen = imagen;
    }
}
