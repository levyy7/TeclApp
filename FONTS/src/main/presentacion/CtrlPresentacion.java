package main.presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Map;

import main.dominio.CtrlDominio;
import main.presentacion.*;

public class CtrlPresentacion {
    
    private static final CtrlPresentacion INSTANCE = new CtrlPresentacion();

    private static CtrlDominio ctrlD;


    private CtrlPresentacion() {
        ctrlD = CtrlDominio.getInstance();
        enableVTeclados();
    }

    public static CtrlPresentacion getInstance() {
        return INSTANCE;
    }



    public static String[][] getAllTeclados() {
        return ctrlD.consultarInfoTeclados();
    }

    public static String[][] getAllAlfabetos() {
        return ctrlD.consultarAlfabetos();
    }

    public static String[][] getAllTLP() {
        String[][] textos = ctrlD.consultarTextos();
        String[][] listas = ctrlD.consultarListas();

        String[][] res = Arrays.copyOf(textos, textos.length + listas.length);
        System.arraycopy(listas, 0, res, textos.length, listas.length);

        return res;
    }

    public static String[][] importTeclados(String path) {
        return ctrlD.importarTeclados(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public static String[][] importAlfabetos(String path) {
        return ctrlD.importarAlfabetos(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public static String[][] importTextos(String path) {
        return ctrlD.importarTextos(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public static String[][] importListas(String path) {
        return ctrlD.importarListas(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public static String[] createTeclado(String nameTec, String nameAlf, String[] nameTLP, String nameAlg) {
        ctrlD.crearTeclado(nameTec, nameAlf, nameTLP, nameAlg);
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    public static String[] createAlfabeto(String nameAlf, String lettersAlf) {
        ctrlD.crearAlfabeto(nameAlf, lettersAlf);
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    public static String[] createTexto(String nameTexto, String texto) {
        ctrlD.crearTexto(nameTexto, texto);
        return ctrlD.consultarTexto(nameTexto);
    }

    public static String[] createLista(String nameLista, Map<String, Integer> lista) {
        ctrlD.crearListaPalabras(nameLista, lista);
        return ctrlD.consultarLista(nameLista);
    }

    public static String[] modifyTeclado(String nameTec, String nameAlf, String[] nameTLP, String nameAlg) {
        ctrlD.modificarTeclado(nameTec, nameAlf, nameTLP, nameAlg);
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    public static String[] modifyAlfabeto(String nameAlf, String lettersAlf) {
        ctrlD.modificarAlfabeto(nameAlf, lettersAlf);
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    public static String[] modifyTexto(String nameTexto, String texto) {
        ctrlD.modificarTexto(nameTexto, texto);
        return ctrlD.consultarTexto(nameTexto);
    }

    public static String[] modifyLista(String nameLista, Map<String, Integer> lista) {
        ctrlD.modificarListaPalabras(nameLista, lista);
        return ctrlD.consultarLista(nameLista);
    }

    public static void deleteTeclado(String nameTec) {
        ctrlD.borrarTeclado(nameTec);
    }

    public static void deleteAlfabeto(String nameAlf) {
        ctrlD.borrarAlfabeto(nameAlf);
    }

    public static void deleteTexto(String nameTexto) {
        ctrlD.borrarTexto(nameTexto);
    }

    public static void deleteLista(String nameLista) {
         ctrlD.borrarTexto(nameLista);
    }

    public static void enableVTeclados() {
        VistaTeclados = new VistaTeclados();
    }

    public static void enableVAlfabetos() {
        VistaAlfabetos = new VistaAlfabetos();
    }

    public static void enableVTLP() {
        VistaTLP = new VistaTLP();
    }

    public static void enableVCrearTeclado() {
        VistaCrearTeclado = new VistaCrearTeclado();
    }

    public static void enableVCrearAlfabeto() {
        VistaCrearAlfabeto = new VistaCrearAlfabeto();
    }

    public static void enableVCrearTexto() {
        VistaCrearTexto = new VistaCrearTexto();
    }

    public static void enableVCrearLista() {
        VistaCrearLista = new VistaCrearLista();
    }

    public static void enableVVerTeclado(String nombreTeclado) {
        char[][] layout = ctrlD.consultarLayoutTeclado(nombreTeclado);
        VistaVerTeclado = new VistaVerTeclado(nombreTeclado, layout);
    }

    public static void enableVExcepcion(String nombreExcepcion, String textoExcepcion) {
        VistaExcepcion = new VistaExcepcion(nombreExcepcion, textoExcepcion);
    }
}
