package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.transform;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.ModifierBase;

public class Offset extends ModifierBase {
    public Offset(double offsetX, double offsetY) {
        this.setImage("Offset", ShapeUtils.createImage(false, offsetX, offsetY));
    }
}
