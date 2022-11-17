package ru.vsu.cs.course2_Lyubchenko_kg.logic;

import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.RealPoint;

import java.awt.*;
import java.util.ArrayList;

public class Graph {
    public static ArrayList<RealPoint> graphArray(String f) {
        ArrayList<RealPoint> pointList = new ArrayList<>();
        Parser parser = new Parser(f);
        boolean isSqrt = parser.isSqrtLog();

        for (int i = -2000; i < 2000; i++) {
            int y = (int) parser.solve(i + 0.000001);
            if (Math.abs(y) <= 2000) {
                if (isSqrt) {
                    if (y != 0) {
                        pointList.add(new RealPoint(i, (int) parser.solve(i)));
                    }
                } else {
                    pointList.add(new RealPoint(i, (int) parser.solve(i)));
                }
            }
        }
        return pointList;
    }
}
