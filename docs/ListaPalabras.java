import java.util.*;

public class ListaPalabras extends TLP{

    private Map<String, Double> listaFreq = new HashMap<String, Double>();

    public ListaPalabras(String nombre, Map<String, Double> listaFreq){
        super(nombre);
        this.listaFreq = listaFreq;
    }

    public void readListaFreq(){
        Scanner t = new Scanner(System.in);
        while(t.hasNext()) {
            listaFreq.putIfAbsent(t.next(), t.nextDouble());
        }
        t.close();
    }

    public Map<String, Double> getListaFreq(){
        return listaFreq;
    }

    public Double getFrequencia(String palabra) {
        return listaFreq.get(palabra);
    }

    //modify???
    public void modify(Map<String, Double> newlist) {
        this.listaFreq = newlist;
    }

    //afegir paraules a la llista si no hi son
    public void addPalabra(String p, Double f) {
        listaFreq.putIfAbsent(p, f);
    }

    public void printSingle(String p, Double f){
        System.out.println("Palabra: " + p + " Frequencia: " + f + "\n");
    }

    public void print(){
        listaFreq.forEach((key, value) -> printSingle(key, value));;       
    }
       
}