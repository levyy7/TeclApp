package main.dominio.algoritmos;

import java.util.*;
import java.awt.Point;

/** 
 * Interfaz de las estrategias para la creación de layouts
 * Sirve como estructura para los algoritmos que se implementan con el fin de resolver el
 * problema de la creación de layouts
 * @author Eneko Sabaté Iturgaiz 
*/
public interface EstrategiaCreacionLayout {
    /** 
     * Método que devuelve una asignación posible para un conjunto de teclas y símbolos,
     * dadas las distancias entre las teclas y la frecuencia entre símbolos
     * @param distTeclas : Matriz de distancias entre las teclas, tal que 
     *                  distTeclas[i][j] es la distancia entre la tecla i 
     *                  y la tecla j. Debe de ser del mismo tamaño que 
     *                  la matriz "freqSimbolos"
     * @param freqSimbolos : Matriz de frecuencias entre símbolos, tal que 
     *                  freqSimbolos[i][j] es la frecuencia en la que el símbolo j 
     *                  aparece exactamente antes o después de i. Debe de ser del mismo 
     *                  tamaño que la matriz "distLoc" 
     * @return ArrayList<Point> : ArrayList de puntos que representan una asignación de 
     *                  teclas y símbolos. Cada punto de la lista representa un símbolo 
     *                  emplazado en una tecla, tal que el atributo "x" del punto 
     *                  representará una tecla y el atributo "y" un símbolo                
    */
    public ArrayList<Point> crearLayout(double[][] distTeclas, int[][] freqSimbolos);

}