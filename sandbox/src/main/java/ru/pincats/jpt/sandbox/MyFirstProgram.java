package ru.pincats.jpt.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(4, 4);
        System.out.println("Distance between points p1(" + p1.x + "," + p1.y + ")" + " and p2(" + p2.x + "," + p2.y + ")" +
                " is " + distance(p1, p2));

        Point p3 = new Point(2, 2);
        Point p4 = new Point(2, 2);
        System.out.println("Distance between points p3(" + p3.x + "," + p4.y + ")" + " and p4(" + p3.x + "," + p4.y + ")" +
                " is " + distance(p3, p4));

        Point p5 = new Point(-2, -2);
        Point p6 = new Point(-4, -4);
        System.out.println("Distance between points p5(" + p5.x + "," + p6.y + ")" + " and p6(" + p5.x + "," + p6.y + ")" +
                " is " + distance(p5, p6));

        Point p7 = new Point(2, 1);
        Point p8 = new Point(4, -1);
        System.out.println("Distance between points p7(" + p7.x + "," + p7.y + ")" + " and p8(" + p8.x + "," + p8.y + ")" +
                " is " + distance(p7, p8));
    }

    /* Calculates distance between two points */
    public static double distance(Point p1, Point p2) {

        double distance  = Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));

        return distance;
    }
}