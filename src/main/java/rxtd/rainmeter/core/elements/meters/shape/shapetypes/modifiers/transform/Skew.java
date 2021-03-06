package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.transform;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.ModifierBase;

public class Skew extends ModifierBase {
    public Skew(double skewX, double skewY, Double anchorX, Double anchorY) {
        this.setImage("Skew", ShapeUtils.createImage(false, skewX, skewY, anchorX, anchorY));
    }
}
