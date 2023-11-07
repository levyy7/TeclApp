import java.util.*;

public class Main {

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

    private static void crearTeclado(CtrlDominio ctrlD) {

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

        System.out.print("Cuantos textos quieres introducir? ");
        int numeroDeTextos = tecInt.nextInt();

        System.out.println("Introduce los nombres de los textos:");
        for (int i = 0; i < numeroDeTextos; ++i) textos.addElement(tec.nextLine());
        
        System.out.print("Por ultimo el nombre del teclado: ");
        teclado = tec.nextLine();

        //System.out.println(teclado+alfab+textos.elementAt(0)+algor);
        ctrlD.crearTeclado(teclado, alfab, textos, algor);
    }

	public static void main(String[] args) {
        
		CtrlDominio ctrlD = new CtrlDominio();

		GuiaInstrucciones();
        
        //Para el cin
        Scanner tec = new Scanner (System.in);
        int instruccion;
        while (true) {

            instruccion = tec.nextInt();
            
            //Instruccion de crear teclado
            if (instruccion == 1) {
                
                crearTeclado(ctrlD);

                //String [] a = new String [5];
                //Vector<String> nombresTLP = new Vector<String>(2);   
                //ctrlD.crearTeclado("PrimerTeclado", "Alfabeto", nombresTLP, "Algoritmo");
            }

            //Instruccion de borrar teclado
            else if (instruccion == 2) {

                //
            }

            //Instruccion de importar Alfabeto
            else if (instruccion == 3) {

            	//
            }

            //Instruccion de importar texto
            else if (instruccion == 4) {

            	//
            }

            //Instruccion de Modificar teclado
            else if (instruccion == 5) {

            	//
            }

            //Instruccion de Modificar texto
            else if (instruccion == 6) {

            	//
            }

            //Instruccion de Salir del programa
            else if (instruccion == 33) break;
        }
    }

}