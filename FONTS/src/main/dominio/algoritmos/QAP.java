package main.dominio.algoritmos;

import main.dominio.ed.Node;
import java.util.*;
import java.awt.Point;

/** 
 * Clase del algoritmo <code>QAP</code>
 * Implementa el algoritmo con el fin de resolver el problema
 * de crear un layout para un teclado
 * @author Eneko Sabaté Iturgaiz 
*/
public class QAP extends EstrategiaCreacionLayout {

    public ArrayList<Point> crearLayout(double[][] distTeclas, int[][] freqSimbolos) {

        
        return branchAndBound(distTeclas, freqSimbolos);
    }

    /** 
     * Algoritmo Branch & Bound para resolver el problema de asignar instalaciones
     * a localizaciones
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @return ArrayList<Point> : ArrayList de la asignación óptima. El tamaño de la lista
     *                  es igual al tamaño de una línea de distLoc (o traficoInst). Cada 
     *                  punto de la lista representa una instalación emplazada, tal que 
     *                  el atributo "x" del punto representará una localización y el atributo 
     *                  "y" una instalación 
    */
    protected ArrayList<Point> branchAndBound(double[][] distLoc, int[][] traficoInst) {
        int n = distLoc.length;
        Node bestSol = initialSolutionBB(distLoc, traficoInst);

        PriorityQueue<Node> pq = 
          new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));

        Node u = new Node(0.0, n);
        pq.offer(u);

        while (pq.size() != 0) {
            u = pq.poll(); 

            //Solution
            if (u.partialSol.size() == n) {
                if (u.cost < bestSol.cost) {
                    bestSol = u;
                }
            }
            //Branch&Bound
            else {
                Node v;

                int locIndex = 0;
                while (u.usedLoc[locIndex]) ++locIndex;

                //Produce new n - partialSol.value.size() possible solutions
                for (int i = 0; i < n; ++i) {
                    if (!u.usedInst[i]) {
                        Point newPlacement = new Point();
                        newPlacement.x = locIndex;
                        newPlacement.y = i;

                        v = new Node(u.cost, u.partialSol, u.usedLoc, u.usedInst);
                        v.cost += costPlaceInst(distLoc, traficoInst, v.partialSol, newPlacement);
                        v.partialSol.add(newPlacement);
                        v.usedLoc[newPlacement.x] = true;
                        v.usedInst[newPlacement.y] = true;
                        v.bound = generateBound(distLoc, traficoInst, v);

                        if (v.bound < bestSol.cost) pq.add(v); 
                    }
                }
            }
        }

        return bestSol.partialSol;
    }

    /** 
     * Devuelve una solución utilizada para acotar un coste mínimo en el Branch & Bound
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @return Node : Devuelve un Node con el atributo "cost" != Null
    */
    protected Node initialSolutionBB(double[][] distLoc, int[][] traficoInst) {
        return (new Node(Double.MAX_VALUE, distLoc.length));
    }

    /** 
     * Calcula el coste de colocar una nueva instalación en una localización, respecto
     * a un conjunto de instalaciones ya emplazadas.
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @param partialSol : ArrayList de instalaciones ya emplazadas. 
     *                  Cada punto de la lista representa una instalación emplazada, tal que 
     *                  el atributo "x" del punto representará una localización y el atributo 
     *                  "y" una instalación 
     * @param newPlacement : Punto de la nueva instalación a emplazar. El atributo "x" del punto representará una localización y el atribto 
     *                  "y" una instalación 
     * @return double : Devuelve el coste del emplazamiento de "newAssignment" respecto a la 
     *                  asignación parcial "partialSol"
    */
    protected double costPlaceInst(double[][] distLoc, int[][] traficoInst, ArrayList<Point> partialSol, Point newPlacement) {
        double sumCosts = 0;

        for (int i = 0; i < partialSol.size(); ++i) {
            sumCosts += costBtw2Assig(distLoc, traficoInst, partialSol.get(i), newPlacement);
        }

        return sumCosts;
    }

    /** 
     * Calcula una lower bound para una asignación parcial dada.
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @param u : Node con la información acerca de una cierta asignación parcial       
     * @return double : Devuelve una lower bound para todas las asignaciones completas
     *                  construibles a partir de "u"
    */
    protected double generateBound(double[][] distLoc, int[][] traficoInst, Node u) {
        return u.cost;
    }
}