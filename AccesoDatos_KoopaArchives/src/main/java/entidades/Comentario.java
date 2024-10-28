package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

public class Comentario {

    private ObjectId idComentario;
    private String contenido;
    private Date fecha_comentario;
    private ObjectId idNoticia;
    private Noticia noticia;

    public Comentario() {

    }

    public Comentario(String contenido, Date fechaComentario) {
        this.contenido = contenido;
        this.fecha_comentario = fechaComentario;
    }

    public ObjectId getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(ObjectId idComentario) {
        this.idComentario = idComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    public ObjectId getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(ObjectId idNoticia) {
        this.idNoticia = idNoticia;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idComentario=" + idComentario + ", contenido=" + contenido + ", fecha_comentario=" + fecha_comentario + ", idNoticia=" + idNoticia + ", noticia=" + noticia + '}';
    }

}
