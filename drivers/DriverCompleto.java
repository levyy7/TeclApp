package DriversStubs;

import ControladorsDomini.CtrlDomini;
import Dades.CtrlItemFile;
import Dades.CtrlRatingsFile;
import Domini.DistanciaBasica;
import Domini.DistanciaEuclidiana;
import Domini.Item;
import Domini.UsuariActiu;
import Domini.Valoracio;
import Excepcions.ExcepcionsRecomanador;
import Excepcions.PuntuacioNoValida;

public class DriverComplet {

    private CtrlDomini cd = CtrlDomini.getInstance();
    

    public static void main(String[] args) throws Exception{

    	System.out.println("Bondia\n");
        DriverCompleto dc = new DriverCompleto();
        dc.cd.inicialitzar();

        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        FileReader fr = new FileReader("../../DATA/"+filename);
        Scanner scan = new Scanner(fr);
        LinkedList<String> comandes = new LinkedList<String>();

        while(scan.hasNextLine()) comandes.add(new String(scan.nextLine()));   //Per cada linea de comandes s'hi afegeix a la llista amb totes les comandes
        for (String i : comandes){
            String array[] = i.split(" "); //Separem les diferents comandes en una array.
            switch(array[0]){
                case "readItems":
                    dC.TestLecturaItems(array[1]); break;
                case "readRatings":
                    dC.TestLecturaRatings(array[1]); break;
                case "readKnown":    
                    dC.TestLecturaKnown(array[1]); break;
                case "readUnknown":    
                    dC.TestLecturaUnknown(array[1]); break;
                case "recomanacioContentBased":
                    if(array.length <= 3)    dC.TestRecomanacioContentBased(array[1], Integer.valueOf(array[2]));
                    else dC.TestRecomanacioContentBased(array); break;
                case "recomanacioCollaborative":
                    if(array.length <= 3)    dC.TestRecomanacioCollaborative(array[1], Integer.valueOf(array[2]));
                    else dC.TestRecomanacioCollaborative(array); break;
                case "newUser":
                    dC.TestcreaUsuari(array[1], array[2]); break;
                case "getUser":
                    dC.TestgetUsuari(array[1]); break;
                case "getValoracions":
                    dC.TestgetValoracions(array[1]); break;
                case "newValoracio":
                    dC.TestcreaValoracio(array[1], array[2], Double.valueOf(array[3])); break;
                case "modificarValoracio":
                    dC.TestmodificarValoracio(array[1], array[2], Double.valueOf(array[3])); break;
                case "distanciaBasica":
                    dC.TestdistanciaBasica(array); break;
                case "distanciaEuclidiana":
                    dC.TestdistanciaEuclidiana(array); break;
                case "exit":
                    System.out.println("Finalitzant el driver..."); break;
            }
        }
    }

    }
    
}	