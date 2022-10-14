package ru.vsu.csf.sapegin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelTransformations extends JPanel {
    private final JTextField scaleField = new JTextField();
    private final JTextField rotationField = new JTextField();
    private final JTextField moveXField = new JTextField();
    private final JTextField moveYField = new JTextField();
    private final JTextField newCenterXField = new JTextField();
    private final JTextField newCenterYField = new JTextField();
    private final JLabel newCenterX = new JLabel("x");
    private final JLabel newCenterY = new JLabel("y");
    private final JLabel scaleLabel = new JLabel("Масштаб");
    private final JLabel rotationLabel = new JLabel("Вращать");
    private final JLabel moveXLabel = new JLabel("Двигать по X");
    private final JLabel moveYLabel = new JLabel("Двигать по Y");
    private final JButton buttonApplyScale = new JButton("Ок");
    private final JButton buttonApplyRotation = new JButton("Ок");
    private final JButton buttonApplyMoveX = new JButton("Ок");
    private final JButton buttonApplyMoveY = new JButton("Ок");
    private final JButton buttonApplyAll = new JButton("Ок всё");

    public PanelTransformations(Window window) {
        setLayout(new GridLayout(9, 4));
        setMaximumSize(new Dimension(250, 300));
        createPanelOfTransformations();

        buttonApplyScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(newCenterXField.getText());
                double y = Double.parseDouble(newCenterYField.getText());
                double coefficientOfScaling = Math.abs(Double.parseDouble(scaleField.getText()));
                double[][] matrix = AffineTransformations.scaling(window.getDrawPanel().getMatrixOfFigure(), x, y, coefficientOfScaling);
                window.getDrawPanel().draw(matrix);
            }
        });

        buttonApplyRotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(newCenterXField.getText());
                double y = Double.parseDouble(newCenterYField.getText());
                double angle = Double.parseDouble(rotationField.getText());
                double[][] matrix = AffineTransformations.rotate(window.getDrawPanel().getMatrixOfFigure(), x, y, angle);
                window.getDrawPanel().draw(matrix);
            }
        });

        buttonApplyMoveX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double dx = Double.parseDouble(moveXField.getText());
                double[][] matrix = AffineTransformations.moveX(window.getDrawPanel().getMatrixOfFigure(), dx);
                window.getDrawPanel().draw(matrix);
            }
        });

        buttonApplyMoveY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double dy = Double.parseDouble(moveYField.getText());
                double[][] matrix = AffineTransformations.moveY(window.getDrawPanel().getMatrixOfFigure(), dy);
                window.getDrawPanel().draw(matrix);
            }
        });

        buttonApplyAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(newCenterXField.getText()); //в теории можно дописать, что если поле пустое, то 0 ставим тоже
                double y = Double.parseDouble(newCenterYField.getText());
                double coefficientOfScaling = Objects.equals(scaleField.getText(), "") ? 1 : Math.abs(Double.parseDouble(scaleField.getText()));
                double angle = Objects.equals(rotationField.getText(), "") ? 0 : Double.parseDouble(rotationField.getText());
                double dx = Objects.equals(moveXField.getText(), "") ? 0 : Double.parseDouble(moveXField.getText());
                double dy = Objects.equals(moveYField.getText(), "") ? 0 : Double.parseDouble(moveYField.getText());
                double[][] matrix = window.getDrawPanel().getMatrixOfFigure();
                if (coefficientOfScaling != 1) {
                    matrix = AffineTransformations.scaling(matrix, x, y, coefficientOfScaling); //window.getDrawPanel().getMatrixOfFigure()
                }
                if (angle != 0) {
                    matrix = AffineTransformations.rotate(matrix, x, y, angle);
                }
                if (dx != 0) {
                    matrix = AffineTransformations.moveX(matrix, dx);
                }
                if (dy != 0) {
                    matrix = AffineTransformations.moveY(matrix, dy);
                }
                window.getDrawPanel().draw(matrix);
            }
        });
    }

    private void createPanelOfTransformations() {
        //надо бы всё очищать и удалять панель при смене фигуры
//        newCenterXField.setText("");
//        newCenterYField.setText("");
//        scaleField.setText("");
//        rotationField.setText("");
//        moveXField.setText("");
//        moveYField.setText("");
        JLabel textPoint = new JLabel("Точка");
        textPoint.setHorizontalAlignment(JLabel.RIGHT);
        add(textPoint);
        add(new JLabel(" вращения"));
        add(new JLabel(""));
        add(newCenterX);
        add(newCenterXField);
        add(new JLabel(""));
        add(newCenterY);
        add(newCenterYField);
        add(new JLabel(""));
        add(scaleLabel);
        add(scaleField);
        add(buttonApplyScale);
        add(rotationLabel);
        add(rotationField);
        add(buttonApplyRotation);
        add(moveXLabel);
        add(moveXField);
        add(buttonApplyMoveX);
        add(moveYLabel);
        add(moveYField);
        add(buttonApplyMoveY);
        add(new JLabel(""));
        add(new JLabel(""));
        add(buttonApplyAll);
        textPoint.setHorizontalAlignment(JLabel.RIGHT);
        newCenterX.setHorizontalAlignment(JLabel.CENTER);
        newCenterY.setHorizontalAlignment(JLabel.CENTER);
        scaleLabel.setHorizontalAlignment(JLabel.CENTER);
        rotationLabel.setHorizontalAlignment(JLabel.CENTER);
        moveXLabel.setHorizontalAlignment(JLabel.CENTER);
        moveYLabel.setHorizontalAlignment(JLabel.CENTER);
        newCenterXField.setText("0");
        newCenterYField.setText("0");
    }
}
