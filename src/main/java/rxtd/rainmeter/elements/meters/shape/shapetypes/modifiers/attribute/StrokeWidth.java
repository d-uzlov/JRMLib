package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute;

import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.ModifierBase;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#StrokeWidth">Rainmeter documentation</a>
 */
public class StrokeWidth extends ModifierBase {
    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#StrokeWidth">Rainmeter documentation</a>
     */
    public StrokeWidth(double width) {
        this.setImage("StrokeWidth", SkinUtils.print(width));
    }
}
