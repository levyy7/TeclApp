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
		Vector<Pair<double, double>> playout = board.getplayout();
		return playout
	}

	/*rarete
	public Teclado setTeclado(String nom, String alfa, String text){
		Input a = getInput(alfa);
		Input t = getInput(text);
		Teclado board = new Teclado(nom, a, t); 
		teclados.put(board);
		return board; 
	}
	*/
	public setLayout(String nombreTeclado, String nombreAlgoritmo, Vector<Integer> layout)(){


	}
	public Teclado getTeclado(String e){
		return teclados.getTeclado(e);
	}

	public void borrarTeclado(String e){
		teclados.pop(board);
	}

	public Vector<Teclado> consultarTeclados(){
		return teclados;
	}

	public getAlgoritmo(nombreTeclado){

	}

	

	public getType(nombreTLP){

	}

	public importarAlfabeto(nombreAlfabeto, alfabeto){

	}

	public importarTexto(nombreTexto, texto){

	}

	public importarListaPalabras(nombreLista, lista){

	}

	public getAlfabeto(nombreTeclado){

	}

	public modificarAlfabeto(nombreAlfabeto, alfabetoNuevo){

	}

	public modificarListaPalabras(noombreLista, listaNueva){

	}

	public void setInput(String e){
		Teclado inputs = new Input(e);
	}

	public Input getInput(String e){
		return inputs.getInput(e);
	}

}
//classe implementada per POL