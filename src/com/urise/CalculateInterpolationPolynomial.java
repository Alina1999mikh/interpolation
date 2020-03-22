package com.urise;

import java.util.Arrays;

public class CalculateInterpolationPolynomial extends AbstractInterpolation {

    public double[] solution(InterpolationPolynomial storage) {
        double[] resultWithAccuracy = new double[2];
        double resultNoAccuracy = p(storage, storage.value);
        resultWithAccuracy[0] = resultNoAccuracy;
        /*double e = eU(storage) + eO(storage);
        resultWithAccuracy[1] = e;
        storage.result = Arrays.copyOfRange(resultWithAccuracy, 0, 2);*/
        return resultWithAccuracy;
    }

    /*private double module(InterpolationPolynomial storage) {
        double result = 1;
        for (int i = 0; i < storage.yxValue.length; i++) {
            result = result * (storage.value - storage.yxIndex[i]);
        }
        return result;
    }

    private double factorial(double n) {
        int result = 1;
        for (int i = 1; i <= n; ++i) result *= i;
        return result;
    }

       private double eU(InterpolationPolynomial storage) {
        double result;
        result = (M(storage) / factorial(storage.yxValue.length)) * Math.abs(module(storage));
        return result;
    }

    private double eO(InterpolationPolynomial storage) {
        return (storage.yxIndex.length) * (Math.pow(10, -1 * (storage.yxIndex.length + 1)));
    }

    private double M(InterpolationPolynomial storage) {
        double k = storage.x[0];
        double degree = storage.x[1];
        for (int i = 0; i < storage.yxIndex.length; i++) {
            k = k * degree;
            degree--;
        }
        return (Math.abs(k));
    }
 */
}