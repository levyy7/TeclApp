package main.dominio;

import java.util.*;

public class UtilesAlgoritmo {

	//Mapa de posiciones de las letras de un alfabeto
	private Map<Character, Integer> inicializaAlfabeto(String alfabeto) {

		Map<Character, Integer> map = new HashMap<>();

		char[] letrasDelAlfabeto = alfabeto.toCharArray();

		for (int i = 0; i < letrasDelAlfabeto.length; ++i) 
			map.put(letrasDelAlfabeto[i], i);
		
		return map;
	}

	//Calculo de una matriz que tiene las frecuencias de las letras en un texto
	public int[][] contarLetras(Vector<String> texto, String alfabeto) {

		Map<Character, Integer> map = new HashMap<>();
		map = inicializaAlfabeto(alfabeto);

		//map.forEach((key, value) -> System.out.println(key + ": " + value));
		
		int tam_alf = alfabeto.length();
		int[][] traficoInt = new int[tam_alf][tam_alf];

		for (int i = 0; i < texto.size(); ++i) {

			//Letra por letra calculamos el trafico
			char[] palabra = texto.elementAt(i).toCharArray();
			int tam = palabra.length;
			for (int j = 0; j < tam-1; ++j) {
				int posLetra1 = map.get(palabra[j]);
				int posLetra2 = map.get(palabra[j+1]);
				traficoInt[posLetra1][posLetra2]++;
				traficoInt[posLetra2][posLetra1]++;
			}

		}

		return traficoInt;
	}

	//Calculo de la matriz de distancias entre posiciones
	public double[][] distLoc(int[][] posiciones) {

		int numDeLetras = posiciones.length*posiciones[0].length;
		double[][] distL = new double[numDeLetras][numDeLetras];

		//Por cada letra miramos la distancia 
		for (int i = 0; i < posiciones.length; ++i) {

			for (int j = 0; j < posiciones.length; ++j) {
				for (int k = 0; k < posiciones[0].length; ++k) {

				}
			}
		}

		return new double[1][1]; //pa k compile
	}

}