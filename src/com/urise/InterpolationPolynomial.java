package com.urise;

public class InterpolationPolynomial {
    double[] x = new double[2];
    double[] yxValue;
    double[] yxIndex;
    double value;
    int e;

    public InterpolationPolynomial(double value) {
        this.e=4;
        yxValue = new double[e];
        yxIndex = new double[e];
        double y = 1;
        x[0] = 1;
        x[1] = 0.5;
        this.value=value;
        yxIndex[0]=(int) value-1;
        for(int i=0;i<yxValue.length;i++)
        {
            if(i!=0) yxIndex[i]=yxIndex[i-1]+1;
            yxValue[i]=Math.pow(((yxIndex[i]))*x[0], x[1])/ y;
        }
    }
}