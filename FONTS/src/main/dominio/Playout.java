package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

/**
 * Clase Playout.
 * Representa la disposición de teclas en un teclado virtual.
 * Calcula la posición de las teclas en función del número de caracteres.
 * Utiliza coordenadas (x, y) de Point2D para representar la posición de cada tecla.
 * @author Mariona Aguilera Folqué
 */
public class Playout {
	private Point2D[] teclas;

    /**
     * Constructor de la clase Playout.
     * Calcula y asigna la posición de las teclas en función del número de caracteres.
     * @param caracteres El número de caracteres para el teclado.
     */
    public Playout(int caracteres){
        this.teclas = new Point2D[caracteres]; 

        int n = (int)Math.sqrt(caracteres);
        if(n*n == caracteres) perfectSquare(n);
        else notPerfectSquare(caracteres, n);
    }

    /**
     * Obtiene el arreglo de coordenadas que representa la posición de las teclas.
     * @return El arreglo de coordenadas de las teclas.
     */
    public Point2D[] getTeclas(){
        return this.teclas;
    }

    public int[][] toIntArray() {

        int size = (int) Math.ceil(Math.sqrt(teclas.length));
        int[][] resultado = new int[size][size];
        int i = 1;
        for (Point2D actual : teclas) {
            int x = (int) actual.getX();
            int y = (int) actual.getY();
            resultado[x][y] = i;
            ++i;
        }
        return resultado;
    }

    // Método privado para organizar las teclas en un cuadrado perfecto
    private void perfectSquare(int n) {
        for(double i = 0; i<n; ++i){
            for(double j = 0; j<n; ++j) this.teclas[(int)(n*i+j)] = new Point2D.Double(i, j);
        }
    }

    // Método privado para organizar las teclas en un cuadrado no perfecto
    private void notPerfectSquare(int caracteres, int n){
        int minsize = (int) Math.ceil(Math.sqrt(caracteres)); //podria ser minsize = n+1;
        Point2D[][] pos = new Point2D[minsize][minsize];
        //asignamos posiciones en el vector de los elementos dentro de la submatriz(imaginaria)
        for(double i = 1; i<minsize; ++i){
            for(double j = 1; j<minsize; ++j){
                pos[(int)i][(int)j] = new Point2D.Double(i, j);
            } 
        }

        //x nos da un orden para las diferentes posiciones a las que queremos poner mas teclas
        int[] x = new int[n];
        int i = 0; int j = -1;
        while(i<n){
            if(i==0) x[i] = minsize/2;
            else  x[i] = x[i-1] + i*j;
            ++i; j*=-1;
        }

        //rellenar las teclas sobrantes
        int s = caracteres -  n*n;
        for(double t = 0; t<s; ++t){
            if((int)t<n) pos[x[(int)t%n]][0] = new Point2D.Double(x[(int)t%n], 0);
            else pos[0][x[(int)t%n]] = new Point2D.Double(0, x[(int)t%n]);

        }

        int c = 0;
        for(int r = 0; r<minsize; ++r){
            for(int t = 0; t<minsize; ++t) {
                if(pos[r][t] != null) {
                    this.teclas[c] = pos[r][t];
                    ++c;
                }
            }
        }
    }        

}
//mariona
