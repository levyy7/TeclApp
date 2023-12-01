package main.persistencia;

import java.io.*;
import java.util.*;

public class CtrlPersistencia {

	private static GestorCSV gestC;

	public static void inicializar() {
		gestC = new GestorCSV();
	}

	public static void guardarTeclados(String[][] tec) {
		gestC.guardarInfo(tec, "../informacion/Teclados.csv");
	}

	public static void guardarAlfabetos(String[][] alf) {
		gestC.guardarInfo(alf, "../informacion/Alfabetos.csv");
	}

	public static void guardarTextos(String[][] text) {
		gestC.guardarInfo(text, "../informacion/Textos.csv");
	}

	public static void guardarListas(String[][] list) {
		gestC.guardarInfo(list, "../informacion/Listas.csv");
	}

	public static String[][] cargarTeclados() {
        return gestC.cargarInfo("../informacion/Teclados.csv");
	}

	public static String[][] cargarAlfabetos() {
        return gestC.cargarInfo("../informacion/Alfabetos.csv");
	}

	public static String[][] cargarTextos() {
        return gestC.cargarInfo("../informacion/Textos.csv");
	}

	public static String[][] cargarListas() {
        return gestC.cargarInfo("../informacion/Listas.csv");
	}
}