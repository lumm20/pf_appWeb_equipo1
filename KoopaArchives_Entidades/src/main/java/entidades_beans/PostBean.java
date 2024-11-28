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
public class PostBean implements Serializable{
    private ImagenBean imagen;
    private UsuarioBean autor;
    private String texto;
    private String categoria;
    private String codigo;
    private Date fechaCreacion;
    private Date ultimaModificacion;
    private boolean anclada;
    
    public PostBean() {
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
        return "PostBean{" + "autor= "+autor+ ", categoria=" + categoria + ", numPost=" + codigo + ", fechaCreacion=" + fechaCreacion + ", ultimaModificacion=" + ultimaModificacion + ", anclada=" + anclada + '}';
    }
}
