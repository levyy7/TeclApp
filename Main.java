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