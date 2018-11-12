package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.ModifierBase;
import rxtd.rainmeter.elements.meters.shape.shapetypes.ExternalDescription;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.extern.Gradient;

import java.awt.Color;
import java.util.Collections;
import java.util.Set;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Stroke">Rainmeter documentation</a>
 */
public class Stroke extends ModifierBase {
    private final Gradient gradient;

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Stroke">Rainmeter documentation</a>
     */
    public Stroke(Color color) {
        this.gradient = null;
        this.setImage("Stroke", "Color " + SkinUtils.print(color));
    }

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Stroke">Rainmeter documentation</a>
     */
    public Stroke(Gradient gradient) {
        this.gradient = gradient;
        this.setImage("Stroke", gradient.getType() + " " + gradient.getName());
    }

    @Override
    public @Nullable Set<ExternalDescription> getExternalDescriptions() {
        return Collections.singleton(this.gradient);
    }
}
