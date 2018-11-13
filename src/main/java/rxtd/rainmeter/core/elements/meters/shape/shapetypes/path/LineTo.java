package rxtd.rainmeter.core.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;

public class LineTo extends PathElementBase {
    public LineTo(double endX, double endY) {
        this.setImage("LineTo", ShapeUtils.createImage(false, endX, endY));
    }
}
