package Excepcions;

public class NGrande extends ExcepcionsCentral{

    public String getTipus() {
        return "NGrande";
    }

    public NGrande() {
        super("el tamany de l'alfabet ha de ser menor que 20");
    }
    
}