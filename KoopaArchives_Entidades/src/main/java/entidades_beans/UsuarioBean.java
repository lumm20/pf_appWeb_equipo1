package entidades_beans;

import java.io.Serializable;

/**
 *
 * @author karim
 */
public class UsuarioBean implements Serializable{
    private String username;
    private String password;
    private String rol;
    private ImagenBean imagen;
    
    public UsuarioBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImagenBean getImagen() {
        return imagen;
    }

    public void setImagen(ImagenBean imagen) {
        this.imagen = imagen;
    }
    
    
}
