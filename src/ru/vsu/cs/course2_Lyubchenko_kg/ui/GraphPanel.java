package ru.vsu.cs.course2_Lyubchenko_kg.ui;

import ru.vsu.cs.course2_Lyubchenko_kg.logic.Bezier;
import ru.vsu.cs.course2_Lyubchenko_kg.logic.Graph;
import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.RealPoint;
import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.WindowPoint;
import ru.vsu.cs.course2_Lyubchenko_kg.ui.component.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {

    private final WindowConverter converter;
    private WindowPoint prevPoint;
    private static String function;
    private static ArrayList<RealPoint> bezierPoint;

    public GraphPanel() {
        converter = new WindowConverter(800, 600, -100, 100, 200, 200);

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                WindowPoint curPoint = new WindowPoint(e.getX(), e.getY());
                RealPoint p1 = converter.s2r(curPoint);
                RealPoint p2 = converter.s2r(prevPoint);
                RealPoint delta = new RealPoint(p2.getX() - p1.getX(), p2.getY() - p1.getY());
                converter.moveCorner(delta);
                prevPoint = curPoint;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        this.addMouseWheelListener(e -> {
            int count = e.getWheelRotation();
            double coef = 1 + 0.05 * (count < 0 ? -1 : 1);
            double scale = 1;
            for (int i = Math.abs(count); i > 0; i--) {
                scale *= coef;
            }
            converter.changeScale(scale);
            repaint();
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                prevPoint = new WindowPoint(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                prevPoint = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        converter.setWindowHeight(getHeight());
        converter.setWindowWidth(getWidth());
        Graphics2D g2 = (Graphics2D) g;

        drawCoordinates(converter, g2);
        drawGrid(converter, g2);

        if (function != null) {
            drawFunction(function, g2, converter);
        }
        if (bezierPoint != null) {
            drawBezier(bezierPoint, g2, converter);
        }

        repaint();
    }

    private static void drawCoordinates(WindowConverter wc, Graphics2D g2) {
        double step = wc.getRealWidth() / wc.getWindowWidth();
        double x = wc.getX();
        double y = 0;

        for (int i = 1; i < wc.getWindowWidth(); i++) {
            double x1 = wc.getX() + i * step;
            double y1 = 0;
            drawLine(new Line(new RealPoint(x, y), new RealPoint(x1, y1)), g2, wc);
            x = x1;
            y = y1;
        }

        step = wc.getRealHeight() / wc.getWindowHeight();
        x = 0;
        y = wc.getY();
        for (int i = 1; i < wc.getWindowHeight(); i++) {
            double y1 = wc.getY() - i * step;
            double x1 = 0;
            drawLine(new Line(new RealPoint(x, y), new RealPoint(x1, y1)), g2, wc);
            x = x1;
            y = y1;
        }
    }

    private static void drawGrid(WindowConverter wc, Graphics2D g2) {
        int step = 1;
        if (wc.getRealWidth() > 1000) {
            int n = 0;
            while (wc.getRealWidth() > 500 * Math.pow(2, n)) {
                n++;
            }
            step = 50 * (int) Math.pow(2, n - 1);
        } else if (wc.getRealWidth() > 350) {
            step = 50;
        } else if (wc.getRealWidth() > 150) {
            step = 20;
        } else if (wc.getRealWidth() > 40) {
            step = 10;
        } else if (wc.getRealWidth() > 20) {
            step = 5;
        }

        for (int i = 0; i < wc.getRealWidth(); i += step) {
            g2.setColor(Color.BLACK);
            if (i == 0) {
                RealPoint rp = new RealPoint(0.1 * step, 0.1 * step);
                g2.drawString(String.valueOf(i), wc.r2s(rp).getX(), wc.r2s(rp).getY());
            } else {
                RealPoint rp = new RealPoint(i, 0.1 * step);

                g2.drawString(String.valueOf(i), wc.r2s(rp).getX(), wc.r2s(rp).getY());
                g2.drawString(String.valueOf(-i), wc.r2s(rp.invertX()).getX(), (float) wc.r2s(rp).getY());

                g2.setColor(Color.lightGray);
                drawLine(new Line(new RealPoint(i, -wc.getRealHeight()), new RealPoint(i, wc.getRealHeight() * 2)), g2, wc);
                drawLine(new Line(new RealPoint(-i, -wc.getRealHeight()), new RealPoint(-i, wc.getRealHeight() * 2)), g2, wc);
            }
        }
        for (int i = 0; i < wc.getRealHeight(); i += step) {
            if (i != 0) {
                RealPoint rp = new RealPoint(0.1 * step, i);

                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(i), wc.r2s(rp).getX(), wc.r2s(rp).getY());
                g2.drawString(String.valueOf(-i), wc.r2s(rp).getX(), wc.r2s(rp.invertY()).getY());

                g2.setColor(Color.lightGray);
                drawLine(new Line(new RealPoint(-wc.getRealWidth(), i), new RealPoint(wc.getRealWidth(), i)), g2, wc);
                drawLine(new Line(new RealPoint(-wc.getRealWidth(), -i), new RealPoint(wc.getRealWidth(), -i)), g2, wc);
            }
        }
    }

    private void drawFunction(String f, Graphics2D g2, WindowConverter wc) {
        List<RealPoint> l = Graph.graphArray(f);
        RealPoint[] arr = l.toArray(new RealPoint[0]);

        drawArray(arr, true, g2, wc);
    }

    private void drawBezier(ArrayList<RealPoint> point, Graphics2D g2, WindowConverter wc) {
        RealPoint[] pointDraw = Bezier.bezier(point);
        drawArray(pointDraw, false, g2, wc);
    }

    private void drawArray(RealPoint[] arr, boolean isTrue, Graphics2D g2, WindowConverter wc) {
        for (int i = 0; i < arr.length - 1; i++) {
            RealPoint p = arr[i];
            RealPoint p1 = arr[i + 1];

            if (isTrue) {
                assert p != null;
                if (p1.getX() - p.getX() <= 1) {
                    g2.setColor(Color.red);
                    drawLine(new Line(p, p1), g2, wc);
                }
            } else {
                g2.setColor(Color.red);
                drawLine(new Line(p, p1), g2, wc);
            }
        }
    }

    private static void drawLine(Line line, Graphics2D g2, WindowConverter wc) {
        double dx, dy, steps, x, y, k;
        double xc, yc;

        WindowPoint p1 = wc.r2s(line.getP1());
        WindowPoint p2 = wc.r2s(line.getP2());

        dx = p2.getX() - p1.getX();
        dy = p2.getY() - p1.getY();
        steps = Math.max(Math.abs(dx), Math.abs(dy));
        xc = (dx / steps);
        yc = (dy / steps);
        x = p1.getX();
        y = p1.getY();
        for (k = 1; k <= steps; k++, x += xc, y += yc) {
            plot((int) Math.round(x), (int) Math.round(y), g2);
        }
    }

    private static void plot(int x, int y, Graphics2D g) {
        int shift = 2;
        g.fillOval(x - shift, y - shift, 2, 2);
    }

    public static void setFunction(String function) {
        GraphPanel.function = function;
    }

    public static void setBezierPoint(ArrayList<RealPoint> bezierPoint) {
        GraphPanel.bezierPoint = bezierPoint;
    }
}
