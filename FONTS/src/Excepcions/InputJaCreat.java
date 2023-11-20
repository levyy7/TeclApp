package Excepcions;

public class InputJaCreat extends ExcepcionsCentral{

    public String getTipus() {
        return "InputJaCreat";
    }

    public InputJaCreat(String s) {
        super("ja existeix un imput amb el nom:"+s);
    }
    
}