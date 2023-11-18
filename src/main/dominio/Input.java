package main.dominio;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class Input{

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

    //llegeix input d'un fitxer de text: file que es la ruta del arxiu en concret
    //in ha de ser "";
    //la excepcion me la hace poner si no da error
    public void readFromFile(String file, String in) throws FileNotFoundException{
        File doc = new File(file);
        Scanner obj = new Scanner(doc);

        //String in = "";
        while(obj.hasNextLine()) in += obj.nextLine();

        obj.close();

        //return in;
    }

    public abstract void setInput();

    //aquesta funcio?
    public abstract void readFromType(String in);

    //llegeix input i el classifica segons es TLP o Alfabet
    //type indica tipus de input, st: cadena buida ("") on es guardara l'alfabet, el text..., file: ruta del file, in: input que s'importa escrit
    public void importInput(String type, String file, String name, String in)throws FileNotFoundException{
        String st = "";
        switch(type){
            case "file":
                readFromFile(file, st);
                break;
            case "write":
                readFromType(in);
                break;
        }
    }   

    public abstract int getSize();

}

//mariona