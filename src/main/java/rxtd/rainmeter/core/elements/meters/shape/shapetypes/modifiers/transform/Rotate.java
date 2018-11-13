package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.transform;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.ModifierBase;

public class Rotate extends ModifierBase {
    public Rotate(double degrees, Double anchorX, Double anchorY) {
        this.setImage("Rotate", ShapeUtils.createImage(false, degrees, anchorX, anchorY));
    }
}
