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
public class VistaTeclados extends JPanel {

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

    private CtrlPresentacion cp;

    /**
     * Inicializa los componentes.
     */
    private void inicializar() {
        general = new JPanel(new BorderLayout());
        cp = CtrlPresentacion.getInstance();
       
        listat = new DefaultListModel<String>();
        String[][] allTec = cp.getAllTeclados();
        for(String[] s : allTec) {
            listat.addElement(s[0]);
        }
        teclados = new JList<String>(listat);
        
        /*
         * listat = new DefaultListModel<String>();
         * listat.addAll(CtrlPresentacion.getInstance().getAllTeclados());
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
    public VistaTeclados(JFrame padre){
        super();
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
                if(barrabusq == new JTextField("")) barrabusq.setText("Introduce el nombre del teclado");
            }
        };

        barrabusq.addFocusListener(borrarTexto);

        //FUNCIONALIDADES DE LOS BOTONES

        JPanel act = this;
        
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
        ActionListener crearteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                VistaCMTeclado vct = new VistaCMTeclado(padre, "Crear Teclado", "");
                vct.setVisible(true);
                String[] info = vct.getData();
                cp.createTeclado(info[0], info[1], info[2].split("\n"), info[3]);
                listat.addElement(info[0]);
            }
        };

        ActionListener modificarteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaCMTeclado vct = new VistaCMTeclado(padre, "Modificar Teclado", teclados.getSelectedValue());
                String[] info = vct.getData();
                String[] n = cp.createTeclado(info[0], info[1], info[2].split("\n"), info[3]);
                for(int i = 0; i<listat.size(); ++i)
                    if(!buscarElemento(n[0])) listat.addElement(n[0]);
            }
        };

        ActionListener borrarteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Boolean res = areyousure(teclados.getSelectedValue());
                if(res) {
                    listat.removeElement(teclados.getSelectedValue());
                    JOptionPane.showMessageDialog(padre,
                            "Teclado borrado correctamente.",
                            "Borrado Exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(padre,
                            "Operación de borrado cancelada.",
                            "Cancelado",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

        ActionListener goAlfabetos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                padre.getContentPane().add(new VistaAlfabetos(padre));
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

        ActionListener importTecl = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                imp = new JFileChooser();
                int result = imp.showOpenDialog(padre);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selected = imp.getSelectedFile();
                    String[][] tt = cp.importTeclados(selected.getAbsolutePath());
                    for(int i = 0; i< tt.length; ++i) {
                        listat.addElement(tt[i][0]);
                    }
                }
                
            }
        };

        ActionListener consultarteclado = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String[] info = cp.getInfoTeclado(teclados.getSelectedValue());
                char[][] layout = cp.getLayoutTeclado(teclados.getSelectedValue());
                VistaVerTeclado vct = new VistaVerTeclado(padre, info[0], info[1], info[2], layout);
                vct.setVisible(true);
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
}