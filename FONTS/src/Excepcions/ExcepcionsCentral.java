package Excepcions;

@SuppressWarnings("serial")

public abstract class ExcepcionsCentral extends Exception{

    public abstract String getTipus();

    public ExcepcionsCentral() {
        super();
    }

    public ExcepcionsCentral(String s) { 
        super(s);
    }
}
//autor POL