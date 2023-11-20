package Excepcions;

public class TecladoYaExiste extends ExcepcionsCentral{

    public String getTipus() {
        return "TecladoYaExiste";
    }

    public TecladoYaExiste(String s) {
        super("el teclat amb nom"+ s + "ja existeix");
    }
    
}