package main.dominio;

import java.util.*;

public abstract class Input{

    protected String nombre;
 
    public Input() {
        
    }

    public Input(String nombre){
        this.nombre = nombre; 
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //retorna el nom del input
    public String getNombre(){
        return nombre;
    }

    //public abstract String getType();

    //assigna input. name es el nom del input, in es el contingut i type es si es un alfabet, llista etc;
    //public abstract void importInput(String name, String in, String type);

    public abstract int getSize();

    public abstract String[] toStringArray();
}

//mariona