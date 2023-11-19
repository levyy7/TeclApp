package Excepcions;

public class TecladoYaExistente extends ExcepcionsCentral{

    public String getTipus() {
        return "TecladoYaExistente";
    }

    public TecladoYaExistente(String s) {
        super("el teclat amb nom"+ s + "ja existeix");
    }
    
}