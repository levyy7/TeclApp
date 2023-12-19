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



    public String[][] getAllTeclados() {
        return ctrlD.consultarInfoTeclados();
    }

    public String[][] getAllAlfabetos() {
        return ctrlD.consultarAlfabetos();
    }

    public String[][] getAllTLP() {
        String[][] textos = ctrlD.consultarTextos();
        String[][] listas = ctrlD.consultarListas();

        String[][] res = Arrays.copyOf(textos, textos.length + listas.length);
        System.arraycopy(listas, 0, res, textos.length, listas.length);

        return res;
    }

    public String[][] importTeclados(String path) {
        return ctrlD.importarTeclados(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public String[][] importAlfabetos(String path) {
        return ctrlD.importarAlfabetos(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public String[][] importTextos(String path) {
        return ctrlD.importarTextos(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public String[][] importListas(String path) {
        return ctrlD.importarListasPalabras(path);
        //return ctrlD.consultarInfoTeclado() ME TIENE QUE DEVOLVER String[][] EL CTRLDOMINIO
    }

    public String[] createTeclado(String nameTec, String nameAlf, String[] nameTLP, String nameAlg) {
        ctrlD.crearTeclado(nameTec, nameAlf, nameTLP, nameAlg);
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    public String[] createAlfabeto(String nameAlf, String lettersAlf) {
        ctrlD.crearAlfabeto(nameAlf, lettersAlf);
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    public String[] createTexto(String nameTexto, String texto) {
        ctrlD.crearTexto(nameTexto, texto);
        return ctrlD.consultarTexto(nameTexto);
    }

    public String[] createLista(String nameLista, Map<String, Integer> lista) {
        ctrlD.crearListaPalabras(nameLista, lista);
        return ctrlD.consultarLista(nameLista);
    }

    public String[] modifyTeclado(String nameTec, String nameAlf, String[] nameTLP, String nameAlg) {
        ctrlD.modificarTeclado(nameTec, nameAlf, nameTLP, nameAlg);
        return ctrlD.consultarInfoTeclado(nameTec);
    }

    public String[] modifyAlfabeto(String nameAlf, String lettersAlf) {
        ctrlD.modificarAlfabeto(nameAlf, lettersAlf);
        return ctrlD.consultarAlfabeto(nameAlf);
    }

    public String[] modifyTexto(String nameTexto, String texto) {
        ctrlD.modificarTexto(nameTexto, texto);
        return ctrlD.consultarTexto(nameTexto);
    }

    public String[] modifyLista(String nameLista, Map<String, Integer> lista) {
        ctrlD.modificarListaPalabras(nameLista, lista);
        return ctrlD.consultarLista(nameLista);
    }

    public void deleteTeclado(String nameTec) {
        ctrlD.borrarTeclado(nameTec);
    }

    public void deleteAlfabeto(String nameAlf) {
        ctrlD.borrarAlfabeto(nameAlf);
    }

    public void deleteTexto(String nameTexto) {
        ctrlD.borrarTexto(nameTexto);
    }

    public void deleteLista(String nameLista) {
         ctrlD.borrarTexto(nameLista);
    }

    public void enableVTeclados() {
        VistaTeclados = new VistaTeclados();
    }

    public void enableVAlfabetos() {
        VistaAlfabetos = new VistaAlfabetos();
    }

    public void enableVTLP() {
        VistaTLP = new VistaTLP();
    }

    public void enableVCrearTeclado() {
        VistaCrearTeclado = new VistaCrearTeclado();
    }

    public void enableVCrearAlfabeto() {
        VistaCrearAlfabeto = new VistaCrearAlfabeto();
    }

    public void enableVCrearTexto() {
        VistaCrearTexto = new VistaCrearTexto();
    }

    public void enableVCrearLista() {
        VistaCrearLista = new VistaCrearLista();
    }

    public void enableVVerTeclado(String nombreTeclado) {
        char[][] layout = ctrlD.consultarLayoutTeclado(nombreTeclado);
        VistaVerTeclado = new VistaVerTeclado(nombreTeclado, layout);
    }

    public void enableVExcepcion(String nombreExcepcion, String textoExcepcion) {
        VistaExcepcion = new VistaExcepcion(nombreExcepcion, textoExcepcion);
    }
}
