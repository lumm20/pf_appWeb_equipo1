package entidades;

/**
 * @author José Karim Franco Valencia - 00000245138
 * @author Luisa Fernanda Morales Espinoza - 000000233450
 * @author Juventino López García - 00000248547
 * @author Paul Alejandro Vazquez Cervantes - 00000241400
 */

import java.util.Date;
import org.bson.types.ObjectId;

public class Comentario {

    private ObjectId _id;
    private String contenido;
    private Date fechaPublicacion;
    private Date fechaModificacion;
    private String idNoticia;
    private Noticia noticia;
    private String idUsuario;
    private Usuario usuario;
    private String idComentario;
    private boolean anclado;

    public Comentario() {

    }

    public Comentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public Comentario(String contenido, String idComentario) {
        this.contenido = contenido;
        this.idComentario = idComentario;
    }

    public Comentario(String contenido, Noticia noticia, Usuario usuario) {
        this.contenido = contenido;
        this.noticia = noticia;
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

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public boolean isAnclado() {
        return anclado;
    }

    public void setAnclado(boolean anclado) {
        this.anclado = anclado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
        return "Comentario{" + "idComentario=" + _id + ", contenido=" + contenido + ", fecha_comentario=" + fechaPublicacion + ", idNoticia=" + idNoticia + ", noticia=" + noticia + ", idUsuario=" + noticia +  ", usuario=" + usuario +'}';
    }

}
