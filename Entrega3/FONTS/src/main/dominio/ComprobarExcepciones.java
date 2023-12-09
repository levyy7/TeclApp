package main.dominio;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
/**
 * Classe que comprueba la sinergia entre los inputs a la hora de crear un teclado
 * @author Pol Ribera Moreno
*/
public class ComprobarExcepciones{

	/**
     * comprueva que todos los carácteres del texto son del alfabeto 
     * @param textos : vector del contenido de los textos que se quieren comprovar
     * @param alfabeto : contenido de un alfabeto
     * @return boolean : False si hay algún carácter de los textos que no pertenece al alfabeto sino True
    */
	public boolean TextoCorrecto(Vector<String> textos, String alfabeto) {
		for (String texto:textos){
			String regex = "[" + Pattern.quote(alfabeto) + "]+";
			if(texto.matches(regex) == false) return false;
		}
		return true;
	}

	/**
     * comprueva que todos los carácteres del texto son del alfabeto 
     * @param llistas : vector del contenido de las listas que se quieren comprovar
     * @param alfabeto : contenido de un alfabeto
     * @return boolean : False si hay algún carácter de las listas que no pertenece al alfabeto sino True
    */
	public boolean ListaCorrecto(Vector<Map<String, Integer>> llistas, String alfabeto){
		for (Map<String, Integer> llista:llistas){
			for(Map.Entry<String, Integer> fila : llista.entrySet()){
				String regex = "[" + Pattern.quote(alfabeto) + "]+";
				String paraula = fila.getKey();
				if(paraula.matches(regex) == false) return false;
			}
				
		}
		return true;
	}

	/**
     * Mira que no hayan dos carácteres repetidos en el alfabeto
     * @param alfabeto : contenido de un alfabeto
     * @return boolean : False si hay algún carácter repetido sino True
    */
	public boolean AlfaCorrecto(String alfabeto){ 
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