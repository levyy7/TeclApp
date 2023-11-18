package main.dominio;
import main.dominio.algoritmos.*;

import java.util.*;

public class CtrlAlgoritmo {
    /* ESTO ES DEL ENEKO
    public Vector< Pair< Pair<Double,Double>, Char>> crearLayout(
        Vector<Vector<Double>> tlp,
        Vector<Vector<Pair<String, Integer>>> listaP, 
        TipoAlg tipo) {

        EstrategiaCreacionLayout algoritmo;

        if (tipo == TipoAlg.QAPBB) algoritmo = new QAPBasic();
        else algoritmo = new Algoritmo2();

        /*
            Input:
                matrixDist: Cada linea repr una tecla y dentro de la linea la dist a las otras 
                teclas -> Simetrica respeto a la diagonal.
                matrixTraf: Cada linea repr un simbolo del alfabeto

        AQUI SE ACABA LO DEL ENEKO*/
    //    Vector<Integer> sol = algoritmo.usarAlgoritmo(matrixDist, matrixTraf);

    //}    

    public int[] usarQAP(Vector<String> texto, String alfabeto) {

        EstrategiaCreacionLayout estC;
        UtilesAlgoritmo ctL = new UtilesAlgoritmo();

        int[][] traficoInt = ctL.CalculoTraficoInt(texto, alfabeto);

        //double[][] distLoc = ctL.distLoc();

        //return estC.crearLayout(distLoc, traficoInt);
        return new int[2]; //Pa k compile
    }



}
