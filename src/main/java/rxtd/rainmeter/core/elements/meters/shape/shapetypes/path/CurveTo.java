package rxtd.rainmeter.core.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ShapeEnding;

public class CurveTo extends PathElementBase {
    public CurveTo(
            double endX, double endY,
            double control1X, double control1Y,
            double control2X, double control2Y,
            ShapeEnding ending) {
        this.setImage("Curve", ShapeUtils.createImage(false,
                endX, endY,
                control1X, control1Y,
                control2X, control2Y,
                ending));
    }
}
