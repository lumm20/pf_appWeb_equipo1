package entidades_beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author karim
 */
public class ComentarioBean implements Serializable{
    private String contenido;
    private Date fechaPublicacion;
    private Date fechaModificacion;
    private String codigoNoticia;
    private ImagenBean perfilUsuario;
    private String autor;
    private String idComentario;
    private boolean anclado;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCodigoNoticia() {
        return codigoNoticia;
    }

    public void setCodigoNoticia(String codigoNoticia) {
        this.codigoNoticia = codigoNoticia;
    }

    public ImagenBean getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(ImagenBean perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public boolean getAnclado() {
        return anclado;
    }

    public void setAnclado(boolean anclado) {
        this.anclado = anclado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ComentarioBean{");
        sb.append("contenido=").append(contenido);
        sb.append(", idComentario=").append(idComentario);
        sb.append(", anclado=").append(anclado);
        sb.append(", codigoNoticia=").append(codigoNoticia);
        sb.append(", autor=").append(autor);
        sb.append('}');
        return sb.toString();
    }
    
}
