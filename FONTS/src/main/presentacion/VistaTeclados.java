package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;


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
    private JButton bconsultarT;
    private JButton bconsultarLay;
    private JButton bborrar;

    private JButton bbuscar;
    private JList teclados;


    private void inicializar() {
        setSize(700, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        general = new JPanel(new BorderLayout());

        //LOS TECLADOS SE GUARDAN EN CSV? SE TENDRA QUE HACER UN DOCUMENT LISTENER
        String[] t = {"p1", "p2", "p3", "p4", "p5", "p1", "p2", "p3", "p4", "p5", "p1", "p2", "p3", "p4", "p5", "p1", "p2", "p3", "p4", "p5"};
        teclados = new JList<String>(t);
    }

    private JButton iniButton(String text, Container container) {
        JButton button = new JButton(text);
        //button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        return button;
    }

    public VistaTeclados(){
        super("Creadora de Teclados");
        inicializar();

        //MENU SUPERIOR
        menuSup = new JPanel(new GridLayout(1, 3));
        bteclados = iniButton("Teclados", menuSup);
        bteclados.setBackground(new Color(204, 204, 204));
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
        botfuncion.add(Box.createRigidArea(new Dimension()));
        bmodificar = iniButton("Modificar", botfuncion);
        bconsultarT = iniButton("Consultar", botfuncion);
        bconsultarLay = iniButton("Ver Layout", botfuncion);
        bborrar = iniButton("Borrar", botfuncion);

        bmodificar.setVisible(false);
        bconsultarT.setVisible(false);
        bconsultarLay.setVisible(false);
        bborrar.setVisible(false);

        func.add(botfuncion, BorderLayout.CENTER);
        general.add(func, BorderLayout.EAST);

        //LISTA DE TECLADOS
        cont = new JPanel(new BorderLayout());
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.WEST);

        //BARRA DE BUSQUEDA
        JPanel busqueda = new JPanel(new FlowLayout());
        JTextField barrabusq = new JTextField("Introduce el nombre del teclado");
        busqueda.add(barrabusq);
        bbuscar = iniButton("Buscar", busqueda);
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
                // Mostrar todos los botones
                bmodificar.setVisible(true);
                bconsultarT.setVisible(true);
                bconsultarLay.setVisible(true);
                bborrar.setVisible(true);
            }
        }; 
    
        teclados.addListSelectionListener(clicarElemento);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaTeclados());
    }
}