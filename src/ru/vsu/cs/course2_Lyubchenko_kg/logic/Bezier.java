package ru.vsu.cs.course2_Lyubchenko_kg.logic;

import java.awt.*;

public class Bezier {
    public static Point[] bezier(Point p0, Point p1, Point p2, Point p3) {
        Point[] CubicArray = cubicBezier(p0, p1, p2, p3);
        return steps(CubicArray);
    }

    public static Point[] steps(Point[] array) {
        Point c0 = array[0];
        Point c1 = array[1];
        Point c2 = array[2];
        Point c3 = array[3];
        double step = 0.01;
        Point[] array2 = new Point[(int)(1 / step)];
        int i = 0;

        for(double s = 0.0; s < 1.0; s += step) {
            int x = (int)((double)c0.x + (double)c1.x * s + (double)c2.x * s * s + (double)c3.x * s * s * s);
            int y = (int)((double)c0.y + (double)c1.y * s + (double)c2.y * s * s + (double)c3.y * s * s * s);
            array2[i] = new Point(x, y);
            ++i;
        }

        return array2;
    }

    public static Point[] cubicBezier(Point p0, Point p1, Point p2, Point p3) {
        Point c0 = new Point();
        Point c1 = new Point();
        Point c2 = new Point();
        Point c3 = new Point();

        c0.x = p0.x;
        c1.x = -3 * p0.x + 3 * p1.x;
        c2.x = 3 * p0.x + -6 * p1.x + 3 * p2.x;
        c3.x = -1 * p0.x + 3 * p1.x + -3 * p2.x + p3.x;

        c0.y = p0.y;
        c1.y = -3 * p0.y + 3 * p1.y;
        c2.y = 3 * p0.y + -6 * p1.y + 3 * p2.y;
        c3.y = -1 * p0.y + 3 * p1.y + -3 * p2.y + p3.y;

        return new Point[]{c0, c1, c2, c3};
    }
}
