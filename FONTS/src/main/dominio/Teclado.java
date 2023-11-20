package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;


public class Teclado {
    private String nombre;
    private String algoritmo; //nombre algoritmo
    private String alfabeto; //nombre alfabeto
    private char[] layout;
    private Playout playout;

    //creadora
    public Teclado(String nombre, String alfabeto, int numchar){
        this.nombre = nombre;
        this.alfabeto = alfabeto;
        this.playout = new Playout(numchar);
        this.layout = null;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getAlgoritmo(){
        return this.algoritmo;
    }

    public String getAlfabeto(){
        return this.alfabeto;
    }

    public char[] getLayout(){
        return this.layout;
    } 

    public int getSizeLayout(){
        return this.layout.length;
    }

    public Point2D[] getPlayout(){
        return this.playout.getTeclas();
    }

    public void setNombre(String newName){
        this.nombre = newName;
    }

    public void setAlgoritmo(String newAlgoritmo){
        this.nombre = newAlgoritmo;
    }

    public void setAlfabeto(String newAlfabeto) {
        this.alfabeto = newAlfabeto;
    }

    public void setLayout(char[] newlayout){
        this.layout = newlayout;
    }
}

//mariona
