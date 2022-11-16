package ru.vsu.cs.course2_Lyubchenko_kg.logic;

import java.awt.*;
import java.util.ArrayList;

public class Graph {
    public static ArrayList<Point> graphArray(String f, int width, int height){
        ArrayList<Point> pointList = new ArrayList<>();
        Parser parser = new Parser(f);

        for (int i = -width; i < width; i++) {
            int y = (int) parser.solve(i);
            if (Math.abs(y)<=height+100) {
                pointList.add(new Point(i, (int) parser.solve(i)));
            }
        }
        return pointList;
    }

    public static ArrayList<Point> graphArray(String f, double par, int width, int height){
        ArrayList<Point> pointList = new ArrayList<>();
        Parser parser = new Parser(f);

        for (int i = -width; i < width; i++) {
            int y = (int) parser.solve(i, par);
            if (Math.abs(y)<=height+100) {
                pointList.add(new Point(i, (int) parser.solve(i, par)));
            }
        }
        return pointList;
    }
}
