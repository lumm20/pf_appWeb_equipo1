package entidades_beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class ContenidoBean implements Serializable{
    private String descripcion;
    private String titulo;
    private List<SubtemaBean> subtemas;
    private ImagenBean imagen;

    public ContenidoBean() {
    }

    public ImagenBean getImagen() {
        return imagen;
    }

    public void setImagen(ImagenBean imagen) {
        this.imagen = imagen;
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

    public List<SubtemaBean> getSubtemas() {
        return subtemas;
    }

    public void setSubtemas(List<SubtemaBean> subtemas) {
        this.subtemas = subtemas;
    }

}
