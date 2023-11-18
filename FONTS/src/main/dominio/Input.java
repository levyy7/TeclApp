package main.dominio;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Input{

    private String nombre;

    public Input(String nombre){
        this.nombre = nombre; 
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //retorna el nom del input
    public String getNombre(){
        return nombre;
    }

    public String getType(){
        return "ERROR";
    }

    //llegeix input d'un fitxer de text: file que es la ruta del arxiu en concret
    //in ha de ser "";
    //la excepcion me la hace poner si no da error
    /*
    public void readFromFile(String file, String in) throws FileNotFoundException{
        File doc = new File(file);
        Scanner obj = new Scanner(doc);

        //String in = "";
        while(obj.hasNextLine()) in += obj.nextLine();

        obj.close();

        //return in;
    }

      //aquesta funcio?
    public void readFromType(String in){

    }
*/
    public void setInput() {

    }

    //assigna input. name es el nom del input, in es el contingut i type es si es un alfabet, llista etc;
    public void importInput(String name, String in, String type){

    }   

    public int getSize(){
        return -1;
    }

}

//mariona