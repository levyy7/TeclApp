package main.presentacion;

import java.awt.*;
import javax.swing.*;

/**
 * La clase App representa la aplicación principal que 
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
     * Constructor de la clase App.
     * Crea una instancia de la aplicación, establece el título y
     * agrega la vista de teclados al contenido del JFrame.
     */
    public FrameMenuPrincipal(){
        super("Creadora de teclados");
        inicializar();

        vt = new VistaTeclados(this);
        getContentPane().add(vt);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameMenuPrincipal());
    }
}
