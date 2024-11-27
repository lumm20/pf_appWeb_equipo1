package entidades;

import org.bson.types.Binary;

public class Image  {
    private String nombreArchivo;
    private String tipoImagen;
    private Binary content;

    public Image() {
    }



    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }

    public String getTipoImagen() { return tipoImagen; }
    public void setTipoImagen(String tipoImagen) { this.tipoImagen = tipoImagen; }


    public Binary getContent() { return content; }
    public void setContent(Binary content) { this.content = content; }

    // Método para convertir Document a Image
    public static Image fromDocument(org.bson.Document doc) {
        Image image = new Image();
        image.setNombreArchivo(doc.getString("nombreArchivo"));
        image.setTipoImagen(doc.getString("tipoImagen"));
        image.setContent(doc.get("content", Binary.class));
        return image;
    }

    // Método para convertir Image a Document
    public org.bson.Document toDocument() {
        return new org.bson.Document()
            .append("nombreArchivo", this.nombreArchivo)
            .append("tipoImagen", this.tipoImagen)
            .append("content", this.content);
    }

    @Override
    public String toString() {
        return "Image{" + ", filename=" + nombreArchivo + ", contentType=" + tipoImagen + ", content=" + content + '}';
    }
    
    
}
