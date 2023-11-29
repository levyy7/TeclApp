package tests;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.*;

import main.dominio.UtilesAlgoritmo;

public class UtilesAlgoritmoTest {
    
    @Test
    public void testCalculoDistLoc() {
        UtilesAlgoritmo algoritmo = new UtilesAlgoritmo();

        //Input para CalculoDistLoc n = 4
        Point2D[] playout = {
            new Point2D.Double(0.0, 0.0),
            new Point2D.Double(0.0, 1.0),
            new Point2D.Double(1.0, 0.0),
            new Point2D.Double(1.0, 1.0),
        };

        double[][] actualMatrix = algoritmo.calculoDistLoc(playout);
        
        double[][] expectedMatrix = {
            {0.0, 1.0, 1.0, Math.sqrt(2)},
            {1.0, 0.0, Math.sqrt(2), 1.0},
            {1.0, Math.sqrt(2), 0.0, 1.0},
            {Math.sqrt(2), 1.0, 1.0, 0.0}
        };

        for (int i = 0; i < actualMatrix.length; ++i) {
            assertArrayEquals(expectedMatrix[i], actualMatrix[i], 0.01);
        }
        
    }

    @Test
    public void testCalculoTraficoInt() {
        UtilesAlgoritmo algoritmo = new UtilesAlgoritmo();

        //Input para CalculoDistLoc n = 4
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('q', 0);
            put('w', 1);
            put('e', 2);
            put('r', 3);
            put(' ', 4);
        }};

        Vector<Map<String, Integer>> listas = new Vector<>(); 
        listas.add(new HashMap<String, Integer>() {{
            put("weqr", 6);
            put("werq", 4);
        }});

        Vector<String> textos = new Vector<>();
        textos.add("qwerrrr qwweqqwer");
        textos.add("qweqwewqeqweqwe qwweqeqwew");
        textos.add("q q q w w w e e e r r r");


        int[][] actualMatrix = algoritmo.calculoTraficoInt(textos, listas, map);

        int[][] expectedMatrix = {
            {2, 10, 14, 10, 7},
            {10, 4, 21, 0, 6},
            {14, 21, 0, 6, 7},
            {10, 0, 6, 6, 6},
            {7, 6, 7, 6, 0}
        };

        for (int i = 0; i < actualMatrix.length; ++i) {
            assertArrayEquals(expectedMatrix[i], actualMatrix[i]);
        }
        
    }

    
}
