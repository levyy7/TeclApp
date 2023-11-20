package drivers;
import java.util.*;

import Excepcions.*;
import main.dominio.CtrlEntrada;

public class DriverEntrada {
    private static CtrlEntrada ctrlE;

    private static void GuiaInstrucciones() {

		ctrlE = new CtrlEntrada();

		System.out.println("Este es el Driver de Entrada, las funcionalidades son:");
		System.out.println("1 = Importar Alfabeto");
		System.out.println("2 = Importar Texto");
		System.out.println("3 = Importar Lista de palabras con frecuencia");

		System.out.println("4 = Modificar Alfabeto");
		System.out.println("5 = Modificar Texto");
		System.out.println("6 = Modificar Lista de palabras con frecuencia");

		System.out.println("7 = Borrar Alfabeto");
		System.out.println("8 = Borrar Texto");
		System.out.println("9 = Borrar Lista de palabras con frecuencia");
		System.out.println("10 = Borrar Teclado");



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

	private static void importarAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto: ");
		String name = tec.nextLine();

		String alfabeto = "";
		System.out.print("Introduce el alfabeto en una linea: ");
		alfabeto += tec.nextLine();

		tec.close();
		try {
			ctrlE.importarAlfabeto(name, alfabeto);
		} catch (InputJaCreat e)
			{System.out.println("Error: "+e.getMessage()); return;}
		catch (AlfabetoInvalido e)
			{System.out.println("Error: "+e.getMessage()); return;}
	}

	private static void importarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto: ");
		String name = tec.nextLine();

		String texto = "";
		System.out.print("Introduce el texto en una linea: ");
		texto += tec.nextLine();

		try {ctrlE.importarTexto(name, texto);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

	private static void importarListaPalabras(){
		Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String name = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> lista = introducirLista();

		
		try {ctrlE.importarListaPalabras(name, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

	private static void modificarAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto a modificar: ");
		String name = tec.nextLine();

		String alfabeto = "";
		System.out.print("Introduce el alfabeto nuevo en una linea: ");
		alfabeto += tec.nextLine();

		try {ctrlE.modificarAlfabeto(name, alfabeto);}
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

	private static void modificarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto a modificar: ");
		String name = tec.nextLine();

		String texto = "";
		System.out.print("Introduce el texto en una linea: ");
		texto += tec.nextLine();

		ctrlE.modificarTexto(name, texto);
	}

	private static void modificarListaPalabras(){
		Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String name = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> lista = introducirLista();

		
		try {ctrlE.importarListaPalabras(name, lista);}
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); return;}

		ctrlE.modificarListaPalabras(name, lista);
	}

	private static void borrarAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto a borrar: ");
		String name = tec.nextLine();	

		try {ctrlE.borrarAlfabeto(name);}
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
	}

	private static void borrarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto a borrar: ");
		String name = tec.nextLine();	

		ctrlE.borrarTexto(name);	
	}

	private static void borrarListaPalabras(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre de la lista de palabras con frecuencia a borrar: ");
		String name = tec.nextLine();	

		ctrlE.borrarListaPalabras(name);	
	}

	private static void borrarTeclado(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del teclado a borrar: ");
		String name = tec.nextLine();	

		try {ctrlE.borrarTeclado(name);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); return;}

	}

	public static void main(String[] args) {
		ctrlE = new CtrlEntrada();
        Scanner tec = new Scanner (System.in);

		GuiaInstrucciones();
        
        //Para el cin
        boolean salir = false;
        while (salir == false) {

            System.out.print("Introduce una nueva instruccion: ");
            int instruccion = tec.nextInt();          

            //Listado de instrucciones
            switch (instruccion) {                

                case 1: importarAlfabeto(); break;
                case 2: importarTexto(); break;
                case 3: importarListaPalabras(); break;

                case 4: modificarAlfabeto(); break;
                case 5: modificarTexto(); break;
                case 6: modificarListaPalabras(); break;

                case 7: borrarAlfabeto(); break;
                case 8: borrarTexto(); break;
                case 9: borrarListaPalabras(); break;
				case 10: borrarTeclado(); break;

                case 33: salir = true; break;
            }
        }
    }
}

//Mariona
