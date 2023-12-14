package main.dominio;

import java.io.*;
import java.util.*;

import Excepcions.*;

import main.dominio.*;
import java.util.HashMap;

import javax.lang.model.type.NullType;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Controlador de entrada
 * Conecta el controlados de dominio los inputs i los teclados
 * @author Pol Ribera Moreno
*/

public class CtrlEntrada{

	/** HashMap que contiene los teclados*/
 	private HashMap<String, Teclado> teclados;

 	/** HashMap que contiene los inputs*/
 	private HashMap<String, Input> inputs;

 	private static CtrlEntrada singletonObject;
 	
 	/** Instancia de la clase ComprobarExcepciones*/
 	private static ComprobarExcepciones comproE;
 	
 	/** Constructora por defecto que inicializa las instancias*/
    public CtrlEntrada(){  
        inicialitzar();
    }

    /**
     * Constructora que inicializa las instancias con la información recibida
     * @param iniTec : Teclados
     * @param iniAlf : Alfabetos
     * @param iniTex : Textos
     * @param iniLis : Listas
     */
    public CtrlEntrada(String[][] iniTec, String[][] iniAlf, String[][] iniTex, String[][] iniLis){  
        inicialitzar();

		inicializarTec(iniTec);
		inicializarAlf(iniAlf);
		inicializarTex(iniTex);
		inicializarLis(iniLis);
    }

    /**
	 * Función que inicializa los teclados
	 * @param iniTec : Teclados
    */
    private void inicializarTec(String[][] iniTec) {
		for (int i = 0; i < iniTec.length; ++i) {
			String[] t = iniTec[i];
			teclados.put(t[0], new Teclado(t));
		}
	}

	/**
	 * Función que inicializa los alfabetos
	 * @param iniAlf : Alfabetos
    */
	private void inicializarAlf(String[][] iniAlf) {
		for (int i = 0; i < iniAlf.length; ++i) {
			String[] a = iniAlf[i];
			inputs.put(a[0], new Alfabeto(a));
		}
	}

	/**
	 * Función que inicializa los textos
	 * @param iniTex : Textos
    */
	private void inicializarTex(String[][] iniTex) {
		for (int i = 0; i < iniTex.length; ++i) {
			String[] t = iniTex[i];
			inputs.put(t[0], new Texto(t));
		}
	}

	/**
	 * Función que inicializa las listas de palabras
	 * @param iniLis : Listas de palabras
    */
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

    /** Función que inicializa las instancias principales*/
	public void inicialitzar(){
	    teclados = new HashMap<String, Teclado>();
	    inputs = new HashMap<String, Input>();
	    comproE = new ComprobarExcepciones();
	}

	/**
     * Crea una nueva instancia de teclado asssignado a un alfabeto 
     * @param nombreTeclado : nombre que tendra el nuevo teclado
     * @param nombreAlfabeto : nombre del alfabeto que usaremos 
     * @return Point2D[] : el playout generado por el teclado
     * @throws NGrande
     * @throws TecladoYaExiste
    */
	public Point2D[] crearTecladoVacio (String nombreTeclado, String nombreAlfabeto, String nombreAlgoritmo) throws NGrande,TecladoYaExiste { 

		Input alfa = inputs.get(nombreAlfabeto);
		int numeroCaracters = alfa.getSize();
		if(numeroCaracters>=20 && nombreAlgoritmo.equals("QAP")) throw new NGrande();
		if(teclados.containsKey(nombreTeclado)) throw new TecladoYaExiste(nombreTeclado);
		Teclado board = new Teclado(nombreTeclado, nombreAlfabeto, numeroCaracters, nombreAlgoritmo);
		teclados.put(nombreTeclado, board);
		Point2D[] playout = board.getPlayout();
		return playout;
	}

	/**
     * Assigna al teclado un layout con el nombre su respectivo algoritmo.
     * @param nombreTeclado : nombre del teclado
     * @param nombreAlgoritmo : nombre del algoritmo 
     * @param layout : vector de chars que representan una distribucion 
	*/
	public void setLayout(String nombreTeclado, char[] layout){ 
		Teclado board = teclados.get(nombreTeclado);
		board.setLayout(layout);
	}

	
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

	/**
     * Consulta el alfabeto de un teclado
     * @param nombreTeclado : nombre del teclado
     * @return string : nombre del alfabeto
     * @throws TecladoInexistente
    */
	public String getAlfabetoTeclado(String nombreTeclado) throws TecladoInexistente{
		if(teclados.containsKey(nombreTeclado) == false) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlfabeto();
	}

	/**
     * Consulta un teclado
     * @param nombreTeclado : nombre del teclado
     * @return Teclado : el teclado
    */
	public Teclado getTeclado(String nombreTeclado) throws TecladoInexistente {
		Teclado board = teclados.get(nombreTeclado);

		if (board == null) throw new TecladoInexistente(nombreTeclado);

		return board;
	}

	/**
     * Borra un teclado
     * @param nombreTeclado : nombre del teclado
     * @throws TecladoInexistente
    */
	public void borrarTeclado(String e) throws TecladoInexistente {
		if (teclados.containsKey(e) == false) throw new TecladoInexistente(e);

		teclados.remove(e);
	}

	/**
     * Creación de una nueva instancia de alfabeto
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

	/**
     * Consulta un alfabeto
     * @param nombreAlfabeto : nombre del alfabeto
     * @return Alfabeto : contenido del alfabeto
     * @throws InputInexistente
     * @throws WrongInputType
    */
	public Alfabeto getAlfabeto(String nombreAlfabeto) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreAlfabeto);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof Alfabeto)) throw new WrongInputType("Alfabeto", in.getClass().getName());

		return (Alfabeto) in;
	}

	/**
     * Modificación de un alfabeto
     * @param nombreAlfabeto : nombre del alfabeto
     * @param alfabetoNuevo : nuevo contenido del alfabeto
     * @throws AlfabetoUsandose
     * @throws AlfabetoInvalido
     * @throws InputInexistente
     * @throws WrongInputType
    */
	public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo) throws AlfabetoUsandose, AlfabetoInvalido, InputInexistente, WrongInputType{
		for (Map.Entry<String, Teclado> texplorado : teclados.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose(nombreAlfabeto);
		}
		if(comproE.AlfaCorrecto(alfabetoNuevo) == false) throw new AlfabetoInvalido(nombreAlfabeto);
		
		Alfabeto alf = getAlfabeto(nombreAlfabeto);
		
		alf.setLetras(alfabetoNuevo);
	}

	/**
     * Borrado de un alfabeto
     * @param nombreAlfabeto : nombre del alfabeto
     * @throws AlfabetoUsandose
     * @throws InputInexistente
     * @throws WrongInputType
    */
	public void borrarAlfabeto(String nombreAlfabeto) throws AlfabetoUsandose, InputInexistente, WrongInputType {
		for (Map.Entry<String, Teclado> texplorado : teclados.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose(nombreAlfabeto);
		}

		Input in = inputs.get(nombreAlfabeto);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof Alfabeto)) throw new WrongInputType("Alfabeto", in.getClass().getName());

     	inputs.remove(nombreAlfabeto);
    }

	/**
     * Creación de una nueva instancia de texto
     * @param nTexto : nombre del texto
     * @param texto : contenido del texto
     * @throws InputJaCreat
    */
	public void importarTexto(String nTexto, String texto) throws InputJaCreat {
		if(inputs.containsKey(nTexto)) throw new InputJaCreat(nTexto);

		Input in = new Texto(nTexto, texto);
		inputs.put(nTexto, in);
	}

	/**
     * Consulta de un texto
     * @param nombreTexto : nombre del texto
     * @return Texto : Texto consultado
     * @throws InputInexistente 
     * @throws WrongInputType
    */
	public Texto getTexto(String nombreTexto) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreTexto);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof Texto)) throw new WrongInputType("Texto", in.getClass().getName());

		return (Texto) in;
	}

	/**
     * Modificación de un texto
     * @param nombreTexto : nombre del texto
     * @param textoNuevo : nuevo contenido del texto
     * @throws InputInexistente 
     * @throws WrongInputType
    */
	public void modificarTexto(String nombreTexto, String textoNuevo) throws InputInexistente, WrongInputType {
		Texto tex = getTexto(nombreTexto);
		
		tex.setTexto(textoNuevo);
	}

	/**
     * Eliminar un texto
     * @param nombreTexto : nombre del texto
     * @throws InputInexistente 
     * @throws WrongInputType
    */
    public void borrarTexto(String nombreTexto) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreTexto);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof Texto)) throw new WrongInputType("Texto", in.getClass().getName());

        inputs.remove(nombreTexto);
    }

	/**
     * Creación de una nueva instancia de ListaPalabras
     * @param nLista : nombre de la lista
     * @param lista : contenido de la lista
     * @throws InputJaCreat
    */
	public void importarListaPalabras(String nLista, Map<String, Integer> lista) throws InputJaCreat{
		if(inputs.containsKey(nLista)) throw new InputJaCreat(nLista);

		Input in = new ListaPalabras(nLista,lista); 
		inputs.put(nLista, in);
	}

	/**
     * Consulta de una lista de palabras
     * @param nombreLista : nombre de la lista
     * @return ListaPalabras : Lista consultada
    */
	public ListaPalabras getListaPalabras(String nombreLista) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreLista);
		
		if (in == null)	throw new InputInexistente();
		if (!(in instanceof ListaPalabras)) throw new WrongInputType("ListaPalabras", in.getClass().getName());

		return (ListaPalabras) in;
	} 

	/**
     * Modificación de una lista de palabras
     * @param nombreLista : nombre de la lista
     * @param listaNueva : nuevo contenido de la lista
    */
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) throws InputInexistente, WrongInputType {
		ListaPalabras lp = getListaPalabras(nombreLista);

		lp.setListaFreq(listaNueva);
	}

	/**
     * Eliminar una lista de palabras
     * @param nombreLista : nombre de la lista
     * @throws InputInexistente 
     * @throws WrongInputType
    */
    public void borrarListaPalabras(String nombreLista) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreLista);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof ListaPalabras)) throw new WrongInputType("ListaPalabras", in.getClass().getName());

        inputs.remove(nombreLista);
    }

    /**
	 * Consulta de un input
	 * @param e : Input a consultar
	 * @return Input : Input consultado
    */
	public Input getInput(String e) throws InputInexistente{
		Input in = inputs.get(e);			
		return in;
	}

	/**
	 * Consulta de un TLP
	 * @param nombreTLP : TLP a consultar
	 * @return TLP : TLP consultado
    */
	public TLP getTLP(String nombreTLP) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreTLP);

		Boolean isText = (in instanceof Texto);
		Boolean isLP = (in instanceof ListaPalabras);
		if (in == null)	throw new InputInexistente();
		if (!(isLP || isText)) throw new WrongInputType("TLP", in.getClass().getName());

		return (TLP) in;
	}
	
	/**
     * Comprobación de que todos los carácteres del texto son del alfabeto
     * @param textos : vector del contenido de los textos que se quieren comprobar
     * @param alfabeto : contenido de un alfabeto
     * @throws TextoNoValido
    */
	public void compruebaTextos(Vector<String> textos, String alfabeto) throws TextoNoValido{
		if(comproE.TextoCorrecto(textos, alfabeto) == false) throw new TextoNoValido();
	}

	/**
     * Comprobación que todos los carácteres del texto son del alfabeto 
     * @param listas : vector del contenido de las listas que se quieren comprobar
     * @param alfabeto : contenido de un alfabeto
     * @throws ListaNoValida
    */
	public void compruebaListas(Vector<Map<String, Integer>> listas, String alfabeto) throws ListaNoValida{
		if(comproE.ListaCorrecto(listas, alfabeto) == false) throw new ListaNoValida();
	}

	/**
     * Consulta de los teclados
     * @return HashMap(String, Teclado): conjunto de teclados
    */
	public HashMap<String, Teclado> getTeclados(){
		return teclados;
	}

	/**
     * Consulta de todos los alfabetos
     * @return HashMap(String, Input): conjunto de alfabetos
    */
	public HashMap<String, Input> getAlfabetos() {
		HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof Alfabeto) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

    /**
     * Consulta de todos los textos
     * @return HashMap(String, Input): conjunto de textos
    */
    public HashMap<String, Input> getTextos() {
    	HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof Texto) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

	/**
     * Consulta de todas las listas
     * @return HashMap(String, Input): conjunto de listas
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