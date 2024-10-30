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
import java.util.Random;

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
            
            usuario.setAdmin(true);
            usuario.setIdUsuario(generarNumeroAleatorio());
            usuariosAdmin.insertOne(usuario);
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

    @Override
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException{
        Usuario user = buscarUsuario(usuario);
        if(user != null){
            String contra = usuario.getContra();
            if(contra != null){
                return user.equals(contra);
            }throw new PersistenciaException("Debe ingresar una contraseña");
        }
        throw new PersistenciaException("El usuario que ingreso no esta registrado");
    }
      private String generarNumeroAleatorio() {
        Random random = new Random();

         long numero = random.nextLong() % 10000000000L; // Limita el rango a 10 cifras

        // Formatea el número a una cadena de 10 caracteres, rellenando con ceros a la izquierda
        return "N"+String.format("%010d", Math.abs(numero)); // Usar Math.abs para evitar números negativos
    }
}
