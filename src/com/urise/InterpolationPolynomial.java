package com.urise;

public class InterpolationPolynomial {
    private double x;
    double[] yxValue;
    double[] yxIndex;
    double value;
    int e;
    double[] result = new double[2];

    public InterpolationPolynomial(double value, int n, double startPoint, double step) {
        this.e = n;
        yxValue = new double[n];
        yxIndex = new double[n];

        this.value = value;
        yxIndex[0] = startPoint;
        for (int i = 0; i < yxValue.length; i++) {
            if (i != 0) yxIndex[i] = yxIndex[i - 1] + step;
            yxValue[i]=calculateY(yxIndex[i]);
        }
    }

    double calculateY(double y) {
        return Math.pow(y,0.5);
    }
}