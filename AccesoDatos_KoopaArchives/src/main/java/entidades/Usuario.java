/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author luisa M
 */
public class Usuario {
    private ObjectId id;
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String contra;
    private String avatarUrl;
    private boolean admin;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, boolean admin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.admin = admin;
    }

    
    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String idUsuario, String nombre, String apellido, String contra) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contra = contra;
    }

    public void setId(ObjectId id){
        this.id = id;
    }
    
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public ObjectId getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append('}');
        return sb.toString();
    }
    
    public void printObjectId(){
        System.out.println("id: "+id.toHexString());
    }
}
