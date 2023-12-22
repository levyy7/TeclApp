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
     * Creación de nuevas instancias de Teclaod
     * @param teclados : teclados a importar
     * @throws TecladoYaExiste
    */
	public void importarTeclados(String[][] teclados) throws TecladoYaExiste, TecladoMalImportado {

		String[] nombres = new String[teclados.length];
		int i = 0;
		for (String[] tecladoActual : teclados) {

	        if (tecladoActual.length != 4) throw new TecladoMalImportado("Tamanyo");

			Teclado tec = new Teclado(tecladoActual);

			Input alfabeto = inputs.get(tec.getAlfabeto());

			if (this.teclados.containsKey(tec.getNombre())) 
				throw new TecladoYaExiste(tec.getNombre());

	        else if (!tec.getAlgoritmo().equals("QAP") && !tec.getAlgoritmo().equals("GEN")) 
	        	throw new TecladoMalImportado("Algoritmo");

	        else if (alfabeto == null)	throw new TecladoMalImportado("Alfabeto no existe");

	        else if (!(alfabeto instanceof Alfabeto))
	        	throw new TecladoMalImportado("Alfabeto no existe");

	        Alfabeto alf = (Alfabeto) alfabeto;
	        String infoAlf = alf.getLetras();
	        if (!comproE.layoutCorrespondeAlfabeto(infoAlf, tec.getLayout()))
	        	throw new TecladoMalImportado("Layout");

	        for (String nombre : nombres) {
	        	if (nombre == null) break;
	        	if (nombre.equals(tec.getNombre())) 
	        		throw new TecladoMalImportado("Nombre repetido");
	        }
	        nombres[i] = tec.getNombre();
	        ++i;
		}

		for (String[] tecladoActual : teclados) {
			Teclado tec = new Teclado(tecladoActual);
			this.teclados.put(tec.getNombre(), tec);
		}
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
	public String[] consultaTeclado(String nombreTeclado) throws TecladoInexistente {
		if (teclados.containsKey(nombreTeclado) == false) throw new TecladoInexistente(nombreTeclado);
		String[] board = teclados.get(nombreTeclado).toStringArray();

		return board;
	}

	/**
	 * Consulta de un layout de un teclado existente
	 * @param nombreTeclado
	 * @return char[][] : layout consultado
	*/
	public char[][] consultaLayoutTeclado(String nombreTeclado) {
		return teclados.get(nombreTeclado).consultaLayout();
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
	public void crearAlfabeto(String nAlfa, String alfa) throws InputJaCreat, AlfabetoInvalido {
		if(inputs.containsKey(nAlfa)) throw new InputJaCreat(nAlfa);
		if(comproE.alfaCorrecto(alfa) == false) throw new AlfabetoInvalido(nAlfa);
		Input in = new Alfabeto(nAlfa, alfa);
		inputs.put(nAlfa, in);
	}

	/**
     * Creación de nuevas instancias de Alfabeto
     * @param alfabetos : alfabetos a importar
     * @throws InputJaCreat
    */
	public void importarAlfabetos(String[][] alfabetos) throws InputJaCreat, AlfabetoMalImportado {
		
		String[] nombres = new String[alfabetos.length];
		int i = 0;
		for (String[] alfabetoActual : alfabetos) {

			if (alfabetoActual.length != 2) 
				throw new AlfabetoMalImportado("Tamanyo");
			
			Input in = new Alfabeto(alfabetoActual);	
			if (inputs.containsKey(in.getNombre())) 
				throw new InputJaCreat(in.getNombre());

			Alfabeto alf = (Alfabeto) in;
			if (!comproE.alfaCorrecto(alf.getLetras())) 
				throw new AlfabetoMalImportado("Caracteres repetidos");
			
			for (String nombreActual : nombres) {
				if (nombreActual == null) break;
				if (nombreActual.equals(in.getNombre())) 
					throw new AlfabetoMalImportado("Nombre repetido");
			}

			nombres[i] = in.getNombre();
			++i;
		}

		for (String[] alfabetoActual : alfabetos) {
			Input in = new Alfabeto(alfabetoActual);
			inputs.put(in.getNombre(), in);
		}
	}

	/**
     * Consulta un alfabeto
     * @param nombreAlfabeto : nombre del alfabeto
     * @return Alfabeto : contenido del alfabeto
     * @throws InputInexistente
     * @throws WrongInputType
    */
	public String[] getAlfabeto(String nombreAlfabeto) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreAlfabeto);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof Alfabeto)) throw new WrongInputType("Alfabeto", in.getClass().getName());

		return in.toStringArray();
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
		if(comproE.alfaCorrecto(alfabetoNuevo) == false) throw new AlfabetoInvalido(nombreAlfabeto);
		
		Alfabeto alf =  (Alfabeto) inputs.get(nombreAlfabeto);
		
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
	public void crearTexto(String nTexto, String texto) throws InputJaCreat {
		if(inputs.containsKey(nTexto)) throw new InputJaCreat(nTexto);

		Input in = new Texto(nTexto, texto);
		inputs.put(nTexto, in);
	}

	/**
     * Creación de nuevas instancias de Texto
     * @param textos : textos a importar
     * @throws InputJaCreat
    */
	public void importarTextos(String[][] textos) throws InputJaCreat, TextoMalImportado {
		
		String[] nombres = new String[textos.length];
		int i = 0;
		for (String[] textoActual : textos) {

			if (textoActual.length != 2) throw new TextoMalImportado("Tamanyo");

			Input in = new Texto(textoActual);
			if(inputs.containsKey(in.getNombre())) throw new InputJaCreat(in.getNombre());
			
			for (String nombreActual : nombres) {
				if (nombreActual == null) break;
				if (nombreActual.equals(in.getNombre())) 
					throw new TextoMalImportado("Nombre repetido");
			}
			nombres[i] = in.getNombre();
			++i;
		}

		for (String[] textoActual : textos) {
			Input in = new Texto(textoActual);
			inputs.put(in.getNombre(), in);
		}
	}

	/**
     * Consulta de un texto
     * @param nombreTexto : nombre del texto
     * @return Texto : Texto consultado
     * @throws InputInexistente 
     * @throws WrongInputType
    */
	public String[] getTexto(String nombreTexto) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreTexto);

		if (in == null)	throw new InputInexistente();
		if (!(in instanceof Texto)) throw new WrongInputType("Texto", in.getClass().getName());

		return in.toStringArray();
	}

	public String[] getInfoTLP(String nombreTLP) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreTLP);

		if (in == null)	throw new InputInexistente();
		else if (!(in instanceof TLP)) throw new WrongInputType("TLP", in.getClass().getName());

		return in.toStringArray();
	}

	/**
     * Modificación de un texto
     * @param nombreTexto : nombre del texto
     * @param textoNuevo : nuevo contenido del texto
     * @throws InputInexistente 
     * @throws WrongInputType
    */
	public void modificarTexto(String nombreTexto, String textoNuevo) throws InputInexistente, WrongInputType {
		Texto tex = (Texto) inputs.get(nombreTexto);
		
		tex.setTexto(textoNuevo);
	}

    /**
	 * Eliminar un TLP
	 * @param nombreTLP
	 * @throws InputInexistente
	 * @throws WrongInputType
    */
    public void borrarTLP(String nombreTLP) throws InputInexistente, WrongInputType {
    	Input in = inputs.get(nombreTLP);

    	if (in == null) throw new InputInexistente();
    	if (!(in instanceof TLP)) throw new WrongInputType("TLP", in.getClass().getName());

    	inputs.remove(nombreTLP);
    }

	/**
     * Creación de una nueva instancia de ListaPalabras
     * @param nLista : nombre de la lista
     * @param lista : contenido de la lista
     * @throws InputJaCreat
    */
	public void crearListaPalabras(String nLista, Map<String, Integer> lista) throws InputJaCreat{
		if(inputs.containsKey(nLista)) throw new InputJaCreat(nLista);

		Input in = new ListaPalabras(nLista,lista); 
		inputs.put(nLista, in);
	}

	/**
     * Creación de nuevas instancias de ListaPalabras
     * @param listas : listas a importar
     * @throws InputJaCreat
    */
	public void importarListasPalabras(String[][] listas) throws InputJaCreat, ListaMalImportada {
		
		String[] nombres = new String[listas.length];
		int i = 0;
		for (String[] listaActual : listas) {

			if (listaActual.length != 2) throw new ListaMalImportada("Tamanyo");

			else if (!comproE.listaBienDefinida(listaActual[1]))
				throw new ListaMalImportada("Lista mal definida");

			Input in = new ListaPalabras(listaActual);
			if(inputs.containsKey(in.getNombre())) throw new InputJaCreat(in.getNombre());
		
			for (String nombreActual : nombres) {
				if (nombreActual == null) break;
				if (nombreActual.equals(in.getNombre())) 
					throw new ListaMalImportada("Nombre repetido");
			}
			nombres[i] = in.getNombre();
			++i;
		}

		for (String[] listaActual : listas) {
			Input in = new ListaPalabras(listaActual);
			inputs.put(in.getNombre(), in);
		}
	}

	/**
     * Consulta de una lista de palabras
     * @param nombreLista : nombre de la lista
     * @return ListaPalabras : Lista consultada
    */
	public String[] getListaPalabras(String nombreLista) throws InputInexistente, WrongInputType {
		Input in = inputs.get(nombreLista);
		
		if (in == null)	throw new InputInexistente();
		if (!(in instanceof ListaPalabras)) throw new WrongInputType("ListaPalabras", in.getClass().getName());

		return in.toStringArray();
	} 

	/**
     * Modificación de una lista de palabras
     * @param nombreLista : nombre de la lista
     * @param listaNueva : nuevo contenido de la lista
    */
	public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) throws InputInexistente, WrongInputType {
		ListaPalabras lp = (ListaPalabras) inputs.get(nombreLista);

		lp.setListaFreq(listaNueva);
	}

    /**
	 * Consulta de un input
	 * @param e : Input a consultar
	 * @return Input : Input consultado
    */
	public String[] getInput(String e) throws InputInexistente{
		String[] in = inputs.get(e).toStringArray();			
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
		if(comproE.textoCorrecto(textos, alfabeto) == false) throw new TextoNoValido();
	}

	/**
     * Comprobación que todos los carácteres del texto son del alfabeto 
     * @param listas : vector del contenido de las listas que se quieren comprobar
     * @param alfabeto : contenido de un alfabeto
     * @throws ListaNoValida
    */
	public void compruebaListas(Vector<Map<String, Integer>> listas, String alfabeto) throws ListaNoValida{
		if(comproE.listaCorrecto(listas, alfabeto) == false) throw new ListaNoValida();
	}

	/**
     * Consulta de los teclados
     * @return HashMap(String, Teclado): conjunto de teclados
    */
	public String[][] consultaInfoTeclados() {
		String[][] tec = new String[teclados.size()][3];
		int i = 0;
		for (Map.Entry<String, Teclado> actual : teclados.entrySet()) {
			String[] tecladoActual = actual.getValue().toStringArray();
			tec[i] = tecladoActual;
			++i;
		}
		return tec;
	}

	/**
     * Consulta de todos los alfabetos
     * @return HashMap(String, Input): conjunto de alfabetos
    */
	public String[][] consultaAlfabetos() {
		HashMap<String, String[]> a  = new HashMap<String, String[]>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof Alfabeto) a.put(actual.getKey(),actual.getValue().toStringArray());
        }

		String[][] alf = new String[a.size()][2];
		int i = 0;
		for (Map.Entry<String, String[]> actual : a.entrySet()) {
			String[] alfabetoActual = actual.getValue();
			alf[i] = alfabetoActual;
			++i;
		}
		return alf;
    }

    /**
     * Consulta de todos los textos
     * @return HashMap(String, Input): conjunto de textos
    */
    public String[][] consultaTextos() {
    	HashMap<String, String[]> a  = new HashMap<String, String[]>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof Texto) a.put(actual.getKey(),actual.getValue().toStringArray());
        }
        
        String[][] tex = new String[a.size()][2];
		int i = 0;
		for (Map.Entry<String, String[]> actual : a.entrySet()) {
			String[] textoActual = actual.getValue();
			tex[i] = textoActual;
			++i;
		}
		return tex;
    }

	/**
     * Consulta de todas las listas
     * @return HashMap(String, Input): conjunto de listas
    */
    public String[][] consultaListas() {
    	HashMap<String, String[]> a  = new HashMap<String, String[]>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof ListaPalabras) a.put(actual.getKey(),actual.getValue().toStringArray());
        }

        String[][] list = new String[a.size()][2];
		int i = 0;
		for (Map.Entry<String, String[]> actual : a.entrySet()) {
			String[] listaActual = actual.getValue();
			list[i] = listaActual;
			++i;
		}
		return list;
    }

    public String[][] consultaInfoTLPs() {
    	HashMap<String, String[]> a  = new HashMap<String, String[]>();
		for (Map.Entry<String, Input> actual: inputs.entrySet()){
        	if (actual.getValue() instanceof TLP) a.put(actual.getKey(),actual.getValue().toStringArray());
        }

        String[][] tlp = new String[a.size()][2];
		int i = 0;
		for (Map.Entry<String, String[]> actual : a.entrySet()) {
			String[] tlpActual = actual.getValue();
			tlp[i] = tlpActual;
			++i;
		}
		return tlp;
    }

}
//classe implementada per POL