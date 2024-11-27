package entidades_beans;

import java.util.Date;

/**
 *
 * @author karim
 */
public class ImagenBean {
    private byte[] imageBytes;
    private String url;
    private String tipoImagen;
    private Date fechaSubida;
    private String nombreArchivo;
    
    public ImagenBean() {
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImagenBean{");
        sb.append("imageBytes=").append(imageBytes);
        sb.append(", tipoImagen=").append(tipoImagen);
        sb.append(", fechaSubida=").append(fechaSubida);
        sb.append(", nombreArchivo=").append(nombreArchivo);
        sb.append(", url=").append(url);
        sb.append('}');
        return sb.toString();
    }
}
