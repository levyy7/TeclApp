package main.dominio;
import Exceptions.*;

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

    private boolean asignarTextosYListas(Vector<String> textos, 
            Vector<Map<String, Integer>> listas, String nombresTLP,
            String alfabeto) {

        for (int i = 0; i < nombresTLP.length(); ++i) {
            
            String nombreTLP = nombresTLP.elementAt(i);
            String type = "";

            try {type = ctrlE.getType(nombreTLP);}
            catch (InputIncorrecte e) 
                {System.out.println("Error: "+e.getMessage()); return;}

            if (type == "Texto") {
                String texto = ctrlE.getTexto(nombreTLP);
                textos.addElement(texto);
            }
            else if (type == "Lista de Palabras") {
                String lista = ctrlE.getListaPalabras(nombreTLP);
                listas.addElement(lista);
            }
        }

        try {
            compruebaTextos(textos, alfabeto);
            compruebaListas(listas, alfabeto);
        }
        catch (TextoNoValido e) 
            {System.out.println("Error: "+e.getMessage()); return false;}
        catch (ListaNoValida e)
            {System.out.println("Error: "+e.getMessage()); return false;}
        return true;
    }

    public void crearTeclado(String nombreTeclado, String nombreAlfabeto, 
           Vector<String> nombresTLP, String nombreAlgoritmo) {
        
        
        String tipo = "";
        try {tipo = ctrlE.getType(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        String alfabeto = "";
        if (tipo == "Alfabeto") alfabeto = ctrlE.getAlfabeto(nombreAlfabeto);
        else {System.out.println("El alfabeto no existe"); return;}

        Vector<String> textos = new Vector<String>();
        Vector<Map<String, Integer>> listas = new Vector<>();
        if (asignarTextosYListas(textos, listas, nombresTLP, alfabeto) == false)
            return;

        if (nombreAlgoritmo == "QAP") {
            try {
                
                try {
                    Point2D[] playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto, alfabeto.length());
                    char[] layout = ctrlA.usarQAP(textos, listas, alfabeto, playout);

                    ctrlE.setLayout(nombreTeclado, nombreAlgoritmo, layout);
                }
                catch (NGrande e)
                    {System.out.println("Error: "+e.getMessage()); return;}
            }
            catch (TecladoYaExistente e) 
                {System.out.println("Error: "+e.getMessage()); return;}
        }

        else System.out.println("Error: "+"El algoritmo no existe");

    }

    public void borrarTeclado(String nombreTeclado) {
        try {ctrlE.borrarTeclado(nombreTeclado);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

    }

    public void importarAlfabeto(String nombreAlfabeto, String alfabeto) {
        try {ctrlE.importarAlfabeto(nombreAlfabeto, alfabeto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void importarTexto(String nombreTexto, String texto) {
        try {ctrlE.importarTexto(nombreTexto, texto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void importarListaPalabras(String nombreLista, Map<String, Integer> lista) {
        try {ctrlE.importarListaPalabras(nombreLista, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void modificarTeclado(String nombreTeclado, String nombreAlfabeto, Vector<String> nombresTLP) {
    	String nombreAlgoritmo = "";
    	try {nombreAlgoritmo = ctrlE.getAlgoritmo(nombreTeclado);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
    	
        String tipo = "";
        try {tipo = ctrlE.getType(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        String alfabeto = "";
        if (tipo == "Alfabeto") alfabeto = ctrlE.getAlfabeto(nombreAlfabeto);
        else {System.out.println("El alfabeto no existe"); return;}

        Vector<String> textos = new Vector<String>();
        Vector<Map<String, Integer>> listas = new Vector<>();
        asignarTextosYListas(textos, listas, nombresTLP, alfabeto);
        if (asignarTextosYListas(textos, listas, nombresTLP, alfabeto) == false)
            return;

        if (nombreAlgoritmo == "QAP") {

            ctrlE.modificaAlfabetoTeclado(nombreTeclado, nombreAlfabeto, alfabeto.length());

            Point2D[] playout = ctrlE.getPlayout(nombreTeclado);
            char[] layout = ctrlA.usarQAP(textos, listas, alfabeto, playout);

            ctrlE.setLayout(nombreTeclado, nombreAlgoritmo, layout);
        }
             
    }

    public void modificarTeclado(String nombreTeclado, Vector<String> textos) {
    	String alfabeto = "";
    	try {alfabeto = ctrlE.getAlfabetoTeclado(nombreTeclado);}
    	catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        modificarTeclado(nombreTeclado, alfabeto, textos);
    }

    public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo) {
        try {ctrlE.modificarAlfabeto(nombreAlfabeto, alfabetoNuevo);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void modificarTexto(String nombreTexto, String textoNuevo) {
    	try {ctrlE.modificarTexto(nombreTexto, textoNuevo);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
    	try {ctrlE.modificarListaPalabras(noombreLista, listaNueva);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void borrarAlfabeto(String nombreAlfabeto) {
        try {ctrlE.borrarAlfabeto(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void borrarTexto(String nombreTexto) {
        try {ctrlE.borrarTexto(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void borrarListaPalabras(String lista) {
        try {ctrlE.borrarListaPalabras(lista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }


}