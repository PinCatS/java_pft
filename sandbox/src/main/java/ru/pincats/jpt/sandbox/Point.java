package ru.pincats.jpt.sandbox;

/**
 * Created by PinCatS on 17.10.2016.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /* Calculates distance relative to p2 */
    public double distance(Point p2) {

        double distance  = Math.sqrt((this.x - p2.x)*(this.x - p2.x) + (this.y - p2.y)*(this.y - p2.y));

        return distance;
    }
}
