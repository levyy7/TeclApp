package main.dominio;

import java.io.FileNotFoundException;
import java.util.*;

public class Texto extends TLP{

    private String text;

    public Texto(String nombre, String text){
        super(nombre);
        this.text = text;
    }

    public String getNombre(){
        return super.getNombre();
    }

    public String getTexto(){
        return text;
    }

    public String getType(){
        return "Texto";
    }

    public void importInput(String name, String in, String type) {
        if(type == "texto") {
            super.setNombre(name);
            this.text = txt;
        }
    }

    public void setTexto(String txt) {
        this.text = txt;
    }

    public void modify(String newtext){
        this.text = newtext;
    }

    public void print(){
        System.out.println(text + "\n");
    }
    
}

//mariona