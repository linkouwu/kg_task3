package ru.vsu.cs.course2_Lyubchenko_kg.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame{
    private JPanel panel1;
    private JPanel graphPanel;
    private JTextField formulaTextField;
    private JButton drawFunctionButton;
    private JButton drawBezierButton;
    private JButton clearButton;
    private JTextField parTextField;

    public Window(){
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        drawFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formula = formulaTextField.getText();
                Double par = Double.parseDouble(parTextField.getText());
                // мне лень
            }
        });
    }

    private void createUIComponents() {
        graphPanel = new GraphPanel();
    }
}
