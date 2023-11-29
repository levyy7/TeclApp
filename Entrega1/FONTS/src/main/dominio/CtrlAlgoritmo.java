package main.dominio;
import main.dominio.algoritmos.*;

import java.util.*;
import java.awt.geom.Point2D;
import java.awt.Point;

public class CtrlAlgoritmo {

    EstrategiaCreacionLayout qapO;
    //EstrategiaCreacionLayout alg2;
    UtilesAlgoritmo utA;

    public CtrlAlgoritmo() {
        utA = new UtilesAlgoritmo();
        qapO = new QAPOptimized();
    }  

    //Mapa de posiciones de las letras de un alfabeto
    private Map<Character, Integer> inicializaAlfabeto(String alfabeto) {

        Map<Character, Integer> map = new HashMap<>();

        char[] letrasDelAlfabeto = alfabeto.toCharArray();

        for (int i = 0; i < letrasDelAlfabeto.length; ++i) 
            map.put(letrasDelAlfabeto[i], i);
        
        return map;
    }

    //Devuelve la letra en la posicion pos
    private Character getLetra(Map<Character, Integer> posiciones, int pos) {

        for (Map.Entry<Character, Integer> m : posiciones.entrySet()) 
            if (m.getValue() == pos) return m.getKey();
        //Nunca llegará aquí el return
        return 'a';
    }

    //Calcula el layout resultante mirando las posiciones de las letras
    //con el mapa y la lista
    private char[] calculoLayout(ArrayList<Point> layoutA,
        Map<Character, Integer> posiciones) {

        char[] layout = new char[posiciones.size()];

        for (Point p : layoutA) {

            int posicion = p.x;
            char letra = getLetra(posiciones, p.y);
            layout[posicion] = letra;
        }

        return layout;
    }

    public char[] usarQAP(Vector<String> texto, 
        Vector<Map<String, Integer>> listas, String alfabeto, 
        Point2D[] playout) {

        Map<Character, Integer> posiciones = new HashMap<>();
        posiciones = inicializaAlfabeto(alfabeto);

        int[][] traficoInt = utA.calculoTraficoInt(texto, listas, posiciones);

        double[][] distLoc = utA.calculoDistLoc(playout);

        ArrayList<Point> layoutA = qapO.crearLayout(distLoc, traficoInt);

        return calculoLayout(layoutA, posiciones);
    }

}
//autor Miguel