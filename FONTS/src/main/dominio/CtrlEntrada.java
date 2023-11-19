package main.dominio;

import java.io.*;
import java.util.*;

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
		if(teclados.contains(nombreTeclado)) throw new TecladoYaExiste();
		Teclado board = new Teclado(nombreTeclado, nombreAlfabeto, numeroCaracters);
		teclados.put(nombreTeclado, board);
		Point2D[] playout = board.getPlayout();
		return playout;
	}

	public Point2D[] getPlayout (String nombreTeclado){
		Teclado board = teclados.get(nombreTeclado);
		Point2D[] playout = board.getPlayout();
		return playout;
	}

	public void setLayout(String nombreTeclado, String nombreAlgoritmo, HashMap<Character, Point> layout){ //no cal
		Teclado board = teclados.get(nombreTeclado);
		board.setAlgoritmo(nombreAlgoritmo);
		board.setLayout(layout);
	}

	public void borrarTeclado(String e) throws TecladoInexistente{
		if(teclados.contains(e) == false) throw new TecladoInexistente(nombreTeclado);
		teclados.remove(e);
	}

	public String getAlgoritmo(String nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlgoritmo();
	}

	public String getType(String nombreTLP) throws InputInexistente{
		if(input.contains(nombreTLP) == false) throw new InputInexistente();
		Input input = inputs.get(nombreTLP);
		return input.getType();
	}

	public void importarAlfabeto(String nAlfa, String alfa) throws InputJaCreat, AlfabetoInvalido {
		if(input.contains(nAlfa)) throw new InputJaCreat();
		if(comproE.AlfaCorrecto(alfa) == false) throw new AlfabetoInvalido();
		importInput(nAlfa, alfa, "Alfabeto");
	}

	public void importarTexto(String nTexto, String texto) throws InputJaCreat {
		if(input.contains(nTexto)) throw new InputJaCreat();
		importInput(nTexto, texto, "Texto");
	}

	public void importarListaPalabras(String nLista, Map<String, Integer> lista) throws InputJaCreat{
		if(input.contains(nLista)) throw new InputJaCreat();
		Input in = new Input(nLista,"","ListaPalabras");
		in.setListaFrecuencia(lista);
		inputs.put(nLista, in);
	}

	public String getAlfabetoTeclado(String nombreTeclado) throws TecladoInexistente{
		if(teclados.contains(nombreTeclado)) throw new TecladoInexistente(nombreTeclado);
		Teclado a = teclados.get(nombreTeclado);

		return a.getAlfabeto();
	}

	private void importInput(String name, String data, String type){
		Input in = new Input(name,data,type); 
		inputs.put(name, in);
	}


	public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo) throws InputInexistente, AlfabetoUsandose{
		for (Map.Entry<String, Teclado> texplorado : mapa.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose();
		}
		if(inputs.contains(nombreAlfabeto) == false) new throw InputInexistente(nombreAlfabeto);
		inputs.remove(nombreAlfabeto);
		importarAlfabeto(nombreAlfabeto, alfabetoNuevo);
	}
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) throws InputInexistente{
		if(inputs.contains(nombreLista) == false) new throw InputInexistente(lista);
		inputs.remove(nombreLista);
		importarListaPalabras(nombreLista, listaNueva);
	}
	public void modificarTexto(String nombreTexto, String textoNuevo) throws InputInexistente{
		if(inputs.contains(nombreTexto) == false) new throw InputInexistente(nombreTexto);
		inputs.remove(nombreTexto);
		importarTexto(nombreTexto, textoNuevo);
	}

	public void borrarAlfabeto(String nombreAlfabeto) throws InputInexistente, AlfabetoUsandose{
		for (Map.Entry<String, Teclado> texplorado : mapa.entrySet()) {
			Teclado board = texplorado.getValue();
			if (board.getAlfabeto() == nombreAlfabeto) throw new AlfabetoUsandose();
		}
		if(inputs.contains(nombreAlfabeto) == false) new throw InputInexistente(nombreAlfabeto);
     	inputs.remove(nombreAlfabeto);
    }

    public void borrarTexto(String nombreTexto) throws InputInexistente{
    	if(inputs.contains(nombreTexto) == false) new throw InputInexistente(nombreTexto);
        inputs.remove(nombreTexto);
    }

    public void borrarListaPalabras(String lista) throws InputInexistente{
    	if(inputs.contains(lista) == false) new throw InputInexistente(lista);
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
	}

	public void compruebaTextos(Vector<String> textos, String alfabeto) throws TextoNoValido{
		if(comproE.TextoCorrecto(textos, alfabeto) == false) new throw TextoNoValido;
	}

	public void compruebaListas(Vector<Map<String, Integer>> listas, String alfabeto) throws ListaNoValida{
		if(comproE.ListaCorrecto(lista, alfabeto) == false) new throw ListaNoValida;
	}

}
//classe implementada per POL