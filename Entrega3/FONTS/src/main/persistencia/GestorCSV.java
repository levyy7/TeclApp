package main.persistencia;

import java.io.*;
import java.util.*;

public class GestorCSV {

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