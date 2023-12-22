package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

import javax.swing.*;


public class VistaVerInput extends JDialog {
	private JPanel general;
    private JPanel centre;
    private JPanel cont;
    private JLabel title;
    private JList input;

	private void inicializar() {
        setSize(300, 300);
        setVisible(true);
        setResizable(false);
        general = new JPanel(new BorderLayout());
        centre = new JPanel();
    }


    public VistaVerInput(String nom, String in){
        super();
        inicializar();

        char[] a = in.toCharArray();
		title = new JLabel(nom); 
		Font font = new Font("Arial", Font.PLAIN, 24);
        title.setFont(font);
        centre.add(title);
    	general.add(centre, BorderLayout.NORTH);
    	
    	cont = new JPanel(new BorderLayout());
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.EAST);
        cont.add(Box.createRigidArea(new Dimension(50, 50)), BorderLayout.WEST);

		int size_a = (int) Math.ceil(a.length/10) + 1;
		String[] resultado = new String[size_a];
		for (int i = 0; i < resultado.length; i += 1) {
			String particion = "";
			for (int j = 0; j < 10; j += 1) {
				if (i*10+j < a.length){
					particion += a[i*10+j];
					if(i*10+j == a.length-1){
						particion += ".";
					} else{
						particion += ", ";
					}
				}
			}
			resultado[i] = particion;
		}
        input = new JList<String>(resultado);
        input.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane laminaDesplazamiento = new JScrollPane(input);
        cont.add(laminaDesplazamiento, BorderLayout.CENTER);

        general.add(cont, BorderLayout.CENTER);


        general.add(Box.createRigidArea(new Dimension(10,10)), BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        getContentPane().add(general);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaVerInput(args[0], args[1]));
    }
}
//clase make by Pol
