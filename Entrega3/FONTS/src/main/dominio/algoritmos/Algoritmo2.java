package main.dominio.algoritmos;

import java.util.*;
import java.awt.Point;

public class Algoritmo2 extends EstrategiaCreacionLayout {
	private static final int GENETIC = 100;
	private static final int POBLACIO = 16;

	public void selectSolutions(ArrayList<ArrayList<Point>> solucions, ArrayList<Double> valors){
        
        for (int i = 0; i < POBLACIO/2; i++) {
            double max = Double.MIN_VALUE;
            for (double num : valors) {
                if (num > max) {
                    max = num;
                }
            }
            int indice = valors.indexOf(max);
            solucions.remove(indice);
            valors.remove(indice);
        }
	}


	public void generarFills(ArrayList<ArrayList<Point>> solucions, ArrayList<Double> valors, double[][] distLoc, int[][] traficoInst){
        ArrayList<ArrayList<Point>> solucions2 = new ArrayList<ArrayList<Point>>();
        for (int i = 0; i < POBLACIO/2; i++) {
            Random rand = new Random();
         	int r = rand.nextInt(solucions.size());        
         	ArrayList<Point> fill = mezclarSolucions(solucions.get(i), solucions.get(r));
         	if(rand.nextInt(2) == 1){
               
         		aplicarMutacio(fill);
               
         	}
         	solucions2.add(fill);
        }
        for (ArrayList<Point> a: solucions2) {
            solucions.add(a);
            valors.add(totalCostSolution(distLoc, traficoInst, a));
        }


	}

	public void aplicarMutacio(ArrayList<Point> a){

		Random rand = new Random();
		int tecla1 = rand.nextInt(a.size());
		int tecla2 = rand.nextInt(a.size());
		Point p1 = a.get(tecla1);
		Point p2 = a.get(tecla2);
		int temp = (int) p1.getX();                                                                    
        Point p3 = new Point((int) p2.getX(), (int) p1.getY());
        Point p4 = new Point(temp, (int) p2.getY());
        a.set(tecla1,p3);
        a.set(tecla2,p4);
	}


	private Point tecla(int index, ArrayList<Point> x){ //retorna el point de a que te el valor x = i
		for (int i=0; i<x.size(); i++){
			Point p = new Point();
			p = x.get(i);
			if( index == p.getX()) return p;
		}
		return new Point(); //impossible
	}

	public ArrayList<Point> mezclarSolucions(ArrayList<Point> a, ArrayList<Point> b){

		ArrayList<Point> c = new ArrayList<Point>();
		Random rand = new Random();
		int tecles_a = rand.nextInt(a.size());
		int tecles_b = a.size()-tecles_a;
		ArrayList<Integer> por_colocar = new ArrayList<Integer>();
		ArrayList<Integer> tecla_vacia = new ArrayList<Integer>();
		Point p = new Point();
		for(int i=0; i<a.size(); i++){
			por_colocar.add(i);
		}
		for(int i=0; i<a.size(); i++){
			if(i < tecles_a){ //colocar les tecles de a
				p = tecla(i, a);
				por_colocar.remove(Integer.valueOf((int) p.getY()));
             	c.add(p);
			} else{ //colocar les tecles de b que no tenen una lletra posada al teclat per a
				p = tecla(i, b);
                
				if(por_colocar.remove(Integer.valueOf((int) p.getY()))){
					c.add(p);
				}else{
					tecla_vacia.add((int) p.getX());
				}
			}
		}
		for(int i=0; i<tecla_vacia.size(); i++){ //assigna les lletres per colocar 
			p = new Point(tecla_vacia.get(i), por_colocar.get(i));
			c.add(p);
		}
		return c;
	}


    public ArrayList<Point> crearLayout(double[][] distLoc, int[][] traficoInst) {
    	ArrayList<ArrayList<Point>> solucions = new ArrayList<ArrayList<Point>>();
    	ArrayList<Double> valors = new ArrayList<>();
    	int n = distLoc.length;
    	for (int i = 0; i < POBLACIO; ++i) {
    		Random rand = new Random();
    		ArrayList<Point> sol_aux = constructGreedyRandomizedSolution(rand, n);
    		solucions.add(sol_aux);
    		valors.add(totalCostSolution(distLoc, traficoInst, sol_aux));
            for (Double punto : valors) {
                 System.out.println(punto);
            }
    	}
            
    	for(int generacio = 0; generacio<GENETIC; ++generacio){

    		selectSolutions(solucions, valors);
    		generarFills(solucions,valors, distLoc, traficoInst);
    	}
    	
    	double min = Double.MAX_VALUE;
    	for (double num : valors) {
                if (num < min) {
                    min = num;
            }
        }
        System.out.println(min);
        return solucions.get(valors.indexOf(min)); 
    }
}
//autor POL