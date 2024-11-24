package entidades;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author luisa M
 */
public class Usuario {
    private ObjectId _id;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String password;
    private String email;
    private String nombre;
    private Date cumpleanos;
    private String username;
    private Image imagen;
    private String rol;
    private String genero;

    public Usuario() {
    }
    
    
    public Usuario(String rol) {
        this.rol = rol;
    }

    public Usuario(String username, String password) {
        this.password = password;
        this.username = username;
    }


    public void setId(ObjectId id){
        this._id = id;
    }

    public void acompletar(String nombre, String apellidoPaterno, String apellidoMaterno, String username, String password, String email, Date cumpleanos, String genero) {
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.cumpleanos = cumpleanos;
        this.username = username;
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectId getId() {
        return _id;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCumpleanos() {
        return cumpleanos;
    }

    public void setCumpleanos(Date cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", nombre=").append(nombre);
        sb.append(", cumpleanos=").append(cumpleanos);
        sb.append(", username=").append(username);
        sb.append(", rol=").append(rol);
        sb.append(", genero=").append(genero);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
    public void printObjectId(){
        System.out.println("id: "+_id.toHexString());
    }
}
