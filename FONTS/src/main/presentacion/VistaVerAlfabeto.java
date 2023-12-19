package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

import javax.swing.*;


public class VistaVerAlfabeto extends JDialog {
	private JPanel general;
    private JPanel centre;
    private JPanel cont;
    private JLabel title;
    private JList alfabeto;

	private void inicializar() {
        setSize(300, 300);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        general = new JPanel(new BorderLayout());
        centre = new JPanel();
    }


    public VistaVerAlfabeto(String nom, String[] a){
        super();
        inicializar();


		title = new JLabel(nom); 
		Font font = new Font("Arial", Font.PLAIN, 24);
        title.setFont(font);
        centre.add(title);
    	general.add(centre, BorderLayout.NORTH);
    	
    	cont = new JPanel(new BorderLayout());
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.WEST);

		//a = {"p1", "p2", "p3", "p4", "p5", "p1"};
		int size_a = (int) Math.ceil(a.length/10) + 1;
		String[] resultado = new String[size_a];
		for (int i = 0; i < resultado.length; i += 1) {
			String particion = "";
			for (int j = 0; j < 10; j += 1) {
				if (i*10+j< a.length){
					particion += a[i*10+j]+", ";
					resultado[i] = particion;
				}
			}
		}
        alfabeto = new JList<String>(resultado);
        alfabeto.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane laminaDesplazamiento = new JScrollPane(alfabeto);
        cont.add(laminaDesplazamiento, BorderLayout.CENTER);

        general.add(cont, BorderLayout.CENTER);


        general.add(Box.createRigidArea(new Dimension(10,10)), BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        getContentPane().add(general);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaVerAlfabeto(args[0], args));
    }
}
//clase make by Pol