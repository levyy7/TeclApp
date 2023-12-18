package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

public class VistaCrearTeclado extends JDialog {

    JPanel general;
    JLabel nombre;
    JTextField nameT;

    JLabel algoritmo;
    JComboBox<String> selecAlgo;

    JLabel alfabeto;
    JTextField nameA;

    JTextArea textos;
    JTextArea listas;

    JButton bit;
    JButton bil;

    JButton bcancel;
    JButton bcreate;

    public VistaCrearTeclado(JFrame owner, String title) {
        super(owner, title, true);
        setLocationRelativeTo(owner);
        setSize(670, 350);

        general = new JPanel(new BorderLayout());
    
        // Sección Norte (Arriba)
        JPanel panelNorte = new JPanel(new FlowLayout());
        nombre = new JLabel("Nombre: ");
        nameT = new JTextField();
        nameT.setPreferredSize(new Dimension(100, 20));
        panelNorte.add(nombre);
        panelNorte.add(nameT);
        general.add(panelNorte, BorderLayout.NORTH);

        algoritmo = new JLabel("Algoritmo: ");
        selecAlgo = new JComboBox<>(new String[]{"QAP", "Algoritmo"});
        panelNorte.add(algoritmo);
        panelNorte.add(selecAlgo);

        alfabeto = new JLabel("Alfabeto: ");
        nameA = new JTextField();
        nameA.setPreferredSize(new Dimension(100, 20));
        panelNorte.add(alfabeto);
        panelNorte.add(nameA);


        // Sección CENTRO 
        JPanel panelCentro = new JPanel(new GridLayout(3, 2, 5, 5));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel t = new JLabel("Introduce textos:");
        textos = new JTextArea();
        textos.setPreferredSize(new Dimension(100, 1000));
        textos.setLineWrap(true);
        textos.setWrapStyleWord(true);
        JScrollPane st = new JScrollPane(textos);

        JLabel l = new JLabel("Introduce listas de palabras con frecuencia:");
        listas = new JTextArea();
        listas.setPreferredSize(new Dimension(100, 1000));
        listas.setLineWrap(true);
        listas.setWrapStyleWord(true);
        JScrollPane sl = new JScrollPane(listas);

        //panelCentro.add(Box.createRigidArea(new Dimension(20,0)));

        bit = new JButton("Enviar");
        bil = new JButton("Enviar");

        panelCentro.add(t);
        panelCentro.add(l);
        panelCentro.add(st);
        panelCentro.add(sl);

        JPanel aux = new JPanel(new BorderLayout());
        aux.add(Box.createRigidArea(new Dimension(200, 20)), BorderLayout.CENTER);
        aux.add(Box.createRigidArea(new Dimension(20, 50)), BorderLayout.SOUTH);
        aux.add(bit, BorderLayout.EAST);

        panelCentro.add(aux);

        aux = new JPanel(new BorderLayout());
        aux.add(Box.createRigidArea(new Dimension(200, 20)), BorderLayout.CENTER);
        aux.add(Box.createRigidArea(new Dimension(20, 50)), BorderLayout.SOUTH);
        aux.add(bil, BorderLayout.EAST);

        panelCentro.add(aux);

        general.add(panelCentro, BorderLayout.CENTER);

        //seccion SUR
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //panelSur.add(Box.createRigidArea(new Dimension(400, 20)), BorderLayout.WEST);
        bcancel = new JButton("Cancelar");
        bcreate = new JButton("Crear");
        panelSur.add(bcancel);
        panelSur.add(bcreate);
        general.add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(owner);
        getContentPane().add(general);




        //los botones enviar recogen la info y la guardan es strings
        //boton crear -> recoge todo y crea el teclado
        //boton cancelar -> cierra la ventana
    }
}
