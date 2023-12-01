package main.dominio;

import java.io.FileNotFoundException;
import java.util.*;

public class Texto extends TLP{

    private String text;

    public Texto(String nombre, String text){
        super(nombre);
        this.text = text;
    }

    public Texto(String[] tex) {
        nombre = tex[0];
        text = tex[1];
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

    public int getSize(){
        return text.length();
    }

    public void importInput(String name, String in, String type) {
        if(type == "texto") {
            super.setNombre(name);
            this.text = in;
        }
    }

    public void setTexto(String txt) {
        this.text = txt;
    }

    public void setNombre(String newName){
        super.setNombre(newName);
    }

    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        info[1] = text;
        return info;
    }
}

//mariona