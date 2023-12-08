package Excepcions;

public class InvalidAlgorithm extends ExcepcionsCentral {

    public InvalidAlgorithm() {
        super("el algoritmo especificado no es válido");
    }

    public String getTipus() {
        return "InvalidAlgorithm";
    }
}
