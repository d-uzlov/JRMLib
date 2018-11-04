package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Ellipse">Rainmeter documentation</a>
 */
public class ShapeEllipse extends ShapeElementBase {
    private final double cx;
    private final double cy;
    private final double rx;
    private final Double ry;

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Ellipse">Rainmeter documentation</a>
     */
    public ShapeEllipse(double cx, double cy, double rx, Double ry) {
        this.cx = cx;
        this.cy = cy;
        this.rx = rx;
        this.ry = ry;

        this.construct();
    }

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Ellipse">Rainmeter documentation</a>
     */
    public ShapeEllipse(double cx, double cy, double radius) {
        this(cx, cy, radius, null);
    }

    private void construct() {
        this.setImage("Ellipse", ShapeUtils.createImage(false, this.cx, this.cy, this.rx, this.ry));
    }
}
