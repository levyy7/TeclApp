package drivers;
import main.dominio.*;

import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;

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
        System.out.println("12 = Borrar lista de palabras");

        System.out.println("13 = Consultar teclados");
        System.out.println("14 = Consultar teclado");

        System.out.println("15 = Consultar alfabetos");
        System.out.println("16 = Consultar alfabeto");

        System.out.println("17 = Consultar textos");
        System.out.println("18 = Consultar texto");

        System.out.println("19 = Consultar listas");
        System.out.println("20 = Consultar lista");
        
        System.out.println("33 = Salir");
	}

    private static void consultarTeclados() {
        HashMap<String, Teclado> teclados = ctrlD.consultarTeclados();
        System.out.println("Los teclados guardados son\n");
        for (String key : teclados.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    private static void consultarTeclado() {
        Scanner tec = new Scanner(System.in);      
        System.out.print("Nombre del teclado a consultar: ");
        String teclado = tec.nextLine();
        Teclado t = ctrlD.consultarTeclado(teclado);

        System.out.println("El Teclado se compone de\n");
        System.out.println("Nombre: "+t.getNombre());
        System.out.println("Algoritmo: "+t.getAlgoritmo());
        System.out.println("Alfabeto: "+t.getAlfabeto());
        System.out.print("Layout:");
        char[] lay = t.getLayout();
        for (int i = 0; i < lay.length; ++i) System.out.print(" "+lay[i]);
        System.out.println("\nPlayout:");
        Point2D[] playout = t.getPlayout();
        for (int i = 0; i < playout.length; ++i) 
            System.out.print(" "+playout[i]);
        System.out.println("\n");
    }

    private static void consultarAlfabetos() {
        HashMap<String, Input> alf = ctrlD.consultarAlfabetos();

        System.out.println("Los alfabetos guardado son");
        for (String key : alf.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    private static void consultarAlfabeto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del alfabeto a consultar: ");
        String nomAlf = tec.nextLine();
        String alf = ctrlD.consultarAlfabeto(nomAlf);

        System.out.println("Nombre: "+nomAlf);
        System.out.println("Alfabeto:");
        char[] alfabeto = alf.toCharArray();
        for (int i = 0; i < alfabeto.length; ++i)
            System.out.print(" "+alfabeto[i]);
        System.out.println("\n");
    }

    private static void consultarTextos() {
        HashMap<String, Input> textos = ctrlD.consultarTextos();

        System.out.println("Los textos guardado son");
        for (String key : textos.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    private static void consultarTexto() {
        Scanner tec = new Scanner(System.in);
        System.out.print("Nombre del texto a consultar: ");
        String nomText = tec.nextLine();
        String text = ctrlD.consultarTexto(nomText);

        System.out.println("Nombre: "+nomText);
        System.out.println("Texto:\n"+text+"\n\n");
    }

    private static void consultarListas() {
        HashMap<String, Input> listas = ctrlD.consultarListas();

        System.out.println("Las listas guardadas son");
        for (String key : listas.keySet())
            System.out.println(key);
        System.out.println("\n");
    }

    private static void consultarLista() {
        Scanner tec = new Scanner(System.in);
        
        System.out.print("Nombre de la lista a consultar: ");
        String nomList = tec.nextLine();
        Map<String, Integer> list = ctrlD.consultarLista(nomList);

        System.out.println("Nombre: "+nomList);
        System.out.println("Lista:");
        for (String key : list.keySet()) {
            System.out.println(key+" "+list.get(key));
        }
        System.out.println("\n");
    }

    private static Vector<String> introducirTextos() {

        Scanner tec = new Scanner(System.in);
        Scanner tecInt = new Scanner(System.in);
        Vector<String> textos = new Vector<String>();

        System.out.print("Cuantos textos/listas quieres introducir: ");
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
        System.out.println("2 = porImplementar");
        entrada = tecInt.nextInt();
        if (entrada == 1) algor = "QAP";
        else if (entrada == 2) algor = "a";
        else algor = "error";

        System.out.print("Introduce el alfabeto: ");
        alfab = tec.nextLine();

        textos = introducirTextos();
        
        System.out.print("Por ultimo el nombre del teclado: ");
        teclado = tec.nextLine();

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
        String modif = tec.nextLine();

        if (modif == "Y") {
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

        System.out.print("Introduce el nuevo texto, cuando acabes presiona enter 2 veces: ");
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

            System.out.print("Introduce una nueva instruccion: ");
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

                case 13: consultarTeclados(); break;
                case 14: consultarTeclado(); break;

                case 15: consultarAlfabetos(); break;
                case 16: consultarAlfabeto(); break;

                case 17: consultarTextos(); break;
                case 18: consultarTexto(); break;

                case 19: consultarListas(); break;
                case 20: consultarLista(); break;

                case 33: salir = true; break;
            }
        }
    }
}
//autor Miguel