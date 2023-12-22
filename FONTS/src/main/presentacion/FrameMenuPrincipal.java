package main.presentacion;

import java.awt.*;
import javax.swing.*;

/**
 * La clase FrameMenuPrincipal representa el menú principal del programa que 
 * actúa como un contenedor JFrame para la creación de teclados.
 * @author Mariona Aguilera Folqué.
 */
public class FrameMenuPrincipal extends JFrame {
    VistaTeclados vt;
    VistaAlfabetos va;
    VistaTLP vl;

    /**
     * Inicializa la configuración del JFrame.
     */
    private void inicializar(){
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    /**
     * Constructora de la clase FrameMenuPrincipal.
     * Inicializa el frame del menú principal con el objetivo de que contenga los
     * JPanels principales del programa.
     */
    public FrameMenuPrincipal(){
        super("Creadora de teclados");
        inicializar();

        vt = new VistaTeclados(this);
        getContentPane().add(vt);
    }

    /*    
    public static void main(String[] args) {
        CtrlPresentacion ctrlP = CtrlPresentacion.getInstance();
        ctrlP.inicializar();
        SwingUtilities.invokeLater(() -> new FrameMenuPrincipal());
    }
    */
    
}
