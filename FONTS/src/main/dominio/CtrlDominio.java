package main.dominio;
import Excepcions.*;
import main.dominio.*;

import java.util.ArrayList;
import java.util.*;
import java.awt.geom.Point2D;

public class CtrlDominio {

    private static CtrlEntrada ctrlE;
    private static CtrlAlgoritmo ctrlA;

    public CtrlDominio() {
        ctrlE = new CtrlEntrada();
        ctrlA = new CtrlAlgoritmo();

    }

    //Consulta de todos los nombres de los teclados
    public HashMap<String, Teclado> consultarTeclados() {
        return ctrlE.getTeclados();
    }

    //Consulta de un teclado
    public Teclado consultarTeclado(String nombreTeclado) {
        return ctrlE.getTeclado(nombreTeclado);
    }

    //Consulta de todos nombres de los alfabetos
    public HashMap<String, Input> consultarAlfabetos() {
        return ctrlE.getAlfabetos();
    }

    //Consulta de todos nombres de los textos
    public HashMap<String, Input> consultarTextos() {
        return ctrlE.getTextos();
    }

    //Consulta de todos nombres de las listas
    public HashMap<String, Input> consultarListas() {
        return ctrlE.getListas();
    }

    //Consulta de un alfabeto
    public String consultarAlfabeto(String nombreAlfabeto) {
        String alfabeto = "";
        try {ctrlE.getAlfabeto(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return "";}
        return alfabeto;
    }

    //Consulta de un texto
    public String consultarTexto(String nombreTexto) {
        return ctrlE.getTexto(nombreTexto);
    }

    //Consulta de una lista
    public Map<String, Integer> consultarLista(String nombreLista) {
        return ctrlE.getListaPalabras(nombreLista);
    }

    //Funcion que asigna a los vectores textos y listas los textos y las
    //listas que corresponden a los nombresTLP. Tambien comprueba si
    //se corresponden con el alfabeto
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

    //Caso de uso: crear teclado
    public void crearTeclado(String nombreTeclado, String nombreAlfabeto, 
           Vector<String> nombresTLP, String nombreAlgoritmo) {
                
        String tipo = "";
        try {tipo = ctrlE.getType(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        String alfabeto = "";
        if (tipo == "Alfabeto") {
            try {alfabeto = ctrlE.getAlfabeto(nombreAlfabeto);}
            catch (InputInexistente e)
                {System.out.println("Error: "+e.getMessage()); return;}
        }
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

    //Caso de uso: borrar teclado
    public void borrarTeclado(String nombreTeclado) {
        try {ctrlE.borrarTeclado(nombreTeclado);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

    }

    //Caso de uso: importar alfabeto
    public void importarAlfabeto(String nombreAlfabeto, String alfabeto) {
        try {ctrlE.importarAlfabeto(nombreAlfabeto, alfabeto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    //Caso de uso: importar texto
    public void importarTexto(String nombreTexto, String texto) {
        try {ctrlE.importarTexto(nombreTexto, texto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    //Caso de uso: importar lista de palabras
    public void importarListaPalabras(String nombreLista, Map<String, Integer> lista) {
        try {ctrlE.importarListaPalabras(nombreLista, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    //Caso de uso: modificar teclado
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
        if (tipo == "Alfabeto") {
            try{alfabeto = ctrlE.getAlfabeto(nombreAlfabeto);}
            catch (InputInexistente e)
                {System.out.println("Error: "+e.getMessage()); return;}
        }
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

    //Caso de uso: modificar teclado sin elegir alfabeto
    public void modificarTeclado(String nombreTeclado, Vector<String> textos) {
    	String alfabeto = "";
    	try {alfabeto = ctrlE.getAlfabetoTeclado(nombreTeclado);}
    	catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        modificarTeclado(nombreTeclado, alfabeto, textos);
    }

    //Caso de uso: modificar alfabeto
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
    }

    //Caso de uso: modificar texto
    public void modificarTexto(String nombreTexto, String textoNuevo) {
    	String tipo = "";
        try {tipo = ctrlE.getType(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Texto")
            {System.out.println("Error: "+"el texto no existe"); return;}
        
        ctrlE.modificarTexto(nombreTexto, textoNuevo);
    }

    //Caso de uso: lista de palabras
    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
    	String tipo = "";
        try {tipo = ctrlE.getType(nombreLista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Lista de Palabras")
            {System.out.println("Error: "+"la lista no existe"); return;}

        ctrlE.modificarListaPalabras(nombreLista, listaNueva);
    }

    //Caso de uso: borrar alfabeto
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

    //Caso de uso: borrar texto
    public void borrarTexto(String nombreTexto) {
        String tipo = "";
        try {tipo = ctrlE.getType(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Texto")
            {System.out.println("Error: "+"el texto no existe"); return;}

        ctrlE.borrarTexto(nombreTexto);
    }

    //Caso de uso: borrar lista de palabras
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
//autor Miguel