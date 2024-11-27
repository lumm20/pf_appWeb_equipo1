package entidades_beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luisa M
 */
public class NoticiaBean implements Serializable{
    private String titulo;
    private String contenido;
    private String categoria;
    private boolean destacada;
    private ImagenBean imagen;
    private Date fechaCreacion;
    private String codigo;
    private String autor;

    public NoticiaBean() {
    }

    public boolean isDestacada() {
        return destacada;
    }

    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ImagenBean getImagen() {
        return imagen;
    }

    public void setImagen(ImagenBean imagen) {
        this.imagen = imagen;
    }
    
        public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        sb.append("NoticiaBean{");
        sb.append("anclada=").append(destacada);
        sb.append(", titulo=").append(titulo);
        sb.append(", imagen=").append(imagen);
        sb.append(", contenido=").append(contenido);
        sb.append(", categoria=").append(categoria);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append('}');
        return sb.toString();
    }
}
