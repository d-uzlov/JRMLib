package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.Modifier;

import java.util.Map;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/">Rainmeter documentation</a>
 */
public interface ShapeElement<T extends ShapeElement<T>> {
    String asString(Map<ShapeElement, String> shapes);

    Map<Class, Modifier> getModifiers();

    T addModifier(Modifier modifier);
}
