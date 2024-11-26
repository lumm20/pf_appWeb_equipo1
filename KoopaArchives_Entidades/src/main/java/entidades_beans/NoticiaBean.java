/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades_beans;

import java.io.Serializable;

/**
 *
 * @author luisa M
 */
public class NoticiaBean extends PostBean implements Serializable{
    private boolean anclada;

    public NoticiaBean() {
    }

    public boolean isAnclada() {
        return anclada;
    }

    public void setAnclada(boolean anclada) {
        this.anclada = anclada;
    }

}
