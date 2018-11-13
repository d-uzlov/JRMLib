package rxtd.rainmeter.core.elements.meters.shape.shapetypes;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.Modifier;

import java.util.Map;
import java.util.Set;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/">Rainmeter documentation</a>
 */
public interface ShapeElement<T extends ShapeElement<T>> {
    String asString(Map<ShapeElement, String> shapes);

    @Nullable
    Set<ExternalDescription> getExternalDescriptions();

    T addModifier(Modifier modifier);
}
