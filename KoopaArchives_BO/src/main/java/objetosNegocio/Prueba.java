/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import entidades_beans.ImagenBean;
import entidades_beans.NoticiaBean;

/**
 *
 * @author karim
 */
public class Prueba {
    public static void main(String[] args) {
        NoticiaBO bo = new NoticiaBO();
        NoticiaBean bean = new NoticiaBean();
        bean.setCodigo("N6077345630");
        NoticiaBean noticia = bo.buscarNoticia(bean);
        
        System.out.println(noticia);
        System.out.println(noticia.getImagen());
    }
}
