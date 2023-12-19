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
     * comprueba que todos los carácteres del texto son del alfabeto 
     * @param textos : vector del contenido de los textos que se quieren comprobar
     * @param alfabeto : contenido de un alfabeto
     * @return boolean : False si hay algún carácter de los textos que no pertenece al alfabeto sino True
    */
	public boolean textoCorrecto(Vector<String> textos, String alfabeto) {
		for (String texto:textos){
			String regex = "[" + Pattern.quote(alfabeto) + "]+";
			if(texto.matches(regex) == false) return false;
		}
		return true;
	}

	/**
     * comprueba que todos los carácteres del texto son del alfabeto 
     * @param llistas : vector del contenido de las listas que se quieren comprobar
     * @param alfabeto : contenido de un alfabeto
     * @return boolean : False si hay algún carácter de las listas que no pertenece al alfabeto sino True
    */
	public boolean listaCorrecto(Vector<Map<String, Integer>> llistas, String alfabeto){
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
	public boolean alfaCorrecto(String alfabeto){ 
		HashSet<Character> caracteresVistos = new HashSet<>();

        for (char c : alfabeto.toCharArray()) {
            if (caracteresVistos.contains(c)) {
                return false; 
            }
            caracteresVistos.add(c); 
        }
        return true;
    }

    /**
	 * Comprueba que un layout se corresponde a un alfabeto
	 * @param alfabeto
	 * @param layout
	 * @return boolean : si corresponde True
    */
    public boolean layoutCorrespondeAlfabeto(String alfabeto, char[] layout) {

    	for (int i = 0; i < layout.length; ++i) {

    		int j = 0;
    		boolean encontrado = false;
    		char caracterActual = layout[i];
    		while (encontrado == false && j < alfabeto.length()) {
    			if (caracterActual == alfabeto.charAt(j)) encontrado = true;
    			++j;
    		}
    		if (encontrado == false) return false;
    	}
    	return true;
    }

    /**
	 * Comprueba que una lista esta bien definida por el formato
	 * (Numero+" "+Integer, Numero+" "+Integer...)
	 * @param lista
	 * @return boolean : si esta bien definida true
    */
    public boolean listaBienDefinida(String lista) {
    	
    	String[] listaPalabras = lista.split(" ");
    	if (listaPalabras.length % 2 != 0) return false;

    	for (int i = 1; i < listaPalabras.length; i += 2) {
    		String[] numAct = listaPalabras[i].split("");
    		for (String num : numAct)
	    		if (!num.equals("1") && !num.equals("2") && !num.equals("3") &&
	    			!num.equals("4") && !num.equals("5") && !num.equals("6") &&
	    			!num.equals("7") && !num.equals("8") && !num.equals("9"))
	    			return false;
    	}
    	return true;
    }

}
//classe implementada per POL