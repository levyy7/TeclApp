import java.util.Vector;

public class CtrlAlgoritmo {
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

        */
        Vector<Integer> sol = algoritmo.usarAlgoritmo(matrixDist, matrixTraf);

    }    
}
