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
    private UsuarioBean publicador;
    private ContenidoBean contenido;
    private String categoria;
    private String numPost;
    private Date fechaCreacion;
    private Date ultimaModificacion;
    private boolean anclada;
    
    public PostBean() {
    }

    public boolean isAnclada() {
        return anclada;
    }

    public void setAnclada(boolean anclada) {
        this.anclada = anclada;
    }

    public UsuarioBean getPublicador() {
        return publicador;
    }

    public void setPublicador(UsuarioBean publicador) {
        this.publicador = publicador;
    }

    public ContenidoBean getContenido() {
        return contenido;
    }

    public void setContenido(ContenidoBean contenido) {
        this.contenido = contenido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNumPost() {
        return numPost;
    }

    public void setNumPost(String numPost) {
        this.numPost = numPost;
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

    
}
