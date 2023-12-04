package main.dominio;
import Excepcións.*;
import main.dominio.*;
import main.persistencia.CtrlPersistencia;

import java.util.ArrayList;
import java.util.*;
import java.awt.geom.Point2D;

/**
 * Controlador de dominio
 * Sirve para conectar la información del resto de controladores para ejecutar
 * ordenadamente el programa. También se encarga de saltar excepciónes
 * para asegurar el correcto Funciónamiento del mismo
 * @author Miguel Ángel Montero Flores
*/
public class CtrlDominio {

    /** Contiene la instancia del controlador de entrada*/
    private static CtrlEntrada ctrlE;

    /** Contiene la instancia del controlador de algoritmo*/
    private static CtrlAlgoritmo ctrlA;

    /** Contiene la instancia del controlador de persistencia*/
    private static CtrlPersistencia ctrlP;

    /**
     * Constructora por defecto que instancia los controladores y carga
     * los datos necesarios de persistencia
    */
    public CtrlDominio() {
        ctrlP = new CtrlPersistencia();
        String[][] teclados = ctrlP.cargarTeclados();
        String[][] alfabetos = ctrlP.cargarAlfabetos();
        String[][] textos = ctrlP.cargarTextos();
        String[][] listas = ctrlP.cargarListas();

        ctrlE = new CtrlEntrada(teclados, alfabetos, textos, listas);
        ctrlA = new CtrlAlgoritmo();
    }

    /**
     * Asigna los textos y las listas correspondientes de los nombresTLP
     * @param textos : Vector de textos, inicialmente vacio que contendra 
     *                 los textos correspondientes
     * @param listas : Vector de listas, inicialmente vacio que contendra
     *                 las listas correspondientes
     * @param nombresTLP : Vector de nombres TLP
     * @return boolean : devuelve true si los textos y listas contienen
     *                   solo caracteres del alfabeto y si todos lo nombresTLP
     *                   pertenecen a textos y listas unicamente. Falso en
     *                   caso contrario
    */
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

    /**
     * Crea un teclado en la base de datos del programa con toda la
     * información necesaria y salta excepciónes si algun parámetro
     * contiene algún problema
     * @param nombreTeclado
     * @param nombreAlfabeto : nombre del alfabeto que usaremos con la
     *                         creacion
     * @param nombresTLP : contenedor de nombres de textos y listas
     * @param nombreAlgoritmo : nombre del algoritmo a utilizar
    */
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

    /**
     * Función que borra un teclado de la base de datos y salta una excepción
     * si no existe
     * @param nombreTeclado : el nombre del teclado a borrar
    */
    public void borrarTeclado(String nombreTeclado) {
        try {ctrlE.borrarTeclado(nombreTeclado);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

    }

    /**
     * Función que importa un alfabeto a la base de datos
     * @param nombreAlfabeto
     * @param alfabeto
    */
    public void importarAlfabeto(String nombreAlfabeto, String alfabeto) {
        try {ctrlE.importarAlfabeto(nombreAlfabeto, alfabeto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    /**
     * Función que importa un texto a la base de datos
     * @param nombreTexto
     * @param texto 
    */
    public void importarTexto(String nombreTexto, String texto) {
        try {ctrlE.importarTexto(nombreTexto, texto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    /**
     * Función que importa una lista de palabras
     * @param nombreLista
     * @param lista : lista de palabras con frecuencias 
    */
    public void importarListaPalabras(String nombreLista, Map<String, Integer> lista) {
        try {ctrlE.importarListaPalabras(nombreLista, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
    }

    /**
     * Función que modifica un teclado existente de la base de datos y hace
     * saltar una excepción en caso de que no sea posible
     * @param nombreTeclado : el nombre del teclado a modificar
     * @param nombreAlfabeto : el nombre del alfabeto que se usará
     * @param nombresTLP : los nombres de los textos y listas que se usaran
    */
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

    /**
     * Subfunción de modificar teclado que se encarga de asignarle el mismo
     * alfabeto que ya contenía el teclado inicialmente a la hora de modificar
     * el teclado
     * @param nombreTeclado : nombre del teclado a modificar
     * @param nombresTLP : nombres de los textos y listas que se usaran
    */
    public void modificarTeclado(String nombreTeclado, Vector<String> nombresTLP) {
    	String alfabeto = "";
    	try {alfabeto = ctrlE.getAlfabetoTeclado(nombreTeclado);}
    	catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        modificarTeclado(nombreTeclado, alfabeto, nombresTLP);
    }

    /**
     * Función que modifica un alfabeto existente en la base de datos
     * @param nombreAlfabeto : nombre del alfabeto a modificar
     * @param alfabetoNuevo : alfabeto que sustituirá al antiguo
    */
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

    /**
     * Función que modifica un texto existente en la base de datos
     * @param nombreTexto : nombre del texto a modificar
     * @param textoNuevo : texto que sustituirá al antiguo
    */
    public void modificarTexto(String nombreTexto, String textoNuevo) {
    	String tipo = "";
        try {tipo = ctrlE.getType(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Texto")
            {System.out.println("Error: "+"el texto no existe"); return;}
        
        ctrlE.modificarTexto(nombreTexto, textoNuevo);
    }

    /**
     * Función que modifica una lista de palabras de la base de datos
     * @param nombreLista : nombre de la lista a modificar
     * @param listaNueva : lista que sustituirá a la antigua
    */
    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
    	String tipo = "";
        try {tipo = ctrlE.getType(nombreLista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Lista de Palabras")
            {System.out.println("Error: "+"la lista no existe"); return;}

        ctrlE.modificarListaPalabras(nombreLista, listaNueva);
    }

    /**
     * Función que borra un alfabeto de la base de datos
     * @param nombreAlfabeto
    */
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

    /**
     * Función que borra un texto de la base de datos
     * @param nombreTexto
    */
    public void borrarTexto(String nombreTexto) {
        String tipo = "";
        try {tipo = ctrlE.getType(nombreTexto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Texto")
            {System.out.println("Error: "+"el texto no existe"); return;}

        ctrlE.borrarTexto(nombreTexto);
    }

    /**
     * Función qe borra una lista de palabras de la base de datos
     * @param nombreLista
    */
    public void borrarListaPalabras(String nombreLista) {
        String tipo = "";
        try {tipo = ctrlE.getType(nombreLista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        if (tipo != "Lista de Palabras")
            {System.out.println("Error: "+"la lista no existe"); return;}

        ctrlE.borrarListaPalabras(nombreLista);
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
    
}
//autor Miguel