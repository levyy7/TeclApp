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



public class VistaCrearInput extends JDialog {
	JPanel general;
    JLabel nombre;
    JTextField nameA;
    JTextArea valorA;
    JButton bcancel;
    JButton bcreate;

    public String[] getData(){
        String[] Vinput;
        Vinput[0] = nameA.getText();
        Vinput[1] = valorA.getText().replaceAll("[\n\r]", "");
        return Vinput;
    }
    public VistaCrearInput(){
        super();//owner, title, true
        //setLocationRelativeTo(owner);
        setSize(350, 200);
        setVisible(true);
        setResizable(false);
        general = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout());
        nombre = new JLabel("Nombre: ");
        nameA = new JTextField();
        nameA.setPreferredSize(new Dimension(100, 20));
        panelNorte.add(nombre);
        panelNorte.add(nameA);
        general.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new FlowLayout());
        nombre = new JLabel("Alfabeto: ");
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


        bcreate.addActionListener(crear);
        bcancel.addActionListener(cancelar);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaCrearInput());
    }
}
//clase make by Pol
