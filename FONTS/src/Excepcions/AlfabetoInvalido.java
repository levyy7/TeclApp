package Excepcions;

public class AlfabetoInvalido extends ExcepcionsCentral{

    public String getTipus() {
        return "AlfabetoInvalido";
    }

    public AlfabetoInvalido(String s) {
        super("l'alfabet" +s+ "te caràcters repetits");
    }
    
}