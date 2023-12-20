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
 * La clase VistaTeclados representa la interfaz gráfica de usuario para la gestión de teclados.
 * @author Mariona Aguilera Folqué
 */
public class VistaTeclados extends JFrame {

    private JPanel general;
    private JPanel menuSup;
    private JPanel cont;
    private JPanel func;

    private JButton bteclados;
    private JButton balfabetos;
    private JButton btlp;

    private JButton bmodificar;
    private JButton bcrear;
    private JButton bimportar;
    private JFileChooser imp;
    private JButton bconsultarT;
    private JButton bborrar;

    private JTextField barrabusq;
    private  DefaultListModel<String> listat;
    private JList<String> teclados;

    /**
     * Inicializa la interfaz gráfica y sus componentes.
     */
    private void inicializar() {
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        general = new JPanel(new BorderLayout());
        
        listat = new DefaultListModel<String>();
        for(int i = 0; i<15; ++i) {
            listat.addElement(String.valueOf(i));
        }
        teclados = new JList<String>(listat);

        /*
         * listat = new DefaultListModel<String>();
         * listat.addAll(ctrlPres.getAllTeclados());
         * teclados = new JList<String>(listat);
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
     * Actualiza la lista de teclados en la interfaz gráfica.
     */
    private void actualizarlistat(){
        //listat.removeAllElements();
        //listat.addAll(ctrlPres.getAllTeclados());
    }

    /**
     * Muestra un cuadro de diálogo para confirmar una operación de borrado.
     *
     * @param name Nombre del teclado a borrar.
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
     * Filtra la lista de teclados según el texto de búsqueda.
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
        teclados.setModel(listaFiltrada);
    }

    /**
     * Constructor de la clase VistaTeclados.
    */
    public VistaTeclados(){
        super("Creadora de Teclados");
        inicializar();

        //MENU SUPERIOR
        menuSup = new JPanel(new GridLayout(1, 3));
        bteclados = iniButton("Teclados", menuSup);
        //bteclados.setBackground(new Color(204, 204, 204));
        bteclados.setEnabled(false);
        balfabetos = iniButton("Alfabetos", menuSup);
        btlp = iniButton("Textos y Listas de palabras", menuSup);

        general.add(menuSup, BorderLayout.NORTH);

        //FUNCIONALIDADES
        func = new JPanel(new BorderLayout());
        func.add(Box.createRigidArea(new Dimension(35, 35)), BorderLayout.NORTH);
        func.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.EAST);
        func.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);
        
        JPanel botfuncion = new JPanel(new GridLayout(7, 1, 5, 10));
        bcrear = iniButton("Crear", botfuncion);
        bimportar = iniButton("Importar", botfuncion);
        botfuncion.add(Box.createRigidArea(new Dimension()));
        bmodificar = iniButton("Modificar", botfuncion);
        bconsultarT = iniButton("Consultar", botfuncion);
        bborrar = iniButton("Borrar", botfuncion);

        
        bmodificar.setEnabled(false);
        bconsultarT.setEnabled(false);
        bborrar.setEnabled(false);

        func.add(botfuncion, BorderLayout.CENTER);
        general.add(func, BorderLayout.EAST);

        //LISTA DE TECLADOS
        cont = new JPanel(new BorderLayout());
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.WEST);

        //BARRA DE BUSQUEDA
        JPanel busqueda = new JPanel(new FlowLayout());
        barrabusq = new JTextField("Introduce el nombre del teclado");
        busqueda.add(barrabusq);
        cont.add(busqueda, BorderLayout.NORTH);
        
        //LISTA
        teclados.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane laminaDesplazamiento = new JScrollPane(teclados);
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
                if(barrabusq == new JTextField("")) barrabusq.setText("Introduce el nombre del teclado");
            }
        };

        barrabusq.addFocusListener(borrarTexto);

        //FUNCIONALIDADES DE LOS BOTONES
        
        ListSelectionListener clicarElemento = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (teclados.isSelectionEmpty()) {
                    bmodificar.setEnabled(false);
                    bconsultarT.setEnabled(false);
                    bborrar.setEnabled(false);
                } 
                else {
                    bmodificar.setEnabled(true);
                    bconsultarT.setEnabled(true);
                    bborrar.setEnabled(true);
                }
            }
        }; 

        JFrame act = this;
        ActionListener crearteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                //supongo que se tiene que hacer desde el controlador
                VistaCrearTeclado vct = new VistaCrearTeclado(act, "Crear Teclado", "");
                vct.setVisible(true);
                actualizarlistat();
            }
        };

        ActionListener modificarteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaCrearTeclado vct = new VistaCrearTeclado(act, "Modificar Teclado", teclados.getSelectedValue());
                vct.setVisible(true);
                actualizarlistat();
            }
        };

        ActionListener borrarteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Boolean res = areyousure(teclados.getSelectedValue());
                if(res) {
                    //ctrPres.deleteTeclado(teclados.getSelectedValue());
                    JOptionPane.showMessageDialog(act,
                            "Teclado borrado correctamente.",
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

        ActionListener goAlfabetos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVAlfabetos();
            }
        };

        ActionListener goTLP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVTLP();
            }
        };

        ActionListener importTecl = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                imp = new JFileChooser();
                int result = imp.showOpenDialog(act);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    //ctrlPres.importTeclado(selected.getAbsolutePath());
                }
                actualizarlistat();
            }
        };

        ActionListener consultarteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ctrlPres.enableVerTeclado(teclados.getSelectedValue());
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
    

        teclados.addListSelectionListener(clicarElemento);
        bcrear.addActionListener(crearteclado);
        balfabetos.addActionListener(goAlfabetos);
        btlp.addActionListener(goTLP);
        bimportar.addActionListener(importTecl);
        bmodificar.addActionListener(modificarteclado);
        bborrar.addActionListener(borrarteclado);
        barrabusq.getDocument().addDocumentListener(find);
        bconsultarT.addActionListener(consultarteclado);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaTeclados());
    }
}