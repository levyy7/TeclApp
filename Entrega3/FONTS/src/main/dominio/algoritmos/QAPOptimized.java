package main.dominio.algoritmos;

import main.dominio.ed.Node;
import java.util.*;
import java.awt.Point;

/** 
 * Clase de la optimización del algoritmo <code>QAP</code>
 * Hace más eficiente el cálculo del QAP mediante una generación de lower
 * bounds más precisa
 * @author Eneko Sabaté Iturgaiz 
*/
public class QAPOptimized extends QAP {
    /** Número de iteraciones que ejecutará el algoritmo greedy GRASP */
    private static final int ITERATIONS_GREEDY = 100;

    @Override 
    protected Node initialSolutionBB(double[][] distLoc, int[][] traficoInst) {
        ArrayList<Point> initialSol = GRASP(distLoc, traficoInst);
        Node u = new Node(totalCostSolution(distLoc, traficoInst, initialSol), distLoc.length);
        u.partialSol = initialSol;
        return u;
    }

    /** 
     * Calcula una solución no óptima para el problema QAP
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @return double: Devuelve una solución final, cerca a la óptima, para el problema QAP. 
     *                  Cada punto de la lista representa una instalación emplazada, tal que 
     *                  el atributo "x" del punto representará una localización y el atributo 
     *                  "y" una instalación 
    */
    private ArrayList<Point> GRASP(double[][] distLoc, int[][] traficoInst) {
        int n = distLoc.length;
        ArrayList<Point> bestSol = new ArrayList<>();
        double bestCost = Double.MAX_VALUE;

        Random rand = new Random();

        for (int iteration = 0; iteration < ITERATIONS_GREEDY; iteration++) {
            ArrayList<Point> currentSolution = constructGreedyRandomizedSolution(rand, n);
            double currentCost = totalCostSolution(distLoc, traficoInst, currentSolution);

            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSol = currentSolution;
            }
        }

        return bestSol;
    }

    @Override
    protected double generateBound(double[][] distLoc, int[][] traficoInst, Node u) {
        return generateGLBound(distLoc, traficoInst, u);
    }


    /** 
     * Genera la Gilmore-Lawler Bound, para una solución parcial de un nodo dada. 
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @param u : Node del árbol de soluciones factibles
     * @return double: Devuelve la Gilmore-Lawler bound basada en la solución parcial 
     *                  especificada por "u"
    */
    private double generateGLBound(double[][] distLoc, int[][] traficoInst, Node u) {
        ArrayList<Integer> notUsedLocIndex = getUsable(u.usedLoc);
        ArrayList<Integer> notUsedInstIndex = getUsable(u.usedInst);
        double boundC12;

        if (notUsedLocIndex.size() == 0) boundC12 = 0;
        else {
            double[][] C1 = generateC1(distLoc, traficoInst, u.partialSol, notUsedLocIndex, notUsedInstIndex);
            double[][] C2 = generateC2(distLoc, traficoInst, notUsedLocIndex, notUsedInstIndex);

            double[][] C12 = matrixSum(C1, C2);

            ArrayList<Point> optimalAssignation = hungarianAlgorithm(C12); 
            boundC12 = sumPointsMatrix(C12, optimalAssignation);
        }

        return u.cost + boundC12;
    }


    /** 
     * Genera la matriz C1 usada para calcular la Gilmore-Lawler bound 
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @param partialSol : Solución parcial con los emplazamientos actuales, donde cada 
     *                  punto de la lista representa una instalación emplazada, tal que 
     *                  el atributo "x" del punto representará una localización y el atributo 
     *                  "y" una instalación 
     * @param notUsedLoc : ArrayList con las localizaciones no usadas
     * @param notUsedInst : ArrayList con las instalaciones no usadas
     * @return double[][] : el elemento (i, j) de la matriz devuelta representa el coste 
     *                  de colocar la iésima instalación no emplazada en la jésima ubicación,
     *                  con respecto a las instalaciones de "partialSol"
    */
    private double[][] generateC1(double[][] distLoc, int[][] traficoInst, ArrayList<Point> partialSol,
            ArrayList<Integer> notUsedLoc, ArrayList<Integer> notUsedInst) {
        double[][] C1 = new double[notUsedInst.size()][notUsedInst.size()];

        for (int i = 0; i < C1.length; ++i) {
            for (int j = 0; j < C1[0].length; ++j) {
                Point newPlacement = new Point(notUsedLoc.get(i), notUsedInst.get(j));
                C1[i][j] = costPlaceInst(distLoc, traficoInst, partialSol, newPlacement);
            }
        }

        return C1;
    }


    /** 
     * Genera la matriz C2 usada para calcular la Gilmore-Lawler bound 
     * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @param notUsedLoc : ArrayList con las localizaciones no usadas
     * @param notUsedInst : ArrayList con las instalaciones no usadas
     * @return double[][] : el elemento (i, j) de la matriz devuelta representa una cota 
     *                  inferior del coste de colocar la iésima instalación no emplazada en 
     *                  la jésima ubicación, con respecto al resto de instalaciones no 
     *                  emplazadas
    */
    private double[][] generateC2(double[][] distLoc, int[][] traficoInst, ArrayList<Integer> notUsedLoc, ArrayList<Integer> notUsedInst) {
        double[][] C2 = new double[notUsedInst.size()][notUsedInst.size()];
        //Vector distancias
        double[] D = new double[notUsedInst.size() - 1];
        //Vector trafico
        int[] T = new int[notUsedInst.size() - 1];

        for (int i = 0; i < C2.length; ++i) {
            for (int j = 0; j < C2[0].length; ++j) {
                ArrayList<Integer> restLocNotPlaced = new ArrayList<>(notUsedLoc);
                restLocNotPlaced.remove(i);
                ArrayList<Integer> restInstNotPlaced = new ArrayList<>(notUsedInst);
                restInstNotPlaced.remove(j);
                
                for (int k = 0; k < D.length; ++k) 
                    D[k] = distLoc[notUsedLoc.get(i)][restLocNotPlaced.get(k)];
                for (int k = 0; k < T.length; ++k) 
                    T[k] = traficoInst[notUsedInst.get(j)][restInstNotPlaced.get(k)];

                Arrays.sort(T);
                Arrays.sort(D);
                //Calcula el producto escalar de T y D como si D estuviera ordenado decrecientemente
                C2[i][j] = inverseScalarProduct(T, D);
            }
        }
        
        return C2;
    }

    /** 
     * Calcula el producto escalar entre dos vectores, donde el segundo vector está invertido 
     * @param v1 : Array de ints
     * @param invertedv2 : Array de doubles. Este vector será invertido antes de realizar el
     *                  producto
     * @return double[][] : El producto escalar entre "v1" y "invertedv2", donde "invertedv2"
     *                  es invertido antes de realizar el producto
    */
    private double inverseScalarProduct(int[] v1, double[] invertedv2) {
        int sum = 0;

        for (int i = 0; i < v1.length; ++i) {
            sum += v1[i]*invertedv2[v1.length - i - 1];
        }

        return sum;
    }

    /** 
     * Suma entre dos matrices
     * @param m1 : Matriz de doubles
     * @param m2 : Matriz de doubles
     * @return double[][] : el elemento (i, j) de la matriz devuelta es la suma del 
     *                      elemento (i, j) de la matriz m1 y del elemento (i, j) de m2
    */
    private double[][] matrixSum(double[][] m1, double[][] m2) {
        double[][] res = new double[m1.length][m1[0].length];

        for (int i = 0; i < m1.length; ++i) {
            for (int j = 0; j < m1[0].length; ++j) {
                res[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return res;
    }

    /** 
     * Suma las posiciones de las matrices especificadas por un conjunto de puntos
     * @param m : Matriz de doubles
     * @param points : ArrayList de Points donde el atributo "x" especifica la línea de
     *                  la matriz m y "y" la columna
     * @return double[][] : Devuelve la suma de todas las posiciones de "m" 
     *                  especificadas por los puntos de "points"
    */
    private double sumPointsMatrix(double[][] m, ArrayList<Point> points) {
        double sum = 0;

        for (int i = 0; i < points.size(); ++i) {
            Point p = points.get(i);
            sum += m[p.x][p.y];
        }

        return sum;
    }

    

    /** 
     * Resuelve instancias de Linear Assignment Problems
     * @param taskCostPerWorker : Matriz donde las filas representan a trabajadores y las 
     *                  columnas trabajos. Los elementos (i, j) de las matrices especifican 
     *                  el coste que resulta asignarle un trabajo j a un trabajador i.
     * @return ArrayList<Point> : Devuelve la asignación óptima, es decir, la forma de 
     *                  asignar n instalaciones a n ubicaciones de manera que el coste total
     *                  sea mínimo
    */
    private ArrayList<Point> hungarianAlgorithm(double[][] taskCostPerWorker) {
        int n = taskCostPerWorker.length;
        double[][] taskCostPerWorkerC = deepCopyMatrix(taskCostPerWorker);
        
        subtractMinimumValueRow(taskCostPerWorkerC);
        subtractMinimumValueCol(taskCostPerWorkerC);

        boolean[] rowCover = new boolean[n];
        boolean[] colCover = new boolean[n];

        int minimumCover = minimumCoverLines(taskCostPerWorkerC, rowCover, colCover);
        while (minimumCover != n) {
            double minNum = minNumNotCovered(taskCostPerWorkerC, rowCover, colCover);
            addNumToCovered(taskCostPerWorkerC, rowCover, colCover, minNum);
            subtractFromMatrix(taskCostPerWorkerC, minNum);

            Arrays.fill(rowCover, false);
            Arrays.fill(colCover, false);
            //System.out.print(" Entro MCL ");
            minimumCover = minimumCoverLines(taskCostPerWorkerC, rowCover, colCover);
            //System.out.print(" Salgo MCL mc = " + minimumCover + " ");
            
            
        }

        return partialMaximumAssignation(taskCostPerWorkerC);
    }

    /** 
     * Realiza una copia en profundidad de una matriz
     * @param mOriginal : Matriz de doubles
     * @return double[][] : Devuelve una copia de "mOriginal", totalmente desvinculada de esta
     *                      (sin ninguna referencia)
    */
    private double[][] deepCopyMatrix(double[][] mOriginal) {
        double[][] mNew = new double[mOriginal.length][mOriginal[0].length];

        for (int i = 0; i < mOriginal.length; i++) {
            for (int j = 0; j < mOriginal[0].length; j++) {
                mNew[i][j] = mOriginal[i][j];
            }
        }

        return mNew;
    }

    /** 
     * Resta el valor mínimo de una fila a la totalidad de la fila, por cada fila
     * @param matrix : Matriz de doubles
     * @return double[][] : Devuelve "matrix" despúes de haber restado el valor mínimo de
     *                  cada fila a su propia fila
    */
    private void subtractMinimumValueRow(double[][] matrix) {

        for (int i = 0; i < matrix.length; ++i) {
            double min = Double.MAX_VALUE;

            for (int j = 0; j < matrix[0].length; ++j) 
                if (matrix[i][j] < min) min = matrix[i][j];

            for (int j = 0; j < matrix[0].length; ++j) 
                matrix[i][j] -= min;
        }
    }

    /** 
     * Resta el valor mínimo de una columna a la totalidad de la columna, por cada columna
     * @param matrix : Matriz de doubles
     * @return double[][] : Devuelve "matrix" despúes de haber restado el valor mínimo de
     *                  cada columna a su propia columna
    */
    private void subtractMinimumValueCol(double[][] matrix) {

        for (int i = 0; i < matrix[0].length; ++i) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < matrix.length; ++j) 
                if (matrix[j][i] < min) min = matrix[j][i];

            for (int j = 0; j < matrix.length; ++j)  
                matrix[j][i] -= min;
        }
    }

    /** 
     * Devuelve el número mínimo no cubierto de la matriz
     * @param matrix : Matriz sobre la que se resta el mínimo numero no cubierto
     * @param rowCover : Indica que filas están cubiertas
     * @param colCover : Indica que columnas están cubiertas
     * @return double : Devuelve el numero mínimo de "matrix" que no este cubierto por las
     *                  líneas especificadas por "rowCover" y "colCover"
    */
    private double minNumNotCovered(double[][] matrix, boolean[] rowCover, boolean[] colCover) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < matrix.length; ++i) {
            if (!rowCover[i]) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    if (!colCover[j] && matrix[i][j] < min) min = matrix[i][j];
                }
            }
        }

        return min;
    }

    /** 
     * Suma un valor a una matriz solo en las líneas cubiertas 
     * Si una posición de la matriz está cubierta por dos líneas, se suma el valor dos veces
     * @param matrix : Matriz sobre la que se suma el valor en las líneas cubiertas
     * @param rowCover : Indica que filas están cubiertas
     * @param colCover : Indica que columnas están cubiertas
     * @param num : Es el valor que se quiere sumar a las líneas cubiertas de la matriz
    */
    private void addNumToCovered(double[][] matrix, boolean[] rowCover, boolean[] colCover, double num) {
        for (int i = 0; i < rowCover.length; ++i) {
            if (rowCover[i]) {
                for (int j = 0; j < colCover.length; ++j) matrix[i][j] += num;
            }
        }

        for (int i = 0; i < colCover.length; ++i) {
            if (colCover[i]) {
                for (int j = 0; j < rowCover.length; ++j) matrix[j][i] += num;
            }
        }
    }


    /** 
     * Resta un valor a la totalidad de la matriz
     * @param matrix : Matriz sobre la que se quiere restar el valor
     * @param num : Es el valor que se quiere usar para restarlo en la matriz
    */
    private void subtractFromMatrix(double[][] matrix, double num) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j)
                matrix[i][j] -= num;
        }
    }

    /** 
     * Calcula el minimo número de líneas (y las propias líneas) que se necesitan para 
     * recubrir los 0s de una matriz
     * @param matrix : Matriz sobre la que se realiza el calculo
     * @param rowCover : Array sobre la que se especifican las filas usadas para recubrir
     *                  los 0s de "matrix"
     * @param colCover : Array sobre la que se especifican las columnas usadas para recubrir
     *                  los 0s de "matrix"
     * @return int : Devuelve el mínimo número de líneas necesarias para recubrir los 0s
     *                  de "matrix"
    */
    private int minimumCoverLines(double[][] matrix, boolean[] rowCover, boolean[] colCover) {
        ArrayList<Point> partialAssignation = partialMaximumAssignation(matrix);
        int numLinesCovered = 0;

        Arrays.fill(rowCover, true);
        Arrays.fill(colCover, false);

        //Mark rows without an assigment
        for (int i = 0; i < partialAssignation.size(); ++i) {
            int rowA = partialAssignation.get(i).x; 
            rowCover[rowA] = false;
        }
        
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;

            //Mark cols where a 0 exists in a marked row
            for (int i = 0; i < matrix[0].length; ++i) {
                for (int j = 0; j < matrix.length && !colCover[i]; ++j) {
                    if (rowCover[j] && matrix[j][i] == 0) {
                        colCover[i] = true;
                        ++numLinesCovered;
                        hasChanged = true;
                    }
                }
            }

            //Mark rows with the assignment in a marked col
            for (int i = 0; i < partialAssignation.size(); ++i) {
                int rowA = partialAssignation.get(i).x;
                int colA = partialAssignation.get(i).y;
                if (!rowCover[rowA] && colCover[colA]) {
                    rowCover[rowA] = true;
                    hasChanged = true;
                }
            }
        } 

        //Finally, invert the values of the row covering
        for (int i = 0; i < rowCover.length; ++i) {
            rowCover[i] = !rowCover[i];
            if (rowCover[i]) ++numLinesCovered;
        }

        return numLinesCovered;
    }


    /** 
     * Calcula el la asignación con el mayo número posible de filas con un zero asignado
     * @param matrix : Matriz sobre la que se realiza el calculo
     * @return Array<Point> : Devuelve las posiciones de la asignación más completa calculada,
     *                  donde cada punto del array representa un cero que forma parte de la 
     *                  asignación
    */
    private ArrayList<Point> partialMaximumAssignation(double[][] matrix) {
        boolean[] partialAsigRows = new boolean[matrix.length];
        boolean[] partialAsigCols = new boolean[matrix[0].length];
        return partialMaximumAssignationRec(matrix, partialAsigRows, partialAsigCols, 0, 0);
    }


    /** 
     * Calcula la asignación con el mayo número posible de filas con un zero asignado
     * @param matrix : Matriz sobre la que se realiza el calculo
     * @param rowAssigned : Filas en las que ya se ha realizado una asignación
     * @param colAssigned : Columnas en las que ya se ha realizado una asignación
     * @param row : Fila de la matriz desde donde se quiere empezar a realizar el cálculo
     * @param col : Columna de la matriz desde donde se quiere empezar a realizar el cálculo
     * @return Array<Point> : Devuelve las posiciones de la asignación más completa calculada 
     *                  empezando desde la fila "row" y la columna "col", donde cada punto 
     *                  del array representa un cero que forma parte de la asignación          
    */
    private ArrayList<Point> partialMaximumAssignationRec(double[][] matrix, boolean[] rowAssigned, boolean[] colAssigned, int row, int col) {
        if (row >= rowAssigned.length) return new ArrayList<Point>();
        else {
            ArrayList<Point> partialAssig1 = new ArrayList<Point>();
            ArrayList<Point> partialAssig2;
            boolean[] rowAssigned1 = rowAssigned.clone();
            boolean[] rowAssigned2 = rowAssigned.clone();
            boolean[] colAssigned1 = colAssigned.clone();
            boolean[] colAssigned2 = colAssigned.clone();
            if (matrix[row][col] == 0 && !colAssigned[col]) {
                rowAssigned1[row] = true;
                colAssigned1[col] = true;
                Point pos = new Point(row,col);
                partialAssig1.add(pos);
                partialAssig1.addAll(partialMaximumAssignationRec(matrix, rowAssigned1, colAssigned1, row + 1, 0));
            } 

            if (col == matrix[0].length - 1) {
                col = 0;
                row = row + 1;
            }
            else col = col + 1;
            partialAssig2 = partialMaximumAssignationRec(matrix, rowAssigned2, colAssigned2, row, col); //.clone()??

            if (partialAssig1.size() >= partialAssig2.size()) return partialAssig1;
            else return partialAssig2;
    
        }
    }

}    