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
		return playout;
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
	/*
	public Teclado getTeclado(String e){
		return teclados.getTeclado(e);
	}
	public Vector<Teclado> consultarTeclados(){
		return teclados;
	}
	*/

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

	public  importarAlfabeto(String nombreAlfabeto, String alfabeto){
		importInput()
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

	public void importInput(String e, ){
		Teclado inputs = new Input(e);
	}

	public Input getInput(String e){
		return inputs.getInput(e);
	} 
	//cargar datos persistencia?

}
//classe implementada per POL