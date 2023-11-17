package main.dominio.algoritmos;

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

public class QAP implements EstrategiaCreacionLayout {

    public ArrayList<Point> crearLayout(
        double[][] distLoc,
        int[][] traficoInst) {

        
        return branchAndBound(distLoc, traficoInst);
    }

    
    protected ArrayList<Point> branchAndBound(double[][] distLoc, int[][] traficoInst) {
        int n = distLoc.length;
        Node bestSol = new Node(initialBound(distLoc, traficoInst), n, n); //greedySolution(distLoc, traficoInst)
        Point e = new Point(-1, -1);
        bestSol.partialSol.add(e);
        PriorityQueue<Node> pq = //Seguramente priority_queue
          new PriorityQueue<>((a, b) -> Double.compare(b.bound, a.bound));

        Node u = new Node(0.0, 0, n);
        pq.offer(u);

        while (pq.size() != 0) {
            u = pq.poll(); 
            System.out.print("(" + u.level + " " + u.bound + " " + u.cost + ")");
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
                        System.out.print(" Entro generate bound ");
                        v.bound = generateBound(distLoc, traficoInst, v);
                        System.out.print(" (" + i + " " + v.cost + " " + v.bound + " " + bestSol.cost + ")");System.out.println();

                        if (v.bound < bestSol.cost) {
                            pq.add(v);
                        } 
                    }
                }
            }
        }

        return bestSol.partialSol;
    }


    protected double initialBound(double[][] distLoc, int[][] traficoInst) {
        return 0.0;
    }

    
    protected double costPlaceInst(double[][] distLoc, int[][] traficoInst, ArrayList<Point> partialSol, Point newPlacement) {
        double sumCosts = 0;

        for (int i = 0; i < partialSol.size(); ++i) {
            sumCosts += costBtw2Assig(distLoc, traficoInst, partialSol.get(i), newPlacement);
        }

        return sumCosts;
    }


    protected double costBtw2Assig(double[][] distLoc, int[][] traficoInst, Point a, Point b) {
        return (distLoc[a.x][b.x]*traficoInst[a.y][b.y]);
    }


    protected double generateBound(double[][] distLoc, int[][] traficoInst, Node u) {
        return u.cost;
    }

}