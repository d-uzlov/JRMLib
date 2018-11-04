package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.ModifierBase;

public class StrokeLineJoin extends ModifierBase {
    StrokeLineJoin(LineJoin type, Double miterLimit) {
        this.setImage("StrokeLineJoin", ShapeUtils.createImage(false, type, miterLimit));
    }
}
