package main.dominio.ed;

import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Point;

public class Node {
    public int level;
    public double cost, bound;
    public ArrayList<Point> partialSol;
    public boolean[] usedLoc, usedInst;
 
    public Node(double cost, int level, int size) {
        this.cost = cost;
        this.level = level;
        this.bound = 0;
        this.partialSol = new ArrayList<>();
        this.usedLoc = new boolean[size]; //can use .fill() to fill values of array
        this.usedInst = new boolean[size];
        Arrays.fill(this.usedLoc, false);
        Arrays.fill(this.usedInst, false);
    }

    public Node(double cost, int level, ArrayList<Point> partialSol, boolean[] usedLoc, boolean[] usedInst) {
        this.cost = cost;
        this.level = level;
        this.bound = 0;
        this.partialSol = new ArrayList<>(partialSol);
        this.usedLoc = usedLoc.clone(); //can use .fill() to fill values of array
        this.usedInst = usedInst.clone();
    }
}