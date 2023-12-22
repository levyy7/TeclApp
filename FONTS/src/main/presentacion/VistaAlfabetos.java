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
 * La clase VistaAlfabetos representa la interfaz gráfica de usuario para la gestión de alfabetos.
 * @author Mariona Aguilera Folqué
 */
public class VistaAlfabetos extends JPanel {

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
    private JButton bconsultarA;
    private JButton bborrar;

    private JTextField barrabusq;
    private  DefaultListModel<String> listat;
    private JList<String> alfabetos;

    /**
     * Inicializa los componentes.
     */
    private void inicializar() {
      
        general = new JPanel(new BorderLayout());
        
        listat = new DefaultListModel<String>();
        for(int i = 0; i<15; ++i) {
            listat.addElement(String.valueOf(i));
        }
        alfabetos = new JList<String>(listat);

        /*
         * listat = new DefaultListModel<String>();
         * listat.addAll(CtrlPresentacion.getInstance().getAllAlfabetos());
         * alfabetos = new JList<String>(listat);
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
     * Busqueda del elemento la lista de teclados en la interfaz gráfica.
     * @param elemento nombre del teclado a buscar.
     * @return elemento encontrado.
     */
    private Boolean buscarElemento(String elemento) {
        for (int i = 0; i < listat.size(); i++) {
            if (listat.getElementAt(i).equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra un cuadro de diálogo para confirmar una operación de borrado.
     *
     * @param name Nombre del alfabeto a borrar.
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
     * Filtra la lista de alfabetos según el texto de búsqueda.
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
        alfabetos.setModel(listaFiltrada);
    }


    /**
     * Constructor de la clase VistaAlfabetos.
    */
    public VistaAlfabetos(JFrame padre){
        super();
        inicializar();

        //MENU SUPERIOR
        menuSup = new JPanel(new GridLayout(1, 3));
        bteclados = iniButton("Teclados", menuSup);
        balfabetos = iniButton("Alfabetos", menuSup);
        balfabetos.setEnabled(false);
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
        bconsultarA = iniButton("Consultar", botfuncion);
        bborrar = iniButton("Borrar", botfuncion);

        
        bmodificar.setEnabled(false);
        bconsultarA.setEnabled(false);
        bborrar.setEnabled(false);

        func.add(botfuncion, BorderLayout.CENTER);
        general.add(func, BorderLayout.EAST);

        //LISTA DE ALFABETOS
        cont = new JPanel(new BorderLayout());
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.WEST);

        //BARRA DE BUSQUEDA
        JPanel busqueda = new JPanel(new FlowLayout());
        barrabusq = new JTextField("Introduce el nombre del alfabeto");
        busqueda.add(barrabusq);
        cont.add(busqueda, BorderLayout.NORTH);
        
        //LISTA
        alfabetos.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane laminaDesplazamiento = new JScrollPane(alfabetos);
        cont.add(laminaDesplazamiento, BorderLayout.CENTER);

        general.add(cont, BorderLayout.CENTER);

        //PARTE INFERIOR
        general.add(Box.createRigidArea(new Dimension(10,10)), BorderLayout.SOUTH);

        
        //setLocationRelativeTo(null);
        add(general);

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
                if (alfabetos.isSelectionEmpty()) {
                    bmodificar.setEnabled(false);
                    bconsultarA.setEnabled(false);
                    bborrar.setEnabled(false);
                } 
                else {
                    bmodificar.setEnabled(true);
                    bconsultarA.setEnabled(true);
                    bborrar.setEnabled(true);
                }
            }
        }; 

        JPanel act = this;
        CtrlPresentacion cp = CtrlPresentacion.getInstance();
        ActionListener crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                VistaCrearInput vca = new VistaCrearInput();
                vca.setVisible(true);
                String[] info = vca.getData();
                cp.createAlfabeto(info[0], info[1]);
                listat.addElement(info[0]);
            }
        };

        ActionListener modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] info = cp.getAlfabeto(alfabetos.getSelectedValue());
                VistaModificarInput vca = new VistaModificarInput(info[0], info[1]);
                String ret = vca.getData();
                String[] n = cp.modifyAlfabeto(info[0], ret);
                for(int i = 0; i<listat.size(); ++i)
                    if(!buscarElemento(n[0])) listat.addElement(n[0]);
            }
        };

        ActionListener borrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Boolean res = areyousure(alfabetos.getSelectedValue());
                if(res) {
                    listat.removeElement(alfabetos.getSelectedValue());
                    JOptionPane.showMessageDialog(act,
                            "Alfabeto borrado correctamente.",
                            "Borrado Exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(act,
                            "Operación de borrado cancelada.",
                            "Cancelado",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

        ActionListener goTeclados = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                padre.getContentPane().add(new VistaTeclados(padre));
                act.setVisible(false);
            }
        };

        ActionListener goTLP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                padre.getContentPane().add(new VistaTLP(padre));
                act.setVisible(false);
            }
        };

        ActionListener importAlf = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                imp = new JFileChooser();
                int result = imp.showOpenDialog(padre);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    String[][] tt = cp.importAlfabetos(selected.getAbsolutePath());
                    for(int i = 0; i< tt.length; ++i) {
                        listat.addElement(tt[i][0]);
                    }
                }
            }
        };

        ActionListener consultar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //verTlp????
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
    

        alfabetos.addListSelectionListener(clicarElemento);
        bcrear.addActionListener(crear);
        bteclados.addActionListener(goTeclados);
        btlp.addActionListener(goTLP);
        bimportar.addActionListener(importAlf);
        bmodificar.addActionListener(modificar);
        bborrar.addActionListener(borrar);
        barrabusq.getDocument().addDocumentListener(find);
        bconsultarA.addActionListener(consultar);
    }
}