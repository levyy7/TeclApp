package main.dominio;

import java.io.*;
import java.util.*;

import Excepcions.*;

import main.dominio.*;
import java.util.HashMap;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Controlador de entrada
 * Conecta el controlados de dominio los inputs i los teclados
 * @author Pol Ribera Moreno
*/

public class CtrlEntrada{

 	private HashMap<String, Teclado> teclados;
 	private HashMap<String, Input> inputs;
 	private static CtrlEntrada singletonObject;
 	private static ComprobarExcepciones comproE;
 	
    public CtrlEntrada(){  
        inicialitzar();
    }

    public CtrlEntrada(String[][] iniTec, String[][] iniAlf, String[][] iniTex, String[][] iniLis){  
        teclados = new HashMap<String, Teclado>();
	    inputs = new HashMap<String, Input>();
	    comproE = new ComprobarExcepciones();

		inicializarTec(iniTec);
		inicializarAlf(iniAlf);
		inicializarTex(iniTex);
		inicializarLis(iniLis);
    }

    private void inicializarTec(String[][] iniTec) {
		for (int i = 0; i < iniTec.length; ++i) {
			String[] t = iniTec[i];
			teclados.put(t[0], new Teclado(t));
		}
	}

	private void inicializarAlf(String[][] iniAlf) {
		for (int i = 0; i < iniAlf.length; ++i) {
			String[] a = iniAlf[i];
			inputs.put(a[0], new Alfabeto(a));
		}
	}

	private void inicializarTex(String[][] iniTex) {
		for (int i = 0; i < iniTex.length; ++i) {
			String[] t = iniTex[i];
			inputs.put(t[0], new Texto(t));
		}
	}

	private void inicializarLis(String[][] iniLis) {
		for (int i = 0; i < iniLis.length; ++i) {
			String[] l = iniLis[i];
			inputs.put(l[0], new ListaPalabras(l));
		}
	}

	public static CtrlEntrada getInstance(){
    	if(singletonObject == null){
        	singletonObject = new CtrlEntrada();
      	}
      return singletonObject;
    }

	public void inicialitzar(){
	    teclados = new HashMap<String, Teclado>();
	    inputs = new HashMap<String, Input>();
	    comproE = new ComprobarExcepciones();
	}

	//pre: el alfabeto "nombreAlfabeto" existe (se comprueba antes de llamar la funcion) i tiene tamaño menor de 20. No hay ningun teclado con el nombre "nombreTeclado"
	//post: crear una nueva instancia de teclado con el alfabeto "nombreAlfabeto" assignado, retorna el playout que creara el teclado  
	/**
     * Crea una nueva instancia de teclado asssignado a un alfabeto 
     * @param nombreTeclado : nombre que tendra el nuevo teclado
     * @param nombreAlfabeto : nombre del alfabeto que usaremos 
     * @return Point2D[] : el playout generado por el teclado
     * @throws NGrande
     * @throws TecladoYaExiste
    */
	public Point2D[] crearTecladoVacio (String nombreTeclado, String nombreAlfabeto) throws NGrande,TecladoYaExiste { 

		Input alfa = inputs.get(nombreAlfabeto);
		int numeroCaracters = alfa.getSize();
		if(numeroCaracters>=20) throw new NGrande();
		if(teclados.containsKey(nombreTeclado)) throw new TecladoYaExiste(nombreTeclado);
		Teclado board = new Teclado(nombreTeclado, nombreAlfabeto, numeroCaracters);
		teclados.put(nombreTeclado, board);
		Point2D[] playout = board.getPlayout();
		return playout;
	}

	//pre: el teclado existe(siempre lo hará)
	//post: asigna al teclado un layout con el nombre su respectivo algoritmo.
	/**
     * Assigna al teclado un layout con el nombre su respectivo algoritmo.
     * @param nombreTeclado : nombre del teclado
     * @param nombreAlgoritmo : nombre del algoritmo 
     * @param layout : vector de chars que representan una distribucion 
	*/
	public void setLayout(String nombreTeclado, String nombreAlgoritmo, char[] layout){ 
		Teclado board = teclados.get(nombreTeclado);
		board.setAlgoritmo(nombreAlgoritmo);
		board.setLayout(layout);
	}

	//pre: el teclado e existe
	//post: borrar la intancia de teclado con nombre e
	/**
     * Borra un teclado
     * @param nombreTeclado : nombre del teclado
     * @throws TecladoInexistente
    */
	public void borrarTeclado(String e) throws TecladoInexistente{
		if(teclados.containsKey(e) == false) throw new TecladoInexistente(e);
		teclados.remove(e);
	}

	//pre: el teclado existe
	//post: retorna el nombre del algoritmo usado para crear el teclado
	/**
     * Consulta el algoritmo de un teclado
     * @param nombreTeclado : nombre del teclado
     * @return string : nombre del algoritmo
     * @throws TecladoInexistente
    */
	public String getAlgoritmo(String nombreTeclado) throws TecladoInexistente{
		if(teclados.containsKey(nombreTeclado) == false) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlgoritmo();
	}

	//pre: el iniput existe
	//post: devuelve el tipo de input que es
	/**
     * Consulta que tipo de input es
     * @param nombreTLP : nombre del input
     * @return string : devuelve el tipo de input que es ("Alfabeto", "Texto", "Lista de Palabras")
     * @throws TecladoInexistente
    */
	public String getType(String nombreTLP) throws InputInexistente{
		if(inputs.containsKey(nombreTLP) == false) throw new InputInexistente();
		Input input = inputs.get(nombreTLP);
		return input.getType();
	}

	//pre: no existe ningun input con nombre nAlfa i alfa no tiene caracteres repetidos 
	//post: crea una nueva instancia de alfabeto llamada nAlfa
	/**
     * crear una nueva instancia de alfabeto
     * @param nAlfa : nombre del alfabeto
     * @param alfa : contenido del alfabeto
     * @throws InputJaCreat
     * @throws AlfabetoInvalido
    */
	public void importarAlfabeto(String nAlfa, String alfa) throws InputJaCreat, AlfabetoInvalido {
		if(inputs.containsKey(nAlfa)) throw new InputJaCreat(nAlfa);
		if(comproE.AlfaCorrecto(alfa) == false) throw new AlfabetoInvalido(nAlfa);
		Input in = new Alfabeto(nAlfa, alfa);
		inputs.put(nAlfa, in);
	}

	//pre: no existe ningun input con nombre nTexto
	//post: crea una nueva instancia de Texto llamada nTexto
	/**
     * crear una nueva instancia de texto
     * @param nTexto : nombre del texto
     * @param texto : contenido del texto
     * @throws InputJaCreat
    */
	public void importarTexto(String nTexto, String texto) throws InputJaCreat {
		if(inputs.containsKey(nTexto)) throw new InputJaCreat(nTexto);
		Input in = new Texto(nTexto, texto);
		inputs.put(nTexto, in);
	}

	//pre: no existe ningun input con nombre nLista
	//post:crea una nueva instancia de ListaPalabras llamada nLista
	/**
     * crear una nueva instancia de ListaPalabras
     * @param nLista : nombre de la lista
     * @param lista : contenido de la lista
     * @throws InputJaCreat
    */
	public void importarListaPalabras(String nLista, Map<String, Integer> lista) throws InputJaCreat{
		if(inputs.containsKey(nLista)) throw new InputJaCreat(nLista);
		Input in = new ListaPalabras(nLista,lista); 
		inputs.put(nLista, in);
	}

	//pre: el teclado exitse
	//post: retorna el nombre del alfabeto del teclado
	/**
     * consulta el alfabeto de un teclado
     * @param nombreTeclado : nombre del teclado
     * @return string : nombre del alfabeto
     * @throws TecladoInexistente
    */
	public String getAlfabetoTeclado(String nombreTeclado) throws TecladoInexistente{
		if(teclados.containsKey(nombreTeclado) == false) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlfabeto();
	}

	//pre: no hay ningun teclado que se haya creado con el alfabeto "nombreAlfabeto" i "alfabetoNuevo" es valido
	//post: canvia el valor de la instancia "nombreAlfabeto" a "alfabetoNuevo"
	/**
     * modificar un alfabeto
     * @param nombreAlfabeto : nombre del alfabeto
     * @param alfabetoNuevo : nuevo contenido del alfabeto
     * @throws AlfabetoUsandose
     * @throws AlfabetoInvalido
    */
	public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo) throws AlfabetoUsandose, AlfabetoInvalido{
		for (Map.Entry<String, Teclado> texplorado : teclados.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose(nombreAlfabeto);
		}
		if(comproE.AlfaCorrecto(alfabetoNuevo) == false) throw new AlfabetoInvalido(nombreAlfabeto);
		inputs.remove(nombreAlfabeto);
		Input in = new Alfabeto(nombreAlfabeto, alfabetoNuevo);
		inputs.put(nombreAlfabeto, in);
	}

	//pre: hay una instancia de ListaPalabras con nombre "nombreLista" (siempre va a pasar en nuestro codigo)
	//post: canvia el valor de la instancia "nombreLista" a "listaNueva"
	/**
     * modificar un lista
     * @param nombreLista : nombre de la lista
     * @param listaNueva : nuevo contenido de la lista
    */
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva){
		inputs.remove(nombreLista);
		Input in = new ListaPalabras(nombreLista,listaNueva); 
		inputs.put(nombreLista, in);
	}

	//pre: hay una instancia de Texto con nombre "nombreTexto" (siempre va a pasar en nuestro codigo)
	//post: canvia el valor de la instancia "nombreTexto" a "textoNuevo"
	/**
     * modificar un texto
     * @param nombreTexto : nombre del texto
     * @param textoNuevo : nuevo contenido del texto
    */
	public void modificarTexto(String nombreTexto, String textoNuevo){
		inputs.remove(nombreTexto);
		Input in = new Texto(nombreTexto, textoNuevo);
		inputs.put(nombreTexto, in);
	}
	
	//pre: el alfabeto existe (siempre passa) i ningun teclado lo esta usando
	//post: borra el alfabeto
	/**
     * eliminar un alfabeto
     * @param nombreAlfabeto : nombre del alfabeto
     * @throws AlfabetoUsandose
    */
	public void borrarAlfabeto(String nombreAlfabeto) throws AlfabetoUsandose{
		for (Map.Entry<String, Teclado> texplorado : teclados.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose(nombreAlfabeto);
		}
     	inputs.remove(nombreAlfabeto);
    }
   
    //pre: el texto existe (siempre passa)
	//post:borra el texto
	/**
     * eliminar un texto
     * @param nombreTexto : nombre del texto
    */
    public void borrarTexto(String nombreTexto){
        inputs.remove(nombreTexto);
    }

    //pre: la listapalabras existe (siempre passa)
	//post:  borrar listapalabras
	/**
     * eliminar una lista
     * @param lista : nombre de la lista
    */
    public void borrarListaPalabras(String lista) {
        inputs.remove(lista);
    }

    //pre: el alfabeto existe
	//post: retorna el alfabeto
	/**
     * consulta un alfabeto
     * @param e : nombre del alfabeto
     * @return string: contenido del alfabeto
     * @throws InputInexistente
    */
	public String getAlfabeto(String e) throws InputInexistente{
		Input in = inputs.get(e);			
		return ((Alfabeto)in).getAlfabeto();
	}
	

	public Input getInput(String e) throws InputInexistente{
		Input in = inputs.get(e);			
		return in;
	}


	//pre: el texto existe
	//post: retorna el texto
	/**
     * consulta un texto
     * @param e : nombre del texto
     * @return string: contenido del texto
    */
	public String getTexto(String e){
		Input in = inputs.get(e);
		return ((Texto)in).getTexto();
	}

	//pre: el listaPalabras existe
	//post: retorna el listasPalabras
	/**
     * consulta una lista
     * @param e : nombre de la lista
     * @return Map<String, Integer>: contenido de la lista
    */
	public Map<String, Integer> getListaPalabras(String e){
		Input in = inputs.get(e);
		return ((ListaPalabras)in).getListaFreq();
	} 

	//pre: 
	//post: retorna true si todos los carácteres del texto son del alfabeto
	/**
     * comprueva que todos los carácteres del texto son del alfabeto 
     * @param textos : vector del contenido de los textos que se quieren comprovar
     * @param alfabeto : contenido de un alfabeto
     * @throws TextoNoValido
    */
	public void compruebaTextos(Vector<String> textos, String alfabeto) throws TextoNoValido{
		if(comproE.TextoCorrecto(textos, alfabeto) == false) throw new TextoNoValido();
	}

	//pre: 
	//post: retorna true si todos los carácteres de la lista de palabras son del alfabeto
	/**
     * comprueva que todos los carácteres del texto son del alfabeto 
     * @param listas : vector del contenido de las listas que se quieren comprovar
     * @param alfabeto : contenido de un alfabeto
     * @throws ListaNoValida
    */
	public void compruebaListas(Vector<Map<String, Integer>> listas, String alfabeto) throws ListaNoValida{
		if(comproE.ListaCorrecto(listas, alfabeto) == false) throw new ListaNoValida();
	}

	//pre: 
	//post: retorna todos los teclados
	/**
     * consulta todos los teclados
     * @return HashMap<String, Teclado>: conjunto de teclados
    */
	public HashMap<String, Teclado> getTeclados(){
		return teclados;
	}

	//pre: 
	//post: retorna el teclado "nombreTeclado"
	/**
     * consulta un teclado
     * @param nombreTeclado : nombre del teclado
     * @return Teclado : el teclado
    */
	public Teclado getTeclado(String nombreTeclado){
		Teclado board = teclados.get(nombreTeclado);
		return board;
	}

	//pre: 
	//post:retorna todos los alfabetos
	/**
     * consulta todos los alfabetos
     * @return HashMap<String, Input>: conjunto de alfabetos
    */
	public HashMap<String, Input> getAlfabetos() {
		HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof Alfabeto) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

    //pre: 
	//post: retorna todos los textos
    /**
     * consulta todos los textos
     * @return HashMap<String, Input>: conjunto de textos
    */
    public HashMap<String, Input> getTextos() {
    	HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof Texto) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

    //pre: 
	//post: retorna todas las listas de palabras 
	/**
     * consulta todas las listas
     * @return HashMap<String, Input>: conjunto de listas
    */
    public HashMap<String, Input> getListas() {
    	HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof ListaPalabras) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

}
//classe implementada per POL