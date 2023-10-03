package ui;

import primos.PrimeCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Ventana extends JFrame{
    private JPanel Main;
    private JTextField txtIni;
    private JTextField txtFin;
    private JButton calcularButton;
    private JList lista;
    private JSpinner spinnerIni;
    private JSpinner spinnerFin;
    private DefaultListModel<String> datos;


    public Ventana(){
        this.setContentPane(Main);
        this.pack();
        this.setTitle("Calculadora de Patataprimos");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrar();

        spinnerIni.setModel(new SpinnerNumberModel(1,1,1000,1));
        spinnerFin.setModel(new SpinnerNumberModel(3,3,1000,1));

        datos = new DefaultListModel<>();
        lista.setModel(datos);

        calcularButton.addActionListener((ActionEvent e)-> rellenarPrimos2());
    }

    private void rellenarPrimos2(){
        Integer numero1 = (Integer) spinnerIni.getValue();
        Integer numero2 = (Integer) spinnerFin.getValue();

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
