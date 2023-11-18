package main.dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ListaPalabras extends TLP{

    private Map<String, Double> listaFreq = new HashMap<String, Double>();

    public ListaPalabras(String nombre, Map<String, Double> listaFreq){
        super(nombre);
        this.listaFreq = listaFreq;
    }

    //afegir paraules a la llista si no hi son
    public void addPalabra(String p, Double f) {
        listaFreq.putIfAbsent(p, f);
    }

    public String getNombre(){
        return super.getNombre();
    }

    public Map<String, Double> getListaFreq(){
        return listaFreq;
    }

    public Double getFrequencia(String palabra) {
        return listaFreq.get(palabra);
    }

    public String getType(){
        return "Lista de Palabras";
    }

    public void setListaFrequencia(Map<String, Double> newlist) {
        this.listaFreq = newlist;
    }
    
    public void modify(Map<String, Double> newlist) {
        this.listaFreq = newlist;
    }

    public void printSingle(String p, Double f){
        System.out.println("Palabra: " + p + " Frequencia: " + f + "\n");
    }

    public void print(){
        listaFreq.forEach((key, value) -> printSingle(key, value));;       
    }
       
}

//mariona