package main.dominio;

import java.util.*;

/**
 * Clase abstracta TLP (TextoListaPalabras), subclase de Input.
 * Representa un bloque de texto asociado a una lista de palabras o a un texto en su.
 * Extiende la clase Input y proporciona métodos abstractos para convertir la información en un arreglo de Strings.
 * @author Mariona Aguilera Folqué
 */
public abstract class TLP extends Input {

    /**
     * Constructor por defecto de la clase TLP.
     */
    public TLP() {
        
    }

    /**
     * Constructor de la clase TLP con nombre.
     * @param nombre El nombre asociado al bloque de texto.
     */
    public TLP(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Convierte la información del bloque de texto en un arreglo de Strings.
     * @return Un arreglo de Strings que contiene la información del bloque.
     */
    public abstract String[] toStringArray();
}
