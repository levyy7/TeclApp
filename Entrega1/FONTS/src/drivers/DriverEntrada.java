package drivers;
import java.util.*;

import Excepcions.*;
import main.dominio.CtrlEntrada;
import main.dominio.Input;
import main.dominio.Teclado;

import java.awt.geom.Point2D;

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

		System.out.println("10 = Consultar alfabetos");
        System.out.println("11 = Consultar alfabeto");

        System.out.println("12 = Consultar textos");
        System.out.println("13 = Consultar texto");

        System.out.println("14 = Consultar listas");
        System.out.println("15 = Consultar lista");

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

		System.out.println("Introduce el alfabeto en una linea: ");
		String alfabeto = tec.nextLine();
        alfabeto.replace(" ", "");

		try {ctrlE.importarAlfabeto(name, alfabeto);}
		catch (InputJaCreat e)
			{System.out.println("Error: "+e.getMessage());return;}
		catch (AlfabetoInvalido e)
			{System.out.println("Error: "+e.getMessage());return;}
	}

	private static void importarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto: ");
		String name = tec.nextLine();

		System.out.print("Introduce el texto en una linea: ");
		String texto = tec.nextLine();
        texto.replace(" ", "");

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

        String type = "";
        try {type = ctrlE.getType(name);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}
        if(type == "Alfabeto") {
            System.out.print("Introduce el alfabeto nuevo en una linea: ");
            String alfabeto = tec.nextLine();
            alfabeto.replace(" ", "");

            try {ctrlE.modificarAlfabeto(name, alfabeto);}
            catch (AlfabetoInvalido e)
                {System.out.println("Error: "+e.getMessage()); return;}
            catch (AlfabetoUsandose e)
                {System.out.println("Error: "+e.getMessage()); return;}
        }
        else System.out.println("No es un alfabeto");
	}

	private static void modificarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto a modificar: ");
		String name = tec.nextLine();

        String type = "";
        try {type = ctrlE.getType(name);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}
        if(type == "Texto") {
            String texto = "";
            System.out.print("Introduce el texto en una linea: ");
            texto += tec.nextLine();

            ctrlE.modificarTexto(name, texto);
        }
        else System.out.println("No es un texto");
	}

	private static void modificarListaPalabras(){
		Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String name = tec.nextLine();

        String type = "";
        try {type = ctrlE.getType(name);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}
        
        if(type == "Lista") {
            System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
            System.out.println("cuando acabes presiona enter: ");
            Map<String, Integer> lista = introducirLista();

            
            try {ctrlE.importarListaPalabras(name, lista);}
            catch (InputJaCreat e)
                {System.out.println("Error: "+e.getMessage()); return;}

            ctrlE.modificarListaPalabras(name, lista);
        }
        else System.out.println("No es una lista de palabras con frecuencia");
	}

	private static void borrarAlfabeto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del alfabeto a borrar: ");
		String name = tec.nextLine();	

        String type = "";
        try {type = ctrlE.getType(name);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}

        if(type == "Alfabeto") {
            try {ctrlE.borrarAlfabeto(name);}
            catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); return;}
        }
        else System.out.println("No es un alfabeto");
	}

	private static void borrarTexto(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre del texto a borrar: ");
		String name = tec.nextLine();	

        String type = "";
        try {type = ctrlE.getType(name);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}

		if(type == "Texto") ctrlE.borrarTexto(name);	
        else System.out.println("No es un texto");
	}

	private static void borrarListaPalabras(){
		Scanner tec = new Scanner(System.in);
		System.out.print("Introduce el nombre de la lista de palabras con frecuencia a borrar: ");
		String name = tec.nextLine();	

        String type = "";
        try {type = ctrlE.getType(name);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}

		if(type == "Lista")ctrlE.borrarListaPalabras(name);
        else System.out.println("No es una lista de palabras con frecuencia");	
	}

	private static void consultarAlfabetos() {
        HashMap<String, Input> alf = ctrlE.getAlfabetos();

        System.out.println("Los alfabetos guardados son");
        for (String key : alf.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

	private static void consultarAlfabeto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del alfabeto a consultar: ");
        String nomAlf = tec.next();

        String type = "";
        try {type = ctrlE.getType(nomAlf);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}

        if(type == "Alfabeto") {
            String alf = "";
            try {alf=ctrlE.getAlfabeto(nomAlf);}
            catch (InputInexistente e)
                {System.out.println("Error: "+e.getMessage());return;}
            
            System.out.println("Nombre: "+ nomAlf);
            System.out.println("Alfabeto:");
            char[] alfabeto = alf.toCharArray();
            for (int i = 0; i < alfabeto.length; ++i)
                System.out.print(" "+alfabeto[i]);
            System.out.println("\n");
        }
        else System.out.println("No es un alfabeto");
    }

	private static void consultarTextos() {
        HashMap<String, Input> textos = ctrlE.getTextos();

        System.out.println("Los textos guardado son");
        for (String key : textos.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    private static void consultarTexto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del texto a consultar: ");
        String nomText = tec.nextLine();
        String type = "";
        try {type = ctrlE.getType(nomText);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}

        if(type == "Texto") {
            String text = ctrlE.getTexto(nomText);
            System.out.println("Nombre: "+nomText);
            System.out.println("Texto:\n"+text+"\n\n");
        }
        else System.out.println("No es un texto");
    }

	private static void consultarListas() {
        HashMap<String, Input> listas = ctrlE.getListas();

        System.out.println("Las listas guardadas son");
        for (String key : listas.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    private static void consultarLista() {
        Scanner tec = new Scanner(System.in);
        
        System.out.print("Nombre de la lista a consultar: ");
        String nomList = tec.nextLine();
        String type = "";
        try {type = ctrlE.getType(nomList);} 
        catch (InputInexistente e) {
            System.out.println("Error: "+e.getMessage()); return;}
        if(type == "Lista") {
            Map<String, Integer> list = ctrlE.getListaPalabras(nomList);
            System.out.println("Nombre: "+nomList);
            System.out.println("Lista:");
            for (String key : list.keySet()) {
                System.out.println(key+" "+list.get(key));
            }
            System.out.println("\n");
        }
        else System.out.println("No es una lista de palabras");
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

                case 10: consultarAlfabetos(); break;
                case 11: consultarAlfabeto(); break;

                case 12: consultarTextos(); break;
                case 13: consultarTexto(); break;

                case 14: consultarListas(); break;
                case 15: consultarLista(); break;

                case 33: salir = true; break;
            }
        }
    }
}

//Mariona
