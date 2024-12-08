package entidades;

/**
 * @author José Karim Franco Valencia - 00000245138
 * @author Luisa Fernanda Morales Espinoza - 000000233450
 */

import java.util.Date;
import org.bson.types.ObjectId;

public class Comentario {

    private ObjectId _id;
    private String contenido;
    private Date fechaPublicacion;
    private Date fechaModificacion;
    private String codigoNoticia;
    private String autor;
    private Usuario usuario;
    private String idComentario;
    private boolean anclado;

    public Comentario() {

    }

    public Comentario(String codigoNoticia) {
        this.codigoNoticia = codigoNoticia;
    }

    public Comentario(String contenido, String idComentario) {
        this.contenido = contenido;
        this.idComentario = idComentario;
    }

    public Comentario(String contenido, Usuario usuario) {
        this.contenido = contenido;
        this.usuario = usuario;
        this.fechaPublicacion = new Date();
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

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCodigoNoticia() {
        return codigoNoticia;
    }

    public void setCodigoNoticia(String codigoNoticia) {
        this.codigoNoticia = codigoNoticia;
    }


    public boolean isAnclado() {
        return anclado;
    }

    public void setAnclado(boolean anclado) {
        this.anclado = anclado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comentario{");
        sb.append(", idComentario=").append(idComentario);
        sb.append(", anclado=").append(anclado);
        sb.append(", usuario=").append(usuario);
        sb.append("contenido=").append(contenido);
        sb.append(", fechaPublicacion=").append(fechaPublicacion);
        sb.append(", fechaModificacion=").append(fechaModificacion);
        sb.append('}');
        return sb.toString();
    }
    

}
