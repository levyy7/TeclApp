package main.dominio;

import java.util.*;
import java.awt.Point;

public class Teclado {
    String nombre;
    String algoritmo;
    String alfabeto;
    Map<Character, Point> distribucion = new HashMap<>();


    public Teclado(String nombre, String algoritmo, String alfabeto, Map<Character, Point> distribucion) {
        this.nombre = nombre;
        this.algoritmo = algoritmo;
        this.alfabeto = alfabeto;
        this.distribucion = distribucion;
        
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public String algoritmo(){
        return this.algoritmo;
    }

    public String alfabeto(){
        return this.alfabeto;
    }

    public Map<Character, Point> getDistribucion(){
        return this.distribucion;
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

    public void setDistribucion(Map<Character, Point> newDist){
        this.distribucion = newDist;
    }

    public void modify(Map<Character, Point> newDist){
        this.distribucion = newDist;
    }
}

//mariona
