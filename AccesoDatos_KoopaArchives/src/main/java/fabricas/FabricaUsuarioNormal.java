/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import entidades.Usuario;
import excepciones.PersistenciaException;
import fachadas.FachadaUsuarios;

/**
 *
 * @author luisa M
 */
public class FabricaUsuarioNormal implements FabricaUsuario{
    private FachadaUsuarios fachada;

    @Override
    public void registrarUsuario(Usuario usuario) throws PersistenciaException {
        fachada.registrarUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException {
        Usuario user = fachada.buscarUsuario(usuario);
        if (user != null) {
            return user;
        }
        throw new PersistenciaException("Usuario no encontrado");
    }
}
