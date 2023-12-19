package Excepcions;

public class ListaMalImportada extends ExcepcionsCentral{

    public String getTipus() {
        return "ListaMalImportada";
    }

    public ListaMalImportada(String a) {
        super("Alguna de las listas importadas esta mal definida por: "+a);
    }
    
}