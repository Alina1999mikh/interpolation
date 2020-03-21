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

    public void draw(InterpolationPolynomial storage) {

        Scanner in = new Scanner(System.in);
        System.out.println("\nВыберите масштаб: 0-обычный | 1-крупный ");
        int scale = in.nextInt();
        if (scale != 0 && scale != 1) {
            System.out.println("Invalid input");
            System.exit(-1);
        }
        // рис интерпол
        List<Double> xDataInterpolation = new ArrayList<>();
        List<Double> yDataInterpolation = new ArrayList<>();
        for (double k = -3; k < 5; k = (k + 0.1)) {
            xDataInterpolation.add(k);
            System.out.println("K " + k + " = " + p(storage, 0));
            yDataInterpolation.add(p(storage, k));
        }
// рис график наш
        List<Double> xDataSqrt = new ArrayList<>();
        List<Double> yDataSqrt = new ArrayList<>();
        for (double i = -3; i < 5; i = i + (1 / Math.pow(10, storage.e))) {
            xDataSqrt.add(i);
            yDataSqrt.add(Math.pow(i, storage.x[1]));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).yAxisTitle("Y").xAxisTitle("X").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        XYSeries series = chart.addSeries("y=sqrt x", xDataSqrt, yDataSqrt);
        series.setMarker(SeriesMarkers.NONE);
        series = chart.addSeries("y=sqrt x interpolation", xDataInterpolation, yDataInterpolation);
        series.setMarker(SeriesMarkers.NONE);
        new SwingWrapper<>(chart).displayChart();
    }
}