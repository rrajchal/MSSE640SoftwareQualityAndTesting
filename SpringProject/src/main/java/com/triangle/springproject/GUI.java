package com.triangle.springproject; /**
 * Graphical User Interface for user input and result
 * It does not draw triangle yet
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, resultLabel;
    private JTextField textField1, textField2, textField3;
    private JButton checkButton;
    private JPanel drawingPanel;
    private double sideA, sideB, sideC;

    public GUI() {
        setTitle("Triangle Type Validator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label1 = new JLabel("Side 1:");
        label1.setBounds(50, 50, 100, 30);
        add(label1);
        textField1 = new JTextField();
        textField1.setBounds(150, 50, 100, 30);
        add(textField1);

        label2 = new JLabel("Side 2:");
        label2.setBounds(50, 100, 100, 30);
        add(label2);
        textField2 = new JTextField();
        textField2.setBounds(150, 100, 100, 30);
        add(textField2);

        label3 = new JLabel("Side 3:");
        label3.setBounds(50, 150, 100, 30);
        add(label3);
        textField3 = new JTextField();
        textField3.setBounds(150, 150, 100, 30);
        add(textField3);

        checkButton = new JButton("Check Triangle Type");
        checkButton.setBounds(125, 200, 150, 30);
        checkButton.addActionListener(this);
        add(checkButton);

        resultLabel = new JLabel("Your Result will be here", SwingConstants.CENTER);
        resultLabel.setBounds(125, 250, 150, 30);
        add(resultLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            sideA = Double.parseDouble(textField1.getText());
            sideB = Double.parseDouble(textField2.getText());
            sideC = Double.parseDouble(textField3.getText());

            Triangle triangle = new Triangle(sideA, sideB, sideC);
            TriangleType type = triangle.getTriangleType();
            resultLabel.setText(type.toString());

        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input.");
        }

    }
}
