/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luisa M
 */
public class ComentarioBean implements Serializable{
    private String contenido;
    private String idComentario;
    private String idPost;
    private String autor;
    private ImagenBean imagenAutor;
    private Date fechaCreacion;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ImagenBean getImagenAutor() {
        return imagenAutor;
    }

    public void setImagenAutor(ImagenBean imagenAutor) {
        this.imagenAutor = imagenAutor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ComentarioBean{");
        sb.append("contenido=").append(contenido);
        sb.append(", idComentario=").append(idComentario);
        sb.append(", idPost=").append(idPost);
        sb.append(", autor=").append(autor);
        sb.append(", imagenAutor=").append(imagenAutor.getNombreArchivo());
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append('}');
        return sb.toString();
    }
    
    
}
