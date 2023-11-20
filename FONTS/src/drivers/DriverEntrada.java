package drivers;
import main.dominio.CtrlAlgoritmo;
import main.dominio.CtrlEntrada;

public class DriverEntrada {
    private static CtrlEntrada ctrlE;

    private static void inicializar() {

		ctrlE = new CtrlEntrada();

		System.out.println("Este es el Driver del Algoritmo, las funcionalidades son:");
		System.out.println("1 = Utilizar QAP");
		System.out.println("33 = Salir");
	}
}
