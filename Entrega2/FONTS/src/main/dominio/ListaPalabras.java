package main.dominio;
import java.util.*;


/**
 * Clase ListaPalabras, subclase de TLP.
 * Representa una lista de palabras junto con sus frecuencias.
 * Extiende la clase TLP y utiliza un mapa para almacenar las frecuencias de las palabras.
 * @author Mariona Aguilera Folqué
 */
public class ListaPalabras extends TLP{

    private Map<String, Integer> listaFreq;

    /**
     * Constructor por defecto de la clase ListaPalabras.
     */
    public ListaPalabras() {

    }

    /**
     * Constructor de la clase ListaPalabras con nombre y un mapa de frecuencias.
     * @param nombre El nombre de la lista de palabras.
     * @param listaFreq El mapa que contiene las palabras y sus frecuencias.
     */
    public ListaPalabras(String nombre, Map<String, Integer> listaFreq){
        this.nombre = nombre;
        this.listaFreq = listaFreq;
    }

    /**
     * Constructor de la clase ListaPalabras a partir de un arreglo de Strings.
     * @param lis Un arreglo de Strings que contiene información de la lista de palabras.
     */
    public ListaPalabras(String[] lis) {
        nombre = lis[0];
        listaFreq = new HashMap<>();

        String[] s = lis[1].split(" ");

        for (int i = 0; i < s.length; i += 2) 
            listaFreq.put(s[i], Integer.valueOf(s[i + 1]));
    }

    /**
     * Agrega una palabra a la lista de palabras si no está presente.
     * @param p La palabra a agregar.
     * @param f La frecuencia asociada a la palabra.
     */
    public void addPalabra(String p, Integer f) {
        listaFreq.putIfAbsent(p, f);
    }

    /**
     * Obtiene el mapa que contiene las palabras y sus frecuencias.
     * @return El mapa de palabras y frecuencias.
     */
    public Map<String, Integer> getListaFreq(){
        return listaFreq;
    }

    /**
     * Establece el mapa de frecuencias de la lista de palabras.
     * @param newlist El nuevo mapa de palabras y frecuencias.
     */
    public void setListaFreq(Map<String, Integer> newlist) {
        this.listaFreq = newlist;
    }

    /**
     * Obtiene la frecuencia asociada a una palabra en la lista.
     * @param palabra La palabra de la cual se desea obtener la frecuencia.
     * @return La frecuencia de la palabra, o null si no está presente.
     */
    public Integer getFreq(String palabra) {
        return listaFreq.get(palabra);
    }

    /**
     * Obtiene el tamaño de la lista de palabras.
     * @return El número de palabras en la lista.
     */
    public int getSize(){
        return listaFreq.size();
    }

    /**
     * Convierte la información de la lista de palabras en un arreglo de Strings.
     * @return Un arreglo de Strings que contiene el nombre y las palabras con sus frecuencias.
     */
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
