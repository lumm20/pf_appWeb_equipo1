/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class PostBean implements Serializable{
    private ImagenBean imagen;
    private UsuarioBean autor;
    private String texto;
    private String categoria;
    private String codigo;
    private Date fechaCreacion;
    private Date ultimaModificacion;
    private boolean anclada;
    private int likes;
    private List<ComentarioBean> comentarios;
    
    public PostBean() {
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<ComentarioBean> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioBean> comentarios) {
        this.comentarios = comentarios;
    }

    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ImagenBean getImagen() {
        return imagen;
    }

    public void setImagen(ImagenBean imagen) {
        this.imagen = imagen;
    }

    public UsuarioBean getAutor() {
        return autor;
    }

    public void setAutor(UsuarioBean autor) {
        this.autor = autor;
    }

    public boolean isAnclada() {
        return anclada;
    }

    public void setAnclada(boolean anclada) {
        this.anclada = anclada;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", autor=").append(autor);
        sb.append(", texto=").append(texto);
        sb.append(", categoria=").append(categoria);
        sb.append(", codigo=").append(codigo);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", anclada=").append(anclada);
        sb.append(", imagen=").append(imagen);
        return sb.toString();
    }

}
