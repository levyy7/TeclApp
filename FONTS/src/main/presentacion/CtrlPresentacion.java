package main.presentacion;

import javax.swing.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;

import main.dominio.CtrlDominio;
import main.presentacion.*;


/** 
 * Clase del controloador de presentación <code>QAP</code>
 * Se encarga tanto de gestionar las habilitaciones y deshabilitaciones de las vistas
 * como de la transferencia de datos entre las capas de presentación y dominio. Ya que
 * es un controlador, se trata de una clase singleton
 * @author Eneko Sabaté Iturgaiz 
*/

public class CtrlPresentacion {
    
    /** Instancia singleton de CtrlPresentacion */
    private static CtrlPresentacion INSTANCE;

    /** Instancia singleton de CtrlDominio */
    private static CtrlDominio ctrlD;

    /** Instancia de la vista Menu Principal del programa  */
    private static FrameMenuPrincipal mp;

    private CtrlPresentacion() {
        //enableVTeclados();
    }

    public void inicializar() {
        ctrlD = CtrlDominio.getInstance();
        SwingUtilities.invokeLater(() -> mp = new FrameMenuPrincipal());
    }

    /** 
     * Retorna la instancia singleton de CtrlPresentacion
     * @return CtrlPresentacion : Única instancia de la clase CtrlPresentacion
    */
    public static CtrlPresentacion getInstance() {
        if (INSTANCE == null) INSTANCE = new CtrlPresentacion();
        return INSTANCE;
    }

    
    /** 
     * Devuelve las características de un teclado
     * @param nameTec : Nombre del teclado que se quiere consultar
     * @return String[] : Teclado expresado tal y como se especifica en
     * la funcion .toStringArray() de la clase Teclado
    */
    public String[] getInfoTeclado(String nameTec) {
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    /** 
     * Devuelve el layout de un teclado
     * @param nameTec : Nombre del teclado del que se quiere consultar el layout
     * @return char[][] : Layout del teclado expresado como una matriz de chars
    */
    public char[][] getLayoutTeclado(String nameTec) {
        return ctrlD.consultarLayoutTeclado(nameTec);
    }

    /** 
     * Devuelve las características de un teclado
     * @param nameTec : Nombre del teclado que se quiere consultar
     * @return String[] : Teclado expresado tal y como se especifica en
     * la funcion .toStringArray() de la clase Teclado
    */
    public String[] getAlfabeto(String nameAlf) {
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    /** 
     * Devuelve las características de un teclado
     * @param nameTec : Nombre del teclado que se quiere consultar
     * @return String[] : Teclado expresado tal y como se especifica en
     * la funcion .toStringArray() de la clase Teclado
    */
    public String[] getTLP(String nameTLP) {
        return ctrlD.consultarTLP(nameTLP);
    }

    /** 
     * Devuelve todos los teclados existentes en el programa
     * @return String[][] : Array de teclados expresados tal y como se especifica en
     * la funcion .toStringArray() de la clase Teclado
    */
    public String[][] getAllTeclados() {
        return ctrlD.consultarInfoTeclados();
    }

    /** 
     * Devuelve todos los alfabetos existentes en el programa
     * @return String[][] : Array de alfabetos expresados tal y como se especifica en
     * la funcion .toStringArray() de la clase Alfabeto
    */
    public String[][] getAllAlfabetos() {
        return ctrlD.consultarAlfabetos();
    }

    /** 
     * Devuelve todos los TLP existentes en el programa
     * @return String[][] : Array de TLP expresados tal y como se especifica en
     * la funcion .toStringArray() de la clase TLP
    */
    public String[][] getAllTLP() {
        String[][] textos = ctrlD.consultarTextos();
        String[][] listas = ctrlD.consultarListas();

        String[][] res = Arrays.copyOf(textos, textos.length + listas.length);
        System.arraycopy(listas, 0, res, textos.length, listas.length);

        return res;
    }

    /** 
     * Importa a la aplicación todos los teclados del fichero especificado por path
     * @param path : path del fichero desde donde se extraen los datos a cargar 
     * @return String[][] : Array de los teclados importados, expresados tal y como 
     * se especifica en la funcion .toStringArray() de la clase Teclado
    */
    public String[][] importTeclados(String path) {
        return ctrlD.importarTeclados(path);
    }

    /** 
     * Importa a la aplicación todos los alfabetos del fichero especificado por path
     * @param path : path del fichero desde donde se extraen los datos a cargar 
     * @return String[][] : Array de los alfabetos importados, expresados tal y como 
     * se especifica en la funcion .toStringArray() de la clase Alfabeto
    */
    public String[][] importAlfabetos(String path) {
        return ctrlD.importarAlfabetos(path);
    }

    /** 
     * Importa a la aplicación todos los textos del fichero especificado por path
     * @param path : path del fichero desde donde se extraen los datos a cargar 
     * @return String[][] : Array de los textos importados, expresados tal y como 
     * se especifica en la funcion .toStringArray() de la clase Texto
    */
    public String[][] importTextos(String path) {
        return ctrlD.importarTextos(path);
    }

    /** 
     * Importa a la aplicación todas las listas del fichero especificado por path
     * @param path : path del fichero desde donde se extraen los datos a cargar 
     * @return String[][] : Array de las listas importados, expresados tal y como 
     * se especifica en la funcion .toStringArray() de la clase ListaPalabras
    */
    public String[][] importListas(String path) {
        return ctrlD.importarListasPalabras(path);
    }

    /** 
     * Crea y guarda un nuevo teclado en la aplicación
     * @param nameTec : Nombre del telcado a crear
     * @param nameAlf : Nombre del alfabeto que se usará en el teclado
     * @param nameTLP : Nombres de los TLP usados para la creación del teclado 
     * @param nameAlg : Nombre del algoritmo utilizado para la creación del teclado.
     * Puede ser "QAP" o "AG".
     * @return String[] : Teclado expresado tal y como se especifica en la funcion 
     * .toStringArray() de la clase Teclado
    */
    public String[] createTeclado(String nameTec, String nameAlf, String[] nameTLP, String nameAlg) {
        ctrlD.crearTeclado(nameTec, nameAlf, nameTLP, nameAlg);
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    /** 
     * Crea y guarda un nuevo alfabeto en la aplicación
     * @param nameAlf : Nombre del alfabeto a crear
     * @param lettersAlf : Letras que contiene el alfabeto
     * @return String[] : Alfabeto expresado tal y como se especifica en la funcion 
     * .toStringArray() de la clase Alfabeto
    */
    public String[] createAlfabeto(String nameAlf, String lettersAlf) {
        ctrlD.crearAlfabeto(nameAlf, lettersAlf);
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    /** 
     * Crea y guarda un nuevo texto en la aplicación
     * @param nameTexto : Nombre del texto a crear
     * @param texto : El texto que se quiere guardar
     * @return String[] : Texto expresado tal y como se especifica en la funcion 
     * .toStringArray() de la clase Texto
    */
    public String[] createTexto(String nameTexto, String texto) {
        ctrlD.crearTexto(nameTexto, texto);
        return ctrlD.consultarTexto(nameTexto);
    }

    /** 
     * Crea y guarda una nueva lista de palabras en la aplicación
     * @param nameLista : Nombre de la lista a crear
     * @param lista : Lista de palabra-frecuencia, donde lista.key representa la 
     * palabra y lista.value la frecuencia asociada a esta
     * @return String[] : Lista de palabras expresada tal y como se especifica en la funcion 
     * .toStringArray() de la clase ListaPalabras
    */
    public String[] createLista(String nameLista, Map<String, Integer> lista) {
        ctrlD.crearListaPalabras(nameLista, lista);
        return ctrlD.consultarLista(nameLista);
    }

    /** 
     * Modifica y guarda un teclado existente en la aplicación
     * @param nameTec : Nombre del telcado a modificar
     * @param nameAlf : Nombre del alfabeto que se usará en el teclado
     * @param nameTLP : Nombres de los TLP usados para la creación del teclado 
     * @param nameAlg : Nombre del algoritmo utilizado para la creación del teclado.
     * Puede ser "QAP" o "AG".
     * @return String[] : Información del teclado modificado, expresada tal y como 
     * se especifica en la funcion .toStringArray() de la clase Teclado
    */
    public String[] modifyTeclado(String nameTec, String nameAlf, String[] nameTLP, String nameAlg) {
        ctrlD.modificarTeclado(nameTec, nameAlf, nameTLP, nameAlg);
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    /** 
     * Modifica y guarda un alfabeto existente en la aplicación
     * @param nameAlf : Nombre del alfabeto a modificar
     * @param lettersAlf : Letras que contiene el alfabeto
     * @return String[] : Información del alfabeto modificado, expresada tal y como 
     * se especifica en la funcion .toStringArray() de la clase Alfabeto
    */
    public String[] modifyAlfabeto(String nameAlf, String lettersAlf) {
        ctrlD.modificarAlfabeto(nameAlf, lettersAlf);
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    /** 
     * Modifica y guarda un texto existente en la aplicación
     * @param nameTexto : Nombre del texto a modificar
     * @param texto : El texto que se quiere guardar
     * @return String[] : Información del texto modificado, expresada tal y como se 
     * especifica en la funcion .toStringArray() de la clase Texto
    */
    public String[] modifyTexto(String nameTexto, String texto) {
        ctrlD.modificarTexto(nameTexto, texto);
        return ctrlD.consultarTexto(nameTexto);
    }

    /** 
     * Modifica y guarda una lista de palabras existente en la aplicación
     * @param nameLista : Nombre de la lista a modificar
     * @param lista : Lista de palabra-frecuencia, donde lista.key representa la 
     * palabra y lista.value la frecuencia asociada a esta
     * @return String[] : Información sobre la lista de palabras, expresada tal y 
     * como se especifica en la funcion .toStringArray() de la clase ListaPalabras
    */
    public String[] modifyLista(String nameLista, Map<String, Integer> lista) {
        ctrlD.modificarListaPalabras(nameLista, lista);
        return ctrlD.consultarLista(nameLista);
    }

    /** 
     * Elimina un teclado guardado en la aplicación
     * @param nameTec : Nombre del telcado a eliminar
    */
    public void deleteTeclado(String nameTec) {
        ctrlD.borrarTeclado(nameTec);
    }

    /** 
     * Elimina un alfabeto guardado en la aplicación
     * @param nameAlf : Nombre del alfabeto a eliminar
    */
    public void deleteAlfabeto(String nameAlf) {
        ctrlD.borrarAlfabeto(nameAlf);
    }

    /** 
     * Elimina un texto guardado en la aplicación
     * @param nameTec : Nombre del texto a eliminar
    */
    public void deleteTLP(String nameTLP) {
        ctrlD.borrarTLP(nameTLP);
    }



    public void enableVTeclados() {
        VistaTeclados vtec = new VistaTeclados(mp);
    }

    public void enableVAlfabetos() {
        VistaAlfabetos va = new VistaAlfabetos(mp);
    }

    public void enableVTLP() {
        VistaTLP vtlp = new VistaTLP(mp);
    }

    /** 
     * Habilita la vista de excepciones para que se de a conocer de que ha ocurrido
     * un error
     * @param nombreExcepcion : Nombre de la excepción que ha ocurrido
     * @param textoExcepcion : Texto explicando la propia excepción
    */
    public void saltaExcepcion(String nameExcepcion, String textoExcepcion) { //String nombreExcepcion, 
        JOptionPane.showMessageDialog(mp, textoExcepcion, nameExcepcion, JOptionPane.ERROR_MESSAGE);
    }
}
