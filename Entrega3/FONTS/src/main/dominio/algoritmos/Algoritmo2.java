package main.dominio.algoritmos;

import java.util.*;
import java.awt.Point;

/** 
 * Clase del algoritmo 2
 * Se basa en un algoritmo genético
 * Permite dada una matriz de distancias i de frequencias generar un teclado eficiente
 * @author Pol Ribera
*/

public class Algoritmo2 extends EstrategiaCreacionLayout {
	/** Número de generaciones que tendra el algoritmo antes de parar */
	private static final int GENETIC = 100;
	/** Número de teclados activos cada geracion */
	private static final int POBLACIO = 16;

	/** 
     * Elimina de la poblacion de n teclados los n/2 teclados mas malos
     * @param solucions: ArrayList de ArrayList de Points donde estan guardados los n teclados de la generación actual
     * @param valors : ArrayList de doubles que indican por cada teclado como de bueno es (heurístico)
    */

	private void selectSolutions(ArrayList<ArrayList<Point>> solucions, ArrayList<Double> valors){
        
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

	/** 
     * Genera la nueva generacion a partir de los teclados supervivientes de la generacion pasada i sus hijos probablemente mutados 
     * @param solucions: ArrayList de ArrayList de Points donde estan guardados todos los teclados de la generación actual
     * @param valors : ArrayList de doubles que indican por cada teclado como de bueno es (heuréstico)
	 * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
    */
	private void generarFills(ArrayList<ArrayList<Point>> solucions, ArrayList<Double> valors, double[][] distLoc, int[][] traficoInst){
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

	/** 
     * Canvia de sitio dos teclas aleatorias del teclado
     * @param a: ArrayList de Points que representa el teclado que se quiere modificar
	*/
	private void aplicarMutacio(ArrayList<Point> a){

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


	/** 
     * Encontrar el Point dentro de a que tiene el valor X = index assumiendo que este exista
     * @param index : int que queremos encontrar
     * @param x : ArrayList de Points el qual es el objeto que estamos buscando
     * @return Point: Devuelve el punto que buscábamos
    */

	private Point tecla(int index, ArrayList<Point> x){
		for (int i=0; i<x.size(); i++){
			Point p = new Point();
			p = x.get(i);
			if( index == p.getX()) return p;
		}
		return new Point(); //impossible
	}


	/** 
     * Genera un hijo a partir de coger las n(escogido aleatoriamente) primeras teclas del padre1 i las otras del padre 2 i juntar-las.
     * Si entre las teclas del padre 2 hay caracteres que ya ha colocado el padre1 se assignaran posteriormente.
     * @param a : ArrayList de Points que representa el padre1
     * @param b : ArrayList de Points que representa el padre2
     * @return ArrayList<Point>: Devuelve el hijo
    */
	private ArrayList<Point> mezclarSolucions(ArrayList<Point> a, ArrayList<Point> b){

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

	/** 
	 * Algoritmo genetico para resolver el problema de asignar instalaciones
     * a localizaciones. Genera una poblacion inicial desde la qual va seleccionando 
     * los mejores individuos i creando nuevos
	 * @param distLoc : Matriz de distancias entre localizaciones, tal que 
     *                  distLoc[i][j] es la distancia entre la localización i 
     *                  y la localización j
     * @param traficoInst : Matriz de tráfico entre instalaciones, tal que 
     *                  traficoInst[i][j] es el tráfico entre la instalación i 
     *                  y la instalación j 
     * @return ArrayList<Point>: Devuelve el mejor teclado de la ultima generación creada
    */

    public ArrayList<Point> crearLayout(double[][] distLoc, int[][] traficoInst) {
    	ArrayList<ArrayList<Point>> solucions = new ArrayList<ArrayList<Point>>();
    	ArrayList<Double> valors = new ArrayList<>();
    	int n = distLoc.length;
    	for (int i = 0; i < POBLACIO; ++i) {
    		Random rand = new Random();
    		ArrayList<Point> sol_aux = constructGreedyRandomizedSolution(rand, n);
    		solucions.add(sol_aux);
    		valors.add(totalCostSolution(distLoc, traficoInst, sol_aux));
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
        return solucions.get(valors.indexOf(min)); 
    }
}
