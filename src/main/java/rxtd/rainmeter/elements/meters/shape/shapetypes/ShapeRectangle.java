package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute.Fill;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute.Stroke;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute.StrokeWidth;

import java.awt.Color;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Rectangle">Rainmeter documentation</a>
 */
public class ShapeRectangle extends ShapeElementBase {
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private final Double rx;
    private final Double ry;

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Rectangle">Rainmeter documentation</a>
     */
    public ShapeRectangle(double x, double y, double width, double height, Double rx, Double ry) {
        if (rx == null && ry != null) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rx = rx;
        this.ry = ry;

        this.construct();
    }

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Rectangle">Rainmeter documentation</a>
     */
    public ShapeRectangle(double x, double y, double width, double height) {
        this(x, y, width, height, null, null);
    }

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Rectangle">Rainmeter documentation</a>
     */
    public ShapeRectangle(double x, double y, double width, double height, Double radius) {
        this(x, y, width, height, radius, null);
    }

    public static ShapeRectangle sharpOutline(int x, int y, int width, int height, int strokeWidth, Color strokeColor) {
        ShapeRectangle rectangle = new ShapeRectangle(x + strokeWidth * 0.5, y + strokeWidth * 0.5, width - strokeWidth, height - strokeWidth);
        rectangle.addModifier(new Stroke(strokeColor));
        rectangle.addModifier(Fill.TRANSPARENT);
        rectangle.addModifier(new StrokeWidth(strokeWidth));
        return rectangle;
    }

    private void construct() {
        this.setImage("Rectangle", ShapeUtils.createImage(false,
                this.x, this.y,
                this.width, this.height,
                this.rx, this.ry));
    }
}
