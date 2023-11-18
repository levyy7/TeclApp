package Excepcions;

public class ListaNoValida extends ExcepcionsCentral{

    public String getTipus() {
        return "ListaNoValida";
    }

    public ListaNoValida(String s, String a) {
        super("la llista "+s+"te caracters fora del alfabet"+a);
    }
    
}