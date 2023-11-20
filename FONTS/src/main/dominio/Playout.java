package main.dominio;

import java.util.*;
import java.awt.geom.Point2D;

public class Playout {
	private Point2D[] teclas;

    public Playout(int caracteres){
        this.teclas = new Point2D[caracteres]; 

        int n = (int)Math.sqrt(caracteres);
        if(n*n == caracteres) perfectSquare(n);
        else notPerfectSquare(caracteres, n);
    }

    public Point2D[] getTeclas(){
        return this.teclas;
    }

    private void perfectSquare(int n) {
        for(double i = 0; i<n; ++i){
            for(double j = 0; j<n; ++j) this.teclas[(int)(n*i+j)] = new Point2D.Double(i, j);
        }
    }

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
        for(double t = 0; t<=s; ++t){
            if(t<=n) pos[x[(int)t%n]][0] = new Point2D.Double(t%n, 0);
            else pos[0][x[(int)t%n]] = new Point2D.Double(0, (t)%n + 1);
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

/*
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Ingresa un número de caracteres: ");
        int n = s.nextInt();

        Playout p = new Playout(n);
        Point2D[] v = p.getTeclas();

        for(int i = 0; i<n; ++i) System.out.println(" " + v[i].getX() + ", " + v[i].getY() + " // ");
    }*/

}
//mariona
