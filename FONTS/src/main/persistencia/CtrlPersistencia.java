package main.persistencia;

import java.io.*;
import java.util.*;
import Excepcions.ArchivoNoEsCSV;

/**
 * Controlador de persistencia
 * Controlador que une los datos del dominio con los datos de la persistencia,
 * haciendo que los datos se guarden en la carpeta "informacion"
 * @author Eneko Sabate Iturgaiz
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

	private static CtrlPersistencia ctrlP;

	/** Constructora por defecto que asigna únicamente al gestorCSV*/
	public CtrlPersistencia() {
		gestC = new GestorCSV();
	}

	public static void inicializar() {
		gestC = new GestorCSV();
	}

	/**
	 * Función que guarda un teclado
	 * @param tec : teclado a guardar
	*/
	public static void guardarTeclado(String[] tec) {
		gestC.guardarInfo(new String[][]{tec}, PATHTec);
	}

	/**
	 * Función que guarda un alfabeto
	 * @param alf : alfabeto a guardar
	*/
	public static void guardarAlfabeto(String[] alf) {
		gestC.guardarInfo(new String[][]{alf}, PATHAlf);
	}

	/**
	 * Función que guarda un texto
	 * @param text : texto a guardar
	*/
	public static void guardarTexto(String[] text) {
		gestC.guardarInfo(new String[][]{text}, PATHTex);
	}

	/**
	 * Función que guarda una lista
	 * @param list : listas a guardar
	*/
	public static void guardarLista(String[] list) {
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
	 * Función que carga un conjunto de teclados de un path específico
	 * @return String[][] : teclados a cargar
	*/
	public static String[][] cargarTeclados(String path) throws ArchivoNoEsCSV {
		if (!compruebaPath(path)) throw new ArchivoNoEsCSV();
        return gestC.cargarInfo(path);
	}

	/**
	 * Función que carga un conjunto de alfabetos
	 * @return String[][] : alfabetos a cargar
	*/
	public static String[][] cargarAlfabetos() {
        return gestC.cargarInfo(PATHAlf);
	}

	/**
	 * Función que carga un conjunto de alfabetos de un path específico
	 * @return String[][] : alfabetos a cargar
	*/
	public static String[][] cargarAlfabetos(String path) throws ArchivoNoEsCSV {
		if (!compruebaPath(path)) throw new ArchivoNoEsCSV();
        return gestC.cargarInfo(path);
	}

	/**
	 * Función que carga un conjunto de textos
	 * @return String[][] : textos a cargar
	*/
	public static String[][] cargarTextos() {
        return gestC.cargarInfo(PATHTex);
	}

	/**
	 * Función que carga un conjunto de textos de un path específico
	 * @return String[][] : textos a cargar
	*/
	public static String[][] cargarTextos(String path) throws ArchivoNoEsCSV {
		if (!compruebaPath(path)) throw new ArchivoNoEsCSV();
        return gestC.cargarInfo(path);
	}

	/**
	 * Función que carga un conjunto de listas
	 * @return String[][] : listas a cargar
	*/
	public static String[][] cargarListas() {
        return gestC.cargarInfo(PATHLis);
	}

	/**
	 * Función que carga un conjunto de listas de un path específico
	 * @return String[][] : listas a cargar
	*/
	public static String[][] cargarListas(String path) throws ArchivoNoEsCSV {
		if (!compruebaPath(path)) throw new ArchivoNoEsCSV();
        return gestC.cargarInfo(path);
	}

	/**
	 * Función que modifica un teclado
	 * @param tec : teclado a modificar
	*/
	public static void modificarTeclado(String[] tec) {
		gestC.borrarInfo(new String[]{tec[0]}, PATHTec);
		gestC.guardarInfo(new String[][]{tec}, PATHTec);
	}

	/**
	 * Función que modifica un alfabeto
	 * @param alf : alfabeto a modificar
	*/
	public static void modificarAlfabeto(String[] alf) {
		gestC.borrarInfo(new String[]{alf[0]}, PATHAlf);
		gestC.guardarInfo(new String[][]{alf}, PATHAlf);
	}

	/**
	 * Función que modificar un texto
	 * @param text : texto a modificar
	*/
	public static void modificarTexto(String[] text) {
		gestC.borrarInfo(new String[]{text[0]}, PATHTex);
		gestC.guardarInfo(new String[][]{text}, PATHTex);
	}

	/**
	 * Función que modifica una lista
	 * @param list : listas a modificar
	*/
	public static void modificarLista(String[] list) {
		gestC.borrarInfo(new String[]{list[0]}, PATHLis);
		gestC.guardarInfo(new String[][]{list}, PATHLis);
	}

	/**
	 * Función que borra un teclado
	 * @param tec : teclados a borrar
	*/
	public static void borrarTeclado(String tec) {
		gestC.borrarInfo(new String[]{tec}, PATHTec);
	}

	/**
	 * Función que borra un alfabeto
	 * @param alf : alfabeto a borrar
	*/
	public static void borrarAlfabeto(String alf) {
		gestC.borrarInfo(new String[]{alf}, PATHAlf);
	}

	/**
	 * Función que borra un texto
	 * @param text : texto a borrar
	*/
	public static void borrarTexto(String text) {
		gestC.borrarInfo(new String[]{text}, PATHTex);
	}

	/**
	 * Función que borra una lista
	 * @param list : lista a borrar
	*/
	public static void borrarLista(String list) {
		gestC.borrarInfo(new String[]{list}, PATHLis);
	}

	/** Función que comprueba que un Path contiene la exntension .csv*/
	private static boolean compruebaPath(String path) {
		if (path.length() < 5) return false;
		String tipoArchivo = path.substring(path.length() - 4);
		if (!tipoArchivo.equals(".csv")) return false;
		return true;
	}
}