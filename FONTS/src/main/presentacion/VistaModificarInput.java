package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

import javax.swing.*;


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

	private void inicializar() {
        setSize(300, 200);
        setVisible(true);
        setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        general = new JPanel(new BorderLayout());
        centre = new JPanel();
    }
    public String[] getData(){
        String[] Vinput;
        Vinput[0] = nom;
        Vinput[1] = valorI.getText().replaceAll("[\n\r]", "");
        return Vinput;
    }

    public VistaModificarInput(String nom, String[] a){
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
                getData();
                dispose();
            }
        };

        ActionListener cancelar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        };

        setLocationRelativeTo(null);
        getContentPane().add(general);

        bmodificar.addActionListener(modificar);
        bcancel.addActionListener(cancelar);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaModificarInput(args[0], args));
    }
}
