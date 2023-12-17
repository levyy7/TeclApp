package drivers;
import java.util.*;

import Excepcions.*;
import main.dominio.CtrlEntrada;
import main.persistencia.CtrlPersistencia;
import main.dominio.Input;
import main.dominio.Teclado;

import java.awt.geom.Point2D;

/**
 * Clase que proporciona un menú interactivo para probar y demostrar las funcionalidades
 * de la clase CtrlEntrada y sus subclases relacionadas.
 * Permite realizar operaciones como crear, importar, modificar, borrar y consultar 
 * alfabetos, textos, listas de palabras y teclados.
 * También proporciona una opción para salir del programa.
 * @author Mariona Aguilera Folqué
 */

public class DriverEntrada {
    private static CtrlEntrada ctrlE;

    /**
     * Muestra las instrucciones disponibles en el menú.
     */
    private static void GuiaInstrucciones() {

		System.out.println("Este es el Driver de Entrada, las funcionalidades son:");

		System.out.println("1 = Crear Alfabeto");
		System.out.println("2 = Crear Texto");
		System.out.println("3 = Crear Lista de palabras con frecuencia");

		System.out.println("4 = Modificar Alfabeto");
		System.out.println("5 = Modificar Texto");
		System.out.println("6 = Modificar Lista de palabras con frecuencia");

		System.out.println("7 = Borrar Alfabeto");
		System.out.println("8 = Borrar Texto");
		System.out.println("9 = Borrar Lista de palabras con frecuencia");
		System.out.println("10 = Borrar Teclado");

		System.out.println("11 = Consultar alfabetos");
        System.out.println("12 = Consultar alfabeto");

        System.out.println("13 = Consultar textos");
        System.out.println("14 = Consultar texto");

        System.out.println("15 = Consultar listas");
        System.out.println("16 = Consultar lista");

		System.out.println("17 = Consultar teclados");
		System.out.println("18 = Consultar teclado");

		System.out.println("33 = Salir");
	}

    /**
     * Permite al usuario introducir una lista de palabras con frecuencia.
     * @return Un mapa que representa la lista de palabras con sus frecuencias.
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

    /**
     * Permite al usuario crear un alfabeto.
     */
	private static void crearAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto: ");
		String name = tec.nextLine();

		System.out.println("Introduce el alfabeto en una linea: ");
		String alfabeto = tec.nextLine();
        alfabeto.replace(" ", "");

		try {ctrlE.crearAlfabeto(name, alfabeto);}
		catch (InputJaCreat e)
			{System.out.println("Error: "+e.getMessage());return;}
		catch (AlfabetoInvalido e)
			{System.out.println("Error: "+e.getMessage());return;}
	}

    /**
     * Permite al usuario crear un texto.
     */
	private static void crearTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto: ");
		String name = tec.nextLine();

		System.out.print("Introduce el texto en una linea: ");
		String texto = tec.nextLine();
        texto.replace(" ", "");

		try {ctrlE.crearTexto(name, texto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

    /**
     * Permite al usuario crear una lista de palabras con frecuencia.
     */
	private static void crearListaPalabras(){
		Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String name = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> lista = introducirLista();

		
		try {ctrlE.crearListaPalabras(name, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

    /**
     * Permite al usuario modificar un alfabeto existente.
     */
	private static void modificarAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto a modificar: ");
		String name = tec.nextLine();

		System.out.print("Introduce el alfabeto nuevo en una linea: ");
		String alfabeto = tec.nextLine();
        alfabeto.replace(" ", "");

		try {ctrlE.modificarAlfabeto(name, alfabeto);}
		catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

    /**
     * Permite al usuario modificar un texto existente.
     */
	private static void modificarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto a modificar: ");
		String name = tec.nextLine();

		String texto = "";
		System.out.print("Introduce el texto en una linea: ");
		texto += tec.nextLine();

		try {ctrlE.modificarTexto(name, texto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

    /**
     * Permite al usuario modificar una lista de palabras con frecuencia existente.
     */
	private static void modificarListaPalabras(){
		Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String name = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> lista = introducirLista();

		
		try {ctrlE.crearListaPalabras(name, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}

		try {ctrlE.modificarListaPalabras(name, lista);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

    /**
     * Permite al usuario borrar un alfabeto existente.
     */
	private static void borrarAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto a borrar: ");
		String name = tec.nextLine();	

		try {ctrlE.borrarAlfabeto(name);}
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

    /**
     * Permite al usuario borrar un texto existente.
     */
	private static void borrarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto a borrar: ");
		String name = tec.nextLine();	

		try {ctrlE.borrarTexto(name);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}	
	}

    /**
     * Permite al usuario borrar una lista de palabras con frecuencia existente.
     */
	private static void borrarListaPalabras(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre de la lista de palabras con frecuencia a borrar: ");
		String name = tec.nextLine();	

		try{ctrlE.borrarListaPalabras(name);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}	
	}

    /**
     * Permite al usuario borrar un teclado existente.
     */
	private static void borrarTeclado(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del teclado a borrar: ");
		String name = tec.nextLine();	

		try {ctrlE.borrarTeclado(name);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

	}

    /**
     * Permite al usuario consultar la lista de alfabetos disponibles.
     */
	private static void consultarAlfabetos() {
        HashMap<String, String[]> alf = ctrlE.getAlfabetos();

        System.out.println("Los alfabetos guardados son");
        for (String key : alf.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /**
     * Permite al usuario consultar un alfabeto específico.
     */
	private static void consultarAlfabeto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del alfabeto a consultar: ");
        String nomAlf = tec.nextLine();
        String[] alf = new String[2];
		try {alf=ctrlE.getAlfabeto(nomAlf);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage());return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}
		
        System.out.println("Nombre: "+ alf[0]);
        System.out.println("Alfabeto:");
        char[] alfabeto = alf[1].toCharArray();
        for (int i = 0; i < alfabeto.length; ++i)
            System.out.print(" "+alfabeto[i]);
        System.out.println("\n");
    }

    /**
     * Permite al usuario consultar la lista de textos disponibles.
     */
	private static void consultarTextos() {
        HashMap<String, String[]> textos = ctrlE.getTextos();

        System.out.println("Los textos guardado son");
        for (String key : textos.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /**
     * Permite al usuario consultar un texto específico.
     */
    private static void consultarTexto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del texto a consultar: ");
        String nomText = tec.nextLine();
        String[] text = new String[2];
        try {text = ctrlE.getTexto(nomText);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}

        System.out.println("Nombre: "+text[0]);
        System.out.println("Texto:\n"+text[1]+"\n\n");
    }

    /**
    * Método para consultar y mostrar los nombres de las listas guardadas en el controlador de entrada.
    */
	private static void consultarListas() {
        HashMap<String, String[]> listas = ctrlE.getListas();

        System.out.println("Las listas guardadas son");
        for (String key : listas.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /**
     * Método para consultar y mostrar el contenido de una lista de palabras a partir de su nombre.
     * Se solicita el nombre de la lista al usuario y se muestra el contenido si la lista existe.
     */
    private static void consultarLista() {
        Scanner tec = new Scanner(System.in);
        
        System.out.print("Nombre de la lista a consultar: ");
        String nomList = tec.nextLine();
        String[] list = new String[2]; 
        try {list = ctrlE.getListaPalabras(nomList);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); return;}

        System.out.println("Nombre: "+list[0]);
        System.out.println("Lista: "+list[1]);
        System.out.println("\n");
    }

    /**
     * Método para consultar y mostrar los nombres de los teclados guardados en el controlador de entrada.
     */
	private static void consultarTeclados() {
        HashMap<String, String[]> teclados = ctrlE.getTeclados();
        System.out.println("Los teclados guardados son\n");
        for (String key : teclados.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /**
     * Método para consultar y mostrar la información detallada de un teclado a partir de su nombre.
     * Se solicita el nombre del teclado al usuario y se muestra la información si el teclado existe.
     */
    private static void consultarTeclado() {
        Scanner tec = new Scanner(System.in);      
        System.out.print("Nombre del teclado a consultar: ");
        String teclado = tec.nextLine();
        String[] t = new String[4];
        try {t = ctrlE.consultaTeclado(teclado);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

        System.out.println("El Teclado se compone de\n");
        System.out.println("Nombre: "+t[0]);
        System.out.println("Algoritmo: "+t[1]);
        System.out.println("Alfabeto: "+t[2]);
        System.out.print("Layout:");
        char[] lay = t[3].toCharArray();
        for (int i = 0; i < lay.length; ++i) System.out.print(" "+lay[i]);
        //System.out.println("\nPlayout:");
        //Point2D[] playout = t.getPlayout();
        //for (int i = 0; i < playout.length; ++i) 
        //    System.out.print(" "+playout[i]);
        System.out.println("\n");
    }

    /**
     * Método principal del programa.
     * Permite al usuario realizar diversas operaciones mediante un menú interactivo.
     */
	public static void main(String[] args) {
		Scanner tec = new Scanner (System.in);

        CtrlPersistencia.inicializar();
        String[][] teclados = CtrlPersistencia.cargarTeclados();
        String[][] alfabetos = CtrlPersistencia.cargarAlfabetos();
        String[][] textos = CtrlPersistencia.cargarTextos();
        String[][] listas = CtrlPersistencia.cargarListas();

        ctrlE = new CtrlEntrada(teclados, alfabetos, textos, listas);

		GuiaInstrucciones();
        
        //Para el cin
        boolean salir = false;
        while (salir == false) {
			
            System.out.print("Introduce una nueva instruccion: ");
			int instruccion = tec.nextInt();
            //Listado de instrucciones
            switch (instruccion) {   
				
				case 1: crearAlfabeto(); break;
                case 2: crearTexto(); break;
                case 3: crearListaPalabras(); break;

                case 4: modificarAlfabeto(); break;
                case 5: modificarTexto(); break;
                case 6: modificarListaPalabras(); break;

                case 7: borrarAlfabeto(); break;
                case 8: borrarTexto(); break;
                case 9: borrarListaPalabras(); break;
				case 10: borrarTeclado(); break;

                case 11: consultarAlfabetos(); break;
                case 12: consultarAlfabeto(); break;

                case 13: consultarTextos(); break;
                case 14: consultarTexto(); break;

                case 15: consultarListas(); break;
                case 16: consultarLista(); break;

                case 17: consultarTeclados(); break;
                case 18: consultarTeclado(); break;

                case 33: salir = true; break;
            }
        }
    }
}
