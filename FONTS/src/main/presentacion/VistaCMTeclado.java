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

/**
 * La clase VistaCMTeclado representa la interfaz gráfica de usuario para la creación y modificación de teclados.
 * @author Mariona Aguilera Folqué
 */
public class VistaCMTeclado extends JDialog {

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
    JButton bconfirm;

    /**
 * Obtiene los datos ingresados en la interfaz gráfica para la creación de un teclado.
 *
 * @return Un array de Strings que contiene los datos ingresados:
 *         - En la posición 0: el nombre del teclado obtenido del campo de texto nameT.
 *         - En la posición 1: el nombre del alfabeto obtenido del campo de texto nameA.
 *         - En la posición 2: el texto largo predictivo obtenido del campo de texto tlp.
 *         - En la posición 3: la opción seleccionada en el JComboBox selecAlgo.
 */
    public String[] getData(){
        String[] ret = {nameT.getText(), nameA.getText(), tlp.getText(), (String)selecAlgo.getSelectedItem()};
        return ret;
    }

    /**
     * Constructor de la clase VistaCMTeclado.
     *
     * @param owner Ventana principal propietaria de este diálogo.
     * @param title Título del diálogo.
     * @param name Nombre inicial para el teclado.
     */
    public VistaCMTeclado(JFrame owner, String title, String name) {
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
        bconfirm = new JButton("Confirmar");
        panelSur.add(bcancel);
        panelSur.add(bconfirm);
        general.add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(owner);
        getContentPane().add(general);

        JDialog act = this;

        /**
         * ActionListener para el botón "Crear", recoge la información y crea el teclado.
         */
        ActionListener crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //recoger toda la informacion de la pantalla y pasarla al controlador de presentacion
                String[] nameTLP = tlp.getText().split("\n");
                //ctrlPres.createTeclado(nameT.getText(), nameA.getText(), nameTLP, selecAlgo.getSelectedItem())
                getData();
                dispose();
            }
        };
        /**
         * ActionListener para el botón "Cancelar", cierra la ventana sin realizar ninguna acción.
         */
        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        };

        /**
         * ActionListener para el botón "Crear Texto", habilita la interfaz para la creación de texto.
         */
        ActionListener crearTexto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVCrearTexto(this)
            }
        };

        /**
         * ActionListener para el botón "Crear Lista", habilita la interfaz para la creación de lista.
         */
        ActionListener crearLista = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVCrearLista(this)
            }
        };

        /**
         * ActionListener para el botón "Importar Texto", permite al usuario seleccionar un archivo de texto para importar.
         */
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

        /**
         * ActionListener para el botón "Importar Lista", permite al usuario seleccionar un archivo de lista para importar.
         */
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

        bconfirm.addActionListener(crear);
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
