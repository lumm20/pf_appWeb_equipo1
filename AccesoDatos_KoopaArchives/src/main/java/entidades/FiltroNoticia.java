package entidades;

import java.util.Date;

/**
 *
 * @author karim
 */
public class FiltroNoticia {

    private Date fechaDesde;
    private Date fechaHasta;
    private String categoria;
    private String titulo;

    public FiltroNoticia(Date fechaDesde, Date fechaHasta, String categoria, String titulo) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.categoria = categoria;
        this.titulo = titulo;
    }


    public FiltroNoticia() {
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    
}
