/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fabricas;

import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author luisa M
 */
public interface FabricaUsuario {
    public void registrarUsuario(Usuario usuario)throws PersistenciaException;
    public Usuario buscarUsuario(Usuario usuario)throws PersistenciaException;
}
