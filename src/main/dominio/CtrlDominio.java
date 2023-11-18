package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

public class CtrlDominio {

    private static CtrlEntrada ctrlE;
    private static CtrlAlgoritmo ctrlA;
    //private static CtrlPersistencia ctrlP;

    public CtrlDominio() {
        ctrlE = new CtrlEntrada();
        ctrlA = new CtrlAlgoritmo();
        //ctrlP = new CtrlPersistencia();

        //ctrlP pillar datos
    }

    //Sacar datos de persistencia
    public void inicializar() {

    }

    public void crearTeclado(String nombreTeclado, String nombreAlfabeto, Vector<String> nombresTLP, String nombreAlgoritmo) {
        
        String alfabeto = "";
        Vector<String> textos = new Vector<String>();
        Vector<Map<String, Integer>> listas = new Vector<>();
        /*
        alfabeto = ctrlE.getAlfabeto(nombreAlfabeto);
        
        for (int i = 0; i < nombresTLP.length; ++i) {
        	
        	String nombreTLP = nombresTLP.elementAt(i);
        	String type = ctrlE.getType(nombreTLP);

        	if (type == "Texto") {
                String texto = ctrlE.getTexto(nombreTLP);
                textos.addElement(texto);
            }
        	else if (type == "ListaPalabras") {
                String lista = ctrlE.getListaPalabras(nombreTLP);
                listas.addElement(lista);
            }
    		else System.out.println("El nombre "+nombreTLP+" no pertenece a ninguna lista ni a ningun texto");
    	}

        Point2D[] playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto);
        
        if (nombreAlgoritmo == "QAP")
            char[] layout = ctrlA.usarQAP(textos, listas, alfabeto, playout);

        ctrlE.setLayout(nombreTeclado, nombreAlgoritmo, layout);
        
        */

    }

    public void borrarTeclado(String nombreTeclado) {
        ctrlE.borrarTeclado(nombreTeclado);
    }

    public void importarAlfabeto(String nombreAlfabeto, String alfabeto) {
        ctrlE.importarAlfabeto(nombreAlfabeto, alfabeto);
    }

    public void importarTexto(String nombreTexto, String texto) {
        ctrlE.importarTexto(nombreTexto, texto);
    }

    public void importarListaPalabras(String nombreLista, Map<String, Integer> lista) {
        ctrlE.importarListaPalabras(nombreLista, lista);
    }

    public void modificarTeclado(String nombreTeclado, String alfabeto, Vector<String> textos) {
    	String algoritmo = "";
    	algoritmo = ctrlE.getAlgoritmo(nombreTeclado);
    	crearTeclado(nombreTeclado, alfabeto, textos, algoritmo);
    }

    public void modificarTeclado(String nombreTeclado, Vector<String> textos) {
    	String alfabeto = "";
    	alfabeto = ctrlE.getAlfabeto(nombreTeclado);
    	modificarTeclado(nombreTeclado, alfabeto, textos);
    }

    public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo) {
        ctrlE.modificarAlfabeto(nombreAlfabeto, alfabetoNuevo);
    }

    public void modificarTexto(String nombreTexto, String textoNuevo) {
    	ctrlE.modificarTexto(nombreTexto, textoNuevo);
    }

    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
    	ctrlE.modificarListaPalabras(noombreLista, listaNueva);
    }


}
