package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

public class Playout {
	private Point2D[] teclas;

    public Playout(int caracteres){
        int n = (int)Math.sqrt(caracteres);
        this.teclas = new Point2D[caracteres]; 

        if(n*n == caracteres) {
            for(double i = 0; i<n; ++i){
                for(double j = 0; j<n; ++j) this.teclas[(int)(n*i+j)].setLocation(i, j);
            }
        }
        else{
            int minsize = (int) Math.ceil(Math.sqrt(caracteres)); //podria ser minsize = n+1;

            //asignamos posiciones en el vector de los elementos dentro de la submatriz(imaginaria)
            for(double i = 1; i<minsize; ++i){
                for(double j = 1; j<minsize; ++j) this.teclas[(int)(minsize*i+j)].setLocation(i, j);
            }

            int[] x = new int[n];
            for(int i = 0; i<n; ++i){
                int j = i;
                if(j%2 != 0) ++j;
                else j *= -1;
                x[i] = (n + 2*j)/2;
            } 
            //rellenar las teclas sobrantes
            int s = caracteres - n*n;
            for(double i = 0; i<s; ++i){
                if(i<n) this.teclas[x[(int)i]*minsize].setLocation(i, 0);
                else this.teclas[x[(int)i]].setLocation(0, i);
            }
        }        
	}

	public Point2D[] getTeclas(){
        return this.teclas;
    }

}
//mariona
