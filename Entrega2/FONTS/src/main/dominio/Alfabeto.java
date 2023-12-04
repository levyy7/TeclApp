package main.dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Alfabeto extends Input{

    private String alphabet;

    /**
     * Constructora de alfabeto con todos sus parametros correspondientes
    */
    public Alfabeto(String nombre, String alphabet){
        super(nombre);
        this.alphabet = alphabet;
    }

    public Alfabeto(String[] alf) {
        nombre = alf[0];
        alphabet = alf[1];
    }

    public String getAlfabeto(){
        return alphabet;
    }

    public String getNombre(){
        return super.getNombre();
    }

    public int getSize(){
        return alphabet.length();
    }

    public String getType(){
        return "Alfabeto";
    }

    public void importInput(String name, String alphabet, String type){
        if(type == "Alfabeto") {
            super.setNombre(name);
            this.alphabet = alphabet;
        }
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void setAlfabeto(String newAlphabet){
        this.alphabet = newAlphabet;
    }

    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        info[1] = alphabet;
        return info;
    }
}

//mariona