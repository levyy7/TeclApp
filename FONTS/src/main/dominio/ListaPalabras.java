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

    public int getSize(){
        return listaFreq.size();
    }

    public void importInput(String name, String in, String type){
        if(type == "Lista"){
            super.setNombre(name);
        }
    }

    public void setListaFrequencia(Map<String, Double> newlist) {
        this.listaFreq = newlist;
    }     
}

//mariona