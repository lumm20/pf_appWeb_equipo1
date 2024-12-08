package objetosNegocio;

import entidades_beans.ComentarioBean;
import entidades_beans.NoticiaBean;

/**
 *
 * @author karim
 */
public class Prueba {
    public static void main(String[] args) {
//        INoticiaBO bo = new NoticiaBO();
//        NoticiaBean bean = new NoticiaBean();
        IComentarioBO bo = new ComentarioBO();
        INoticiaBO bo2 = new NoticiaBO();

        NoticiaBean n = new NoticiaBean();
        n.setCodigo("N6770884989");
//        ComentarioBean c = new ComentarioBean();
//        c.setIdComentario("N0540101153");
        System.out.println(bo2.desanclar(n));
        
//        ComentarioBean bean = new ComentarioBean();

//    bean.setAutor("zoro");
//    bean.setCodigoNoticia("N5526980303");
////        bean.setContenido("Comentario tutancamon");
//        bean.setIdComentario("N0803444332");
//        NoticiaBean beanNoticia = new NoticiaBean();
//
//        if (bo.borrarComentario(bean)) {
//            System.out.println("Si edito");
//        } else {
//            System.out.println("No edito");
//        }
//    beanNoticia.setCodigo("N5526980303");
//    List<ComentarioBean> comentarios = bo.obtenerComentariosPorNoticia(beanNoticia);
//    for(var comentario: comentarios){
//        
//        System.out.println(comentario);
//    }
//    bo.registrarComentario(bean);

    }
}
