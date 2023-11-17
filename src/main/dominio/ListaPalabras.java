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

    public void readFromFile(String file) throws FileNotFoundException{
        File doc = new File(file);
        Scanner t = new Scanner(doc);
        while(t.hasNextLine()) {
           this.listaFreq.putIfAbsent(t.next(), t.nextDouble());
        }
        t.close();
    }

    //afegir paraules a la llista si no hi son
    public void addPalabra(String p, Double f) {
        listaFreq.putIfAbsent(p, f);
    }

    public void readFromType(String lp){
        String[] parts = lp.split("\r?\n");
        int n = parts.length;
        for(int i = 0; i<n; ++i){
            String[] word = parts[i].split(" ");
            addPalabra(word[0], Double.valueOf(word[1]));
        }
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