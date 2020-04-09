package com.urise;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrawInterpolation extends AbstractInterpolation {
    private double[] scale = new double[2];

    public void draw(InterpolationPolynomial storage) {

        Scanner in = new Scanner(System.in);

        System.out.println("\nВведите масштаб от | до ");
        scale[0] = in.nextDouble();
        scale[1] = in.nextDouble();
        if (scale[0] > scale[1]) {
            System.out.println("Invalid input");
            System.exit(-1);
        }
        if (scale[0] > storage.yxIndex[0]) scale[0] = storage.yxIndex[0];
        if (scale[1] < storage.yxIndex[storage.yxIndex.length - 1]) scale[1] = storage.yxIndex[1];

        XYChart chart = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).yAxisTitle("Y").xAxisTitle("X").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        XYSeries series = drawFunction(storage, chart);
        drawPoint(storage, chart);
        drawInterpolation(storage, series, chart);
        new SwingWrapper<>(chart).displayChart();
    }

    private void drawPoint(InterpolationPolynomial storage, XYChart chart) {
        for (int i = 0; i < storage.yxIndex.length; i++) {
            List<Double> listStoragePointX = new ArrayList<>();
            List<Double> listStoragePointY = new ArrayList<>();
            listStoragePointX.add(storage.yxIndex[i]);
            listStoragePointY.add(storage.yxValue[i]);
            chart.addSeries("point " + (i+1), listStoragePointX, listStoragePointY);
        }
    }

    private void drawInterpolation(InterpolationPolynomial storage, XYSeries series, XYChart chart) {
        List<Double> xDataInterpolation = new ArrayList<>();
        List<Double> yDataInterpolation = new ArrayList<>();
        for (double k = scale[0]; k < scale[1]; k = (k + 0.01)) {
            xDataInterpolation.add(k);
            yDataInterpolation.add(p(storage, k));
        }
        series.setMarker(SeriesMarkers.NONE);
        series = chart.addSeries("y= interpolation", xDataInterpolation, yDataInterpolation);
        series.setMarker(SeriesMarkers.NONE);
    }

    private XYSeries drawFunction(InterpolationPolynomial storage, XYChart chart) {
        List<Double> xDataSqrt = new ArrayList<>();
        List<Double> yDataSqrt = new ArrayList<>();
        for (double i = scale[0]; i < scale[1]; i = i + 0.01) {
            xDataSqrt.add(i);
            yDataSqrt.add(storage.calculateY(i));
        }
        return chart.addSeries("y=function", xDataSqrt, yDataSqrt);
    }
}