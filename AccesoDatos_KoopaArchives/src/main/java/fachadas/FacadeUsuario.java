package fachadas;

import daos.IUsuarioDAO;
import daos.UsuarioDAO;
import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author luisa M
 */
public class FacadeUsuario implements IFacadeUsuario{

    private final IUsuarioDAO usuarioDAO;

    public FacadeUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws PersistenciaException {
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario iniciarSesion(Usuario usuario) throws PersistenciaException {
        return usuarioDAO.iniciarSesion(usuario);
    }

    @Override
    public Usuario hardcodearAdmin(Usuario usuario) throws PersistenciaException {
        
        return usuarioDAO.registrarUsuario(usuario);
    }

    
}
