package Excepcions;

@SuppressWarnings("serial") //La classe RunTimeException té un atribut que aquesta classe no te sentit que l'usi 

public abstract class ExcepcionsCentral extends Exception{

    public abstract String getTipus();

    public ExcepcionsCentral() {
        super();
    }

    public ExcepcionsCentral(String s) { //aquesta funcio no cal crec
        super(s);
    }
}