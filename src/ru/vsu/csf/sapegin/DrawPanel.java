package ru.vsu.csf.sapegin;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private int[] xCoordinates;
    private int[] yCoordinates;

    private static final int X_CENTER = 640;
    private static final int Y_CENTER = 440;

    boolean isFigureExist = false;
    private double[][] matrixOfFigure = null;

    public void draw(double[][] matrix) {
        xCoordinates = new int[matrix.length];
        yCoordinates = new int[matrix.length];

        matrixOfFigure = matrix;
        double[][] convertMatrix = convertToThisCoordinateSystem(matrix); //будет ли вообще существовать исходная матрица

        for (int i = 0; i < xCoordinates.length; i++) {
            xCoordinates[i] = (int) convertMatrix[i][0];
            yCoordinates[i] = (int) convertMatrix[i][1];
        }
        isFigureExist = true;
        repaint();
    }

    public double[][] getMatrixOfFigure() {
        return matrixOfFigure;
    }

    private double[][] convertToThisCoordinateSystem(double[][] matrix) {
        return AffineTransformations.offset(AffineTransformations.reflectionX(matrix), X_CENTER, Y_CENTER);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.BLACK);
        graphics.drawLine(640, 0, 640, 1000); //vertical
        graphics.drawLine(0, 440, 2000, 440); //horizontal
        // (0;0) → (640, 440)

        for (int i = 0; i < 40; i++) {
            graphics.drawLine(637, i * 40, 643, i * 40);
            graphics.drawLine(i * 40, 437, i * 40, 443);
        }

        if (isFigureExist) {
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawPolygon(xCoordinates, yCoordinates, xCoordinates.length);
            isFigureExist = false;
        }
    }
}
