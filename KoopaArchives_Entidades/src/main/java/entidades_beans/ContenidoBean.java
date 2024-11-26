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
    private String urlImg;

    public ContenidoBean() {
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

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
    
    
}
