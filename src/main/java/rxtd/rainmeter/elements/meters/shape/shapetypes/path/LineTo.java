package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;

public class LineTo extends PathElementBase {
    public LineTo(double endX, double endY) {
        this.setImage("LineTo", ShapeUtils.createImage(false, endX, endY));
    }
}
