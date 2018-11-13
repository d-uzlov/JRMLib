package rxtd.rainmeter.core.elements.meters.shape.shapetypes;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;

public class ShapeCurve extends ShapeElementBase {
    public ShapeCurve(double startX, double startY,
                      double endX, double endY,
                      double control1X, double control1Y,
                      double control2X, double control2Y,
                      ShapeEnding ending) {
        this.setImage("Curve", ShapeUtils.createImage(false,
                startX, startY,
                endX, endY,
                control1X, control1Y,
                control2X, control2Y,
                ending));
    }
}
