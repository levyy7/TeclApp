package drivers;
import main.dominio.CtrlAlgoritmo;
import main.dominio.Playout;

import java.util.*;
import java.awt.Point;
import java.lang.Object;
import java.awt.geom.Point2D;

public class DriverAlgoritmo {

	private static CtrlAlgoritmo ctrlA;

	private static void inicializar() {

		ctrlA = new CtrlAlgoritmo();

		System.out.println("Este es el Driver del Algoritmo, las funcionalidades son:");
		System.out.println("1 = Utilizar QAP");
		System.out.println("33 = Salir");
	}

	private static Map<String, Integer> introducirLista() {

        Scanner tec = new Scanner(System.in);
        
        Map<String, Integer> lista = new HashMap<>();
        boolean acabado = false;

        String cadena = "";
        while (acabado == false) {
            
            cadena = tec.nextLine();

            String[] palabraConNumero = cadena.split(" ");

            int tam = palabraConNumero.length;

            if (tam != 2) {
                System.out.println("Fin de la lista");
                acabado = true;
            } 
            else {
                String palabra = palabraConNumero[0];
                int numero = Integer.valueOf(palabraConNumero[1]);
                lista.put(palabra, numero);
            }
            
        }

        return lista;
    }

	private static void usarQAP() {

		Scanner tecInt = new Scanner(System.in);
		Scanner tec = new Scanner(System.in);
		
		System.out.println("Introduce el Alfabeto: ");
		String alfabeto = tec.nextLine();
		int tam = alfabeto.length();

		Playout play = new Playout(tam);
		Point2D[] playout = play.getTeclas();
		
		System.out.print("Cuantos textos quieres introducir: ");
		int numTextos = tecInt.nextInt();

		Vector<String> textos = new Vector<>();
		if (numTextos != 0) {
			System.out.println("Introduce los textos linea por linea:");
			for (int i = 0; i < numTextos; ++i) textos.addElement(tec.nextLine());
		}

		System.out.print("Cuantas listas quieres introducir: ");
		int numListas = tecInt.nextInt();

		Vector<Map<String, Integer>> listas = new Vector<>();
		if (numListas != 0) {
			System.out.println("Introduce las listas con enters:");
			for (int i = 0; i < numListas; ++i) 
				listas.addElement(introducirLista());
		}


		char[] layout = ctrlA.usarQAP(textos, listas, alfabeto, playout);

		System.out.println("\nEl layout es:\n");
		for (int i = 0; i < layout.length; ++i) System.out.print(layout[i]+" ");
		System.out.println("\n");
	}

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		
		inicializar();

		boolean finalizar = false;
		while (finalizar == false) {

			System.out.print("Introduce una nueva instruccion: ");
			int instruccion = tec.nextInt();

			switch(instruccion) {

				case 1: usarQAP(); break;

				case 33: finalizar = true; break;
			}
		}
	}
}
//autor Miguel