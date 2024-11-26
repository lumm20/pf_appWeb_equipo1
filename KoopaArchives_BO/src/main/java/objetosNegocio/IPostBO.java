/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio;

import entidades_beans.FiltroBusquedaBean;
import entidades_beans.PostBean;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface IPostBO {
    public boolean subirNoticia(PostBean post);
    public boolean subirPublicacion(PostBean post);
    public boolean eliminarNoticia(PostBean post);
    public boolean eliminarPublicacion(PostBean post);
    public PostBean buscarNoticia(PostBean post);
    public PostBean buscarPublicacion(PostBean post);
    public List<PostBean> buscarNoticiasConFiltro(FiltroBusquedaBean filtro);
}
