package main.presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VistaMenuPrincipal extends JFrame {

    private JPanel main;
    private JPanel menu;
    private JLabel title;
    private JButton teclados;
    private JButton alfabetos;
    private JButton tlp;
    private JButton exit = new JButton("Salir");

    private void inicializar() {
        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        main = new JPanel(new BorderLayout());
        title = new JLabel("Creadora de teclados");
        menu = new JPanel();
    }

    private JButton iniButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
        return button;
    }

    public VistaMenuPrincipal() {
        super("Creadora de teclados");
        inicializar();

        JPanel north = new JPanel();
        Font font = new Font("Arial", Font.PLAIN, 24);
        title.setFont(font);
        north.add(title);
        main.add(north, BorderLayout.NORTH);

        menu.setLayout(new GridLayout(5, 1, 20, 15));

        menu.add(Box.createRigidArea(new Dimension(0, 0)));
        teclados = iniButton("Teclados", menu);
        alfabetos = iniButton("Alfabetos", menu);
        tlp = iniButton("Textos y Listas de Palabras", menu);
        menu.add(Box.createRigidArea(new Dimension(0, 15)));

        main.add(Box.createRigidArea(new Dimension(80,0)), BorderLayout.WEST);
        main.add(Box.createRigidArea(new Dimension(80,0)), BorderLayout.EAST);
        main.add(menu, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.add(Box.createRigidArea(new Dimension(200, 0)), BorderLayout.WEST);
        south.add(exit, BorderLayout.EAST);
        main.add(south, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        getContentPane().add(main);

        
        ActionListener sortir = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        
        exit.addActionListener(sortir);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaMenuPrincipal());
    }
}
