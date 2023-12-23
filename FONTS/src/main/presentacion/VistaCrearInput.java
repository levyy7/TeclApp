package main.presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.File;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



import javax.swing.*;


/**
 * VistaCrearInput es una clase que extiende JDialog para crear una interfaz gráfica de usuario
 * para crear un input.
 * @author Pol Ribera Moreno
 */
public class VistaCrearInput extends JDialog {
	JPanel general;
    JLabel nombre;
    JTextField nameA;
    JTextArea valorA;
    JButton bcancel;
    JButton bcreate;

    String closeReason;
    
    
    public String getCloseReason() {
        return closeReason;
    }

     /**
     * Coge el nombre y el texto del área de texto, eliminando cualquier carácter de nueva línea.
     * 
     * @return String[] Los datos de recogidos.
     */
    public String[] getData(){
        String[] Vinput= new String[2];
        Vinput[0] = nameA.getText();
        Vinput[1] = valorA.getText().replaceAll("[\n\r]", "");
        return Vinput;
    }

    /**
     * Constructor para VistaCrearInput. 
     * 
     */
    public VistaCrearInput(JFrame owner){
        super(owner, "Creadora Inputs", true);
        //super();
        setLocationRelativeTo(owner);
        setSize(350, 200);
        setResizable(false);
        general = new JPanel(new BorderLayout());
        closeReason = "Closed by user";

        JPanel panelNorte = new JPanel(new FlowLayout());
        nombre = new JLabel("Nombre: ");
        nameA = new JTextField();
        nameA.setPreferredSize(new Dimension(100, 20));
        panelNorte.add(nombre);
        panelNorte.add(nameA);
        general.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new FlowLayout());
        nombre = new JLabel("Contenido: ");
        valorA = new JTextArea(5, 20);
        panelCentral.add(nombre);
        panelCentral.add(valorA);
        general.add(panelCentral, BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bcancel = new JButton("Cancelar");
        bcreate = new JButton("Crear");
        panelSur.add(bcancel);
        panelSur.add(bcreate);
        general.add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        getContentPane().add(general);


        JDialog act = this;
        ActionListener crear = new ActionListener() {
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


        bcreate.addActionListener(crear);
        bcancel.addActionListener(cancelar);
        setVisible(true);
    }

}
//clase make by Pol
