package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.transform;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.ModifierBase;

public class Scale extends ModifierBase {
    public Scale(double scaleX, double scaleY, Double anchorX, Double anchorY) {
        this.setImage("Scale", ShapeUtils.createImage(false, scaleX, scaleY, anchorX, anchorY));
    }
}
