package main.dominio;

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
        /* ESTO ES DEL ENEKO
        CtrlEntrada ce = new CtrlEntrada();
        CtrlAlgoritmo ca = new CtrlAlgoritmo();

        Alfabeto alf = ce.cargarAlfabeto(nombreAlfabeto);
        PhysicalLayout pl = ce.crearPhysicalLayout(alf.size());

        //LSL = "Lista de sucesores a letras" ex. "mercadona" la e sucede a la m una vez
        Vector<Vector<Vector<pair<Integer>>>> lslConj = ce.cargarLSL(nombresTLP);

        Vector<Vector<Double>> lslGlobal = ca.calcularTLPGlobal(lslConj);
        
        Vector<Integer> layout = ca.crearLayout(pl.distanceMatrix(), lslGlobal, nombreAlgoritmo);

        ce.insertarTeclado(nombreTeclado, layout, alf);
        HASTA AQUI LO DEL ENEKO xd*/

        String alfabeto = "";
        Vector<String> textos = new Vector<String>();
        Vector<Map<String, Integer>> listas = new Vector<>();

        /*
        alfabeto = ctrlE.getInput(nombreAlfabeto);
        for (int i = 0; i < nombresTLP.length: ++i) {
        	
        	String nombreTLP = nombresTLP.elementAt(i);
        	String type = ctrlE.getType(nombreTLP);

        	if (type == "Texto") textos.addElement(ctrlE.getInput(nombreTLP));
        	else if (type == "ListaPalabras") listas.addElement(ctrlE.getInput(nombreTLP));
    		else System.out.println("El nombre "+nombreTLP+" no pertenece a ninguna lista ni a ningun texto");
    	}
		*/

        //Vector<Pair<double, double>> playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto);

        int[] layout = ctrlA.usarQAP(textos, alfabeto);

        //ctrlE.setLayout(nombreTeclado, nombreAlgoritmo, layout);

    }

    public void borrarTeclado(String nombreTeclado) {
        //ctrlE.borrarTeclado(nombreTeclado);
    }

    public void importarAlfabeto(String nombreAlfabeto, String alfabeto) {
        //ctrlE.importarAlfabeto(nombreAlfabeto, alfabeto);
    }

    public void importarTexto(String nombreTexto, String texto) {
        //ctrlE.importarTexto(nombreTexto, texto);
    }

    public void importarListaPalabras(String nombreLista, Map<String, Integer> lista) {
    	//ctrlE.importarListaPalabras(nombreLista, lista);
    }

    public void modificarTeclado(String nombreTeclado, String alfabeto, Vector<String> textos) {
    	String algoritmo = "";
    	//algoritmo = ctrlE.getAlgoritmo(nombreTeclado);
    	crearTeclado(nombreTeclado, alfabeto, textos, algoritmo);
    }

    public void modificarTeclado(String nombreTeclado, Vector<String> textos) {
    	String alfabeto = "";
    	//alfabeto = ctrlE.getAlfabeto(nombreTeclado);
    	modificarTeclado(nombreTeclado, alfabeto, textos);
    }

    public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo) {
    	//ctrlE.modificarAlfabeto(nombreAlfabeto, alfabetoNuevo);
    }

    public void modificarTexto(String nombreTexto, String textoNuevo) {
    	//ctrlE.modificarTexto;
    }

    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
    	//ctrlE.modificarListaPalabras(noombreLista, listaNueva);
    }


}
