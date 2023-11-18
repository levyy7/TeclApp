package Controladors;
import java.io.*;
import java.util.*;

import main.dominio.*;
import java.util.HashSet;


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
		
		Input alfa = inputs.get(nombreAlfabeto);
		int numeroCaractes = alfa.getSize();
		Teclado board = new Teclado(nombreTeclado, nombreAlfabeto, numeroCaracters);
		teclados.add(nombreTeclado, board);
		Vector<Point<double, double>> playout = board.getplayout();
		return playout;
	}

	public void setLayout(String nombreTeclado, String nombreAlgoritmo, Vector<Integer> layout){
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
		importInput(nAlfa, alfa, "Alphabeto");
	}

	public void importarTexto(Sting nTexto, String texto){
		importInput(nTexto, texto, "Texto");
	}

	public void importarListaPalabras(String nLista, Map<String, Integer> lista){
		Input in = new Input(nLista,lista,"ListaPalabras");
		inputs.add(nLista, in);
	}

	public String getAlfabeto(String nombreTeclado){
		Teclado a = teclados.get(nombreTeclado);
		return a.alphabeto();
	}

	public void modificarAlfabeto(String nombreAlfabeto, String alfabetoNuevo){
		inputs.remove(nombreAlfabeto);
		importarAlfabeto(nombreAlfabeto, alfabetoNuevo);
	}
	public void modificarListaPalabras(String nombreLista, String listaNueva){
		inputs.remove(nombreLista);
		importarListaPalabras(nombreLista, listaNueva);
	}
	public void modificarTexto(String nombreTexto, String textoNuevo){
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