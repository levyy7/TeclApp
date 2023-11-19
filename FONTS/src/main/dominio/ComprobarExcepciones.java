package main.dominio;

//import java.util.*;
import java.util.regex.Pattern;

public class ComprobarExcepciones{

	public boolean TextoCorrecto(Vector<String> textos, String alfabeto) {//texto solo caracteres del alfabeto
		for (String texto:textos){
			String regex = "[" + Pattern.quote(alfabeto) + "]+";
			if(texto.matches(regex) == false) return false;
		}
		return true;
	}

	public boolean ListaCorrecto(Vector<Map<String, Integer>> lista, String alfabeto){
		for (Map<String, Integer> llista:llistas){
			for(String paraula : llista){
				String regex = "[" + Pattern.quote(alfabeto) + "]+";
				if(paraula.matches(regex) == false) return false;
			}
				
		}
		return true;
	}

	public boolean AlfaCorrecto(String alfabeto){ //mirar que no hi ha dos caracters repetits
		HashSet<Character> caracteresVistos = new HashSet<>();

        for (char c : alfabeto.toCharArray()) {
            if (caracteresVistos.contains(c)) {
                return false; 
            }
            caracteresVistos.add(c); 
        }
        return true;
    }

}
//classe implementada per POL