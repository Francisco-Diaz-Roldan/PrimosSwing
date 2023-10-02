package ui;

import primos.PrimeCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ventana extends JFrame{
    private JPanel Main;
    private JTextField txtInit;
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

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> numeros = PrimeCalculator.inRange(1, 1000);
                datos.addAll(numeros);
            }
        });
    }

    public void mostrar(){
        this.setVisible(true);
    }
}
