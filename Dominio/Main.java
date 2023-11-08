import java.util.*;

public class Main {

    private static CtrlDominio ctrlD;

	private static void GuiaInstrucciones() {

        System.out.println("Guía de instrucciones");
        System.out.println("1 = Crear teclado");
		System.out.println("2 = Borrar teclado");
		System.out.println("3 = Importar alfabeto");
		System.out.println("4 = Importar texto");
		System.out.println("5 = Modificar teclado");
		System.out.println("6 = Modificar texto");
		System.out.println("33 = Salir");
	}

    private static Vector<String> introducirTextos() {

        Scanner tec = new Scanner(System.in);
        Scanner tecInt = new Scanner(System.in);
        Vector<String> textos = new Vector<String>();

        System.out.print("Cuantos textos quieres introducir? ");
        int numeroDeTextos = tecInt.nextInt();

        System.out.println("Introduce los nombres de los textos:");
        for (int i = 0; i < numeroDeTextos; ++i) textos.addElement(tec.nextLine());
    
        return textos;
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
        ctrlD.importarAlfabeto();
    }

    private static void importarTexto() {
        ctrlD.importarTexto();
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

    private static void modificarTexto() {
        
        Scanner tec = new Scanner(System.in);

        System.out.print("Introduce el nombre del texto a modificar: ");
        String nombreTexto = tec.nextLine();

        ctrlD.modificarTexto(nombreTexto);
    }

	public static void main(String[] args) {
        
		ctrlD = new CtrlDominio();

		GuiaInstrucciones();
        
        //Para el cin
        Scanner tec = new Scanner (System.in);
        int instruccion;
        while (true) {

            instruccion = tec.nextInt();          

            //Listado de instrucciones
            if (instruccion == 1) crearTeclado();
            
            else if (instruccion == 2) borrarTeclado();
            
            else if (instruccion == 3) importarAlfabeto();

            else if (instruccion == 4) importarTexto();

            else if (instruccion == 5) modificarTeclado();

            else if (instruccion == 6) modificarTexto();

            else if (instruccion == 33) break;
        }
    }

}