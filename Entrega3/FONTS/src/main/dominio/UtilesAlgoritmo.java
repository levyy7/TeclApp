package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

/**
 * Clase Utiles algoritmo
 * Clase que contiene funciones necesarias para ejecutar correctamente los
 * algoritmos
 * @author Miguel Ángel Montero Flores
*/
public class UtilesAlgoritmo {

	/**
	 * Función que calcula el tráfico de los caracteres
	 * @param textos
	 * @param listas
	 * @param map : mapa de posiciones
	 * @return int[][] : matriz tráfico int
	*/
	public int[][] calculoTraficoInt(Vector<String> textos, Vector<Map<String, Integer>> listas, Map<Character, Integer> map) {
		
		int tam_alf = map.size();
		int[][] traficoInt = new int[tam_alf][tam_alf];

		//Recorremos todo el texto
		for (int i = 0; i < textos.size(); ++i) {

			//Letra por letra calculamos el trafico
			char[] palabra = textos.elementAt(i).toCharArray();
			int tam = palabra.length;
			for (int j = 0; j < tam-1; ++j) {
				int posLetra1 = map.get(palabra[j]);
				int posLetra2 = map.get(palabra[j+1]);
				traficoInt[posLetra1][posLetra2]++;
				traficoInt[posLetra2][posLetra1]++;
			}
		}

		//Recorremos todas las listas
		for (int i = 0; i < listas.size(); ++i) {

			Map<String, Integer> lista = listas.elementAt(i);

			//Recorremos una lista
			for (Map.Entry<String, Integer> entry : lista.entrySet()) {

				char[] palabra = entry.getKey().toCharArray();
				Integer frecuencia = entry.getValue();
				int tam = palabra.length;

				//Letra por letra calculamos el trafico
				for (int j = 0; j < tam-1; ++j) {
					int posLetra1 = map.get(palabra[j]);
					int posLetra2 = map.get(palabra[j+1]);
					traficoInt[posLetra1][posLetra2] += frecuencia;
					traficoInt[posLetra2][posLetra1] += frecuencia;
				}
			}
		}

		return traficoInt;
	}

	/**
	 * Función que calcula la matriz DistLoc, que contendrá la distancia
	 * entre cada tecla del physical layout
	 * @param playout : physical layout
	 * @return double[][] : matriz DistLoc
	*/
	public double[][] calculoDistLoc(Point2D[] playout) {
	    int tam = playout.length;
	    double[][] distLoc = new double[tam][tam];

	    for (int i = 0; i < tam; ++i) {
	        for (int j = i; j < tam; ++j) {
	        	double x = distanciaEuclidiana(playout[i], playout[j]);
	            distLoc[i][j] = x;
	            distLoc[j][i] = x;
	        }
	    }

	    return distLoc;
	}

	/**
	 * Función auxiliar que calcula la distancia euclidiana entre 2 puntos
	 * @param p1 : punto 1
	 * @param p2 :  punto 2
	 * return double : distancia Euclidiana entre los puntos p1 y p2
	*/
	private double distanciaEuclidiana(Point2D p1, Point2D p2) {
	    return Math.sqrt((p1.getX() - p2.getX())*(p1.getX() - p2.getX()) + 
	    	(p1.getY() - p2.getY())*(p1.getY() - p2.getY()));
	}
}
//autor Miguel