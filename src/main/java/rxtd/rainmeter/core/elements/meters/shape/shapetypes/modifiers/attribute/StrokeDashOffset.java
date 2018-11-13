package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.attribute;

import rxtd.rainmeter.core.SkinUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.ModifierBase;

public class StrokeDashOffset extends ModifierBase {
    public StrokeDashOffset(double width) {
        this.setImage("StrokeDashOffset", SkinUtils.print(width));
    }
}
