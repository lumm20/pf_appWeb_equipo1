/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author luisa M
 */
public class UsuarioDAO {
    private IConexion conexion;
    private MongoCollection<Usuario> usuariosNormales;
    private MongoCollection<Usuario> usuariosAdmin;
    
    public UsuarioDAO(){
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        usuariosNormales = baseDatos.getCollection("UsuariosNormales", Usuario.class);
        usuariosAdmin = baseDatos.getCollection("UsuariosAdmin", Usuario.class);
    }
    
    public Usuario agregarUsuario(Usuario usuario, boolean esAdmin) throws PersistenciaException{
        InsertOneResult result;
        try {
            if(esAdmin){
                result = usuariosAdmin.insertOne(usuario);
            }else{
                result = usuariosNormales.insertOne(usuario);
            }
            
            if(result != null){
                usuario.setAdmin(esAdmin);
                usuario.setId(result.getInsertedId().asObjectId().getValue());
                return usuario;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al agregar el usuario");
        }
    }
    
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException{
        try {
            Usuario user;
            if(usuario.isAdmin()){
                user = usuariosAdmin.find(Filters.eq("idUsuario",usuario.getIdUsuario())).first();
            }else{
                user = usuariosNormales.find(Filters.eq("idUsuario",usuario.getIdUsuario())).first();
            }
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al buscar el usuario");
        }
    }
}
