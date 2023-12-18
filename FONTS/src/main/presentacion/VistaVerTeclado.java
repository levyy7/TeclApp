package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;

public class VistaVerTeclado extends JDialog{
    private JPanel general;
    private JLabel nombre;
    private JLabel algoritmo;
    private JLabel alfabeto;

    private JButton bok;
    //supongo que hacemos un ctrPresentacion.getTeclado(String name) que nos devuelve el teclado a visualizar o que nos pasa todos los parametros
    public VistaVerTeclado(JFrame owner, String name, String algo, String alfa){
        super(owner, name, true);
        setLocationRelativeTo(owner);
        setSize(300,300);

        general = new JPanel(new BorderLayout());
        JPanel info = new JPanel(new GridLayout(3, 1));
        nombre = new JLabel(name);
        algoritmo = new JLabel(algo);
        alfabeto = new JLabel(alfa);
        info.add(nombre);
        info.add(algoritmo);
        info.add(alfabeto);

        general.add(info, BorderLayout.CENTER);
        JPanel sur = new JPanel(new FlowLayout());
        sur.add(Box.createRigidArea(new Dimension(250, 30)));
        bok = new JButton("OK");
        sur.add(bok);


        setLocationRelativeTo(owner);
        getContentPane().add(general);
    }
}
