package Excepcions;

public class InputInexistente extends ExcepcionsCentral{

    public String getTipus() {
        return "InputInexistente";
    }

    public InputInexistente() {
        super("El input seleccionado no existe");
    }
    
    
}