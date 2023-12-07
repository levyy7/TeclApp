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

	/** Path relativa del archivo "Teclados.csv" */
	private static final String PATHTec = "../informacion/Teclados.csv";
	/** Path relativa del archivo "Alfabetos.csv" */
	private static final String PATHAlf = "../informacion/Alfabetos.csv";
	/** Path relativa del archivo "Textos.csv" */
	private static final String PATHTex = "../informacion/Textos.csv";
	/** Path relativa del archivo "Listas.csv" */
	private static final String PATHLis = "../informacion/Listas.csv";



	/** Constructora por defecto que asigna únicamente al gestorCSV*/
	public CtrlPersistencia() {
		gestC = new GestorCSV();
	}

	/**
	 * Función que guarda un conjunto de teclados
	 * @param tec : teclados a guardar
	*/
	public static void guardarTeclados(String[] tec) {
		gestC.guardarInfo(new String[][]{tec}, PATHTec);
	}

	/**
	 * Función que guarda un conjunto de alfabetos
	 * @param alf : alfabetos a guardar
	*/
	public static void guardarAlfabetos(String[] alf) {
		gestC.guardarInfo(new String[][]{alf}, PATHAlf);
	}

	/**
	 * Función que guarda un conjunto de textos
	 * @param text : textos a guardar
	*/
	public static void guardarTextos(String[] text) {
		gestC.guardarInfo(new String[][]{text}, PATHTex);
	}

	/**
	 * Función que guarda un conjunto de listas
	 * @param list : listas a guardar
	*/
	public static void guardarListas(String[] list) {
		gestC.guardarInfo(new String[][]{list}, PATHLis);
	}

	/**
	 * Función que carga un conjunto de teclados
	 * @return String[][] : teclados a cargar
	*/
	public static String[][] cargarTeclados() {
        return gestC.cargarInfo(PATHTec);
	}

	/**
	 * Función que carga un conjunto de alfabetos
	 * @return String[][] : alfabetos a cargar
	*/

	public static String[][] cargarAlfabetos() {
        return gestC.cargarInfo(PATHAlf);
	}

	/**
	 * Función que carga un conjunto de textos
	 * @return String[][] : textos a cargar
	*/

	public static String[][] cargarTextos() {
        return gestC.cargarInfo(PATHTex);
	}

	/**
	 * Función que carga un conjunto de listas
	 * @return String[][] : listas a cargar
	*/
	public static String[][] cargarListas() {
        return gestC.cargarInfo(PATHLis);
	}

	/**
	 * Función que guarda un conjunto de teclados
	 * @param tec : teclados a guardar
	*/
	public static void modificarTeclado(String[] tec) {
		gestC.borrarInfo(new String[]{tec[0]}, PATHTec);
		gestC.guardarInfo(new String[][]{tec}, PATHTec);
	}

	/**
	 * Función que guarda un conjunto de alfabetos
	 * @param alf : alfabetos a guardar
	*/
	public static void modificarAlfabeto(String[] alf) {
		gestC.borrarInfo(new String[]{alf[0]}, PATHAlf);
		gestC.guardarInfo(new String[][]{alf}, PATHAlf);
	}

	/**
	 * Función que guarda un conjunto de textos
	 * @param text : textos a guardar
	*/
	public static void modificarTexto(String[] text) {
		gestC.borrarInfo(new String[]{text[0]}, PATHTex);
		gestC.guardarInfo(new String[][]{text}, PATHTex);
	}

	/**
	 * Función que guarda un conjunto de listas
	 * @param list : listas a guardar
	*/
	public static void modificarLista(String[] list) {
		gestC.borrarInfo(new String[]{list[0]}, PATHLis);
		gestC.guardarInfo(new String[][]{list}, PATHLis);
	}

	/**
	 * Función que guarda un conjunto de teclados
	 * @param tec : teclados a guardar
	*/
	public static void borrarTeclado(String tec) {
		gestC.borrarInfo(new String[]{tec}, PATHTec);
	}

	/**
	 * Función que guarda un conjunto de alfabetos
	 * @param alf : alfabetos a guardar
	*/
	public static void borrarAlfabeto(String alf) {
		gestC.borrarInfo(new String[]{alf}, PATHAlf);
	}

	/**
	 * Función que guarda un conjunto de textos
	 * @param text : textos a guardar
	*/
	public static void borrarTexto(String text) {
		gestC.borrarInfo(new String[]{text}, PATHTex);
	}

	/**
	 * Función que guarda un conjunto de listas
	 * @param list : listas a guardar
	*/
	public static void borrarLista(String list) {
		gestC.borrarInfo(new String[]{list}, PATHLis);
	}
}