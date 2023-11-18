package main.dominio.algoritmos;

import main.dominio.ed.Node;
import java.util.*;
import java.awt.Point;


public class QAPOptimized extends QAP {
    
    private static final int MAX_ITERATIONS_GREEDY = 100;
    private static final double ALPHA = 0.5;

    @Override 
    protected Node initialSolution(double[][] distLoc, int[][] traficoInst) {
        return GRASP(distLoc, traficoInst);
    }

    private Node GRASP(double[][] distLoc, int[][] traficoInst) {
        int n = distLoc.length;
        Node u = new Node(Double.MAX_VALUE, n, n);

        Random rand = new Random();

        for (int iteration = 0; iteration < MAX_ITERATIONS_GREEDY; iteration++) {
            ArrayList<Point> currentSolution = constructGreedyRandomizedSolution(rand, n);
            double currentCost = totalCostSolution(distLoc, traficoInst, currentSolution);
            printPointList(currentSolution);
            if (currentCost < u.cost) {
                u.cost = currentCost;
                u.partialSol = currentSolution;
            }
        }

        return u;
    }


    private ArrayList<Point> constructGreedyRandomizedSolution(Random rand, int size) {
        ArrayList<Point> solution = new ArrayList<>();
        boolean[] usedInst = new boolean[size];

        for (int loc = 0; loc < size; loc++) {
            ArrayList<Integer> notUsedInst = getUsable(usedInst);

            if (!notUsedInst.isEmpty()) {
                double randomValue = rand.nextDouble();
                double threshold = ALPHA * randomValue; // Adjusted for GRASP

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

    private double totalCostSolution(double[][] distLoc, int[][] traficoInst, ArrayList<Point> l) {
        double sum = 0;
        for (int i = 0; i < l.size(); ++i) {
            Point p1 = l.get(i);
            for (int j = i + 1; j < l.size(); ++j) {
                Point p2 = l.get(j);
                sum += costBtw2Assig(distLoc, traficoInst, p1, p2);
            }
        }
        System.out.println(sum);
        return sum;
    }

    /* private Node greedySolution(double[][] distLoc, int[][] traficoInst) {
        int n = distLoc.length;
        Node u = new Node(0, n, n);

        for (int i = 0; i < n; ++i) {
            Point bestAssig = new Point(-1, -1);
            double minCost = Double.MAX_VALUE;
            
            for (int j = 0; j < n; ++j) {
                if (!u.usedInst[j]) {
                    Point assig = new Point(i, j);
                    double cost = costPlaceInst(distLoc, traficoInst, u.partialSol, assig);
                    if (cost < minCost) {
                        bestAssig = assig;
                        minCost = cost;
                    }
                }
            }

            u.cost += minCost;
            u.partialSol.add(bestAssig);
            u.usedInst[bestAssig.y] = true;
        }
        
        return u;
    }
    */

    /*private int sumArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i) sum = sum + array[i];
        return sum;
    }

    private double sumArray(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; ++i) sum = sum + array[i];
        return sum;
    }*/

    @Override
    protected double generateBound(double[][] distLoc, int[][] traficoInst, Node u) {
        return generateGLBound(distLoc, traficoInst, u);
    }


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
        //System.out.print("(" + u.level + " " + u.bound + " " + u.cost + ")");

        return u.cost + boundC12;
    }

    private ArrayList<Integer> getUsable(boolean[] b) {
        ArrayList<Integer> res = new ArrayList<>();

        for(int i = 0; i < b.length; ++i) {
            if (!b[i]) {
                res.add(i);
            }
        }
        return res;
    }


    private double[][] generateC1(double[][] distLoc, int[][] traficoInst, ArrayList<Point> partialSol,
            ArrayList<Integer> notUsedLocIndex, ArrayList<Integer> notUsedInstIndex) {
        double[][] C1 = new double[notUsedInstIndex.size()][notUsedInstIndex.size()];

        for (int i = 0; i < C1.length; ++i) {
            for (int j = 0; j < C1[0].length; ++j) {
                Point newPlacement = new Point(notUsedLocIndex.get(i), notUsedInstIndex.get(j));
                C1[i][j] = costPlaceInst(distLoc, traficoInst, partialSol, newPlacement);
            }
        }

        return C1;
    }


    private double[][] generateC2(double[][] distLoc, int[][] traficoInst, ArrayList<Integer> notUsedLocIndex, ArrayList<Integer> notUsedInstIndex) {
        double[][] C2 = new double[notUsedInstIndex.size()][notUsedInstIndex.size()];
        //Vector distancias
        double[] D = new double[notUsedInstIndex.size() - 1];
        //Vector trafico
        int[] T = new int[notUsedInstIndex.size() - 1];

        for (int i = 0; i < C2.length; ++i) {
            for (int j = 0; j < C2[0].length; ++j) {
                ArrayList<Integer> restLocNotPlaced = new ArrayList<>(notUsedLocIndex);
                restLocNotPlaced.remove(i);
                ArrayList<Integer> restInstNotPlaced = new ArrayList<>(notUsedInstIndex);
                restInstNotPlaced.remove(j);
                
                for (int k = 0; k < D.length; ++k) 
                    D[k] = distLoc[notUsedLocIndex.get(i)][restLocNotPlaced.get(k)];
                for (int k = 0; k < T.length; ++k) 
                    T[k] = traficoInst[notUsedInstIndex.get(j)][restInstNotPlaced.get(k)];

                Arrays.sort(T);
                Arrays.sort(D);
                //Calcula el producto escalar de T y D como si D estuviera ordenado decrecientemente
                C2[i][j] = inverseScalarProduct(T, D);
            }
        }
        
        return C2;
    }


    private double inverseScalarProduct(int[] v1, double[] invertedv2) {
        int sum = 0;

        for (int i = 0; i < v1.length; ++i) {
            sum += v1[i]*invertedv2[v1.length - i - 1];
        }

        return sum;
    }


    private double[][] matrixSum(double[][] m1, double[][] m2) {
        double[][] res = new double[m1.length][m1[0].length];

        for (int i = 0; i < m1.length; ++i) {
            for (int j = 0; j < m1[0].length; ++j) {
                res[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return res;
    }

    private double sumPointsMatrix(double[][] m, ArrayList<Point> points) {
        double sum = 0;

        for (int i = 0; i < points.size(); ++i) {
            Point p = points.get(i);
            sum += m[p.x][p.y];
        }

        return sum;
    }

    

    private void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private ArrayList<Point> hungarianAlgorithm(double[][] taskCostPerWorker) {
        int n = taskCostPerWorker.length;
        double[][] taskCostPerWorkerC = deepCopyMatrix(taskCostPerWorker);
        
        printMatrix(taskCostPerWorkerC);
        subtractMinimumValueRow(taskCostPerWorkerC);
        subtractMinimumValueCol(taskCostPerWorkerC);
        printMatrix(taskCostPerWorkerC);

        boolean[] rowCover = new boolean[n];
        boolean[] colCover = new boolean[n];
        System.out.print(" Entro MCL " );
        int minimumCover = minimumCoverLines(taskCostPerWorkerC, rowCover, colCover);
        System.out.print(" Salgo MCL = " + minimumCover + " ");
        while (minimumCover != n) {
            System.out.println();
            printMatrix(taskCostPerWorkerC);
            double minNum = minNumNotCovered(taskCostPerWorkerC, rowCover, colCover);
            addNumToCovered(taskCostPerWorkerC, rowCover, colCover, minNum);
            subtractFromMatrix(taskCostPerWorkerC, minNum);
            printMatrix(taskCostPerWorkerC);
            System.out.println();

            Arrays.fill(rowCover, false);
            Arrays.fill(colCover, false);
            System.out.print(" Entro MCL ");
            minimumCover = minimumCoverLines(taskCostPerWorkerC, rowCover, colCover);
            System.out.print(" Salgo MCL mc = " + minimumCover + " ");
            
            
        }

        return partialMaximumAssignation(taskCostPerWorkerC);
    }

    private double[][] deepCopyMatrix(double[][] mOriginal) {
        double[][] mNew = new double[mOriginal.length][mOriginal[0].length];

        for (int i = 0; i < mOriginal.length; i++) {
            for (int j = 0; j < mOriginal[0].length; j++) {
                mNew[i][j] = mOriginal[i][j];
            }
        }

        return mNew;
    }

    private void subtractMinimumValueRow(double[][] matrix) {

        for (int i = 0; i < matrix.length; ++i) {
            double min = Double.MAX_VALUE;

            for (int j = 0; j < matrix[0].length; ++j) 
                if (matrix[i][j] < min) min = matrix[i][j];

            for (int j = 0; j < matrix[0].length; ++j) 
                matrix[i][j] -= min;
        }
    }

    private void subtractMinimumValueCol(double[][] matrix) {

        for (int i = 0; i < matrix[0].length; ++i) {
            double min = Double.MAX_VALUE;
            for (int j = 0; j < matrix.length; ++j) 
                if (matrix[j][i] < min) min = matrix[j][i];

            for (int j = 0; j < matrix.length; ++j)  
                matrix[j][i] -= min;
        }
    }


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

    private void subtractFromMatrix(double[][] matrix, double num) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j)
                matrix[i][j] -= num;
        }
    }


    private int minimumCoverLines(double[][] matrix, boolean[] rowCover, boolean[] colCover) {
        ArrayList<Point> partialAssignation = partialMaximumAssignation(matrix);
        int numLinesCovered = 0;
        //printPointList(partialAssignation);

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

        for (int i = 0; i < rowCover.length; ++i) System.out.println(rowCover[i] + " ");
        for (int i = 0; i < colCover.length; ++i) System.out.println(colCover[i] + " ");

        return numLinesCovered;
    }


    private ArrayList<Point> partialMaximumAssignation(double[][] matrix) {
        boolean[] partialAsigRows = new boolean[matrix.length];
        boolean[] partialAsigCols = new boolean[matrix[0].length];
        return partialMaximumAssignationRec(matrix, partialAsigRows, partialAsigCols, 0, 0);
    }


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

    

    private static void printPointList(ArrayList<Point> l) {
        for (int i = 0; i < l.size(); ++i) {
            Point p = l.get(i);
            System.out.print("(" + p.x + " " + p.y + ")" + " ");
        }
        System.out.println();
    }

    

}