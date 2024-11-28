package entidades_beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luisa M
 */
public class NoticiaBean extends PostBean implements Serializable{
    private String titulo;
    private boolean destacada;

    public NoticiaBean() {
    }

    public boolean isDestacada() {
        return destacada;
    }

    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NoticiaBean{");
        sb.append("anclada=").append(destacada);
        sb.append(", titulo=").append(titulo);
        sb.append('}');
        return sb.toString();
    }
}
