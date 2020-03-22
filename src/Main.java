import com.urise.CalculateInterpolationPolynomial;
import com.urise.DrawInterpolation;
import com.urise.InterpolationPolynomial;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nВведите значение: ");
        double value = in.nextDouble();
        System.out.print("\nВведите число точек интерполяции: ");
        int n = in.nextInt();
        System.out.print("\nВведите шаг: ");
        double step = in.nextDouble();
        System.out.print("\nВведите Х первой точки: ");
        double startPoint = in.nextDouble();
        InterpolationPolynomial storage = new InterpolationPolynomial(value, n, startPoint, step);
        CalculateInterpolationPolynomial calculate = new CalculateInterpolationPolynomial();
        double[] result = calculate.solution(storage);
        System.out.printf("Result: %.4f +-%.4f", result[0], result[1]);
        DrawInterpolation draw = new DrawInterpolation();
        draw.draw(storage);
    }
}