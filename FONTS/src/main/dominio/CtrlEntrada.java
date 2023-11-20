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
	}

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

	public void setLayout(String nombreTeclado, String nombreAlgoritmo, char[] layout){ 
		Teclado board = teclados.get(nombreTeclado);
		board.setAlgoritmo(nombreAlgoritmo);
		board.setLayout(layout);
	}

	public void borrarTeclado(String e) throws TecladoInexistente{
		if(teclados.containsKey(e) == false) throw new TecladoInexistente(e);
		teclados.remove(e);
	}

	public String getAlgoritmo(String nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlgoritmo();
	}

	public String getType(String nombreTLP) throws InputInexistente{
		if(inputs.containsKey(nombreTLP) == false) throw new InputInexistente();
		Input input = inputs.get(nombreTLP);
		return input.getType();
	}

	public void importarAlfabeto(String nAlfa, String alfa) throws InputJaCreat, AlfabetoInvalido {
		if(inputs.containsKey(nAlfa)) throw new InputJaCreat(nAlfa);
		if(comproE.AlfaCorrecto(alfa) == false) throw new AlfabetoInvalido(nAlfa);
		//importInput(nAlfa, alfa, "Alfabeto");
		Input in = new Alfabeto(nAlfa, alfa);
		inputs.put(nAlfa, in);
	}

	public void importarTexto(String nTexto, String texto) throws InputJaCreat {
		if(inputs.containsKey(nTexto)) throw new InputJaCreat(nTexto);
		//importInput(nTexto, texto, "Texto");
		Input in = new Texto(nTexto, texto);
		inputs.put(nTexto, in);
	}

	/*private void importInput(String name, String data, String type){
		Input in = new Input(name,data,type); 
		inputs.put(name, in);
	}*/

	public void importarListaPalabras(String nLista, Map<String, Integer> lista) throws InputJaCreat{
		if(inputs.containsKey(nLista)) throw new InputJaCreat(nLista);
		//Input in = new Input(nLista,"","ListaPalabras"); in.setListaFrecuencia(lista);
		Input in = new ListaPalabras(nLista,lista); //problema double mariona
		inputs.put(nLista, in);
	}

	public String getAlfabetoTeclado(String nombreTeclado) throws TecladoInexistente{
		if(teclados.containsKey(nombreTeclado)) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlfabeto();
	}

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
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva){
		inputs.remove(nombreLista);
		Input in = new ListaPalabras(nombreLista,listaNueva); //problema double mariona
		inputs.put(nombreLista, in);
	}
	public void modificarTexto(String nombreTexto, String textoNuevo){
		inputs.remove(nombreTexto);
		Input in = new Texto(nombreTexto, textoNuevo);
		inputs.put(nombreTexto, in);
	}

	public void borrarAlfabeto(String nombreAlfabeto) throws InputInexistente, AlfabetoUsandose{
		for (Map.Entry<String, Teclado> texplorado : teclados.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose(nombreAlfabeto);
		}
		if(inputs.containsKey(nombreAlfabeto) == false) throw new InputInexistente();
     	inputs.remove(nombreAlfabeto);
    }

    public void borrarTexto(String nombreTexto) throws InputInexistente{
    	if(inputs.containsKey(nombreTexto) == false) throw new InputInexistente();
        inputs.remove(nombreTexto);
    }

    public void borrarListaPalabras(String lista) throws InputInexistente{
    	if(inputs.containsKey(lista) == false) throw new InputInexistente();
        inputs.remove(lista);
    }

	public String getAlfabeto(String e){
		Input in = inputs.get(e);
		return in.getAlfabeto();
	}
	public String getTexto(String e){
		Input in = inputs.get(e);
		return in.getTexto();
	}
	public Map<String, Integer> getListaPalabras(String e){
		Input in = inputs.get(e);
		return in.getListaFreq();
	} //arrglar amb mariona

	public void compruebaTextos(Vector<String> textos, String alfabeto) throws TextoNoValido{
		if(comproE.TextoCorrecto(textos, alfabeto) == false) throw new TextoNoValido();
	}

	public void compruebaListas(Vector<Map<String, Integer>> listas, String alfabeto) throws ListaNoValida{
		if(comproE.ListaCorrecto(listas, alfabeto) == false) throw new ListaNoValida();
	}

}
//classe implementada per POL