package drivers;
import main.dominio.CtrlAlgoritmo;

import java.util.*;
import java.lang.Object;
import java.awt.geom.Point2D.Double;

public class DriverAlgoritmo {

	private static CtrlAlgoritmo ctrlA;

	private static void inicializar() {

		ctrlA = new CtrlAlgoritmo();

		System.out.println("Este es el Driver del Algoritmo, las funcionalidades son:");
		System.out.println("1 = Utilizar QAP");
		System.out.println("33 = Salir");
	}

	private static void usarQAP() {

		Scanner tecInt = new Scanner(System.in);
		Scanner tec = new Scanner(System.in);

		System.out.println("Introduce un phisical layout:");
		
		System.out.print("Cuantos textos quieres introducir: ");
		int numTextos = tecInt.nextInt();

		Vector<String> textos = new Vector<>();
		System.out.println("Introduce los textos linea por linea:");
		for (int i = 0; i < numTextos; ++i) textos.addElement(tec.nextLine());

		System.out.println("Introduce el Alfabeto: ");
		String alfabeto = tec.nextLine();
		/*int tam = alfabeto.length();

		int columnas = tam / 2;
		int filas = 0;
		if (tam % 2 != 0) filas = tam / 2 + 1;
		else filas = tam / 2;

		
		Point2D.Double[] playout = new Point2D.Double[tam];

		for (int i = 0; i < filas; ++i) {
			for (int j = 0; j < columnas; ++j) {
				playout[i+j] = Point2D.Double(i, j);
			}
		}*/


		int[] layout = ctrlA.usarAlgoritmo(textos, alfabeto);
	}

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		
		inicializar();

		boolean finalizar = false;
		while (finalizar == false) {

			int instruccion = tec.nextInt();

			switch(instruccion) {

				case 1: usarQAP(); break;

				case 33: finalizar = true; break;
			}
		}
	}
}