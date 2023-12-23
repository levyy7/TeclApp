package main.dominio;
import Excepcions.*;
import main.dominio.*;
import main.persistencia.CtrlPersistencia;
import main.presentacion.CtrlPresentacion;

import java.util.*;
import java.awt.geom.Point2D;

/**
 * Controlador de dominio
 * Sirve para conectar la información del resto de controladores para ejecutar
 * ordenadamente el programa. También se encarga de saltar excepciónes
 * para asegurar el correcto Funciónamiento del mismo
 * @author Miguel Ángel Montero Flores
*/
public class CtrlDominio {

    private static CtrlDominio INSTANCE = new CtrlDominio();

    /** Contiene la instancia del controlador de entrada*/
    private static CtrlEntrada ctrlE;

    /** Contiene la instancia del controlador de algoritmo*/
    private static CtrlAlgoritmo ctrlA;


    private static CtrlPresentacion ctrlP;

    /**
     * Constructora por defecto que instancia los controladores y carga
     * los datos necesarios de persistencia
    */
    private CtrlDominio() {
        CtrlPersistencia.inicializar();
        ctrlP = CtrlPresentacion.getInstance();
        String[][] teclados = CtrlPersistencia.cargarTeclados();
        String[][] alfabetos = CtrlPersistencia.cargarAlfabetos();
        String[][] textos = CtrlPersistencia.cargarTextos();
        String[][] listas = CtrlPersistencia.cargarListas();

        ctrlE = new CtrlEntrada(teclados, alfabetos, textos, listas);
        ctrlA = new CtrlAlgoritmo();
    }

    public static CtrlDominio getInstance() {
        if (INSTANCE == null) INSTANCE = new CtrlDominio();
        return INSTANCE;
    }

    /**
     * Crea un teclado en la base de datos del programa con toda la
     * información necesaria y salta excepciónes si algun parámetro
     * contiene algún problema
     * @param nombreTeclado
     * @param nombreAlfabeto : nombre del alfabeto que usaremos con la
     *                         creacion
     * @param nombresTLP : contenedor de nombres de textos y listas
     * @param nombreAlgoritmo : nombre del algoritmo a utilizar
    */
    public void crearTeclado(String nombreTeclado, String nombreAlfabeto, 
           String[] nombresTLP, String nombreAlgoritmo) {
                
        String letrasAlfabeto;
        try {letrasAlfabeto = ctrlE.getAlfabeto(nombreAlfabeto)[1];}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}


        Vector<String> textos = new Vector<String>();
        Vector<Map<String, Integer>> listas = new Vector<>();
        
        try {asignarTextosYListas(textos, listas, nombresTLP, letrasAlfabeto);}
        catch (InputInexistente e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (TextoNoValido e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (ListaNoValida e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}

        try {
            Point2D[] playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto, nombreAlgoritmo);
            char[] layout = ctrlA.calcularLayout(textos, listas, letrasAlfabeto, playout, nombreAlgoritmo);

            ctrlE.setLayout(nombreTeclado, layout);
            CtrlPersistencia.guardarTeclado(ctrlE.consultaTeclado(nombreTeclado));
        }
        catch (NGrande e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (TecladoYaExiste e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InvalidAlgorithm e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (TecladoInexistente e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage());  return;}
    }

    /**
     * Función que importa teclados de un fichero de texto. 
     * Si el formato o el Path está mal definido salta una excepción.
     * @param path
    */
    public String[][] importarTeclados(String path) {
        try {
            String[][] teclados = CtrlPersistencia.cargarTeclados(path);
            ctrlE.importarTeclados(teclados);
            for (String[] actual : teclados) CtrlPersistencia.guardarTeclado(actual);

            String[][] croppedTec = new String[teclados.length][3];
            for (int i = 0; i < teclados.length; ++i) croppedTec[i] = teclados[i];
            return croppedTec;
        }
        catch (TecladoYaExiste e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (TecladoMalImportado e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (ArchivoNoEsCSV e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
    }

    /**
     * Función que modifica un teclado existente de la base de datos y hace
     * saltar una excepción en caso de que no sea posible
     * @param nombreTeclado : el nombre del teclado a modificar
     * @param nombreAlgoritmo : el algoritmo que se usara
     * @param nombreAlfabeto : el nombre del alfabeto que se usará
     * @param nombresTLP : los nombres de los textos y listas que se usaran
    */
    public void modificarTeclado(String nombreTeclado, String nombreAlfabeto, String[] nombresTLP, String nombreAlgoritmo) {
        
        String letras;
        try{letras = ctrlE.getAlfabeto(nombreAlfabeto)[1];}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}

        Vector<String> textos = new Vector<String>();
        Vector<Map<String, Integer>> listas = new Vector<>();
        
        try {asignarTextosYListas(textos, listas, nombresTLP, letras);}
        catch (InputInexistente e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (TextoNoValido e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (ListaNoValida e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}


        try {
            borrarTeclado(nombreTeclado);

            Point2D[] playout = ctrlE.crearTecladoVacio(nombreTeclado, nombreAlfabeto, nombreAlgoritmo);
            char[] layout = ctrlA.calcularLayout(textos, listas, letras, playout, nombreAlgoritmo);

            ctrlE.setLayout(nombreTeclado, layout);   
                
            CtrlPersistencia.modificarTeclado(ctrlE.consultaTeclado(nombreTeclado));
            }
        catch (NGrande e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InvalidAlgorithm e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (TecladoYaExiste e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (TecladoInexistente e) 
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        
        
    }

    /**
     * Función que borra un teclado de la base de datos y salta una excepción
     * si no existe
     * @param nombreTeclado : el nombre del teclado a borrar
    */
    public void borrarTeclado(String nombreTeclado) {
        try {
            ctrlE.borrarTeclado(nombreTeclado);
            CtrlPersistencia.borrarTeclado(nombreTeclado);
        }
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
    }

    /**
     * Función que crea un alfabeto y salta una
     * excepción si el alfabeto ya está creado o si tiene algun carácter
     * repetido
     * @param nombreAlfabeto
     * @param alfabeto
    */
    public void crearAlfabeto(String nombreAlfabeto, String alfabeto) {
        try {
            ctrlE.crearAlfabeto(nombreAlfabeto, alfabeto);
            CtrlPersistencia.guardarAlfabeto(ctrlE.getAlfabeto(nombreAlfabeto));
        }
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InputInexistente e) //Aqui nunca entrara
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e) //Aqui nunca entrara
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
    }

    /**
     * Función que importa alfabetos de un fichero de texto. 
     * Si el formato o el Path está mal definido salta una excepción.
     * @param path
    */
    public String[][] importarAlfabetos(String path) {
        try {
            String[][] alfabetos = CtrlPersistencia.cargarAlfabetos(path);
            ctrlE.importarAlfabetos(alfabetos);
            for (String[] actual : alfabetos) CtrlPersistencia.guardarAlfabeto(actual);

            return alfabetos;
        }
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (AlfabetoMalImportado e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (ArchivoNoEsCSV e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
    }

    /**
     * Función que modifica un alfabeto existente y salta
     * una excepción si el alfabeto está siendo usado por algún teclado, si
     * el alfabeto no existe o el nuevo alfabeto contiene carácteres repetidos
     * @param nombreAlfabeto : nombre del alfabeto a modificar
     * @param alfabetoNuevo : alfabeto que sustituirá al antiguo
    */
    public void modificarAlfabeto(String nombreAlfabeto, String letrasNuevas) {
        try {
            ctrlE.modificarAlfabeto(nombreAlfabeto, letrasNuevas);
            CtrlPersistencia.modificarAlfabeto(ctrlE.getAlfabeto(nombreAlfabeto));
        }
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (AlfabetoInvalido e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
    }

    /**
     * Función que borra un alfabeto y salta una excepcion si el alfabeto
     * está siendo usado, si no existe ningún Input o si existe el Input
     * pero no pertenece a un alfabeto
     * @param nombreAlfabeto
    */
    public void borrarAlfabeto(String nombreAlfabeto) {
        try {
            ctrlE.borrarAlfabeto(nombreAlfabeto);
            CtrlPersistencia.borrarAlfabeto(nombreAlfabeto);
        }
        catch (AlfabetoUsandose e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
    }

    /**
     * Función que crea un texto y salta una excepción si el texto existe
     * @param nombreTexto
     * @param texto 
    */
    public void crearTexto(String nombreTexto, String texto) {
        try {
            ctrlE.crearTexto(nombreTexto, texto);
            CtrlPersistencia.guardarTexto(ctrlE.getTexto(nombreTexto));
        }
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InputInexistente e) //Aqui nunca entrara
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e) //Aqui nunca entrara
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
    }

    /**
     * Función que importa textos de un fichero de texto. 
     * Si el formato o el Path está mal definido salta una excepción.
     * @param path
    */
    public String[][] importarTextos(String path) {
        try {
            String[][] textos = CtrlPersistencia.cargarTextos(path);
            ctrlE.importarTextos(textos);
            for (String[] actual : textos) CtrlPersistencia.guardarTexto(actual);

            return textos;
        }
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (TextoMalImportado e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (ArchivoNoEsCSV e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
    }    

    /**
     * Función que modifica un texto existente y salta una excepción si el
     * texto no existe o existe el Input pero no es del tipo correspondiente
     * @param nombreTexto : nombre del texto a modificar
     * @param textoNuevo : texto que sustituirá al antiguo
    */
    public void modificarTexto(String nombreTexto, String textoNuevo) {
        try {
            ctrlE.modificarTexto(nombreTexto, textoNuevo);
            CtrlPersistencia.modificarTexto(ctrlE.getTexto(nombreTexto));
        }
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
    }

    /**
     * Función que borra un TLP y salta una excepción si el TLP no existe
     * @param nombreTLP
    */
    public void borrarTLP(String nombreTLP) {
        try {
            TLP tlp = ctrlE.getTLP(nombreTLP);
            ctrlE.borrarTLP(nombreTLP);
            if (tlp instanceof Texto) CtrlPersistencia.borrarTexto(nombreTLP);
            else CtrlPersistencia.borrarLista(nombreTLP);
        }
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}  
    }

    /**
     * Función que crea una lista de palabras y salta una excepción si la
     * lista ya existe
     * @param nombreLista
     * @param lista : lista de palabras con frecuencias 
    */
    public void crearListaPalabras(String nombreLista, Map<String, Integer> lista) {
        try {
            ctrlE.crearListaPalabras(nombreLista, lista);
            CtrlPersistencia.guardarLista(ctrlE.getListaPalabras(nombreLista));
        }
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (InputInexistente e) //Aqui nunca entrara
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e) //Aqui nunca entrara
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;} 
    }

    /**
     * Función que importa listas de palabras de un fichero de texto. 
     * Si el formato o el Path está mal definido salta una excepción.
     * @param path
    */
    public String[][] importarListasPalabras(String path) {
        try {
            String[][] listas = CtrlPersistencia.cargarListas(path);
            ctrlE.importarListasPalabras(listas);
            for (String[] actual : listas) CtrlPersistencia.guardarLista(actual);

            return listas;
        }
        catch (InputJaCreat e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (ListaMalImportada e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
        catch (ArchivoNoEsCSV e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return new String[0][0];}
    }
    
    /**
     * Función que modifica una lista de palabras y salta una excepción si no
     * existe o no pertenece a una lista
     * @param nombreLista : nombre de la lista a modificar
     * @param listaNueva : lista que sustituirá a la antigua
    */
    public void modificarListaPalabras(String nombreLista, Map<String, Integer> listaNueva) {
        try {
            ctrlE.modificarListaPalabras(nombreLista, listaNueva);
            CtrlPersistencia.modificarLista(ctrlE.getInput(nombreLista));
        }
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return;} 
    }

    /** 
     * Consulta de los teclados
     * @return String[][] : contiene los teclados guardados
    */
    public String[][] consultarInfoTeclados() {
        return ctrlE.consultaInfoTeclados();
    }

    /**
     * Consulta de un teclado
     * @param nombreTeclado
     * @return String[] : contiene el teclado consultado
    */
    public String[] consultarInfoTeclado(String nombreTeclado) {
        try {return ctrlE.consultaTeclado(nombreTeclado);}
        catch (TecladoInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
    }

    /**
     * Consulta del layout de un teclado existente
     * @param nombreTeclado
     * @return char[][] : contiene el Layout consultado
    */
    public char[][] consultarLayoutTeclado(String nombreTeclado) {
        return ctrlE.consultaLayoutTeclado(nombreTeclado);
    }

    /** 
     * Consulta de los alfabetos
     * @return String[][] : contiene los alfabetos guardados
    */
    public String[][] consultarAlfabetos() {
        return ctrlE.consultaAlfabetos();
    }

    /** 
     * Consulta de los textos
     * @return String[][] : contiene los textos guardados
    */
    public String[][] consultarTextos() {
        return ctrlE.consultaTextos();
    }

    /** 
     * Consulta de las listas
     * @return String[][] : contiene las listas guardadas
    */
    public String[][] consultarListas() {
        return ctrlE.consultaListas();
    }

    /**
     * Consulta de los TLPs
     * @return String[][] : contiene los TLPs guardadas
    */
    public String[][] consultarTLPs() {
        return ctrlE.consultaInfoTLPs();
    }

    /** 
     * Consulta de un alfabeto
     * @param nombreAlfabeto
     * @return String[] : contiene el alfabeto consultado
    */
    public String[] consultarAlfabeto(String nombreAlfabeto) {
        try {return ctrlE.getAlfabeto(nombreAlfabeto);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
    }

    /** 
     * Consulta de un texto
     * @param nombreTexto
     * @return String[] : contiene el texto consultado
    */
    public String[] consultarTexto(String nombreTexto) {
        try {
            return ctrlE.getTexto(nombreTexto);
        }
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
    }

    /** 
     * Consulta de una lista de palabras
     * @param nombreLista
     * @return String[] : contiene la lista consultada
    */
    public String[] consultarLista(String nombreLista) {
        try {
            return ctrlE.getListaPalabras(nombreLista);
        }
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
    }

    /**
     * Consulta de un TLP
     * @param nombreTLP
     * @return String[] : contiene el LTP consultado
    */
    public String[] consultarTLP(String nombreTLP) {
        try {return ctrlE.getInfoTLP(nombreTLP);}
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
    }

    public String consultarTipoTLP(String nombreTLP) {
        try {
            TLP tlp = ctrlE.getTLP(nombreTLP);
            if (tlp instanceof Texto) return "Texto";
            else return "Lista";
        }
        catch (InputInexistente e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
        catch (WrongInputType e)
            {System.out.println("Error: "+e.getMessage()); ctrlP.saltaExcepcion(e.getTipus(), "Error: "+e.getMessage()); return null;}
    }

    /**
     * Asigna los textos y las listas correspondientes de los nombresTLP y
     * salta una excepción si alguno de los nombres no existe como Input,
     * existe pero no ni de tipo Texto ni de tipo ListaPalabras, algún texto
     * no corresponde al alfabeto o alguna lista no corresponde al alfabeto
     * @param textos : Vector de textos, inicialmente vacio que contendra 
     *                 los textos correspondientes
     * @param listas : Vector de listas, inicialmente vacio que contendra
     *                 las listas correspondientes
     * @param nombresTLP : Vector de nombres TLP
    */
    private void asignarTextosYListas(Vector<String> textos, 
            Vector<Map<String, Integer>> listas, String[] nombresTLP,
            String alfabeto) throws InputInexistente, WrongInputType, TextoNoValido, ListaNoValida {

        for (int i = 0; i < nombresTLP.length; ++i) {
            String nombreTLP = nombresTLP[i];

            TLP tlp;
            try {tlp = ctrlE.getTLP(nombreTLP);}
            catch (InputInexistente e) {throw e;}
            catch (WrongInputType e) {throw e;}

            if (tlp instanceof Texto) textos.addElement(((Texto)tlp).getTexto()); 
            else listas.addElement(((ListaPalabras)tlp).getListaFreq());
        }

        try {
            ctrlE.compruebaTextos(textos, alfabeto);
            ctrlE.compruebaListas(listas, alfabeto);
        }
        catch (TextoNoValido e) {throw e;}
        catch (ListaNoValida e) {throw e;}
    }
}
//autor Miguel