package main.dominio.algoritmos;

import java.util.*;
import java.awt.Point;

/** 
 * Interfaz de las estrategias para la creación de layouts
 * Sirve como estructura para los algoritmos que se implementan con el fin de resolver el
 * problema de la creación de layouts
 * @author Eneko Sabaté Iturgaiz 
*/
public abstract class EstrategiaCreacionLayout {

    private static final double ALPHA = 0.5;

    protected ArrayList<Point> constructGreedyRandomizedSolution(Random rand, int size) {
        ArrayList<Point> solution = new ArrayList<>();
        boolean[] usedInst = new boolean[size];

        for (int loc = 0; loc < size; loc++) {
            ArrayList<Integer> notUsedInst = getUsable(usedInst);

            if (!notUsedInst.isEmpty()) {
                double randomValue = rand.nextDouble();
                double threshold = ALPHA * randomValue;

                int selectedInst = 0;
                double cumulativeProbability = 0.0;

                while (selectedInst < notUsedInst.size() - 1 &&
                        cumulativeProbability + 1.0 / notUsedInst.size() < threshold) {
                    cumulativeProbability += 1.0 / notUsedInst.size();
                    ++selectedInst;
                }

                Point p = new Point(loc, notUsedInst.get(selectedInst));
                solution.add(p);
                usedInst[p.y] = true;
            }
        }

        return solution;
    }

    protected ArrayList<Integer> getUsable(boolean[] b) {
        ArrayList<Integer> res = new ArrayList<>();

        for(int i = 0; i < b.length; ++i) {
            if (!b[i]) {
                res.add(i);
            }
        }
        return res;
    }

    protected double costBtw2Assig(double[][] distLoc, int[][] traficoInst, Point a, Point b) {
        return (distLoc[a.x][b.x]*traficoInst[a.y][b.y]);
    }
    
    protected double totalCostSolution(double[][] distLoc, int[][] traficoInst, ArrayList<Point> l) {
        double sum = 0;
        for (int i = 0; i < l.size(); ++i) {
            Point p1 = l.get(i);
            for (int j = i + 1; j < l.size(); ++j) {
                Point p2 = l.get(j);
                sum += costBtw2Assig(distLoc, traficoInst, p1, p2);
            }
        }
        return sum;
    }

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
    public abstract ArrayList<Point> crearLayout(double[][] distTeclas, int[][] freqSimbolos);
}