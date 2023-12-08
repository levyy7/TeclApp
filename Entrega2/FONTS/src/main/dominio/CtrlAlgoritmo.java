package main.dominio;
import main.dominio.algoritmos.*;

import java.util.*;

import Excepcions.InvalidAlgorithm;

import java.awt.geom.Point2D;
import java.awt.Point;

/**
 * Controlador de Algoritmo
 * Controlador que une la informacion necesaria para utilizar las
 * funcionalidades principales de los algoritmos
 * @author Miguel Ángel Montero Flores
*/
public class CtrlAlgoritmo {

    /** Contiene la instancia de la clase QAPOptimized*/
    

    /** Contiene la instancia de la clase Algoritmo2*/
    //private static EstrategiaCreacionLayout alg2;

    /** Contiene la instancia de la clase UtilesAlgoritmo*/
    private static UtilesAlgoritmo utA;

    public CtrlAlgoritmo() {
        utA = new UtilesAlgoritmo();
        //qapO = new QAPOptimized();
    }  

    /**
     * Función que inicializa el mapa de posiciones de los caracteres del
     * alfabeto
     * @param alfabeto
     * @return Map(Character, Integer) : mapa de posiciones
    */
    //Mapa de posiciones de las letras de un alfabeto
    private Map<Character, Integer> inicializaAlfabeto(String alfabeto) {

        Map<Character, Integer> map = new HashMap<>();

        char[] letrasDelAlfabeto = alfabeto.toCharArray();

        for (int i = 0; i < letrasDelAlfabeto.length; ++i) 
            map.put(letrasDelAlfabeto[i], i);
        
        return map;
    }

    /**
     * Función que devuelve la letra correspondiente de la posicion pos
     * del mapa de posiciones
     * @param posiciones : mapa de posiciones
     * @param pos : posicion a consultar
     * @return Character : caracter correspondiente de la posicion pos
    */
    private Character getLetra(Map<Character, Integer> posiciones, int pos) {

        for (Map.Entry<Character, Integer> m : posiciones.entrySet()) 
            if (m.getValue() == pos) return m.getKey();
        //Nunca llegará aquí el return
        return 'a';
    }

    /**
     * Función que calcula el Layout resultante de un teclado juntando
     * el output de los algoritmos usados y el mapa de posiciones
     * @param layoutA : output de algun algoritmo
     * @param posiciones : mapa de posiciones
     * @return char[] : array de caracteres que corresponde al layout del
     *                  teclado
    */
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

    /**
     * Función que calcula el layout resultante de un teclado al utilizar QAP
     * @param textos : textos usados para la creacion del layout
     * @param listas : listas usadas para la creacion del layout
     * @param alfabeot : afabeto usado para la creacion del layout
     * @param playout : physical layout
     * @return char[] : layout resultante
    */
    public char[] calcularLayout(Vector<String> textos, 
        Vector<Map<String, Integer>> listas, String alfabeto, 
        Point2D[] playout, String tipoAlgoritmo) throws InvalidAlgorithm {
        
        EstrategiaCreacionLayout alg;

        if (tipoAlgoritmo == "QAP") alg = new QAPOptimized();
        else if (tipoAlgoritmo == "GEN") alg = new AG();
        else throw new InvalidAlgorithm();

        Map<Character, Integer> posiciones = new HashMap<>();
        posiciones = inicializaAlfabeto(alfabeto);

        int[][] traficoInt = utA.calculoTraficoInt(textos, listas, posiciones);

        double[][] distLoc = utA.calculoDistLoc(playout);

        ArrayList<Point> layoutA = alg.crearLayout(distLoc, traficoInt);

        return calculoLayout(layoutA, posiciones);
    }

}
//autor Miguel