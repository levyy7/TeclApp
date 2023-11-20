package Excepcions;

public class AlfabetoUsandose extends ExcepcionsCentral{

    public String getTipus() {
        return "AlfabetoUsandose";
    }

    public AlfabetoUsandose(String s) {
        super("l'alfabet"+s+"no es pot modificar perquè s'està utilitzant a un teclat");
    }
    
}