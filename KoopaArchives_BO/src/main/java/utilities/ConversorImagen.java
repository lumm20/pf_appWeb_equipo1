package utilities;

/**
 *
 * @author karim
 */

import entidades.Image;
import entidades_beans.ImagenBean;
import java.util.UUID;
import org.bson.types.Binary;

public class ConversorImagen {
    
    public static ImagenBean convertirAImagenBean(Image imagen){
        ImagenBean bean = new ImagenBean();
        bean.setFechaSubida(imagen.getFechaSubida());
        bean.setNombreArchivo(imagen.getNombreArchivo());
        bean.setTipoImagen(imagen.getTipoImagen());
        bean.setImageBytes(imagen.getContent().getData());
        
        return bean;
    }
    
    public static Image convertirAImagenDAO(ImagenBean bean){
        Image imagen = new Image();
        imagen.setContent(new Binary(bean.getImageBytes()));
        imagen.setFechaSubida(bean.getFechaSubida());
        imagen.setNombreArchivo(bean.getNombreArchivo());
        imagen.setTipoImagen(bean.getTipoImagen());
        
        return imagen;
    }
    
}
