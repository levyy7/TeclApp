package main.dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Alfabeto extends Input{

    private String letras;

    /**
     * Constructora de alfabeto con todos sus parametros correspondientes
    */
    public Alfabeto(String nombre, String letras){
        this.nombre = nombre;
        this.letras = letras;
    }

    public Alfabeto(String[] alf) {
        nombre = alf[0];
        letras = alf[1];
    }

    public String getLetras(){
        return letras;
    }

    public void setLetras(String letras){
        this.letras = letras;
    }

    public int getSize(){
        return letras.length();
    }

    /*public void importInput(String name, String alphabet, String type){
        if(type == "Alfabeto") {
            super.setNombre(name);
            this.alphabet = alphabet;
        }
    }*/
    
    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        info[1] = letras;
        return info;
    }
}

//mariona