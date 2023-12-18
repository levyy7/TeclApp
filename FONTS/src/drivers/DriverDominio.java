package drivers;
import main.dominio.*;

import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Driver de controlador de Dominio
 * Driver que se encarga de comprobar las funcionalidades del controlador
 * de dominio. Dado que es el controlador más grande también comprueba
 * el resto de clases del dominio y de persistencia
 * @author Miguel Ángel Montero Flores
*/
public class DriverDominio {

    /** Controlador de dominio a interaccionar */
    private static CtrlDominio ctrlD;

    /** Listado de funcionalidades a probar */
	private static void GuiaInstrucciones() {

        System.out.println("Guía de instrucciones");
        System.out.println("1 = Crear teclado");
        System.out.println("2 = Importar teclados");
		System.out.println("3 = Borrar teclado");

        System.out.println("4 = Crear alfabeto");
        System.out.println("5 = Crear texto");
        System.out.println("6 = Crear lista");

		System.out.println("7 = Importar alfabetos");
		System.out.println("8 = Importar textos");
        System.out.println("9 = Importar listas de palabras");

		System.out.println("10 = Modificar teclado");
        System.out.println("11 = Modificar alfabeto");
		System.out.println("12 = Modificar texto");
        System.out.println("13 = Modificar lista de palabras");

        System.out.println("14 = Borrar alfabeto");
        System.out.println("15 = Borrar texto");
        System.out.println("16 = Borrar lista de palabras");

        System.out.println("17 = Consultar teclados");
        System.out.println("18 = Consultar teclado");

        System.out.println("19 = Consultar alfabetos");
        System.out.println("20 = Consultar alfabeto");

        System.out.println("21 = Consultar textos");
        System.out.println("22 = Consultar texto");

        System.out.println("23 = Consultar listas");
        System.out.println("24 = Consultar lista");
        
        System.out.println("33 = Salir");
	}

    /** Función que consulta los teclados guardados */
    private static void consultarTeclados() {
        HashMap<String, String[]> teclados = ctrlD.consultarTeclados();
        System.out.println("Los teclados guardados son\n");
        for (String key : teclados.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /** Función que consulta un teclado */
    private static void consultarTeclado() {
        Scanner tec = new Scanner(System.in);      
        System.out.print("Nombre del teclado a consultar: ");
        String teclado = tec.nextLine();
        String[] t = ctrlD.consultarInfoTeclado(teclado);

        System.out.println("El Teclado se compone de\n");
        System.out.println("Nombre: "+t[0]);
        System.out.println("Algoritmo: "+t[1]);
        System.out.println("Alfabeto: "+t[2]);

        char[][] lay = ctrlD.consultarLayoutTeclado(teclado);
        System.out.println("Layout:");
        if (lay.length > 0) {
            for (int i = 0; i < lay.length; ++i) {
                for (int j = 0; j < lay[0].length; ++j)
                    System.out.print(" "+lay[i][j]);

                System.out.println();    
            }
        }

        System.out.println("\n");
    }

    /** Función que consultalos alfabetos guardados */
    private static void consultarAlfabetos() {
        HashMap<String, String[]> alf = ctrlD.consultarAlfabetos();

        System.out.println("Los alfabetos guardado son");
        for (String key : alf.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /** Función que consulta un alfabeto */
    private static void consultarAlfabeto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del alfabeto a consultar: ");
        String nomAlf = tec.nextLine();
        String[] alf = ctrlD.consultarAlfabeto(nomAlf);

        System.out.println("Nombre: "+alf[0]);
        System.out.println("Alfabeto:");
        char[] alfabeto = alf[1].toCharArray();
        for (int i = 0; i < alfabeto.length; ++i)
            System.out.print(" "+alfabeto[i]);
        System.out.println("\n");
    }

    /** Función que consulta los textos guardados*/
    private static void consultarTextos() {
        HashMap<String, String[]> textos = ctrlD.consultarTextos();

        System.out.println("Los textos guardado son");
        for (String key : textos.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /** Función que consulta un texto*/
    private static void consultarTexto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del texto a consultar: ");
        String nomText = tec.nextLine();
        String[] text = ctrlD.consultarTexto(nomText);

        System.out.println("Nombre: "+text[0]);
        System.out.println("Texto:\n"+text[1]+"\n\n");
    }

    /** Función que consulta las listas guardadas*/
    private static void consultarListas() {
        HashMap<String, String[]> listas = ctrlD.consultarListas();

        System.out.println("Las listas guardadas son");
        for (String key : listas.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    /** Función que consulta una lista */
    private static void consultarLista() {
        Scanner tec = new Scanner(System.in);
        
        System.out.print("Nombre de la lista a consultar: ");
        String nomList = tec.nextLine();
        String[] list = ctrlD.consultarLista(nomList);

        System.out.println("Nombre: "+nomList);
        System.out.println("Lista:\n"+list[1]);
        System.out.println("\n");
    }

    /** 
     * Función que introduce nombres de textos y listas
     * @return Vector(String) : nombres de textos y listas
    */
    private static String[] introducirTextos() {

        Scanner tec = new Scanner(System.in);
        Scanner tecInt = new Scanner(System.in);

        System.out.print("Cuantos textos/listas quieres introducir: ");
        int numeroDeTextos = tecInt.nextInt();

        String[] nombresTLP = new String[numeroDeTextos];
        System.out.println("Introduce los nombres de los textos/listas:");
        for (int i = 0; i < numeroDeTextos; ++i) nombresTLP[i] = tec.nextLine();
    
        return nombresTLP;
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

    /** Función que se encarga de comprobar la creación de un teclado */
    private static void crearTeclado() {

        String algor, alfab, teclado;
        
        Scanner tec = new Scanner(System.in);
        Scanner tecInt = new Scanner(System.in);
        int entrada;

        System.out.println("Introduce tipo de algoritmo:");
        System.out.println("1 = QAP");
        System.out.println("2 = GEN");
        entrada = tecInt.nextInt();
        if (entrada == 1) algor = "QAP";
        else if (entrada == 2) algor = "GEN";
        else algor = "error";

        System.out.print("Introduce el alfabeto: ");
        alfab = tec.nextLine();

        String[] textos = introducirTextos();
        
        System.out.print("Por ultimo el nombre del teclado: ");
        teclado = tec.nextLine();

        ctrlD.crearTeclado(teclado, alfab, textos, algor);
    }

    private static void importarTeclados() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el path de los teclados a importar: ");
        String path = tec.nextLine();

        ctrlD.importarTeclados(path);
    }

    /** Función que se encarga de comprobar el borrado de un teclado */
    private static void borrarTeclado() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Introduce el nombre del teclado a borrar: ");
        String nombreTeclado = tec.nextLine();

        ctrlD.borrarTeclado(nombreTeclado);
    }

    /** Función que se encarga de comprobar la importación de un alfabeto */
    private static void crearAlfabeto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del nuevo alfabeto: ");
        String nombreAlfabeto = tec.nextLine();
        
        System.out.print("Escribe el alfabeto en una linea: ");
        String alfabeto = tec.nextLine();

        ctrlD.crearAlfabeto(nombreAlfabeto, alfabeto);
    }

    /** Función que se encarga de comprobar la importación de un texto */
    private static void crearTexto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del nuevo texto: ");
        String nombreTexto = tec.nextLine();
        
        System.out.print("Escribe el texto en una linea: ");
        String texto = tec.nextLine();

        ctrlD.crearTexto(nombreTexto, texto);
    }

    /** Función que se encarga de comprobar la importación de una lista */
    private static void crearListaPalabras() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la nueva lista: ");
        String nombreLista = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, ");
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> lista = introducirLista();

        ctrlD.crearListaPalabras(nombreLista, lista);
    }

    private static void importarAlfabetos() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el path de los alfabetos a importar: ");
        String path = tec.nextLine();

        ctrlD.importarAlfabetos(path);
    }

    private static void importarTextos() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el path de los textos a importar: ");
        String path = tec.nextLine();

        ctrlD.importarTextos(path);
    }

    private static void importarListasPalabras() {
        
        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el path de las listas a importar: ");
        String path = tec.nextLine();

        ctrlD.importarListasPalabras(path);
    }

    /** Función que se encarga de comprobar la modificación de un teclado */
    private static void modificarTeclado() {

        Scanner tec = new Scanner(System.in);
        Scanner tecChar = new Scanner(System.in);

        System.out.print("Introduce el nombre del teclado a modificar: ");
        String nombreTeclado = tec.nextLine();

        String[] textos = introducirTextos();

        System.out.println("Quieres modificar el alfabeto?");
        System.out.println("Y = Si");
        System.out.println("N = No");
        String modif = tec.nextLine();

        if (modif == "Y") {
            System.out.print("Introduce el nombre del nuevo alfabeto: ");
            String alfabeto = tec.nextLine();
            ctrlD.modificarTeclado(nombreTeclado, alfabeto, textos);
        }
        else ctrlD.modificarTeclado(nombreTeclado, textos);
    }

    /** Función que se encarga de comprobar la modificación de un alfabeto */
    private static void modificarAlfabeto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del alfabeto a modificar: ");
        String nombreAlfabeto = tec.nextLine();

        System.out.print("Escribe el alfabeto nuevo en una linea: ");
        String alfabetoNuevo = tec.nextLine();

        ctrlD.modificarAlfabeto(nombreAlfabeto, alfabetoNuevo);
    }

    /** Función que se encarga de comprobar la modficación de un texto */
    private static void modificarTexto() {
        
        Scanner tec = new Scanner(System.in);

        System.out.print("Introduce el nombre del texto a modificar: ");
        String nombreTexto = tec.nextLine();
        
        System.out.print("Escribe el texto en una linea: ");
        String texto = tec.nextLine();

        ctrlD.modificarTexto(nombreTexto, texto);
    }

    /** Función que se encarga de comprobar la modificación de una lista */
    private static void modificarListaPalabras() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre de la lista a modificar: ");
        String nombreLista = tec.nextLine();
        
        System.out.print("Escribe la nueva lista con un nombre y un numero separado por espacios, "); 
        System.out.print("cuando acabes presiona enter: ");
        Map<String, Integer> listaNueva = introducirLista();

        ctrlD.modificarListaPalabras(nombreLista, listaNueva);
    }

    /** Función que se encarga de comprobar el borrado de un alfabeto */
    private static void borrarAlfabeto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del alfabeto a borrar: ");
        String nombreAlfabeto = tec.nextLine();
        
        ctrlD.borrarAlfabeto(nombreAlfabeto);
    }

    /** Función que se encarga de comprobar el borrado de un texto */
    private static void borrarTexto() {

        Scanner tec = new Scanner(System.in);

        System.out.print("Escribe el nombre del texto a borrar: ");
        String nombreTexto = tec.nextLine();
        
        ctrlD.borrarAlfabeto(nombreTexto);
    }

    /** Función que se encarga de comprobar el borrado de una lista */
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

            System.out.print("Introduce una nueva instruccion: ");
            int instruccion = tec.nextInt();          

            //Listado de instrucciones
            switch (instruccion) {

                case 1: crearTeclado(); break;
                case 2: importarTeclados(); break;
                case 3: borrarTeclado(); break;

                case 4: crearAlfabeto(); break;
                case 5: crearTexto(); break;
                case 6: crearListaPalabras(); break;

                case 7: importarAlfabetos(); break;
                case 8: importarTextos(); break;
                case 9: importarListasPalabras(); break;

                case 10: modificarTeclado(); break;
                case 11: modificarAlfabeto(); break;
                case 12: modificarTexto(); break;
                case 13: modificarListaPalabras(); break;

                case 14: borrarAlfabeto(); break;
                case 15: borrarTexto(); break;
                case 16: borrarListaPalabras(); break;

                case 17: consultarTeclados(); break;
                case 18: consultarTeclado(); break;

                case 19: consultarAlfabetos(); break;
                case 20: consultarAlfabeto(); break;

                case 21: consultarTextos(); break;
                case 22: consultarTexto(); break;

                case 23: consultarListas(); break;
                case 24: consultarLista(); break;

                case 33: salir = true; break;
            }
        }
    }
}
//autor Miguel