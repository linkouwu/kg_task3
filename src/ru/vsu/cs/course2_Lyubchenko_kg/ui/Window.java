package ru.vsu.cs.course2_Lyubchenko_kg.ui;

import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.RealPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame{
    private JPanel panel1;
    private JPanel graphPanel;
    private JTextField formulaTextField;
    private JButton drawFunctionButton;
    private JButton drawBezierButton;
    private JButton clearFunctionButton;
    private JTabbedPane tabbedPane1;
    private JTextField bezierTextField;
    private JButton clearBezierButton;
    private JTextArea bezierTextArea;

    public Window(){
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        drawFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formula = formulaTextField.getText();
                GraphPanel.setBezierPoint(null);
                GraphPanel.setFunction(formula);
            }
        });
        clearFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphPanel.setFunction(null);
            }
        });
        drawBezierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<RealPoint> pointList = convertStringToList(bezierTextArea.getText());
                GraphPanel.setFunction(null);
                GraphPanel.setBezierPoint(pointList);
            }
        });
        clearBezierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphPanel.setBezierPoint(null);
            }
        });
    }

    private ArrayList<RealPoint> convertStringToList(String s){
        String[] lineList = s.split("\n");
        ArrayList<RealPoint> pointArray= new ArrayList<>();
        for (String line: lineList) {
            try {
                if (!s.equals("")) {
                    String[] arr = line.split("[\\s,]");
                    pointArray.add(new RealPoint(Integer.parseInt(arr[0]), Integer.parseInt(arr[arr.length-1])));
                }
            } catch (Exception ignored) {
            }
        }
        return pointArray;
    }

    private void createUIComponents() {
        graphPanel = new GraphPanel();
    }
}
