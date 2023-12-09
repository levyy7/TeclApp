package drivers;
import main.dominio.*;
import main.persistencia.*;

import java.util.*;

/**
 * Driver de Controlador de persistencia
 * Driver encargado de comprobar que las funcionalidades del controlador
 * funcionan correctamente
 * @author Miguel Ángel Montero Flores
*/  

public class DriverPersistencia {

	/** Controlador de Persistencia a interaccionar*/
	private static CtrlPersistencia ctrlP;

	/** Lista de funcionalidades*/
	private static void instrucciones() {

		System.out.println("Guia de instrucciones");

		System.out.println("1 = Guardar Teclado");
		System.out.println("2 = Cargar Teclados");
		System.out.println("3 = Modificar Teclado");
		System.out.println("4 = Borrar Teclado\n");

		System.out.println("5 = Guardar Alfabeto");
		System.out.println("6 = Cargar Alfabetos");
		System.out.println("7 = Modificar Alfabeto");
		System.out.println("8 = Borrar Alfabeto\n");

		System.out.println("9 = Guardar Texto");
		System.out.println("10 = Cargar Textos");
		System.out.println("11 = Modificar Texto");
		System.out.println("12 = Borrar Texto\n");

		System.out.println("13 = Guardar Lista De Palabras");
		System.out.println("14 = Cargar Listas");
		System.out.println("15 = Modificar Lista De Palabras");
		System.out.println("16 = Borrar Lista De Palabras\n");

		System.out.println("33 = Salir");
	}

	/** Función que crea un teclado auxiliar y lo guarda*/
	private static void guardarTeclado() {


		Scanner tec = new Scanner(System.in);
		Scanner tecInt = new Scanner(System.in);

		String[] teclado = new String[4];

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.println("Introduce el algoritmo: ");
		System.out.println("1 = QAP");
		System.out.println("2 = GEN");
		int alg = tecInt.nextInt();
		String algoritmo = "";
		if (alg == 1) algoritmo = "QAP";
		else algoritmo = "GEN";

		System.out.print("Introduce el nombre del alfabeto: ");
		String alfabeto = tec.nextLine();

		System.out.print("Introduce el layout: ");
		String layout = tec.nextLine();

		teclado[0] = nombre;
		teclado[1] = algoritmo;
		teclado[2] = alfabeto;
		teclado[3] = layout;

		ctrlP.guardarTeclado(teclado);
	}

	/** Función que carga y muestra los teclados*/
	private static void cargarTeclados() {

		String[][] teclados = ctrlP.cargarTeclados();

		System.out.println("Los teclados son:");
		for (String[] tec : teclados) {
			for (String parametro : tec) System.out.print(" "+parametro);
			System.out.println();
		}
	}

	/** Función que modifica un teclado*/
	private static void modificarTeclado() {

		Scanner tec = new Scanner(System.in);
		Scanner tecInt = new Scanner(System.in);

		String[] teclado = new String[4];

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.println("Introduce el algoritmo: ");
		System.out.println("1 = QAP");
		System.out.println("2 = GEN");
		int alg = tecInt.nextInt();
		String algoritmo = "";
		if (alg == 1) algoritmo = "QAP";
		else algoritmo = "GEN";

		System.out.print("Introduce el nuevo nombre del alfabeto: ");
		String alfabeto = tec.nextLine();

		System.out.print("Introduce el nuevo layout: ");
		String layout = tec.nextLine();

		teclado[0] = nombre;
		teclado[1] = algoritmo;
		teclado[2] = alfabeto;
		teclado[3] = layout;

		ctrlP.modificarTeclado(teclado);
	}

	/** Función que borra un teclado*/
	private static void borrarTeclado() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		ctrlP.borrarTeclado(nombre);
	}

	/** Función que crea un alfabeto auxiliar y lo guarda*/
	private static void guardarAlfabeto() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.print("Introduce el alfabeto: ");
		String alfabeto = tec.nextLine();

		String[] alf = new String[2];
		alf[0] = nombre;
		alf[1] = alfabeto;
		ctrlP.guardarAlfabeto(alf);
	}

	/** Función que carga y muestra los alfabetos*/
	private static void cargarAlfabetos() {

		String[][] alfabetos = ctrlP.cargarAlfabetos();

		System.out.println("Los alfabetos son:");
		for (String[] alf : alfabetos) {
			for (String parametro : alf) System.out.print(" "+parametro);
			System.out.println();
		}
	}

	/** Función que modifica un alfabeto*/
	private static void modificarAlfabeto() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.print("Introduce el alfabeto: ");
		String alfabeto = tec.nextLine();

		String[] alf = new String[2];
		alf[0] = nombre;
		alf[1] = alfabeto;
		ctrlP.modificarAlfabeto(alf);
	}

	/** Función que borra un alfabeto*/
	private static void borrarAlfabeto() {
		
		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		ctrlP.borrarAlfabeto(nombre);
	}

	/** Función que crea un texto auxiliar y lo guarda*/
	private static void guardarTexto() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.print("Introduce el texto: ");
		String texto = tec.nextLine();

		String[] tex = new String[2];
		tex[0] = nombre;
		tex[1] = texto;
		ctrlP.guardarTexto(tex);
	}

	/** Función que carga y muestra los textos*/
	private static void cargarTextos() {

		String[][] textos = ctrlP.cargarTextos();

		System.out.println("Los textos son:");
		for (String[] tex : textos) {
			for (String parametro : tex) System.out.print(" "+parametro);
			System.out.println();
		}
	}

	/** Función que modifica un texto*/
	private static void modificarTexto() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.print("Introduce el texto: ");
		String texto = tec.nextLine();

		String[] tex = new String[2];
		tex[0] = nombre;
		tex[1] = texto;
		ctrlP.modificarTexto(tex);
	}

	/** Función que borra un texto*/
	private static void borrarTexto() {
		
		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		ctrlP.borrarTexto(nombre);
	}

	/** Función que crea una lista auxiliar y la guarda*/
	private static void guardarLista() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.print("Introduce la lista: ");
		Map<String, Integer> listaFreq = introducirLista();

		String[] lista = new String[2];
		lista[0] = nombre;
		String list = "";
        boolean primero = true;
        for (Map.Entry<String, ?> entry : listaFreq.entrySet()) {
            if (primero == false) list += " ";
            list += entry.getKey()+" "+entry.getValue();
            primero = false;
        }
        lista[1] = list;
		ctrlP.guardarLista(lista);
	}

	/** Función que carga y muestra las listas de palabras*/
	private static void cargarListas() {

		String[][] listas = ctrlP.cargarListas();

		System.out.println("Las listas son:");
		for (String[] lis : listas) {
			for (String parametro : lis) System.out.print(" "+parametro);
			System.out.println();
		}
	}

	/** Función que modifica una lista de palabras*/
	private static void modificarLista() {

		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		System.out.print("Introduce la lista: ");
		Map<String, Integer> listaFreq = introducirLista();

		String[] lista = new String[2];
		lista[0] = nombre;
		String list = "";
        boolean primero = true;
        for (Map.Entry<String, ?> entry : listaFreq.entrySet()) {
            if (primero == false) list += " ";
            list += entry.getKey()+" "+entry.getValue();
            primero = false;
        }
        lista[1] = list;
		ctrlP.modificarLista(lista);
	}

	/** Función que borra una lista de palabras*/
	private static void borrarLista() {
		
		Scanner tec = new Scanner(System.in);

		System.out.print("Introduce el nombre: ");
		String nombre = tec.nextLine();

		ctrlP.borrarLista(nombre);
	}

	/**
     * Función que introduce una lista por terminal y la devuelve
     * @return Map(String, Integer) : lista de palabras introducida 
    */
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

	public static void main(String[] args) {

		Scanner tec = new Scanner(System.in);
		ctrlP = new CtrlPersistencia();

		instrucciones();

		boolean acabado = false;
		while (acabado == false) {

			System.out.print("Introduce una nueva instruccion: ");
			int instruccion = tec.nextInt();
			switch(instruccion) {

				case 1: guardarTeclado(); break;
				case 2: cargarTeclados(); break;
				case 3: modificarTeclado(); break;
				case 4: borrarTeclado(); break;
				
				case 5: guardarAlfabeto(); break;
				case 6: cargarAlfabetos(); break;
				case 7: modificarAlfabeto(); break;
				case 8: borrarAlfabeto(); break;

				case 9: guardarTexto(); break;
				case 10: cargarTextos(); break;
				case 11: modificarTexto(); break;
				case 12: borrarTexto(); break;

				case 13: guardarLista(); break;
				case 14: cargarListas(); break;
				case 15: modificarLista(); break;
				case 16: borrarLista(); break;

				case 33: acabado = true; break;

			}
		}
	}
}
//autor Miguel