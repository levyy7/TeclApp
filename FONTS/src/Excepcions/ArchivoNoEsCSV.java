package Excepcions;

public class ArchivoNoEsCSV extends ExcepcionsCentral{

    public String getTipus() {
        return "ArchivoNoEsCSV";
    }

    public ArchivoNoEsCSV() {
        super("El archivo que se quiere importar no tiene la extension .csv");
    }
    
    
}