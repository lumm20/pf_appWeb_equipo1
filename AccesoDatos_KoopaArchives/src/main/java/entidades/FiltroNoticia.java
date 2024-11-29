package entidades;

import java.util.Date;

/**
 * Clase que representa un filtro para buscar noticias.
 *
 * Un filtro de noticias puede tener una fecha de inicio, una fecha de fin, una
 * categoría y un título.
 *
 * @author karim
 */
public class FiltroNoticia {

    private Date fechaDesde;
    private Date fechaHasta;
    private boolean inicio;
    private String categoria;
    private String titulo;
    private boolean destacada;

    /**
     * Constructor que inicializa el filtro con una fecha de inicio, una fecha
     * de fin, una categoría y un título.
     *
     * @param fechaDesde Fecha de inicio del filtro.
     * @param fechaHasta Fecha de fin del filtro.
     * @param categoria Categoría del filtro.
     * @param titulo Título del filtro.
     */
    public FiltroNoticia(Date fechaDesde, Date fechaHasta, String categoria, String titulo) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.categoria = categoria;
        this.titulo = titulo;
    }

    /**
     * Constructor por defecto.
     */
    public FiltroNoticia() {
    }

    /**
     * Obtiene la fecha de inicio del filtro.
     *
     * @return Fecha de inicio del filtro.
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Establece la fecha de inicio del filtro.
     *
     * @param fechaDesde Fecha de inicio del filtro.
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Obtiene la fecha de fin del filtro.
     *
     * @return Fecha de fin del filtro.
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Establece la fecha de fin del filtro.
     *
     * @param fechaHasta Fecha de fin del filtro.
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Obtiene la categoría del filtro.
     *
     * @return Categoría del filtro.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del filtro.
     *
     * @param categoria Categoría del filtro.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el título del filtro.
     *
     * @return Título del filtro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del filtro.
     *
     * @param titulo Título del filtro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDestacada() {
        return destacada;
    }

    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }

    public boolean esPaginaInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    
    
}
