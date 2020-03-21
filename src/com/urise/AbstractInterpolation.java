package com.urise;

abstract class AbstractInterpolation {

    double p(InterpolationPolynomial storage, double value) {
        double result = 0;
        for (int i = 0; i < storage.yxIndex.length; i++) {
            result = result + storage.yxValue[i] * fractionResult(storage, i, value);
        }
        return result;
    }

    private double fractionResult(InterpolationPolynomial storage, int index, double value) {
        double numerator = 1;
        double denominator = 1;
        for (int i = 0; i < storage.yxIndex.length; i++) {
            if (i != index) {
                numerator = numerator * (value - storage.yxIndex[i]);
                denominator = denominator * (storage.yxIndex[index] - storage.yxIndex[i]);
            }
        }
        return numerator / denominator;
    }
}