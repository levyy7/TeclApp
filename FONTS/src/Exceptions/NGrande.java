package Excepcions;

public class NGrande extends ExcepcionsCentral{

    public String getTipus() {
        return "NGrande";
    }

    public NGrande() {
        super("la n ha de ser menor que x");
    }
    
}