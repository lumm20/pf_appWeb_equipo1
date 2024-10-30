/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.MongoException;
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
public class UsuarioAdminDAO implements IUsuarioDAO{
    private final MongoCollection<Usuario> usuariosAdmin;
    private final IConexion conexion;
    
    
    public UsuarioAdminDAO() {
        this.conexion = Conexion.getInstance();
        MongoDatabase baseDatos = this.conexion.obtenerBaseDatos();
        usuariosAdmin = baseDatos.getCollection("UsuariosAdmin", Usuario.class);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws PersistenciaException {
        try {
            if(buscarUsuario(usuario) != null)
                throw new PersistenciaException("ya hay un usuario registrado con ese Id");
            usuariosAdmin.insertOne(usuario);
            usuario.setAdmin(true);
            return usuario;
        } catch (MongoException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al agregar el usuario");
        }
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException {
        try {
            Usuario user = usuariosAdmin.find(Filters.eq("idUsuario", usuario.getIdUsuario())).first();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("error al buscar el usuario");
        }
    }
}
