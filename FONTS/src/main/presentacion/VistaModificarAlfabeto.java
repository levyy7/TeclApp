package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

import javax.swing.*;


public class VistaModificarAlfabeto extends JDialog {
	private JPanel general;
    private JPanel centre;
    private JPanel cont;
    private JLabel title;
    private JList alfabeto;
    private JLabel nombre;
    private JTextArea valorA;
    private JButton bcancel;
    private JButton bcreate;

	private void inicializar() {
        setSize(300, 200);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        general = new JPanel(new BorderLayout());
        centre = new JPanel();
    }


    public VistaModificarAlfabeto(String nom, String[] a){
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

		String resultado = "";
        int j = 0;
		for (int i = 0; i < a.length; i += 1) {
            resultado += a[i]+", ";
            j += 1;
			if(j > 9){
                j = 0;
                resultado += "\n";
            }
		}

        JPanel panelCentral = new JPanel(new FlowLayout());
        nombre = new JLabel("Alfabeto: ");
        valorA = new JTextArea(5, 20);
        valorA.setText(resultado);
        panelCentral.add(nombre);
        panelCentral.add(valorA);

        general.add(panelCentral, BorderLayout.CENTER);


        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bcancel = new JButton("Cancelar");
        bcreate = new JButton("Modificar");
        panelSur.add(bcancel);
        panelSur.add(bcreate);
        general.add(panelSur, BorderLayout.SOUTH);



        setLocationRelativeTo(null);
        getContentPane().add(general);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaModificarAlfabeto(args[0], args));
    }
}