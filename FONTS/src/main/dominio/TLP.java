package main.dominio;

import java.util.*;

//textoListaPalabras
public class TLP extends Input {

    public TLP(String nombre){
        super(nombre);
    }

    public String getNombre(){
        return super.getNombre();
    }

    public void setNombre(String nombre){
        super.setNombre(nombre);
    }
    /* 
    public void readFromFile(String file){
        String in = "";
        super.readFromFile(file, in);
        this.alphabet = in;
    }

    public void readFromType(String in){
        super.readFromType(in);
        this.alphabet = in;
    }*/
}

//mariona