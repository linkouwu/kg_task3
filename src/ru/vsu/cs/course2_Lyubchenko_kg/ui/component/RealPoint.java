package ru.vsu.cs.course2_Lyubchenko_kg.ui.component;

public class RealPoint {
    private double x, y;

    public RealPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public RealPoint invertX() {
        return new RealPoint(-this.x, this.y);
    }

    public RealPoint invertY() {
        return new RealPoint(this.x, -this.y);
    }
}
