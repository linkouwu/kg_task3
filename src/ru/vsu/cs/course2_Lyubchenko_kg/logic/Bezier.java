package ru.vsu.cs.course2_Lyubchenko_kg.logic;

import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.RealPoint;

import java.awt.*;
import java.util.ArrayList;

public class Bezier {
    public static RealPoint[] bezier(ArrayList<RealPoint> pointList) {
        RealPoint[] pointArray = pointList.toArray(new RealPoint[0]);
        return stepsBezier(pointArray);
    }

    private static RealPoint[] stepsBezier(RealPoint[] array) {
        double step = 0.01;
        RealPoint[] array2 = new RealPoint[(int) (1 / step)];
        int n = array.length;

        for (double t = 0.00; t < 1; t += step) {
            double x = 0;
            double y = 0;
            for (int i = 0; i < n; i++) {
                x += array[i].getX() * Math.pow(t, i) * Math.pow((1 - t), n - i - 1) * (int) (factorial(n - 1) / factorial(i) / factorial(n - i - 1));
                y += array[i].getY() * Math.pow(t, i) * Math.pow((1 - t), n - i - 1) * (int) (factorial(n - 1) / factorial(i) / factorial(n - i - 1));
            }

            array2[(int) Math.round(t * (1 / step))] = new RealPoint((int) Math.round(x), (int) Math.round(y));
        }

        return array2;
    }

    private static long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
