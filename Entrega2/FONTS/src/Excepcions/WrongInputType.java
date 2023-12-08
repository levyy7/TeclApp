package Excepcions;

public class WrongInputType extends ExcepcionsCentral {
    
    public WrongInputType(String shouldBe, String is) {
        super("la clase accedida es de tipo " + is + " pero debería ser " + shouldBe);
    }

    public String getTipus() {
        return "WrongInputType";
    }
}
