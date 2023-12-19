package Excepcions;

public class TextoMalImportado extends ExcepcionsCentral{

    public String getTipus() {
        return "TextoMalImportado";
    }

    public TextoMalImportado(String a) {
        super("Alguno de los textos importados esta mal definido por: "+a);
    }
    
}