package fachadas;

import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author karim
 */
public interface IFacadeUsuario {
    public boolean registrarUsuario(Usuario usuario) throws PersistenciaException;
    public Usuario existeUsuario(Usuario usuario) throws PersistenciaException;
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException;
    public Usuario iniciarSesion(Usuario usuario) throws PersistenciaException;
    public Usuario hardcodearAdmin(Usuario usuario) throws PersistenciaException ;
}
