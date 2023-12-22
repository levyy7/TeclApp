package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

import javax.swing.*;

/**
 * VistaModificarInput es una clase que extiende JDialog para crear una interfaz gráfica de usuario
 * para modificar un input.
 * @author Pol Ribera Moreno
 */

public class VistaModificarInput extends JDialog {
	private JPanel general;
    private JPanel centre;
    private JPanel cont;
    private JLabel title;
    private JList input;
    private JLabel nombre;
    private JTextArea valorI;
    private JButton bcancel;
    private JButton bmodificar;

    String closeReason;
    
    
    public String getCloseReason() {
        return closeReason;
    }

	private void inicializar() {
        setSize(300, 200);
        setVisible(true);
        setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        general = new JPanel(new BorderLayout());
        centre = new JPanel();
    }
    /**
     * Coge el texto del área de texto, eliminando cualquier carácter de nueva línea.
     * 
     * @return String Los datos de entrada modificados.
     */
    public String getData(){
        return valorI.getText().replaceAll("[\n\r]", "");
    }

     /**
     * Constructor para VistaModificarInput. 
     * 
     * @param nom El nombre del input que se mostrará en el diálogo.
     * @param in El texto del input que se quiere modificar.
     */
    public VistaModificarInput(String nom, String in){
        super();
        inicializar();
        char[] a = in.toCharArray();
        closeReason = "Closed by user";

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
            resultado += a[i];
            j += 1;
			if(j > 20){
                j = 0;
                resultado += "\n";
            }
		}

        JPanel panelCentral = new JPanel(new FlowLayout());
        nombre = new JLabel("Input: ");
        valorI = new JTextArea(5, 20);
        valorI.setText(resultado);
        panelCentral.add(nombre);
        panelCentral.add(valorI);

        general.add(panelCentral, BorderLayout.CENTER);


        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bcancel = new JButton("Cancelar");
        bmodificar = new JButton("Modificar");
        panelSur.add(bcancel);
        panelSur.add(bmodificar);
        general.add(panelSur, BorderLayout.SOUTH);

        ActionListener modificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                closeReason = "Operation Finished";
                getData();
                dispose();
            }
        };

        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                closeReason = "Closed by user";
                dispose();
            }
        };

        setLocationRelativeTo(null);
        getContentPane().add(general);

        bmodificar.addActionListener(modificar);
        bcancel.addActionListener(cancelar);
    }

}
//clase make by Pol
