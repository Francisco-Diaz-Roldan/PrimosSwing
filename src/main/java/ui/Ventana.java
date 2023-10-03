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
    private JList list1;
    private DefaultListModel<Integer> datos;

    public Ventana(){
        this.setContentPane(Main);
        this.pack();
        this.setTitle("Calculadora de Patataprimos");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrar();

        datos = new DefaultListModel<>();
        list1.setModel(datos);

        calcularButton.addActionListener((ActionEvent e)-> rellenarPrimos());
    }

    private void rellenarPrimos() {
        Integer numero1 = Integer.valueOf(txtIni.getText());
        Integer numero2 = Integer.valueOf(txtFin.getText());

        ArrayList<Integer> numeros = PrimeCalculator.inRange(numero1, numero2);

        datos.clear();
        datos.addAll(numeros);
    }

    public void mostrar(){
        this.setVisible(true);
    }
}
