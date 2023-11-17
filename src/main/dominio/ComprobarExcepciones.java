//import java.util.*;
import java.util.regex.Pattern;

public class ComprobarExcepciones{

	public static void main(String[] args){ //texto solo caracteres del alfabeto
		String alfabeto = "asdfeqc ";
		String texto= "asse fesaq ade";
		String regex = "[" + Pattern.quote(alfabeto) + "]+";
		System.out.println(texto.matches(regex));
	}


	public boolean esCorrecto(Vector<String> texto, String alfabeto) {
		//return texto.matches(alfabeto);


	//public boolean InputCorrecto(){

	//}
}


//exepciones, input incorrecto
//texto no relacionado con el alphabeto
//borrar teclado no existente
//canviar alphaveto


/*
lista:
String Int
palabra 3


sadhjkbdaskdb dahshdiags
çdadyasbd dajkbda