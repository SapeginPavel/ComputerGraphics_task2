package ru.vsu.csf.sapegin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelParametersOfFigure extends JPanel {
    private JTextField x1Field = new JTextField();
    private JTextField y1Field = new JTextField();
    private JTextField x2Field = new JTextField();
    private JTextField y2Field = new JTextField();
    private JTextField x3Field = new JTextField();
    private JTextField y3Field = new JTextField();
    private JTextField x4Field = new JTextField();
    private JTextField y4Field = new JTextField();

    private JLabel x1 = new JLabel("x1 ");
    private JLabel y1 = new JLabel("y1 ");
    private JLabel x2 = new JLabel("x2 ");
    private JLabel y2 = new JLabel("y2 ");
    private JLabel x3 = new JLabel("x3 ");
    private JLabel y3 = new JLabel("y3 ");

    private JLabel heightOfRectangle = new JLabel("height ");
    private JLabel widthOfRectangle = new JLabel("width ");

    private JTextField heightOfRectangleField = new JTextField();
    private JTextField widthOfRectangleField = new JTextField();

    private boolean isRectangle = false;
    private double[][] matrixOfCoordinates;

    private static final Dimension DEFAULT_DIMENSION = new Dimension(55, 20);

    public PanelParametersOfFigure displayFieldsForTriangle() {
        isRectangle = false;
        preparePanel();

        List<JComponent> components = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            components.add(new JLabel(""));
        }

        components.add(x1);
        components.add(x1Field);
        components.add(x2);
        components.add(x2Field);
        components.add(x3);
        components.add(x3Field);

        components.add(y1);
        components.add(y1Field);
        components.add(y2);
        components.add(y2Field);
        components.add(y3);
        components.add(y3Field);

        addComponentsToPanelFromList(components);
        return this;
    }

    public PanelParametersOfFigure displayFieldsForRectangle() {
        isRectangle = true;
        preparePanel();

        List<JComponent> components = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            components.add(new JLabel(""));
        }

        components.add(x1);
        components.add(x1Field);
        components.add(y1);
        components.add(y1Field);
        components.add(new JLabel(""));
        components.add(heightOfRectangle);
        components.add(heightOfRectangleField);
        components.add(widthOfRectangle);
        components.add(widthOfRectangleField);
        addComponentsToPanelFromList(components);
        return this;
    }

    private void preparePanel() {
        this.removeAll();
        x1Field.setText("");
        x2Field.setText("");
        x3Field.setText("");
        y1Field.setText("");
        y2Field.setText("");
        y3Field.setText("");
        heightOfRectangleField.setText("");
        widthOfRectangleField.setText("");

        heightOfRectangle.setHorizontalAlignment(JTextField.RIGHT);
        widthOfRectangle.setHorizontalAlignment(JTextField.RIGHT);
        x1.setHorizontalAlignment(JTextField.RIGHT);
        x2.setHorizontalAlignment(JTextField.RIGHT);
        x3.setHorizontalAlignment(JTextField.RIGHT);
        y1.setHorizontalAlignment(JTextField.RIGHT);
        y2.setHorizontalAlignment(JTextField.RIGHT);
        y3.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void addComponentsToPanelFromList(List<JComponent> components) {
        for (JComponent comp : components) {
            comp.setMinimumSize(DEFAULT_DIMENSION);
            comp.setMaximumSize(DEFAULT_DIMENSION);
            add(comp);
        }
    }

    public double[][] getMatrixOfCoordinates() {
        createMatrixOfCoordinates();
        return matrixOfCoordinates;
    }

    private void createMatrixOfCoordinates() {
        matrixOfCoordinates = convertArrayCoordinatesToMatrix(readCoordinatesFromPanel());
//        System.out.println("___________");
//        for (int i = 0; i < matrixOfCoordinates.length; i++) {
//            System.out.println(Arrays.toString(matrixOfCoordinates[i]));
//        }
    }

    private double[][] convertArrayCoordinatesToMatrix(double[] coordinates) {
        double[][] matrix = new double[coordinates.length / 2][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = coordinates[2 * i + j];
            }
            matrix[i][2] = 1;
        }


        return matrix;
    }

    private double[] readCoordinatesFromPanel() {
        double[] coordinates;
            if (isRectangle) {
                coordinates = new double[8];
                double x = Double.parseDouble(x1Field.getText());
                double y = Double.parseDouble(y1Field.getText());
                coordinates[0] = x;
                coordinates[1] = y;
                double h = Double.parseDouble(heightOfRectangleField.getText());
                double w = Double.parseDouble(widthOfRectangleField.getText());
                coordinates[2] = x + w;
                coordinates[3] = y;
                coordinates[4] = x + w;
                coordinates[5] = y - h;
                coordinates[6] = x;
                coordinates[7] = y - h;
            } else {
                coordinates = new double[6];
                coordinates[0] = Double.parseDouble(x1Field.getText());
                coordinates[1] = Double.parseDouble(y1Field.getText());
                coordinates[2] = Double.parseDouble(x2Field.getText());
                coordinates[3] = Double.parseDouble(y2Field.getText());
                coordinates[4] = Double.parseDouble(x3Field.getText());
                coordinates[5] = Double.parseDouble(y3Field.getText());
        }
        return coordinates;
    }
}
