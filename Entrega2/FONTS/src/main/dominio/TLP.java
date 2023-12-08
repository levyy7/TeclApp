package main.dominio;

import java.util.*;

//textoListaPalabras
public abstract class TLP extends Input {

    public TLP() {
        
    }

    public TLP(String nombre){
        this.nombre = nombre;
    }

    /*

    public String getNombre(){
        return super.getNombre();
    }

    public void setNombre(String nombre){
        super.setNombre(nombre);
    }
    */

    public abstract String[] toStringArray();
}

//mariona