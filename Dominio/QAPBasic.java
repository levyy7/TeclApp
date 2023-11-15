import java.util.*;
import java.awt.Point;

class Node {
    int level;
    double cost, bound;
    ArrayList<Point> partialSol;
    boolean[] usedLoc, usedInst;
 
    Node(double cost, int level, int size) {
        this.cost = cost;
        this.level = level;
        this.bound = 0;
        this.partialSol = new ArrayList<>();
        this.usedLoc = new boolean[size]; //can use .fill() to fill values of array
        this.usedInst = new boolean[size];
        Arrays.fill(this.usedLoc, false);
        Arrays.fill(this.usedInst, false);
    }

    Node(double cost, int level, ArrayList<Point> partialSol, boolean[] usedLoc, boolean[] usedInst) {
        this.cost = cost;
        this.level = level;
        this.bound = 0;
        this.partialSol = new ArrayList<>(partialSol);
        this.usedLoc = usedLoc.clone(); //can use .fill() to fill values of array
        this.usedInst = usedInst.clone();
    }
}

public class QAPBasic implements EstrategiaCreacionLayout {
    
    
    public ArrayList<Point> crearLayout(
        double[][] distLoc,
        int[][] traficoInst) {

        
        return branchAndBound(distLoc, traficoInst);
    }

    private int sumArrayInt(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i) sum = sum + array[i];
        return sum;
    }

    private double sumArrayDouble(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; ++i) sum = sum + array[i];
        return sum;
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


    private double greedySolution(
        double[][] distLoc,
        int[][] traficoInst) {
        
        //Greedy solution to the QAP pronlem. The result is not optimal!
        double cost = 0.0;
        int n = distLoc.length;
        
        //int[] sol = new int[n];
        double[] sumRowDistLoc = new double[n];
        int[] sumRowTraficoInst = new int[n];

        for (int i = 0; i < n; ++i) {
            sumRowDistLoc[i] = sumArrayDouble(distLoc[i]);
            sumRowTraficoInst[i] = sumArrayInt(traficoInst[i]);
        }

        Arrays.sort(sumRowDistLoc);
        Arrays.sort(sumRowTraficoInst);

        for (int i = 0; i < n; ++i) 
            cost += sumRowTraficoInst[i]*sumRowDistLoc[n - i - 1];

        //System.out.print("Una cota superior para el problema es: " + cost); System.out.println();
        return cost;
    }

    private ArrayList<Integer> getToFalseIndex(boolean[] b) {
        ArrayList<Integer> res = new ArrayList<>();

        for(int i = 0; i < b.length; ++i) {
            if (!b[i]) {
                res.add(i);
            }
        }
        
        return res;
    }

    private double costBtw2Assig(double[][] distLoc, int[][] traficoInst, Point a, Point b) {
        return (distLoc[a.x][b.x]*traficoInst[a.y][b.y]);
    }

    private double costPlaceInst(double[][] distLoc, int[][] traficoInst, ArrayList<Point> partialSol, Point newPlacement) {
        double sumCosts = 0;

        for (int i = 0; i < partialSol.size(); ++i) {
            sumCosts += costBtw2Assig(distLoc, traficoInst, partialSol.get(i), newPlacement);
        }

        return sumCosts;
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

    private double inverseScalarProductIntDouble(int[] v1, double[] invertedv2) {
        int sum = 0;

        for (int i = 0; i < v1.length; ++i) {
            sum += v1[i]*invertedv2[v1.length - i - 1];
        }

        return sum;
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
                
                for (int k = 0; k < D.length; ++k) {
                    //System.out.print("(" + restLocNotPlaced.size() + " " + restInstNotPlaced.size() + ")" + " ");
                    D[k] = distLoc[notUsedLocIndex.get(i)][restLocNotPlaced.get(k)];
                }
                for (int k = 0; k < T.length; ++k) T[k] = traficoInst[notUsedInstIndex.get(j)][restInstNotPlaced.get(k)];

                Arrays.sort(T);
                Arrays.sort(D);
                //Calcula el producto escalar de T y D como si D estuviera ordenado decrecientemente
                C2[i][j] = inverseScalarProductIntDouble(T, D);
            }
        }
        
        return C2;
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

    private double generateGLBound(double[][] distLoc, int[][] traficoInst, Node u) {
        ArrayList<Integer> notUsedLocIndex = getToFalseIndex(u.usedLoc);
        ArrayList<Integer> notUsedInstIndex = getToFalseIndex(u.usedInst);
        double boundC12;

        if (notUsedLocIndex.size() == 0) boundC12 = 0;
        else {
            double[][] C1 = generateC1(distLoc, traficoInst, u.partialSol, notUsedLocIndex, notUsedInstIndex);
            //System.out.print("(" + notUsedLocIndex.size() + " " + notUsedInstIndex.size() + ")" + " ");
            //System.out.println();
            double[][] C2 = generateC2(distLoc, traficoInst, notUsedLocIndex, notUsedInstIndex);

            double[][] C12 = matrixSum(C1, C2);
            //System.out.print(" M[0][0] = " + C12[0][0] + ") ");
            ArrayList<Point> optimalAssignation = hungarianAlgorithm(C12); 
            //System.out.print(" M[0][0] = " + C12[0][0] + ") ");
            boundC12 = sumPointsMatrix(C12, optimalAssignation);
        }
        //System.out.print(" (" + boundC12 + ") ");

        return u.cost + boundC12;
    }

    private ArrayList<Point> branchAndBound(double[][] distLoc, int[][] traficoInst) {
        int n = distLoc.length;
        Node bestSol = new Node(greedySolution(distLoc, traficoInst), n, n); //greedySolution(distLoc, traficoInst)
        Point e = new Point(-1, -1);
        bestSol.partialSol.add(e);
        PriorityQueue<Node> pq = //Seguramente priority_queue
          new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));

        Node u = new Node(0.0, 0, n);
        pq.offer(u);

        while (pq.size() != 0) {
            u = pq.poll(); 
            //System.out.print("(" + u.level + " " + u.bound + " " + u.cost + ")");
            //Solution
            if (u.level == n) {
                if (u.cost < bestSol.cost) {
                    bestSol = u;
                }
            }
            //Branch&Bound
            else {
                Node v;
                //System.out.print(" I reach B&B");
                int locIndex = 0;
                while (u.usedLoc[locIndex]) ++locIndex;

                //Produce new n - partialSol.value.size() possible solutions
                for (int i = 0; i < n; ++i) {
                    if (!u.usedInst[i]) {
                        Point newPlacement = new Point();
                        newPlacement.x = locIndex;
                        newPlacement.y = i;

                        v = new Node(u.cost, u.level + 1, u.partialSol, u.usedLoc, u.usedInst);
                        v.cost += costPlaceInst(distLoc, traficoInst, v.partialSol, newPlacement);
                        v.partialSol.add(newPlacement);
                        v.usedLoc[newPlacement.x] = true;
                        v.usedInst[newPlacement.y] = true;
                        v.bound = generateGLBound(distLoc, traficoInst, v);
                        //System.out.print(" (" + i + " " + v.cost + " " + v.bound + " " + bestSol.cost + ")");System.out.println();

                        if (v.bound < bestSol.cost) {
                            pq.add(v);
                        } 
                    }
                }
            }
        }

        return bestSol.partialSol;
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
            minimumCover = minimumCoverLines(taskCostPerWorkerC, rowCover, colCover);
        }

        return partialMaximumAssignation(taskCostPerWorkerC);
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

            if (partialAssig1.size() >= partialAssig2.size()) {
                System.arraycopy(rowAssigned, 0, rowAssigned1, 0, rowAssigned.length);
                System.arraycopy(colAssigned, 0, colAssigned1, 0, colAssigned.length);
                return partialAssig1;
            }
            else {
                System.arraycopy(rowAssigned, 0, rowAssigned2, 0, rowAssigned.length);
                System.arraycopy(colAssigned, 0, colAssigned2, 0, colAssigned.length);
                return partialAssig2;
            }
        }
    }

    private ArrayList<Point> partialMaximumAssignation(double[][] matrix) {
        boolean[] partialAsigRows = new boolean[matrix.length];
        boolean[] partialAsigCols = new boolean[matrix[0].length];
        return partialMaximumAssignationRec(matrix, partialAsigRows, partialAsigCols, 0, 0);
    }

    private int minimumCoverLines(double[][] matrix, boolean[] rowCover, boolean[] colCover) {
        ArrayList<Point> partialAssignation = partialMaximumAssignation(matrix);
        int numLinesCovered = 0;


        //Mark rows without an assigment
        Arrays.fill(rowCover, true);
        for (int i = 0; i < partialAssignation.size(); ++i) {
            int rowA = partialAssignation.get(i).x; 
            rowCover[rowA] = false;
        }

        //Mark cols where a 0 exists in a marked row
        Arrays.fill(colCover, false);
        for (int i = 0; i < matrix.length; ++i) {
            if(rowCover[i]) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    if (matrix[i][j] == 0) colCover[j] = true;
                    ++numLinesCovered;
                }
            }
        }

        //Mark rows with the assignment in a marked col
        for (int i = 0; i < partialAssignation.size(); ++i) {
            int rowA = partialAssignation.get(i).x;
            int colA = partialAssignation.get(i).y;
            if (colCover[colA]) rowCover[rowA] = true;
        }

        //Finally, invert the values of the row covering
        for (int i = 0; i < rowCover.length; ++i) {
            rowCover[i] = !rowCover[i];
            if (rowCover[i]) ++numLinesCovered;
        }

        return numLinesCovered;
    }

}