import com.urise.CalculateInterpolationPolynomial;
import com.urise.InterpolationPolynomial;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nВведите значение: ");
        double value = in.nextDouble();
        InterpolationPolynomial storage = new InterpolationPolynomial(value);
        CalculateInterpolationPolynomial calculate = new CalculateInterpolationPolynomial();
        double[]result=calculate.solution(storage);
        System.out.printf("Result: %.4f +-%.4f", result[0], result[1]);
        calculate.draw(storage);
    }
}