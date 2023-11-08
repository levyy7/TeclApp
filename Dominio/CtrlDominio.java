import java.util.*;

public class CtrlDominio {

    //private CtrlEntrada ctrlE;
    private CtrlAlgoritmo ctrlA;
    //private CtrlPersistencia ctrlP;

    public CtrlDominio() {
        //ctrlE = new CtrlEntrada();
        ctrlA = new CtrlAlgoritmo();
    }

    public void crearTeclado(String nombreTeclado, String nombreAlfabeto, Vector<String> nombresTLP, String nombreAlgoritmo) {
        /*
        CtrlEntrada ce = new CtrlEntrada();
        CtrlAlgoritmo ca = new CtrlAlgoritmo();

        Alfabeto alf = ce.cargarAlfabeto(nombreAlfabeto);
        PhysicalLayout pl = ce.crearPhysicalLayout(alf.size());

        //LSL = "Lista de sucesores a letras" ex. "mercadona" la e sucede a la m una vez
        Vector<Vector<Vector<pair<Integer>>>> lslConj = ce.cargarLSL(nombresTLP);

        Vector<Vector<Double>> lslGlobal = ca.calcularTLPGlobal(lslConj);
        
        Vector<Integer> layout = ca.crearLayout(pl.distanceMatrix(), lslGlobal, nombreAlgoritmo);

        ce.insertarTeclado(nombreTeclado, layout, alf);
        */

        String alfabeto = "";
        Vector<String> textos = new Vector<String>();

        //alfabeto = ctrlE.getInput(nombreAlfabeto);
        //for (int i = 0; i < nombresTLP.length: ++i) textos.addElement(ctrlE.getInput(nombresTLP.elementAt(i)));

        int[] layout = ctrlA.usarAlgoritmo(textos, alfabeto);

    }

    public void borrarTeclado(String nombreTeclado) {
        //ctrlE.borrarTeclado(nombreTeclado);
    }

    public void importarAlfabeto() {
        //ctrlE.importarAlfabeto();
    }

    public void importarTexto() {
        //ctrlE.importarTexto();
    }

    public void modificarTeclado(String nombreTeclado, String alfabeto, Vector<String> textos) {

    }

    public void modificarTeclado(String nombreTeclado, Vector<String> textos) {

    }

    public void modificarTexto(String nombreTexto) {

    }


}
