package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import java.io.File;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.presentacion.*;


/**
 * La clase VistaTLP representa la interfaz gráfica de usuario para la gestión de textos y listas de palabras.
 * @author Mariona Aguilera Folqué
 */
public class VistaTLP extends JFrame {

    private JPanel general;
    private JPanel menuSup;
    private JPanel cont;
    private JPanel func;

    private JButton bteclados;
    private JButton balfabetos;
    private JButton btlp;

    private JButton bmodificar;
    private JButton bcreart;
    private JButton bcrearl;
    private JButton bimportart;
    private JButton bimportarl;
    private JFileChooser imp;
    private JButton bconsultarTLP;
    private JButton bborrar;

    private JTextField barrabusq;
    private  DefaultListModel<String> listat;
    private JList<String> tlps;

    /**
     * Inicializa la interfaz gráfica y sus componentes.
     */
    private void inicializar() {
        setSize(700, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        general = new JPanel(new BorderLayout());
        
        listat = new DefaultListModel<String>();
        for(int i = 0; i<15; ++i) {
            listat.addElement(String.valueOf(i));
        }
        tlps = new JList<String>(listat);

        /*
         * listat = new DefaultListModel<String>();
         * listat.addAll(ctrlPres.getAllTLP());
         * tlps = new JList<String>(listat);
         */
    }

    /**
     * Crea un botón con el texto especificado y lo agrega al contenedor.
     *
     * @param text      Texto del botón.
     * @param container Contenedor al que se agregará el botón.
     * @return JButton creado.
     */
    private JButton iniButton(String text, Container container) {
        JButton button = new JButton(text);
        //button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        return button;
    }

    /**
     * Actualiza la lista de textos y listas de palabras en la interfaz gráfica.
     */
    private void actualizarlistat(){
        //listat.removeAllElements();
        //listat.addAll(ctrlPres.getALLTLP());
    }

    /**
     * Muestra un cuadro de diálogo para confirmar una operación de borrado.
     *
     * @param name Nombre del texto o lista a borrar.
     * @return true si se confirma el borrado, false si se cancela.
     */
    private Boolean areyousure(String name){
        int confirmacion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Estás seguro de que quieres borrar " + name + "?",
                        "Confirmar Borrado",
                        JOptionPane.YES_NO_OPTION);

        return (confirmacion == JOptionPane.YES_OPTION);
    }

    /**
     * Filtra la lista de textos y listas según el texto de búsqueda.
     */
    private void buscando(){
        String textoBusqueda = barrabusq.getText().toLowerCase();
        DefaultListModel<String> listaFiltrada = new DefaultListModel<>();
    
        // Filtrar y actualizar la lista
        for (int i = 0; i < listat.getSize(); i++) {
            String dato = listat.getElementAt(i);
            if (dato.toLowerCase().contains(textoBusqueda)) {
                listaFiltrada.addElement(dato);
            }
        }
    
        // Establecer la lista filtrada como el nuevo modelo de la JList
        tlps.setModel(listaFiltrada);
    }

    /**
     * Constructor de la clase VistaTLP.
    */
    public VistaTLP(){
        super("Creadora de Teclados");
        inicializar();

        //MENU SUPERIOR
        menuSup = new JPanel(new GridLayout(1, 3));
        bteclados = iniButton("Teclados", menuSup);
        balfabetos = iniButton("Alfabetos", menuSup);
        btlp = iniButton("Textos y Listas de palabras", menuSup);
        btlp.setEnabled(false);

        general.add(menuSup, BorderLayout.NORTH);

        //FUNCIONALIDADES
        func = new JPanel(new BorderLayout());
        func.add(Box.createRigidArea(new Dimension(35, 35)), BorderLayout.NORTH);
        func.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.EAST);
        func.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);
        
        JPanel botfuncion = new JPanel(new GridLayout(9, 1, 5, 10));
        bcreart = iniButton("Crear Texto", botfuncion);
        bcrearl = iniButton("Crear Lista", botfuncion);
        bimportart = iniButton("Importar Texto", botfuncion);
        bimportarl = iniButton("Importar Lista", botfuncion);
        botfuncion.add(Box.createRigidArea(new Dimension()));
        bmodificar = iniButton("Modificar", botfuncion);
        bconsultarTLP = iniButton("Consultar", botfuncion);
        bborrar = iniButton("Borrar", botfuncion);

        
        bmodificar.setEnabled(false);
        bconsultarTLP.setEnabled(false);
        bborrar.setEnabled(false);

        func.add(botfuncion, BorderLayout.CENTER);
        general.add(func, BorderLayout.EAST);

        //LISTA DE TLP
        cont = new JPanel(new BorderLayout());
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.WEST);

        //BARRA DE BUSQUEDA
        JPanel busqueda = new JPanel(new FlowLayout());
        barrabusq = new JTextField("Introduce el nombre del texto o lista");
        busqueda.add(barrabusq);
        cont.add(busqueda, BorderLayout.NORTH);
        
        //LISTA
        tlps.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane laminaDesplazamiento = new JScrollPane(tlps);
        cont.add(laminaDesplazamiento, BorderLayout.CENTER);

        general.add(cont, BorderLayout.CENTER);

        //PARTE INFERIOR
        general.add(Box.createRigidArea(new Dimension(10,10)), BorderLayout.SOUTH);

        
        setLocationRelativeTo(null);
        getContentPane().add(general);

        //FUNCIONALIDADES ESTETICAS
        FocusListener borrarTexto = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Al obtener el foco, limpiar el texto
                barrabusq.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(barrabusq == new JTextField("")) barrabusq.setText("Introduce el nombre del alfabeto");
            }
        };

        barrabusq.addFocusListener(borrarTexto);

        //FUNCIONALIDADES DE LOS BOTONES
        
        ListSelectionListener clicarElemento = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (tlps.isSelectionEmpty()) {
                    bmodificar.setEnabled(false);
                    bconsultarTLP.setEnabled(false);
                    bborrar.setEnabled(false);
                } 
                else {
                    bmodificar.setEnabled(true);
                    bconsultarTLP.setEnabled(true);
                    bborrar.setEnabled(true);
                }
            }
        }; 

        JFrame act = this;
        ActionListener crearT = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //llamar a creartexto 
                actualizarlistat();
            }
        };

        ActionListener crearL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            //llamar a crear lista
            actualizarlistat();
            }
        };


        ActionListener modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //llamar a modificar 
                actualizarlistat();
            }
        };

        ActionListener borrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Boolean res = areyousure(tlps.getSelectedValue());
                if(res) {
                    //ctrPres.deleteTLP(tlps.getSelectedValue());
                    JOptionPane.showMessageDialog(act,
                            "Texto o lista borrado correctamente.",
                            "Borrado Exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(act,
                            "Operación de borrado cancelada.",
                            "Cancelado",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                actualizarlistat();
            }
        };

        ActionListener goTeclados = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVTeclados();
            }
        };

        ActionListener goAlfabetos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVAlfabeto();
            }
        };

        ActionListener importT = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                imp = new JFileChooser();
                int result = imp.showOpenDialog(act);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    //ctrlPres.importTextos(selected.getAbsolutePath());
                }
                actualizarlistat();
            }
        };

        ActionListener importL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                imp = new JFileChooser();
                int result = imp.showOpenDialog(act);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    //ctrlPres.importListas(selected.getAbsolutePath());
                }
                actualizarlistat();
            }
        };

        ActionListener consultar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVerTLP(tlps.getSelectedValue());
            }
        };

        DocumentListener find = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscando();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 buscando();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 buscando();
            }
        };
    

        tlps.addListSelectionListener(clicarElemento);
        bcreart.addActionListener(crearT);
        bcrearl.addActionListener(crearL);
        bteclados.addActionListener(goTeclados);
        balfabetos.addActionListener(goAlfabetos);
        bimportart.addActionListener(importT);
        bimportarl.addActionListener(importL);
        bmodificar.addActionListener(modificar);
        bborrar.addActionListener(borrar);
        barrabusq.getDocument().addDocumentListener(find);
        bconsultarTLP.addActionListener(consultar);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaTLP());
    }
}