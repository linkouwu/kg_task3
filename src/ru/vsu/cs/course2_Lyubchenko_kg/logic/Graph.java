package ru.vsu.cs.course2_Lyubchenko_kg.logic;

import java.awt.*;
import java.util.ArrayList;

public class Graph {
    public static ArrayList<Point> graphArray(String f){
        ArrayList<Point> pointList = new ArrayList<>();
        Parser parser = new Parser(f);
        boolean isSqrt = parser.isSqrtLog();

        for (int i = -2000; i < 2000; i++) {
            int y = (int) parser.solve(i+0.000001);
            if (Math.abs(y)<=2000) {
                if (isSqrt){
                    if (y!=0){
                        pointList.add(new Point(i, (int) parser.solve(i)));
                    }
                } else {
                    pointList.add(new Point(i, (int) parser.solve(i)));
                }
            }
        }
        return pointList;
    }
}
