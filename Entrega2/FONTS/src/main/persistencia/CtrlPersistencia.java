package main.persistencia;

import java.io.*;
import java.util.*;

/**
 * Controlador de persistencia
 * Controlador que une los datos del dominio con los datos de la persistencia,
 * haciendo que los datos se guarden en la carpeta "informacion"
 * @author Miguel Ángel Montero Flores
*/
public class CtrlPersistencia {

	/** Contiene la instancia del GestorCSV */
	private static GestorCSV gestC;

	/** Constructora por defecto que asigna únicamente al gestorCSV*/
	public static void inicializar() {
		gestC = new GestorCSV();
	}

	/**
	 * Función que guarda un conjunto de teclados
	 * @param tec : teclados a guardar
	*/
	public static void guardarTeclados(String[][] tec) {
		gestC.guardarInfo(tec, "../informacion/Teclados.csv");
	}

	/**
	 * Función que guarda un conjunto de alfabetos
	 * @param alf : alfabetos a guardar
	*/
	public static void guardarAlfabetos(String[][] alf) {
		gestC.guardarInfo(alf, "../informacion/Alfabetos.csv");
	}

	/**
	 * Función que guarda un conjunto de textos
	 * @param text : textos a guardar
	*/
	public static void guardarTextos(String[][] text) {
		gestC.guardarInfo(text, "../informacion/Textos.csv");
	}

	/**
	 * Función que guarda un conjunto de listas
	 * @param list : listas a guardar
	*/
	public static void guardarListas(String[][] list) {
		gestC.guardarInfo(list, "../informacion/Listas.csv");
	}

	/**
	 * Función que carga un conjunto de teclados
	 * @return String[][] : teclados a cargar
	*/
	public static String[][] cargarTeclados() {
        return gestC.cargarInfo("../informacion/Teclados.csv");
	}

	/**
	 * Función que carga un conjunto de alfabetos
	 * @return String[][] : alfabetos a cargar
	*/

	public static String[][] cargarAlfabetos() {
        return gestC.cargarInfo("../informacion/Alfabetos.csv");
	}

	/**
	 * Función que carga un conjunto de textos
	 * @return String[][] : textos a cargar
	*/

	public static String[][] cargarTextos() {
        return gestC.cargarInfo("../informacion/Textos.csv");
	}

	/**
	 * Función que carga un conjunto de listas
	 * @return String[][] : listas a cargar
	*/
	public static String[][] cargarListas() {
        return gestC.cargarInfo("../informacion/Listas.csv");
	}
}
//autor Miguel