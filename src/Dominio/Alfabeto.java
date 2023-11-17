import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Alfabeto extends Input{

    private String alphabet;

    public Alfabeto(String nombre, String alphabet){
        super(nombre);
        this.alphabet = alphabet;
    }

    //READ FROM FILE AND READ FROM TYPING
    //asumo que asi lo hara bien, puede ser que se tenga que leer varias lineas
    public void readFromFile(String file) throws FileNotFoundException{
        String in = "";
        super.readFromFile(file, in);
        this.alphabet = in;
    }

    public void readFromType(String in){
        super.readFromType(in);
        this.alphabet = in;
    }

    public String getAlfabeto(){
        return alphabet;
    }

    public String getNombre(){
        return super.getNombre();
    }

    public void setAlfabeto(String alphabet){
        this.alphabet = alphabet;
    }

    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void print(){
        int n = alphabet.length();
        for(int i = 0; i<n; ++i) System.out.println(alphabet.charAt(i) + " ");
    }

    public void modify(String newAlphabet){
        this.alphabet = newAlphabet;
    }
}

//mariona