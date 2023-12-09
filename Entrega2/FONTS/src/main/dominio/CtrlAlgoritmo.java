package main.dominio;
import main.dominio.algoritmos.*;

import java.util.*;

import Excepcions.InvalidAlgorithm;

import java.awt.geom.Point2D;
import java.awt.Point;

/**
 * Controlador de Algoritmo
 * Controlador que une la información necesaria para utilizar las
 * funcionalidades principales de los algoritmos
 * @author Miguel Ángel Montero Flores
*/
public class CtrlAlgoritmo {

    /** Contiene la instancia de la clase UtilesAlgoritmo*/
    private static UtilesAlgoritmo utA;

    /** Constructora de Algoritmo por default que inicializa UtilesAlgoritmo*/
    public CtrlAlgoritmo() {
        utA = new UtilesAlgoritmo();
    }  

    /**
     * Función que inicializa el mapa de posiciones de los caracteres del
     * alfabeto
     * @param alfabeto
     * @return Map(Character, Integer) : mapa de posiciones
    */
    private Map<Character, Integer> inicializaAlfabeto(String alfabeto) {

        Map<Character, Integer> map = new HashMap<>();

        char[] letrasDelAlfabeto = alfabeto.toCharArray();

        for (int i = 0; i < letrasDelAlfabeto.length; ++i) 
            map.put(letrasDelAlfabeto[i], i);
        
        return map;
    }

    /**
     * Función que devuelve la letra correspondiente de la posición pos
     * del mapa de posiciones
     * @param posiciones : mapa de posiciones
     * @param pos : posicion a consultar
     * @return Character : carácter correspondiente de la posicion pos
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
     * Función que calcula el layout resultante de un teclado
     * @param textos : textos usados para la creación del layout
     * @param listas : listas usadas para la creación del layout
     * @param alfabeto : afabeto usado para la creación del layout
     * @param playout : physical layout
     * @param tipoAlgoritmo : algoritmo a utilizar
     * @return char[] : layout resultante
     * @throws InvalidAlgorithm
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