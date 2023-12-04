package main.persistencia;

import java.io.*;
import java.util.*;

/**
 * Clase GestorCSV
 * Clase encargada de guardar y cargar datos
 * @author Miguel Ángel Montero Flores
*/
public class GestorCSV {

    /**
     * Función que guarda la información enviada en la base de datos
     * @param inf : información a guardar
     * @param filePath : Path donde guardar la información
    */
	public static void guardarInfo(String[][] inf, String filePath) {
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        
            for (String[] ent : inf) {
        
                writer.write("\""+ent[0]+"\"");
        
                for (int i = 1; i < ent.length; ++i)
                    writer.write(","+"\""+ent[i]+"\"");
                    writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    /**
     * Función que carga la información especificada al filePath
     * @param filePath
     * @return String[][] : matriz que contiene toda la información en datos
     *                      primitivos
    */
	public static String[][] cargarInfo(String filePath) {
		
        List<String[]> info = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replaceAll("^\"|\"$", "");
                }
                
                info.add(parts);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info.toArray(new String[info.size()][]);
	}

}