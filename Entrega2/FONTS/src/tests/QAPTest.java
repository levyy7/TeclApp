package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.awt.Point;

import main.dominio.algoritmos.*;

import org.junit.*;

public class QAPTest {

    @Test
    public void testQAPn4() {
        EstrategiaCreacionLayout algoritmo = new QAP();

        //Input para QAP n = 4
        double[][] distLoc = {
            {0, 2, 3, 1},
            {2, 0, 1, 4},
            {3, 1, 0, 2},
            {1, 4, 2, 0}
        };
        int[][] traficoInst = {
            {0, 1, 2, 3},
            {1, 0, 4, 2},
            {2, 4, 0, 1},
            {3, 2, 1, 0}
        };

        ArrayList<Point> actualList = algoritmo.crearLayout(distLoc, traficoInst);

        double actualValue = totalCostSolution(distLoc, traficoInst, actualList);

        
        double expectedValue = 22.0;


        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void testQAPn10() {
        EstrategiaCreacionLayout algoritmo = new QAP();

        //Input para QAP n = 10
        int[][] traficoInst = {
            {0, 13, 11, 12, 14, 15, 12, 11, 13, 14},
            {13, 0, 2, 1, 2, 3, 1, 2, 4, 2},
            {11, 2, 0, 4, 1, 2, 3, 1, 2, 1},
            {12, 1, 4, 0, 2, 1, 4, 2, 1, 3},
            {14, 2, 1, 2, 0, 3, 1, 3, 2, 2},
            {15, 3, 2, 1, 3, 0, 2, 4, 1, 1},
            {12, 1, 3, 4, 1, 2, 0, 3, 1, 2},
            {11, 2, 1, 2, 3, 4, 3, 0, 2, 4},
            {13, 4, 2, 1, 2, 1, 1, 2, 0, 3},
            {14, 2, 1, 3, 2, 1, 2, 4, 3, 0}
          };

      double[][] distLoc = {
            {0, 11, 12, 13, 14, 15, 16, 17, 18, 19},
            {11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
            {12, 1, 0, 1, 2, 3, 4, 5, 6, 7},
            {13, 2, 1, 0, 1, 2, 3, 4, 5, 6},
            {14, 3, 2, 1, 0, 1, 2, 3, 4, 5},
            {15, 4, 3, 2, 1, 0, 1, 2, 3, 4},
            {16, 5, 4, 3, 2, 1, 0, 1, 2, 3},
            {17, 6, 5, 4, 3, 2, 1, 0, 1, 2},
            {18, 7, 6, 5, 4, 3, 2, 1, 0, 1},
            {19, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

        ArrayList<Point> actualList = algoritmo.crearLayout(distLoc, traficoInst);

        double actualValue = totalCostSolution(distLoc, traficoInst, actualList);

        
        double expectedValue = 834.0;


        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void testQAPOptimizedn4() {
        EstrategiaCreacionLayout algoritmo = new QAPOptimized();

        //Input para QAP n = 4
        double[][] distLoc = {
            {0, 2, 3, 1},
            {2, 0, 1, 4},
            {3, 1, 0, 2},
            {1, 4, 2, 0}
        };
        int[][] traficoInst = {
            {0, 1, 2, 3},
            {1, 0, 4, 2},
            {2, 4, 0, 1},
            {3, 2, 1, 0}
        };

        ArrayList<Point> actualList = algoritmo.crearLayout(distLoc, traficoInst);

        double actualValue = totalCostSolution(distLoc, traficoInst, actualList);

        
        double expectedValue = 22.0;

        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void testQAPOptimizedn10() {
        EstrategiaCreacionLayout algoritmo = new QAPOptimized();

        //Input para QAP n = 10
        int[][] traficoInst = {
            {0, 13, 11, 12, 14, 15, 12, 11, 13, 14},
            {13, 0, 2, 1, 2, 3, 1, 2, 4, 2},
            {11, 2, 0, 4, 1, 2, 3, 1, 2, 1},
            {12, 1, 4, 0, 2, 1, 4, 2, 1, 3},
            {14, 2, 1, 2, 0, 3, 1, 3, 2, 2},
            {15, 3, 2, 1, 3, 0, 2, 4, 1, 1},
            {12, 1, 3, 4, 1, 2, 0, 3, 1, 2},
            {11, 2, 1, 2, 3, 4, 3, 0, 2, 4},
            {13, 4, 2, 1, 2, 1, 1, 2, 0, 3},
            {14, 2, 1, 3, 2, 1, 2, 4, 3, 0}
          };

      double[][] distLoc = {
            {0, 11, 12, 13, 14, 15, 16, 17, 18, 19},
            {11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
            {12, 1, 0, 1, 2, 3, 4, 5, 6, 7},
            {13, 2, 1, 0, 1, 2, 3, 4, 5, 6},
            {14, 3, 2, 1, 0, 1, 2, 3, 4, 5},
            {15, 4, 3, 2, 1, 0, 1, 2, 3, 4},
            {16, 5, 4, 3, 2, 1, 0, 1, 2, 3},
            {17, 6, 5, 4, 3, 2, 1, 0, 1, 2},
            {18, 7, 6, 5, 4, 3, 2, 1, 0, 1},
            {19, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

        ArrayList<Point> actualList = algoritmo.crearLayout(distLoc, traficoInst);

        double actualValue = totalCostSolution(distLoc, traficoInst, actualList);

        
        double expectedValue = 834.0;


        assertEquals(expectedValue, actualValue, expectedValue*0.1);
    }

     @Test
    public void testGeneticn4() {
        EstrategiaCreacionLayout algoritmo = new Algoritmo2();

        //Input n = 4
        double[][] distLoc = {
            {0, 2, 3, 1},
            {2, 0, 1, 4},
            {3, 1, 0, 2},
            {1, 4, 2, 0}
        };
        int[][] traficoInst = {
            {0, 1, 2, 3},
            {1, 0, 4, 2},
            {2, 4, 0, 1},
            {3, 2, 1, 0}
        };

        ArrayList<Point> actualList = algoritmo.crearLayout(distLoc, traficoInst);

        double actualValue = totalCostSolution(distLoc, traficoInst, actualList);

        
        double expectedValue = 22.0;


        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void testGeneticn10() {
        EstrategiaCreacionLayout algoritmo = new Algoritmo2();

        //Input n = 10
        int[][] traficoInst = {
            {0, 13, 11, 12, 14, 15, 12, 11, 13, 14},
            {13, 0, 2, 1, 2, 3, 1, 2, 4, 2},
            {11, 2, 0, 4, 1, 2, 3, 1, 2, 1},
            {12, 1, 4, 0, 2, 1, 4, 2, 1, 3},
            {14, 2, 1, 2, 0, 3, 1, 3, 2, 2},
            {15, 3, 2, 1, 3, 0, 2, 4, 1, 1},
            {12, 1, 3, 4, 1, 2, 0, 3, 1, 2},
            {11, 2, 1, 2, 3, 4, 3, 0, 2, 4},
            {13, 4, 2, 1, 2, 1, 1, 2, 0, 3},
            {14, 2, 1, 3, 2, 1, 2, 4, 3, 0}
          };

      double[][] distLoc = {
            {0, 11, 12, 13, 14, 15, 16, 17, 18, 19},
            {11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
            {12, 1, 0, 1, 2, 3, 4, 5, 6, 7},
            {13, 2, 1, 0, 1, 2, 3, 4, 5, 6},
            {14, 3, 2, 1, 0, 1, 2, 3, 4, 5},
            {15, 4, 3, 2, 1, 0, 1, 2, 3, 4},
            {16, 5, 4, 3, 2, 1, 0, 1, 2, 3},
            {17, 6, 5, 4, 3, 2, 1, 0, 1, 2},
            {18, 7, 6, 5, 4, 3, 2, 1, 0, 1},
            {19, 8, 7, 6, 5, 4, 3, 2, 1, 0}};

        ArrayList<Point> actualList = algoritmo.crearLayout(distLoc, traficoInst);

        double actualValue = totalCostSolution(distLoc, traficoInst, actualList);

        
        double expectedValue = 834.0;


        assertEquals(expectedValue, actualValue, 0.01);
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

        return sum;
    }

    private double costBtw2Assig(double[][] distLoc, int[][] traficoInst, Point a, Point b) {
        return (distLoc[a.x][b.x]*traficoInst[a.y][b.y]);
    }
}
