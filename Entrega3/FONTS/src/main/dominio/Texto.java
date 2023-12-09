package main.dominio;

import java.io.FileNotFoundException;
import java.util.*;

public class Texto extends TLP{

    private String text;


    public Texto() {

    }

    public Texto(String nombre, String text){
        this.nombre = nombre;
        this.text = text;
    }

    public Texto(String[] tex) {
        nombre = tex[0];
        text = tex[1];
    }
    

    public String getTexto(){
        return text;
    }

    public void setTexto(String txt) {
        this.text = txt;
    }

    public int getSize(){
        return text.length();
    }

    /*public void importInput(String name, String in, String type) {
        if(type == "texto") {
            super.setNombre(name);
            this.text = in;
        }
    }*/


    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        info[1] = text;
        return info;
    }
}

//mariona