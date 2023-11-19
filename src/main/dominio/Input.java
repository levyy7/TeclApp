package main.dominio;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Input{

    private String nombre;

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

    public String getType(){
        return "ERROR";
    }

    public void importInput(String name, String in, String type){
        
    }   

    public int getSize(){
        return -1;
    }

}

//mariona