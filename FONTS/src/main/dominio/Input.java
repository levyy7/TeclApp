package main.dominio;

import java.util.*;

/**
 * Clase abstracta Input.
 * Representa una entrada genérica con un nombre asociado.
 * Contiene métodos abstractos que deben ser implementados por las clases hijas.
 * @author Mariona Aguilera Folqué
 */
public abstract class Input{

    protected String nombre;
 
    /**
     * Constructor por defecto de la clase Input.
     */
    public Input() {
        
    }

    /**
     * Constructor de la clase Input con un nombre específico.
     * @param nombre El nombre asociado a la entrada.
     */
    public Input(String nombre){
        this.nombre = nombre; 
    }

    /**
     * Establece el nombre de la entrada.
     * @param nombre El nuevo nombre para la entrada.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de la entrada.
     * @return El nombre de la entrada.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método abstracto para obtener el tamaño de la entrada.
     * Debe ser implementado por las clases hijas.
     * @return El tamaño de la entrada.
     */
    public abstract int getSize();

    /**
     * Método abstracto para convertir la información de la entrada en un arreglo de Strings.
     * Debe ser implementado por las clases hijas.
     * @return Un arreglo de Strings que contiene la información de la entrada.
     */
    public abstract String[] toStringArray();
}

//mariona