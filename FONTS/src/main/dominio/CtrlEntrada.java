package main.dominio;

import java.io.*;
import java.util.*;

import Excepcions.*;

import main.dominio.*;
import java.util.HashMap;
import java.awt.Point;
import java.awt.geom.Point2D;


public class CtrlEntrada{

 	private HashMap<String, Teclado> teclados;
 	private HashMap<String, Input> inputs;
 	private static CtrlEntrada singletonObject;
 	private static ComprobarExcepciones comproE;
 	
    public CtrlEntrada(){  
        inicialitzar();
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
	public void setLayout(String nombreTeclado, String nombreAlgoritmo, char[] layout){ 
		Teclado board = teclados.get(nombreTeclado);
		board.setAlgoritmo(nombreAlgoritmo);
		board.setLayout(layout);
	}

	//pre: el teclado e existe
	//post: borrar la intancia de teclado con nombre e
	public void borrarTeclado(String e) throws TecladoInexistente{
		if(teclados.containsKey(e) == false) throw new TecladoInexistente(e);
		teclados.remove(e);
	}

	//pre: el teclado existe
	//post: retorna el nombre del algoritmo usado para crear el teclado
	public String getAlgoritmo(String nombreTeclado) throws TecladoInexistente{
		if(teclados.containsKey(nombreTeclado) == false) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlgoritmo();
	}

	//pre: el iniput existe
	//post: devuelve el tipo de input que es
	public String getType(String nombreTLP) throws InputInexistente{
		if(inputs.containsKey(nombreTLP) == false) throw new InputInexistente();
		Input input = inputs.get(nombreTLP);
		return input.getType();
	}

	//pre: no existe ningun input con nombre nAlfa i alfa no tiene caracteres repetidos 
	//post: crea una nueva instancia de alfabeto llamada nAlfa
	public void importarAlfabeto(String nAlfa, String alfa) throws InputJaCreat, AlfabetoInvalido {
		if(inputs.containsKey(nAlfa)) throw new InputJaCreat(nAlfa);
		if(comproE.AlfaCorrecto(alfa) == false) throw new AlfabetoInvalido(nAlfa);
		Input in = new Alfabeto(nAlfa, alfa);
		inputs.put(nAlfa, in);
	}

	//pre: no existe ningun input con nombre nTexto
	//post: crea una nueva instancia de Texto llamada nTexto
	public void importarTexto(String nTexto, String texto) throws InputJaCreat {
		if(inputs.containsKey(nTexto)) throw new InputJaCreat(nTexto);
		Input in = new Texto(nTexto, texto);
		inputs.put(nTexto, in);
	}

	//pre: no existe ningun input con nombre nLista
	//post:crea una nueva instancia de ListaPalabras llamada nLista
	public void importarListaPalabras(String nLista, Map<String, Integer> lista) throws InputJaCreat{
		if(inputs.containsKey(nLista)) throw new InputJaCreat(nLista);
		Input in = new ListaPalabras(nLista,lista); 
		inputs.put(nLista, in);
	}

	//pre: el teclado exitse
	//post: retorna el nombre del alfabeto del teclado
	public String getAlfabetoTeclado(String nombreTeclado) throws TecladoInexistente{
		if(teclados.containsKey(nombreTeclado) == false) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlfabeto();
	}

	//pre: no hay ningun teclado que se haya creado con el alfabeto "nombreAlfabeto" i "alfabetoNuevo" es valido
	//post: canvia el valor de la instancia "nombreAlfabeto" a "alfabetoNuevo"
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
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva){
		inputs.remove(nombreLista);
		Input in = new ListaPalabras(nombreLista,listaNueva); 
		inputs.put(nombreLista, in);
	}

	//pre: hay una instancia de Texto con nombre "nombreTexto" (siempre va a pasar en nuestro codigo)
	//post: canvia el valor de la instancia "nombreTexto" a "textoNuevo"
	public void modificarTexto(String nombreTexto, String textoNuevo){
		inputs.remove(nombreTexto);
		Input in = new Texto(nombreTexto, textoNuevo);
		inputs.put(nombreTexto, in);
	}

	//pre: el alfabeto existe (siempre passa) i ningun teclado lo esta usando
	//post: borra el alfabeto
	public void borrarAlfabeto(String nombreAlfabeto) throws AlfabetoUsandose{
		for (Map.Entry<String, Teclado> texplorado : teclados.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose(nombreAlfabeto);
		}
     	inputs.remove(nombreAlfabeto);
    }

    //pre: el texto existe (siempre passa)
	//post:borra el texto
    public void borrarTexto(String nombreTexto){
        inputs.remove(nombreTexto);
    }

    //pre: la listapalabras existe (siempre passa)
	//post:  borrar listapalabras
    public void borrarListaPalabras(String lista) {
        inputs.remove(lista);
    }

    //pre: el alfabeto existe
	//post: retorna el alfabeto
	public String getAlfabeto(String e) throws InputInexistente{
		Input in = inputs.get(e);			
		return ((Alfabeto)in).getAlfabeto();
	}

	//pre: el texto existe
	//post: retorna el texto
	public String getTexto(String e){
		Input in = inputs.get(e);
		return ((Texto)in).getTexto();
	}

	//pre: el listaPalabras existe
	//post: retorna el listasPalabras
	public Map<String, Integer> getListaPalabras(String e){
		Input in = inputs.get(e);
		return ((ListaPalabras)in).getListaFreq();
	} 

	//pre: 
	//post: retorna true si todos los carácteres del texto son del alfabeto
	public void compruebaTextos(Vector<String> textos, String alfabeto) throws TextoNoValido{
		if(comproE.TextoCorrecto(textos, alfabeto) == false) throw new TextoNoValido();
	}

	//pre: 
	//post: retorna true si todos los carácteres de la lista de palabras son del alfabeto
	public void compruebaListas(Vector<Map<String, Integer>> listas, String alfabeto) throws ListaNoValida{
		if(comproE.ListaCorrecto(listas, alfabeto) == false) throw new ListaNoValida();
	}

	//pre: 
	//post: retorna todos los teclados
	public HashMap<String, Teclado> getTeclados(){
		return teclados;
	}

	//pre: 
	//post: retorna el teclado "nombreTeclado"
	public Teclado getTeclado(String nombreTeclado){
		Teclado board = teclados.get(nombreTeclado);
		return board;
	}

	//pre: 
	//post:retorna todos los alfabetos
	public HashMap<String, Input> getAlfabetos() {
		HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual instanceof Alfabeto) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

    //pre: 
	//post: retorna todos los textos
    public HashMap<String, Input> getTextos() {
    	HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual instanceof Texto) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

    //pre: 
	//post: retorna todas las listas de palabras 
    public HashMap<String, Input> getListas() {
    	HashMap<String, Input> a  = new HashMap<String, Input>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual instanceof ListaPalabras) a.put(actual.getKey(),actual.getValue());
        }
        return a;
    }

}
//classe implementada per POL