package Excepcions;

public class TecladoMalImportado extends ExcepcionsCentral{

    public String getTipus() {
        return "TecladoMalImportado";
    }

    public TecladoMalImportado(String a) {
        super("Alguno de los teclados importados esta mal definido por: "+a);
    }
    
}