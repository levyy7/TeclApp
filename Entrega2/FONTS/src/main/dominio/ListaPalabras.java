package main.dominio;
import java.util.*;


public class ListaPalabras extends TLP{

    private Map<String, Integer> listaFreq;

    public ListaPalabras(String nombre, Map<String, Integer> listaFreq){
        super(nombre);
        this.listaFreq = listaFreq;
    }

    public ListaPalabras(String[] lis) {
        nombre = lis[0];
        listaFreq = new HashMap<>();

        String[] s = lis[1].split(" ");

        for (int i = 0; i < s.length; i += 2) 
            listaFreq.put(s[i], Integer.valueOf(s[i + 1]));
    }

    //afegir paraules a la llista si no hi son
    public void addPalabra(String p, Integer f) {
        listaFreq.putIfAbsent(p, f);
    }

    public String getNombre(){
        return super.getNombre();
    }

    public Map<String, Integer> getListaFreq(){
        return listaFreq;
    }

    public Integer getFrequencia(String palabra) {
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

    public void setListaFrequencia(Map<String, Integer> newlist) {
        this.listaFreq = newlist;
    }

    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        String list = "";
        boolean primero = true;
        for (Map.Entry<String, ?> entry : listaFreq.entrySet()) {
            if (primero == false) list += " ";
            list += entry.getKey()+" "+entry.getValue();
            primero = false;
        }
        info[1] = list;
        return info;
    }
}

//mariona