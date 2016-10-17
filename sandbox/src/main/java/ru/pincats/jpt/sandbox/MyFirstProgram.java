package ru.pincats.jpt.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {

        testDistance(2, 2, 4, 4);       // all positive
        testDistance(2, 2, 2, 2);       // points at the same location, expect 0
        testDistance(-2, -2, -4, -4);   // all negative
        testDistance(2, 1, 4, -1);      // some negative
        testDistance(2, 1, 2, -1);      // x the same, expect 2
        testDistance(2, 1, 4, 1);       // y the same, expect 2

    }

    public static void testDistance(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        System.out.println("Distance between points p1(" + p1.x + "," + p1.y + ")" + " and p2(" + p2.x + "," + p2.y + ")" +
                " is " + p1.distance(p2));
    }

}