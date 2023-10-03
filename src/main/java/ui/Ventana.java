package ui;

import primos.PrimeCalculator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ventana extends JFrame{
    private JPanel Main;
    private JTextField txtIni;
    private JTextField txtFin;
    private JButton calcularButton;
    private JList lista;
    private JSpinner spinnerIni;
    private JSpinner spinnerFin;
    private JLabel info;
    private JButton guardarButton;
    private DefaultListModel<String> datos;

    private ArrayList<Integer> primos = new ArrayList<>();

    public Ventana(){
        this.setContentPane(Main);
        this.pack();
        this.setTitle("Calculadora de primos");
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mostrar();

        spinnerIni.setModel(new SpinnerNumberModel(1,1,1000,1));
        spinnerFin.setModel(new SpinnerNumberModel(3,3,1000,1));

        datos = new DefaultListModel<>();
        lista.setModel(datos);

        calcularButton.addActionListener((ActionEvent e)-> rellenarPrimos2());
        lista.addListSelectionListener((ListSelectionEvent e) -> mostrarPrimo(e) );
        guardarButton.addActionListener(e -> guardarPrimos());
    }

    private void guardarPrimos() {
        System.out.println("Botón guardar");
        var dialogoGuardar = new JFileChooser();

       if( dialogoGuardar.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
           File f = dialogoGuardar.getSelectedFile();

           try(var bw = new BufferedWriter(new FileWriter(f))){
                for ( Integer primo : primos ) {
                    bw.write(primo+ ", ");
               }
                bw.newLine();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }

    }

    private void mostrarPrimo(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            System.out.println("Evento de lista ");
            String primo = (String) lista.getSelectedValue();
            //JOptionPane.showMessageDialog(null, "Número primo: "+primo ,"Información", JOptionPane.INFORMATION_MESSAGE);
            info.setText("Número primo: "+primo );
        }
    }

    private void rellenarPrimos2(){
        Integer numero1 = (Integer) spinnerIni.getValue();
        Integer numero2 = (Integer) spinnerFin.getValue();

        if(numero1>numero2){
            JOptionPane.showMessageDialog(null, "El segundo número debe ser mayor");
        } else{
            primos= PrimeCalculator.inRange(numero1, numero2);

            datos.clear();

            if(primos.isEmpty()) datos.addElement("No hay primos");

            for(Integer n : primos){
                datos.addElement(n.toString());
            }

            info.setText("Total: " + datos.size());

        }

    }

    private void rellenarPrimos() {

        Integer numero1 = null;
        Integer numero2 = null;

        try {
            numero1 = Integer.valueOf(txtIni.getText());
            numero2 = Integer.valueOf(txtFin.getText());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Introduce números");
        }


        if (numero1!=null && numero2!=null) {

            if(numero1>numero2){
                JOptionPane.showMessageDialog(null, "El segundo número debe ser mayor");
            } else{
                ArrayList<Integer> numeros = PrimeCalculator.inRange(numero1, numero2);

                datos.clear();

                if(numeros.isEmpty()) datos.addElement("No hay primos");

                for(Integer n : numeros){
                    datos.addElement(n.toString());
                }
            }
        }




    }

    public void mostrar(){
        this.setVisible(true);
    }
}