package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.attribute;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.core.SkinUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ExternalDescription;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.ModifierBase;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.extern.Gradient;

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
    public @Nullable Set<ExternalDescription> getExternalDescriptions() {
        return Collections.singleton(this.gradient);
    }
}
