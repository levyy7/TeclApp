package ControladorsEntrada;

import java.io.*;
import java.util.*;

import .*;
import dominio.*;
import Excepcions.*;

public class CtrlEntrada{

 	private Vector<Teclado> teclados;
 	private Vector<Input> inputs;

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
	    teclados = new Vector<Teclado>();
	    inputs = new Vector<Input>();
	}


	public Teclado setTeclado(String nom, String alfa, String text){
		Input a = getInput(alfa);
		Input t = getInput(text);
		Teclado board = new Teclado(nom, a, t); //problemes en la diferencició de alphabets i textos i exepcions
		teclados.add(board);
		return board; 
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

	public void setInput(String e){
		Teclado inputs = new Input(e);
	}

	public Input getInput(String e){
		return inputs.getInput(e);
	}

//modificar texto?
//modificar teclado?
//guardar teclado?

}
//classe implementada per POL