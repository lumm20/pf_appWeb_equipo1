
import entidades_beans.UsuarioBean;
import objetosNegocio.IUsuarioBO;
import objetosNegocio.UsuarioBO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author karim
 */
public class prueba {
    public static void main(String[] args) {
        IUsuarioBO usuarioBO = new UsuarioBO();
        UsuarioBean frijolito = new UsuarioBean();
        frijolito.setUsername("luffy27");
        frijolito.setPassword("luffy");
        
        UsuarioBean frijolitoEncontrado = usuarioBO.iniciarSesion(frijolito);
        
        if(frijolitoEncontrado != null){
            System.out.println(frijolitoEncontrado.getRol());
            System.out.println(frijolitoEncontrado.getUsername());
        }
    }
   
}
