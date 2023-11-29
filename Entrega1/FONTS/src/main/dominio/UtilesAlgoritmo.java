package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

public class UtilesAlgoritmo {


	//Calculo de una matriz que tiene las frecuencias de las letras en un texto
	public int[][] calculoTraficoInt(Vector<String> texto, Vector<Map<String, Integer>> listas, Map<Character, Integer> map) {
		
		int tam_alf = map.size();
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

		for (int i = 0; i < listas.size(); ++i) {

			Map<String, Integer> lista = listas.elementAt(i);

			for (Map.Entry<String, Integer> entry : lista.entrySet()) {

				char[] palabra = entry.getKey().toCharArray();
				Integer frecuencia = entry.getValue();
				int tam = palabra.length;

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

	//Calculo de la matriz de distancias entre posiciones
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

	private double distanciaEuclidiana(Point2D p1, Point2D p2) {
	    return Math.sqrt((p1.getX() - p2.getX())*(p1.getX() - p2.getX()) + 
	    	(p1.getY() - p2.getY())*(p1.getY() - p2.getY()));
	}
}
//autor Miguel