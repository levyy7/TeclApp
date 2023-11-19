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

	public Point2D[] crearTecladoVacio (String nombreTeclado, String nombreAlfabeto) throws NGrande,TecladoYaExiste, { 

		Input alfa = inputs.get(nombreAlfabeto);
		int numeroCaracters = alfa.getSize();
		if(numeroCaracters>10) throw new NGrande();
		if() 
		Teclado board = new Teclado(nombreTeclado, nombreAlfabeto, numeroCaracters);
		teclados.put(nombreTeclado, board);
		Point2D[] playout = board.getPlayout();
		return playout;
	}

	public void setLayout(String nombreTeclado, String nombreAlgoritmo, HashMap<Character, Point> layout){
		Teclado board = teclados.get(nombreTeclado);
		board.setAlgoritmo(nombreAlgoritmo);
		board.setLayout(layout);
	}

	public void borrarTeclado(String e){
		teclados.remove(e);
	}

	public String getAlgoritmo(String nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlgoritmo();
	}

	public String getType(String nombreTLP){
		Input input = inputs.get(nombreTLP);
		//return input.getType();
		return "descomentar";
	}

	public void importarAlfabeto(String nAlfa, String alfa){
		importInput(nAlfa, alfa, "Alphabeto");
	}

	public void importarTexto(String nTexto, String texto){
		importInput(nTexto, texto, "Texto");
	}

	public void importarListaPalabras(String nLista, Map<String, Integer> lista){
		//Input in = new Input(nLista,lista,"ListaPalabras");
		//inputs.put(nLista, in);
	}

	public String getAlfabeto(String nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.getAlfabeto();
	}

	public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo){
		inputs.remove(nombreAlfabeto);
		importarAlfabeto(nombreAlfabeto, alfabetoNuevo);
	}
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva){
		inputs.remove(nombreLista);
		importarListaPalabras(nombreLista, listaNueva);
	}
	public void modificarTexto(String nombreTexto, String textoNuevo){
		inputs.remove(nombreTexto);
		importarTexto(nombreTexto, textoNuevo);
	}

	private void importInput(String name, String data, String type){
		//Input in = new Input(name,data,type); //al reves que la Mariona
		//inputs.put(name, in);
	}

	public String getAlfabeto(String e){
		Input in = inputs.get(e);
		return in.getDato();
	}
	public String getTexto(String e){ //esta mal!!!
		Input in = inputs.get(e);
		return in.getDato();
	}
	public Map<String, Integer> getLista(String e){ //esta mal!!!
		Input in = inputs.get(e);
		return in.getDato();
	}

}
//classe implementada per POL