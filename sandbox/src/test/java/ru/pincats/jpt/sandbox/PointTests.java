package ru.pincats.jpt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by PinCatS on 23.10.2016.
 */
public class PointTests {

    @Test
    public void testDistance() {
        // x the same, expect 2
        Point p1 = new Point(2, 1);
        Point p2 = new Point(2, -1);

        Assert.assertEquals(p1.distance(p2), 2.0);

        // all positive
        p1.setX(2); p1.setY(2);
        p2.setX(4); p2.setY(4);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);

        // all negative
        p1.setX(-2); p1.setY(-2);
        p2.setX(-4); p2.setY(-4);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);

        // some negative
        p1.setX(2); p1.setY(1);
        p2.setX(4); p2.setY(-1);
        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);

        // points at the same location, expect 0
        p1.setX(2); p1.setY(2);
        p2.setX(2); p2.setY(2);
        Assert.assertEquals(p1.distance(p2), 0.0);

        // y the same, expect 2
        p1.setX(2); p1.setY(1);
        p2.setX(4); p2.setY(1);
        Assert.assertEquals(p1.distance(p2), 2.0);

        // let's try real numbers, expect 2
        p1.setX(2.5); p1.setY(1.5);
        p2.setX(4.5); p2.setY(1.5);
        Assert.assertEquals(p1.distance(p2), 2.0);

    }

}
