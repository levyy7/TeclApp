package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
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

    JTextArea tlp;

    JButton bimporttext;
    JButton bimportlist;

    JButton bct;
    JButton bcl;

    JButton bit;
    JButton bil;

    JButton bcancel;
    JButton bcreate;

    public VistaCrearTeclado(JFrame owner, String title, String name) {
        super(owner, title, true);
        setLocationRelativeTo(owner);
        setSize(670, 350);

        general = new JPanel(new BorderLayout());
    
        // Sección Norte (Arriba)
        JPanel panelNorte = new JPanel(new FlowLayout());
        nombre = new JLabel("Nombre: ");
        nameT = new JTextField(name);
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
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel info = new JLabel("Introduce nombres de textos y/o listas de palabras con frecuencia:");
        tlp = new JTextArea();
        tlp.setPreferredSize(new Dimension(75, 1000));
        tlp.setLineWrap(true);
        tlp.setWrapStyleWord(true);
        JScrollPane st = new JScrollPane(tlp);


        //panelCentro.add(Box.createRigidArea(new Dimension(20,0)));

        bit = new JButton("Enviar");
        bil = new JButton("Enviar");

        bimporttext = new JButton("Importar Texto");
        bimportlist = new JButton("Importar Lista");

        bct = new JButton("Crear Texto");
        bcl = new JButton("Crear Lista");

        panelCentro.add(info, BorderLayout.NORTH);
        JPanel aux = new JPanel();
        aux.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        aux.add(Box.createRigidArea(new Dimension(5, 5)));
        aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
        aux.add(bct);
        aux.add(Box.createRigidArea(new Dimension(10, 10)));
        aux.add(bimporttext);
        panelCentro.add(aux, BorderLayout.WEST);

        aux = new JPanel();
        aux.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        aux.add(Box.createRigidArea(new Dimension(5, 5)));
        aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
        aux.add(bcl);
        aux.add(Box.createRigidArea(new Dimension(10, 10)));
        aux.add(bimportlist);
        panelCentro.add(aux, BorderLayout.EAST);

        panelCentro.add(st, BorderLayout.CENTER);

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

        JDialog act = this;
        ActionListener crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //recoger toda la informacion de la pantalla y pasarla al controlador de presentacion
                String[] nameTLP = tlp.getText().split("\n");
                //ctrlPres.createTeclado(nameT.getText(), nameA.getText(), nameTLP, selecAlgo.getSelectedItem())
                dispose();
            }
        };

        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        };

        ActionListener crearTexto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVCrearTexto(this)
            }
        };

        ActionListener crearLista = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVCrearLista(this)
            }
        };

        ActionListener importarTexto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser imp = new JFileChooser();
                int result = imp.showOpenDialog(act);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    //ctrlPres.importTextos(selected.getAbsolutePath());
                }
            }
        };

        ActionListener importarLista = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser imp = new JFileChooser();
                int result = imp.showOpenDialog(act);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    //ctrlPres.importListas(selected.getAbsolutePath());
                }
            }
        };

        bcreate.addActionListener(crear);
        bcancel.addActionListener(cancelar);
        bct.addActionListener(crearTexto);
        bcl.addActionListener(crearLista);
        bimporttext.addActionListener(importarTexto);
        bimportlist.addActionListener(importarLista);

        //los botones enviar recogen la info y la guardan es strings
        //boton crear -> recoge todo y crea el teclado
        //boton cancelar -> cierra la ventana
    }
}
