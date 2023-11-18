//import java.util.*;
import java.util.regex.Pattern;

public class ComprobarExcepciones{

	public boolean TextoCorrecto(Vector<String> textos, String alfabeto) {//texto solo caracteres del alfabeto
		for (String texto:textos){
			String regex = "[" + Pattern.quote(alfabeto) + "]+";
			if(!texto.matches(regex)) return false;
		}
		return true;
	}

	public boolean ListaCorrecto(Vector<String> lista, String alfabeto){

	}

	public boolean AlfaCorrecto(String alfabeto){
		HashSet<Character> caracteresVistos = new HashSet<>();

        for (char c : alfabeto.toCharArray()) {
            if (caracteresVistos.contains(c)) {
                return false; // Se encontró un carácter repetido
            }
            caracteresVistos.add(c); // Agrega el carácter al conjunto
        }
        return true; // No se encontraron caracteres repetidos
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