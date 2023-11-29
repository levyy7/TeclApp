package Excepcions;

public class TecladoInexistente extends ExcepcionsCentral{

    public String getTipus() {
        return "TecladoInexistente";
    }

    public TecladoInexistente(String s) {
        super("el teclat amb nom "+ s + " no existeix");
    }
    
}