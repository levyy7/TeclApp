//package ControladorsEntrada;

import java.io.*;
import java.util.*;
/*
import Dades.*;
import Domini.*;
import Excepcions.*;
*/
public class CtrlEntrada{

 	private HashMap<String, Teclado> teclados;
 	private HashMap<String, Input> inputs;

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

	public Vector<Pair<double, double>> crearTecladoVacio(String nombreTeclado, String nombreAlfabeto){ 
		Teclado board = new Teclado(nombreTeclado, nombreAlfabeto);

		teclados.add(nombreTeclado, board);
		Input alfa = inputs.get(nombreAlfabeto);
		int nombreCaractes = alfa.getSize();
		Vector<Pair<double, double>> playout = board.getplayout(nombreCaracters);
		return playout;
	}

	public setLayout(String nombreTeclado, String nombreAlgoritmo, Vector<Integer> layout)(){
		board = teclados.get(nombreTeclado);
		board.setAlgoritmo(nombreAlgoritmo);
		board.setLayout(layout);
	}

	public void borrarTeclado(String e){
		teclados.remove(e);
	}

	public String getAlgoritmo(String nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.algoritmo();
	}

	public String getType(String nombreTLP){
		Input input = inputs.get(nombreTLP);
		return input.getType();
	}

	public void importarAlfabeto(String nAlfa, String alfa){
		importInput(nAlfa, alfa, "Alphabeto")
	}

	public void importarTexto(Sting nTexto, String texto){
		importInput(nTexto, texto, "Texto")
	}

	public void importarListaPalabras(String nLista, Map<String, Integer> lista){
		importInput(nLista, lista, "ListaPalabras") //jaja problema
	}

	public String getAlfabeto(nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.alphabeto();
	}

	public modificarAlfabeto(nombreAlfabeto, alfabetoNuevo){
		inputs.remove(nombreAlfabeto);
		importarAlfabeto(nombreAlfabeto, alfabetoNuevo);
	}
	public modificarListaPalabras(nombreLista, listaNueva){
		inputs.remove(nombreLista);
		importarListaPalabras(nombreLista, listaNueva);
	}
	public modificarTexto(String nombreTexto, String textoNuevo){
		inputs.remove(nombreTexto);
		importarTexto(nombreTexto, textoNuevo);
	}

	private void importInput(String name, String data, String type){
		Input in = new Input(name,data,type); //al reves que la Mariona
		inputs.add(name, in);
	}

	public Input getInput(String e){
		return inputs.getInput(e);
	}

}
//classe implementada per POL