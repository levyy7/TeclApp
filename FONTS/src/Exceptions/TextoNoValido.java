package Excepcions;

public class TecladoNoValido extends ExcepcionsCentral{

    public String getTipus() {
        return "TecladoNoValido";
    }

    public TecladoNoValido(String s, String a) {
        super("el text"+s+"te caracters fora del alfabet"+a);
    }
    
}