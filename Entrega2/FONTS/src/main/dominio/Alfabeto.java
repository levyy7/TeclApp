package main.dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Clase Alfabeto, subclase de Input
 * Clase que contiene funciones necesarias gestionar correctamente un
 * alfabeto
 * @author Mariona Aguilera Folqué
*/

public class Alfabeto extends Input{

    private String letras;

    /**
     * Constructor de Alfabeto con todos sus parámetros correspondientes.
     * @param nombre El nombre del alfabeto.
     * @param letras Las letras que componen el alfabeto.
     */
    public Alfabeto(String nombre, String letras){
        this.nombre = nombre;
        this.letras = letras;
    }

     /**
     * Constructor de Alfabeto a partir de un arreglo de Strings.
     * @param alf Un arreglo de Strings que contiene información del alfabeto.
     */
    public Alfabeto(String[] alf) {
        nombre = alf[0];
        letras = alf[1];
    }

    /**
     * Obtiene las letras que componen el alfabeto.
     * @return Las letras del alfabeto.
     */
    public String getLetras(){
        return letras;
    }

    /**
     * Establece las letras del alfabeto.
     * @param letras Las letras que compondrán el alfabeto.
     */
    public void setLetras(String letras){
        this.letras = letras;
    }

    /**
     * Obtiene el tamaño del alfabeto.
     * @return El número de letras en el alfabeto.
     */
    public int getSize(){
        return letras.length();
    }

    /**
     * Convierte la información del alfabeto en un arreglo de Strings.
     * @return Un arreglo de Strings que contiene el nombre y las letras del alfabeto.
     */
    
    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        info[1] = letras;
        return info;
    }
}