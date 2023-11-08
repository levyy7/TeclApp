import java.util.*;

public class Alfabeto extends Input{

    private String alphabet;

    public Alfabeto(String nombre, String alphabet){
        super(nombre);
        this.alphabet = alphabet;
    }
    //asumo que asi lo hara bien, puede ser que se tenga que leer varias lineas
    public void readAlfabeto(){
        Scanner t = new Scanner(System.in);
        this.alphabet = t.nextLine();
        t.close(); //me pedia cerrar el canal
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

/* 
    public void save(){

    }
    */
}