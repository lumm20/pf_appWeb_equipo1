package factories;

import entidades.Usuario;

/**
 *
 * @author karim
 */
public class FactoryUser {
    
    public static final int ADMIN = 1;
    public static final int NORMAL = 2;

    
    public static Usuario crearUsuario(int tipoUsuario) {
        switch (tipoUsuario) {
            case ADMIN -> {
                return new Usuario("Admin");
            }
            case NORMAL -> {
                return new Usuario("Normal");
            }
            default ->
                throw new IllegalArgumentException("Tipo de usuario no válido: " + tipoUsuario);
        }
    }
}
