package Excepcions;

public class AlfabetoMalImportado extends ExcepcionsCentral{

    public String getTipus() {
        return "AlfabetoMalImportado";
    }

    public AlfabetoMalImportado(String a) {
        super("Alguno de los alfabetos importados esta mal definido por: "+a);
    }
    
}