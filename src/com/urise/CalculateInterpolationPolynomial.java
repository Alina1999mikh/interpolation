package com.urise;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CalculateInterpolationPolynomial {

    private double[] result = new double[2];

    public double[] solution(InterpolationPolynomial storage) {
        double resultNoAccuracy = p(storage);
        double e = eU(storage) + eO(storage);
        double[] resultWithAccuracy = new double[2];
        resultWithAccuracy[0] = resultNoAccuracy;
        resultWithAccuracy[1] = e;
        this.result = Arrays.copyOfRange(resultWithAccuracy, 0, 2);
        return resultWithAccuracy;
    }

    private double p(InterpolationPolynomial storage) {
        double result = 0;
        for (int i = 0; i < storage.yxIndex.length; i++) {
            result = result + storage.yxValue[i] * fractionResult(storage, i);
        }
        return result;
    }

    private double fractionResult(InterpolationPolynomial storage, int index) {
        double numerator = 1;
        double denominator = 1;
        for (int i = 0; i < storage.yxIndex.length; i++) {
            if (i != index) {
                numerator = numerator * (storage.value - storage.yxIndex[i]);
                denominator = denominator * (storage.yxIndex[index] - storage.yxIndex[i]);
            }
        }
        return numerator / denominator;
    }

    private double eU(InterpolationPolynomial storage) {
        double result;
        result = (M(storage) / factorial(storage.yxValue.length)) * Math.abs(module(storage));
        return result;
    }

    private double eO(InterpolationPolynomial storage) {
        return (storage.yxIndex.length) * (Math.pow(10, -1 * (storage.yxIndex.length + 1)));
    }

    private double module(InterpolationPolynomial storage) {
        double result = 1;
        for (int i = 0; i < storage.yxValue.length; i++) {
            result = result * (storage.value - storage.yxIndex[i]);
        }
        return result;
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

    private double factorial(double n) {
        int result = 1;
        for (int i = 1; i <= n; ++i) result *= i;
        return result;
    }

    public void draw(InterpolationPolynomial storage) {

        Scanner in = new Scanner(System.in);
        System.out.println("\nВыберите масштаб: 0-обычный | 1-крупный ");
        int scale = in.nextInt();
        if (scale != 0 && scale != 1) {
            System.out.println("Invalid input");
            System.exit(-1);
        }

        List<Double> xDataInterpolation = new ArrayList<>();
        List<Double> yDataInterpolation = new ArrayList<>();
        List<Double> xIntersectionPointInterpolation = new ArrayList<>();
        List<Double> yIntersectionPointInterpolation = new ArrayList<>();
        for (double i = scale; i < storage.yxIndex.length - scale; i++) {
            xDataInterpolation.add(storage.yxIndex[(int) i]);
            yDataInterpolation.add(storage.yxValue[(int) i]);
        }
        xIntersectionPointInterpolation.add(storage.value);
        yIntersectionPointInterpolation.add(result[0]);
        xIntersectionPointInterpolation.add(storage.value);
        yIntersectionPointInterpolation.add(result[0] - result[1]);
        xIntersectionPointInterpolation.add(storage.value);
        yIntersectionPointInterpolation.add(result[0] + result[1]);

        List<Double> xDataSqrt = new ArrayList<>();
        List<Double> yDataSqrt = new ArrayList<>();
        List<Double> xIntersectionPointSqrt = new ArrayList<>();
        List<Double> yIntersectionPointSqrt = new ArrayList<>();
        double intersectionPointSqrt = 0;
        for (double i = storage.yxIndex[scale]; i <= storage.yxIndex[storage.yxIndex.length - 1 - scale]; i = i + (1 / Math.pow(10, storage.e))) {
            xDataSqrt.add(i);
            yDataSqrt.add(Math.sqrt(i));
            if (Math.abs(i - storage.value) < 0.0000001) {
                xIntersectionPointSqrt.add(i);
                intersectionPointSqrt = Math.sqrt(i);
                yIntersectionPointSqrt.add(intersectionPointSqrt);
            }
        }
        int e=10000;
        XYChart chart = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).yAxisTitle("Y").xAxisTitle("X").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        XYSeries series = chart.addSeries("y=sqrt x", xDataSqrt, yDataSqrt);
        series.setMarker(SeriesMarkers.NONE);
        chart.addSeries("y=sqrt x interpolation", xDataInterpolation, yDataInterpolation);
        for(int i=0;i<result.length;i++){
            result[i]=Math.round(result[i]*e);
            result[i]=result[i]/e;
        }
        series = chart.addSeries("Intersection Point Interpolation= " +result[0] + "+-" + result[1], xIntersectionPointInterpolation, yIntersectionPointInterpolation);
        series.setMarker(SeriesMarkers.NONE);
        intersectionPointSqrt=Math.round(intersectionPointSqrt*e);
        intersectionPointSqrt=intersectionPointSqrt/e;
        chart.addSeries("Intersection Point Sqrt= " +intersectionPointSqrt, xIntersectionPointSqrt, yIntersectionPointSqrt);
        new SwingWrapper<>(chart).displayChart();
    }
}