package ru.vsu.cs.course2_Lyubchenko_kg.ui;

import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.RealPoint;
import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.WindowPoint;

import java.awt.*;

public class WindowConverter {
    private int windowWidth, windowHeight;
    private double x, y;
    private double realWidth, realHeight;

    public WindowConverter(int windowWidth, int windowHeight, double x, double y, double realWidth, double realHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.x = x;
        this.y = y;
        this.realWidth = realWidth;
        this.realHeight = realHeight;
    }

    public WindowPoint r2s(RealPoint p) {
        int x = (int) (windowWidth * (p.getX() - this.x) / realWidth);
        int y = (int) (windowHeight * (this.y - p.getY()) / realHeight);
        return new WindowPoint(x, y);
    }

    public RealPoint s2r(WindowPoint p) {
        double x = this.x + p.getX() * realWidth / windowWidth;
        double y = this.y - p.getY() * realHeight / windowHeight;
        return new RealPoint(x, y);
    }

    public void moveCorner(RealPoint p) {
        x += p.getX();
        y += p.getY();
    }

    public void changeScale(double s) {
        realWidth *= s;
        realHeight *= s;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRealWidth() {
        return realWidth;
    }

    public double getRealHeight() {
        return realHeight;
    }
}
