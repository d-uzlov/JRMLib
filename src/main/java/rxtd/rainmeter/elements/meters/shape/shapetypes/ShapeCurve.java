package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;

public class ShapeCurve extends ShapeElementBase {
    private final double startX;
    private final double startY;
    private final double endX;
    private final double endY;
    private final double control1X;
    private final double control1Y;
    private final double control2X;
    private final double control2Y;
    private final ShapeEnding ending;

    public ShapeCurve(double startX, double startY,
                      double endX, double endY,
                      double control1X, double control1Y,
                      double control2X, double control2Y,
                      ShapeEnding ending) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.control1X = control1X;
        this.control1Y = control1Y;
        this.control2X = control2X;
        this.control2Y = control2Y;
        this.ending = ending;

        this.setImage("Curve", ShapeUtils.createImage(false,
                this.startX, this.startY,
                this.endX, this.endY,
                this.control1X, this.control1Y,
                this.control2X, this.control2Y,
                this.ending));
    }
}
