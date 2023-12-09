package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

/**
 * Clase Teclado.
 * Representa un teclado virtual con un nombre, un algoritmo asociado, un alfabeto y un diseño de teclado.
 * Utiliza la clase Playout para determinar la disposición de las teclas en el teclado.
 * @author Mariona Aguilera Folqué
 */
public class Teclado {
    /** El nombre del teclado. */
    private String nombre;

    /** El nombre del algoritmo asociado al teclado. */
    private String algoritmo;

    /** El nombre del alfabeto asociado al teclado. */
    private String alfabeto;

    /** Arreglo que representa el diseño de las teclas en el teclado. */
    private char[] layout;

    /** Objeto Playout que determina la disposición de las teclas en el teclado. */
    private Playout playout;

    /**
     * Constructor de la clase Teclado.
     * @param nombre El nombre del teclado.
     * @param alfabeto El nombre del alfabeto asociado al teclado.
     * @param numchar El número de caracteres en el teclado para calcular la disposición.
     */
    public Teclado(String nombre, String alfabeto, int numchar) {
        this.nombre = nombre;
        this.alfabeto = alfabeto;
        this.playout = new Playout(numchar);
        this.layout = null;
    }

    /**
     * Constructor de la clase Teclado a partir de un arreglo de Strings.
     * @param tec Un arreglo de Strings que contiene información del teclado.
     */
    public Teclado(String[] tec) {
        nombre = tec[0];
        algoritmo = tec[1];
        alfabeto = tec[2];
        layout = tec[3].toCharArray();
        playout = new Playout(layout.length);
    }

    /**
     * Obtiene el nombre del teclado.
     * @return El nombre del teclado.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene el nombre del algoritmo asociado al teclado.
     * @return El nombre del algoritmo.
     */
    public String getAlgoritmo() {
        return this.algoritmo;
    }

    /**
     * Obtiene el nombre del alfabeto asociado al teclado.
     * @return El nombre del alfabeto.
     */
    public String getAlfabeto() {
        return this.alfabeto;
    }

    /**
     * Obtiene el diseño de teclado como un arreglo de caracteres.
     * @return El arreglo de caracteres que representa el diseño de teclado.
     */
    public char[] getLayout() {
        return this.layout;
    }

    /**
     * Obtiene el tamaño del diseño de teclado.
     * @return El número de caracteres en el diseño de teclado.
     */
    public int getSizeLayout() {
        return this.layout.length;
    }

    /**
     * Obtiene la disposición de las teclas en el teclado como un arreglo de Point2D.
     * @return El arreglo de Point2D que representa la disposición de las teclas.
     */
    public Point2D[] getPlayout() {
        return this.playout.getTeclas();
    }

    /**
     * Establece un nuevo nombre para el teclado.
     * @param newName El nuevo nombre del teclado.
     */
    public void setNombre(String newName) {
        this.nombre = newName;
    }

    /**
     * Establece un nuevo nombre para el algoritmo asociado al teclado.
     * @param newAlgoritmo El nuevo nombre del algoritmo.
     */
    public void setAlgoritmo(String newAlgoritmo) {
        this.algoritmo = newAlgoritmo;
    }

    /**
     * Establece un nuevo nombre para el alfabeto asociado al teclado.
     * @param newAlfabeto El nuevo nombre del alfabeto.
     */
    public void setAlfabeto(String newAlfabeto) {
        this.alfabeto = newAlfabeto;
    }

    /**
     * Establece un nuevo diseño de teclado.
     * @param newlayout El nuevo diseño de teclado representado como un arreglo de caracteres.
     */
    public void setLayout(char[] newlayout) {
        this.layout = newlayout;
    }

    /**
     * Convierte la información del teclado en un arreglo de Strings.
     * @return Un arreglo de Strings que contiene la información del teclado.
     */
    public String[] toStringArray() {
        String[] info = new String[4];
        info[0] = nombre;
        info[1] = algoritmo;
        info[2] = alfabeto;
        info[3] = new String(layout).trim();

        return info;
    }
}
