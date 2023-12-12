package Excepcions;

public class TextoNoValido extends ExcepcionsCentral{

    public String getTipus() {
        return "TextoNoValido";
    }

    public TextoNoValido() {
        super("un text te caracters fora del alfabet");
    }
    
}