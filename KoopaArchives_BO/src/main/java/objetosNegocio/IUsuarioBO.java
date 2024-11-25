package objetosNegocio;

import entidades_beans.UsuarioBean;
import entidades_beans.UsuarioRegistroBean;



/**
 *
 * @author karim
 */
public interface IUsuarioBO {
    public boolean registrarUsuario(UsuarioRegistroBean usuario);
    public UsuarioRegistroBean buscarUsuario(UsuarioRegistroBean usuario);
    public UsuarioBean iniciarSesion(UsuarioBean usuario);
    public UsuarioRegistroBean hardcodearAdmin(UsuarioRegistroBean usuario);
}
