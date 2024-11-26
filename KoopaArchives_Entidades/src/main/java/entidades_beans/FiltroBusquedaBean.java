/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_beans;

import java.util.Date;

/**
 *
 * @author luisa M
 */
public class FiltroBusquedaBean {
    private UsuarioBean creadorPost;
    private Date fechaDesde;
    private Date fechaHasta;
    private String categoria;

    public FiltroBusquedaBean() {
    }

    public UsuarioBean getCreadorPost() {
        return creadorPost;
    }

    public void setCreadorPost(UsuarioBean creadorPost) {
        this.creadorPost = creadorPost;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}
