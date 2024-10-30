/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachadas;

import entidades.Comentario;
import entidades.Noticia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author karim
 */
public interface IFacadeComentario {
     public void registrarComentario(Comentario comentario);
    public Comentario buscarComentario(Comentario Comentario);
    public void eliminarComentario(Comentario comentario);
    public void anclarComentario(Comentario comentario);
    public void editarComentario(Comentario comentario);
    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia);
}
