package main.dominio.ed;

import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Point;

/** 
 * Clase de la estructura de datos Node
 * Representa un nodo en el árbol de soluciones creado por el algoritmo Branch & Bound
 * @author Eneko Sabaté Iturgaiz 
*/
public class Node {
    /** El coste de la solución parcial del Node */
    public double cost;

    /** 
     * Una lower bound de todas las soluciones que se pueden construir a partir de 
     * la solución parcial del Node
    */
    public double bound;

    
    /** 
     * ArrayList de instalaciones ya emplazadas. 
     * Cada punto de la lista representa una instalación emplazada, tal que el atributo "x" 
     * del punto representará una localización y el atributo "y" una instalación 
    */
    public ArrayList<Point> partialSol;

    /** 
     * Array de las localizaciones utilizadas hasta el momento. Si usedLoc[i] es "true" 
     * entonces la ubicación i ya habrá sido utilizada en la solución parcial. En caso
     * contrario, la ubicación todavía no se ha usado.
    */
    public boolean[] usedLoc;

    /** 
     * Array de las instalaciones utilizadas hasta el momento. Si usedInst[i] es "true" 
     * entonces la instalación i ya habrá sido utilizada en la solución parcial. En caso
     * contrario, la instalación todavía no se ha usado.
    */
    public boolean[] usedInst;
    


    /** 
     * Clase constructora especificando el coste actual y el tamaño del problema total. 
     * Los arrays esatarán declarados e inicializados a false, y el ArrayList estará 
     * delcarado pero vacío.
     * @param cost : especifica el atributo "cost"
     * @param size : especifica el tamaño de los arrays
    */
    public Node(double cost, int size) {
        this.cost = cost;
        this.bound = 0;
        this.partialSol = new ArrayList<>();
        this.usedLoc = new boolean[size]; //can use .fill() to fill values of array
        this.usedInst = new boolean[size];
        Arrays.fill(this.usedLoc, false);
        Arrays.fill(this.usedInst, false);
    }

    /** 
     * Clase constructora especificando el coste actual, una solución parcial y las 
     * localizaciones e instalaicones usadas en esta solución parcial.
     * @param cost : especifica el atributo "cost"
     * @param partialSol : especifica el atributo "partialSol"
     * @param usedLoc : especifica el atributo "usedLoc"
     * @param usedInst : especifica el atributo "usedInst"
    */
    public Node(double cost, ArrayList<Point> partialSol, boolean[] usedLoc, boolean[] usedInst) {
        this.cost = cost;
        this.bound = 0;
        this.partialSol = new ArrayList<>(partialSol);
        this.usedLoc = usedLoc.clone(); //can use .fill() to fill values of array
        this.usedInst = usedInst.clone();
    }
}