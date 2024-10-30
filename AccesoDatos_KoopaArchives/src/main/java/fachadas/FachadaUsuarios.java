/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import daos.IUsuarioDAO;
import daos.UsuarioAdminDAO;
import daos.UsuarioNormalDAO;
import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author luisa M
 */
public class FachadaUsuarios {
    private final IUsuarioDAO usuarioNormalDAO;
    private final IUsuarioDAO usuarioAdminDAO;
    
    public FachadaUsuarios(){
        this.usuarioAdminDAO = new UsuarioAdminDAO();
        this.usuarioNormalDAO = new UsuarioNormalDAO();
    }
    
    public void registrarUsuario(Usuario usuario) throws PersistenciaException{
        if(usuario.isAdmin()){
            usuarioAdminDAO.registrarUsuario(usuario);
        }else{
            usuarioNormalDAO.registrarUsuario(usuario);
        }
    } 
    
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException{
        Usuario user;
        if(usuario.isAdmin()){
            user = usuarioAdminDAO.buscarUsuario(usuario);
        }else{
            user = usuarioNormalDAO.buscarUsuario(usuario);
        }
        return user;
    }
    
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException{
        boolean logged;
        if(usuario.isAdmin()){
            logged = usuarioAdminDAO.iniciarSesion(usuario);
        }else{
            logged = usuarioNormalDAO.iniciarSesion(usuario);
        }
        return logged;
    }
}
