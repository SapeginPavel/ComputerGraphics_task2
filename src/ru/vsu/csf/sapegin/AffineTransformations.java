package ru.vsu.csf.sapegin;

public class AffineTransformations {

    private static double xCenter = 640; //по-хорошему, надо центры как-то устанавливать
    private static double yCenter = 440;

    private static double[][] multiply(double[][] m1, double[][] m2) {
        double[][] res = new double[m1.length][m2[0].length];
        for(int i=0;i< m1.length;i++) {
            for (int u = 0; u < m2[0].length; u++) {
                for (int j = 0; j < m2.length; j++) {
                    res[i][u] += m1[i][j] * m2[j][u];
                }
            }
        }
        return res;
    }

    public static double[][] reflectionX(double[][] matrix) {
        double[][] matrixOfReflectionX = {{1, 0, 0}, {0, -1, 0}, {0, 0, 1}};
        return multiply(matrix, matrixOfReflectionX);
    }

    public static double[][] offset(double[][] matrix, double m, double n) {
        double[][] matrixOfOffset = {{1, 0, 0}, {0, 1, 0}, {m, n, 1}};
        return multiply(matrix, matrixOfOffset);
    }

    public static double[][] scaling(double[][] matrix, double x, double y, double coefficientOfScaling) {
        matrix = setNewCenterOfCoordinates(matrix, -x, -y);
        matrix = scaling(matrix, coefficientOfScaling);
        matrix = setNewCenterOfCoordinates(matrix, x, y);
        return matrix;
    }

    public static double[][] scaling(double[][] matrix, double coefficientOfScaling) { //коэфф общий
        double[][] matrixScale = new double[3][3];
        matrixScale[0][0] = coefficientOfScaling;
        matrixScale[1][1] = coefficientOfScaling;
        matrixScale[2][2] = 1;
        return multiply(matrix, matrixScale);
    }

    public static double[][] rotate(double[][] matrix, double x, double y, double angle) {
        matrix = setNewCenterOfCoordinates(matrix, -x, -y);
        matrix = rotate(matrix, angle);
        matrix = setNewCenterOfCoordinates(matrix, x, y);
        return matrix;
    }

    public static double[][] rotate(double[][] matrix, double angle) {
        double[][] matrixOfRotate = {
                {Math.cos(angle), Math.sin(angle), 0},
                {-Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 1}
        };
        return multiply(matrix, matrixOfRotate);
    }

    public static double[][] moveX(double[][] matrix, double dx) {
        double[][] matrixOfOffset = {{1, 0, 0}, {0, 1, 0}, {dx, 0, 1}};
        return multiply(matrix, matrixOfOffset);
    }

    public static double[][] moveY(double[][] matrix, double dy) {
        double[][] matrixOfOffset = {{1, 0, 0}, {0, 1, 0}, {0, dy, 1}};
        return multiply(matrix, matrixOfOffset);
    }

    private static double[][] setNewCenterOfCoordinates(double[][] matrix, double x, double y) {
        return AffineTransformations.offset(matrix, x, y);
    }
}
