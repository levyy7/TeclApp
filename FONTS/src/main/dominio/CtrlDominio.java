package main.dominio;
import Excepcions.*;
import main.dominio.*;

import java.util.ArrayList;
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
            Vector<Map<String, Integer>> listas, Vector<String> nombresTLP,
            String alfabeto) {

        for (int i = 0; i < nombresTLP.size(); ++i) {
            
            String nombreTLP = nombresTLP.elementAt(i);
            String type = "";

            try {type = ctrlE.getType(nombreTLP);}
            catch (InputInexistente e) 
                {System.out.println("Error: "+e.getMessage()); return false;}

            if (type == "Texto") {
                String texto = ctrlE.getTexto(nombreTLP);
                textos.addElement(texto);
            }
            else if (type == "Lista de Palabras") {
                Map<String, Integer> lista = ctrlE.getListaPalabras(nombreTLP);
                listas.addElement(lista);
            }
        }

        try {
            ctrlE.compruebaTextos(textos, alfabeto);
            ctrlE.compruebaListas(listas, alfabeto);
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
                Point2D[] playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto);
                char[] layout = ctrlA.usarQAP(textos, listas, alfabeto, playout);

                ctrlE.setLayout(nombreTeclado, nombreAlgoritmo, layout);   
            }
            catch (NGrande e)
                {System.out.println("Error: "+e.getMessage()); return;}
            catch (TecladoYaExiste e) 
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
            try {
                borrarTeclado(nombreTeclado);

                Point2D[] playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto);
                char[] layout = ctrlA.usarQAP(textos, listas, alfabeto, playout);

                ctrlE.setLayout(nombreTeclado, nombreAlgoritmo, layout);   
            }
            catch (NGrande e)
                {System.out.println("Error: "+e.getMessage()); return;}
            catch (TecladoYaExiste e) //Aqui nunca entrara
                {return;}
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
        String tipo = "";
        try {tipo = ctrlE.getType(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Alfabeto")
            {System.out.println("Error: "+"el alfabeto no existe"); return;}

        try {ctrlE.modificarAlfabeto(nombreAlfabeto, alfabetoNuevo);}
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (InputInexistente e) //Aqui nunca entrara
            {return;}
        catch (InputJaCreat e) //Aqui nunca entrara
            {return;}
    }

    public void modificarTexto(String nombreTexto, String textoNuevo) {
    	String tipo = "";
        try {tipo = ctrlE.getType(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Texto")
            {System.out.println("Error: "+"el texto no existe"); return;}
        
        try {ctrlE.modificarTexto(nombreTexto, textoNuevo);}
        catch (InputInexistente e) //Aqui nunca entrara
            {return;}
        catch (InputJaCreat e) //Idem
            {return;}
    }

    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
    	String tipo = "";
        try {tipo = ctrlE.getType(nombreLista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Lista de Palabras")
            {System.out.println("Error: "+"la lista no existe"); return;}

        try {ctrlE.modificarListaPalabras(nombreLista, listaNueva);}
        catch (InputInexistente e) //Aqui nunca entrara
            {return;}
        catch (InputJaCreat e) //Idem
            {return;}
    }

    public void borrarAlfabeto(String nombreAlfabeto) {
        String tipo = "";
        try {tipo = ctrlE.getType(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Alfabeto")
            {System.out.println("Error: "+"el alfabeto no existe"); return;}

        try {ctrlE.borrarAlfabeto(nombreAlfabeto);}
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    public void borrarTexto(String nombreTexto) {
        String tipo = "";
        try {tipo = ctrlE.getType(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Texto")
            {System.out.println("Error: "+"el texto no existe"); return;}

        ctrlE.borrarTexto(nombreTexto);
    }

    public void borrarListaPalabras(String nombreLista) {
        String tipo = "";
        try {tipo = ctrlE.getType(nombreLista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Lista de Palabras")
            {System.out.println("Error: "+"la lista no existe"); return;}

        ctrlE.borrarListaPalabras(nombreLista);
    }

}
//auto Miguel