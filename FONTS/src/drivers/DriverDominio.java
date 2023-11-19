package drivers;
import main.dominio.CtrlDominio;

import java.util.*;

public class DriverDominio {

    private static CtrlDominio ctrlD;

	private static void GuiaInstrucciones() {

        System.out.println("Guía de instrucciones");
        System.out.println("1 = Crear teclado");
		System.out.println("2 = Borrar teclado");

		System.out.println("3 = Importar alfabeto");
		System.out.println("4 = Importar texto");
        System.out.println("5 = Importar lista de palabras");

		System.out.println("6 = Modificar teclado");
        System.out.println("7 = Modificar alfabeto");
		System.out.println("8 = Modificar texto");
        System.out.println("9 = Modificar lista de palabras");

        System.out.println("10 = Borrar alfabeto");
        System.out.println("11 = Borrar texto");
        System.out.println("12 = Borrar alfabeto");

        System.out.println("33 = Salir");
	}

    private static Vector<String> introducirTextos() {

        Scanner tec = new Scanner(System.in);
        Scanner tecInt = new Scanner(System.in);
        Vector<String> textos = new Vector<String>();

        System.out.print("Cuantos textos/listas quieres introducir? ");
        int numeroDeTextos = tecInt.nextInt();

        System.out.println("Introduce los nombres de los textos/listas:");
        for (int i = 0; i < numeroDeTextos; ++i) textos.addElement(tec.nextLine());
    
        return textos;
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

    private static void crearTeclado() {

        String algor, alfab, teclado;
        Vector<String> textos = new Vector<String>();
        
        Scanner tec = new Scanner(System.in);
        Scanner tecInt = new Scanner(System.in);
        int entrada;

        System.out.println("Introduce tipo de algoritmo:");
        System.out.println("1 = QAP");
        System.out.println("2 = a");
        entrada = tecInt.nextInt();
        if (entrada == 1) algor = "QAP";
        else if (entrada == 2) algor = "a";
        else algor = "error";

        System.out.print("Introduce el alfabeto: ");
        alfab = tec.nextLine();

        textos = introducirTextos();
        
        System.out.print("Por ultimo el nombre del teclado: ");
        teclado = tec.nextLine();

        //System.out.println(teclado+alfab+textos.elementAt(0)+algor);
        ctrlD.crearTeclado(teclado, alfab, textos, algor);
    }

    private static void borrarTeclado() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Introduce el nombre del teclado a borrar: ");
        String nombreTeclado = tec.nextLine();

        ctrlD.borrarTeclado(nombreTeclado);
    }

    private static void importarAlfabeto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del nuevo alfabeto: ");
        String nombreAlfabeto = tec.nextLine();
        
        System.out.print("Escribe el alfabeto en una linea: ");
        String alfabeto = tec.nextLine();
        alfabeto.replace(" ", "");

        ctrlD.importarAlfabeto(nombreAlfabeto, alfabeto);
    }

    private static void importarTexto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del nuevo texto: ");
        String nombreTexto = tec.nextLine();
        
        System.out.print("Escribe el texto en una linea: ");
        String texto = tec.nextLine();

        ctrlD.importarTexto(nombreTexto, texto);
    }

    private static void importarListaPalabras() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String nombreLista = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> lista = introducirLista();

        ctrlD.importarListaPalabras(nombreLista, lista);
    }

    private static void modificarTeclado() {

        Scanner tec = new Scanner(System.in);
        Scanner tecChar = new Scanner(System.in);

        System.out.print("Introduce el nombre del teclado a modificar: ");
        String nombreTeclado = tec.nextLine();

        Vector<String> textos = introducirTextos();

        System.out.println("Quieres modificar el alfabeto?");
        System.out.println("Y = Si");
        System.out.println("N = No");
        Character modif = tec.next().charAt(0);

        if (modif == 'Y') {
            System.out.print("Introduce el nombre del nuevo alfabeto: ");
            String alfabeto = tec.nextLine();
            ctrlD.modificarTeclado(nombreTeclado, alfabeto, textos);
        }
        else ctrlD.modificarTeclado(nombreTeclado, textos);
    }

    private static void modificarAlfabeto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del alfabeto a modificar: ");
        String nombreAlfabeto = tec.nextLine();

        System.out.print("Escribe el alfabeto nuevo en una linea: ");
        String alfabetoNuevo = tec.nextLine();

        ctrlD.modificarAlfabeto(nombreAlfabeto, alfabetoNuevo);
    }

    private static void modificarTexto() {
        
        Scanner tec = new Scanner(System.in);

        System.out.print("Introduce el nombre del texto a modificar: ");
        String nombreTexto = tec.nextLine();

        System.out.print("Introdce el nuevo texto, cuando acabes presiona enter 2 veces: ");
        String texto = "";
        boolean acabado = false;
        while (acabado == false) {
            String linea = tec.nextLine();
            if (Integer.valueOf(linea) == -1) acabado = true;
            else texto = texto+" "+linea;
        }

        ctrlD.modificarTexto(nombreTexto, texto);
    }

    private static void modificarListaPalabras() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la lista a modificar: ");
        String nombreLista = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, "); 
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> listaNueva = introducirLista();

        ctrlD.modificarListaPalabras(nombreLista, listaNueva);
    }

    private static void borrarAlfabeto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del alfabeto a borrar: ");
        String nombreAlfabeto = tec.nextLine();
        
        ctrlD.borrarAlfabeto(nombreAlfabeto);
    }

    private static void borrarTexto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del texto a borrar: ");
        String nombreTexto = tec.nextLine();
        
        ctrlD.borrarAlfabeto(nombreTexto);
    }

    private static void borrarListaPalabras() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la lista a borrar: ");
        String nombreLista = tec.nextLine();

        ctrlD.borrarListaPalabras(nombreLista);
    }

	public static void main(String[] args) {
        
		ctrlD = new CtrlDominio();
        Scanner tec = new Scanner (System.in);

		GuiaInstrucciones();
        
        //Para el cin
        boolean salir = false;
        while (salir == false) {

            int instruccion = tec.nextInt();          

            //Listado de instrucciones
            switch (instruccion) {

                case 1: crearTeclado(); break;
                case 2: borrarTeclado(); break;

                case 3: importarAlfabeto(); break;
                case 4: importarTexto(); break;
                case 5: importarListaPalabras(); break;

                case 6: modificarTeclado(); break;
                case 7: modificarAlfabeto(); break;
                case 8: modificarTexto(); break;
                case 9: modificarListaPalabras(); break;

                case 10: borrarAlfabeto(); break;
                case 11: borrarTexto(); break;
                case 12: borrarListaPalabras(); break;

                case 33: salir = true; break;
            }
        }
    }

}