package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

/**
 * La clase VistaVerTeclado permite al usuario poder visualizar el teclado creado,
 * así como consultar otras características de este
 * @author Eneko Sabaté Iturgaiz
 */
public class VistaVerTeclado extends JDialog{
    private JPanel general;
    private JPanel gLayout;
    private JPanel info;
    private JLabel nombre;
    private JLabel algoritmo;
    private JLabel alfabeto;
    private JButton bok;
    

    /**
     * Constructora de la clase VistaVerTeclado
     * Hace display del layout, nombre, algoritmo y alfabeto del teclado pertinente.
     */
    public VistaVerTeclado(JFrame owner, String name, String algo, String alfa, char[][] layout){
        super(owner, name, true);
        setLocationRelativeTo(owner);
        setSize(800, 800);
        setMinimumSize(new Dimension(400, 400));
        
        gLayout = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { 
                super.paintComponent(g);
                displayMatrix(g, layout);
            }
        };

        gLayout.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gLayout.repaint(); // Repaint the panel on resize
            }
        });

        
        info = new JPanel(new GridLayout(1, 3));
        nombre = new JLabel("Nombre: " + name);
        algoritmo = new JLabel("Algoritmo: " + algo);
        alfabeto = new JLabel("Alfabeto: " + alfa);
        info.add(nombre);
        info.add(algoritmo);
        info.add(alfabeto);

        general = new JPanel(new BorderLayout());
        general.add(gLayout, BorderLayout.CENTER);
        general.add(info, BorderLayout.PAGE_END);
        JPanel sur = new JPanel(new FlowLayout());
        sur.add(Box.createRigidArea(new Dimension(250, 30)));
        bok = new JButton("OK");
        sur.add(bok);


        setLocationRelativeTo(owner);
        getContentPane().add(general);
    }

    /**
     * Función auxiliar para construir el Graphics que se usará en el JPanel
     */
    private void displayMatrix(Graphics g, char[][] layout) {
        int rows = layout.length;
        int cols = layout[0].length;

        // Leave a margin of 10 pixels on each side
        int marginX = 20;
        int marginY = 20;

        int availableWidth = getWidth() - 2*marginX;
        int availableHeight = getHeight() - 2*marginY - getInsets().top;

        int cellWidth = availableWidth / cols;
        int cellHeight = availableHeight / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = layout[i][j];
                String valueStr = String.valueOf(c);
                int x = marginX + j * cellWidth;
                int y = marginY + i * cellHeight;

                g.drawRect(x, y, cellWidth, cellHeight);
                g.drawString(valueStr, x + cellWidth / 2, y + cellHeight / 2);
            }
        }
    }

    public static void main(String[] args) {
        FrameMenuPrincipal mp = new FrameMenuPrincipal();
        char[][] matrixData = {
            {'a', 'a', 'a'},
            {'a', 'a', 'a'},
            {'a', 'a', 'a'}
    };
        VistaVerTeclado vvt = new VistaVerTeclado(mp, "tec1", "QAP", "abcdefg", matrixData);
        vvt.setVisible(true);
        //SwingUtilities.invokeLater(() -> new 
        //    VistaVerTeclado(mp, "tec1", "QAP", "abcdefg"));
    }
}
