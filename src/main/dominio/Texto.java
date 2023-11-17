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

    public void readFromFile(String file) throws FileNotFoundException{
        String in = "";
        super.readFromFile(file, in);
        this.text = in;
    }

    public void readFromType(String in){
        super.readFromType(in);
        this.text = in;
    }


    public void modify(String newtext){
        this.text = newtext;
        //sha de cridar a fer una nova llista de caracter amb frequencia de caracters?????
    }

    public void print(){
        System.out.println(text + "\n");
    }
    
}

//mariona