package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.attribute;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.ModifierBase;

import java.util.List;

public class StrokeDashes extends ModifierBase {
    StrokeDashes(List<Double> dashes) {
        this.setImage("StrokeDashes", ShapeUtils.createImage(false, dashes));
    }
}
