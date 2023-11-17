package main.dominio.algoritmos;

import java.util.*;
import java.awt.Point;

public interface EstrategiaCreacionLayout {
    
    public ArrayList<Point> crearLayout(
        double[][] distLoc,
        int[][] traficoInst);

}