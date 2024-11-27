package utilities;

/**
 *
 * @author karim
 */

import entidades.Image;
import entidades_beans.ImagenBean;
import java.util.Base64;
import org.bson.types.Binary;

public class ConversorImagen {
    
    public static ImagenBean convertirAImagenBean(Image imagen){
        ImagenBean bean = new ImagenBean();
        bean.setNombreArchivo(imagen.getNombreArchivo());
        bean.setTipoImagen(imagen.getTipoImagen());
        bean.setImageBytes(imagen.getContent().getData());
        
        return bean;
    }
    public static ImagenBean convertirAImagenBean2(Image imagen){
        ImagenBean bean = new ImagenBean();
        bean.setNombreArchivo(imagen.getNombreArchivo());
        bean.setTipoImagen(imagen.getTipoImagen());
        bean.setImageBytes(imagen.getContent().getData());
        String base64Image = Base64.getEncoder().encodeToString(bean.getImageBytes());
        bean.setUrl(base64Image);
        return bean;
    }
    
    public static Image convertirAImagenDAO(ImagenBean bean){
        Image imagen = new Image();
        imagen.setContent(new Binary(bean.getImageBytes()));
        imagen.setNombreArchivo(bean.getNombreArchivo());
        imagen.setTipoImagen(bean.getTipoImagen());
        
        return imagen;
    }
    
}
