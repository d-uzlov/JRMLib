package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute;

import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.ModifierBase;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.extern.ExternalDescription;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.extern.Gradient;

import java.awt.Color;
import java.util.Collections;
import java.util.Set;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Fill">Rainmeter documentation</a>
 */
public class Fill extends ModifierBase {
    public final static Fill TRANSPARENT = new Fill(new Color(0, 0, 0, 0));
    private final Gradient gradient;

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Fill">Rainmeter documentation</a>
     */
    public Fill(Color color) {
        this.gradient = null;
        this.setImage("Fill", "Color " + SkinUtils.print(color));
    }

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Fill">Rainmeter documentation</a>
     */
    public Fill(Gradient gradient) {
        this.gradient = gradient;
        this.setImage("Fill", gradient.getType() + " " + gradient.getName());
    }

    @Override
    public Set<ExternalDescription> getExternalDescriptions() {
        return Collections.singleton(this.gradient);
    }
}
