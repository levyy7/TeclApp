package main.dominio;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Clase Texto, subclase de TLP.
 * Representa un bloque de texto con un nombre asociado.
 * Extiende la clase TLP y contiene el texto como una cadena de caracteres.
 * @author Mariona Aguilera Folqué
 */
public class Texto extends TLP {

    /** La cadena de texto. */
    private String text;

    /**
     * Constructor por defecto de la clase Texto.
     */
    public Texto() {

    }

    /**
     * Constructor de la clase Texto con nombre y texto.
     * @param nombre El nombre asociado al bloque de texto.
     * @param text La cadena de texto.
     */
    public Texto(String nombre, String text) {
        this.nombre = nombre;
        this.text = text;
    }

    /**
     * Constructor de la clase Texto a partir de un arreglo de Strings.
     * @param tex Un arreglo de Strings que contiene información del bloque de texto.
     */
    public Texto(String[] tex) {
        nombre = tex[0];
        text = tex[1];
    }

    /**
     * Obtiene el texto asociado al bloque.
     * @return La cadena de texto.
     */
    public String getTexto() {
        return text;
    }

    /**
     * Establece un nuevo texto para el bloque.
     * @param txt El nuevo texto a asignar.
     */
    public void setTexto(String txt) {
        this.text = txt;
    }

    /**
     * Obtiene el tamaño del bloque de texto.
     * @return El número de caracteres en el texto.
     */
    public int getSize() {
        return text.length();
    }

    /**
     * Convierte la información del bloque de texto en un arreglo de Strings.
     * @return Un arreglo de Strings que contiene el nombre y el texto del bloque.
     */
    @Override
    public String[] toStringArray() {
        String[] info = new String[2];
        info[0] = nombre;
        info[1] = text;
        return info;
    }
}