//import java.util.*;
import java.util.regex.Pattern;

public class ComprobarExcepciones{

	public boolean TextoCorrecto(Vector<String> texto, String alfabeto) {//texto solo caracteres del alfabeto
		String regex = "[" + Pattern.quote(alfabeto) + "]+";
		return texto.matches(regex);
	}

	public boolean ListaCorrecto(){

	}

	public boolean AlfaCorrecto(){
		boolean[] caracteresVistos = new boolean[1920];

	}

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