package Excepcions;

public class ListaNoValida extends ExcepcionsCentral{

    public String getTipus() {
        return "ListaNoValida";
    }

    public ListaNoValida() {
        super("hay una llista que te caracters fora del alfabet");
    }
    
}