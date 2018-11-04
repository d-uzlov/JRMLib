package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.ShapeEnding;

public class CurveTo extends PathElementBase {
    private final double endX;
    private final double endY;
    private final double control1X;
    private final double control1Y;
    private final double control2X;
    private final double control2Y;
    private final ShapeEnding ending;

    public CurveTo(
            double endX, double endY,
            double control1X, double control1Y,
            double control2X, double control2Y,
            ShapeEnding ending) {
        this.endX = endX;
        this.endY = endY;
        this.control1X = control1X;
        this.control1Y = control1Y;
        this.control2X = control2X;
        this.control2Y = control2Y;
        this.ending = ending;

        this.setImage("Curve", ShapeUtils.createImage(false,
                this.endX, this.endY,
                this.control1X, this.control1Y,
                this.control2X, this.control2Y,
                this.ending));
    }
}
