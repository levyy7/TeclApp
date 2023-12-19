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
     * Función que sobreescribe la información enviada en el filePath
     * @param inf : información a guardar
     * @param filePath : Path donde guardar la información
    */
	public void guardarInfoOW(String[][] inf, String filePath) {
		
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
     * Función que guarda la información enviada en el filePath
     * @param inf : información a guardar
     * @param filePath : Path donde guardar la información
    */
	public void guardarInfo(String[][] inf, String filePath) {
		List<String[]> info = new ArrayList<>(Arrays.asList(cargarInfo(filePath)));
        
        info.addAll(Arrays.asList(inf));

        guardarInfoOW(info.toArray(new String[info.size()][]), filePath);
	}

    /**
     * Función que carga la información especificada al filePath
     * @param filePath
     * @return String[][] : matriz que contiene toda la información en datos
     *                      primitivos
    */
	public String[][] cargarInfo(String filePath) {
		
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


    /**
     * Función que borra la línea que contenga alguno de los String del
     * array primaryKeys en el archivo filePath
     * @param primaryKeys : nombres a borrar
     * @param filePath : Path donde borrar la información
    */
    public void borrarInfo(String[] primaryKeys, String filePath) {
        List<String[]> info = new ArrayList<>(Arrays.asList(cargarInfo(filePath)));
        
        info.removeIf(array -> equalsAny(array[0], primaryKeys));

        guardarInfoOW(info.toArray(new String[info.size()][]), filePath);
    }

    private boolean equalsAny(String s, String[] array) {
        Boolean same = false;
        for (int i = 0; i < array.length && !same; ++i) 
            same = s.equals(array[i]);

        return same;
    }

}