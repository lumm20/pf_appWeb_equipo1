/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachadas;

import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author karim
 */
public interface IFacadeUsuario {
    public void registrarUsuario(Usuario usuario) throws PersistenciaException;
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException;
    public Usuario iniciarSesion(Usuario usuario) throws PersistenciaException;
    public Usuario hardcodearAdmin(Usuario usuario) throws PersistenciaException ;
}
