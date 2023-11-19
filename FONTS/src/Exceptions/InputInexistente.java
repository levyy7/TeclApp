package Excepcions;

public class InputInexistente extends ExcepcionsCentral{

    public String getTipus() {
        return "InputInexistente";
    }

    public InputInexistente() {
        super("Imput incorrecte tontito");
    }
    
    
}