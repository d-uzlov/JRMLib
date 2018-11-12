package rxtd.rainmeter.elements.meters.shape.shapetypes;

import org.jetbrains.annotations.Nullable;
import rxtd.Pair;
import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.Modifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Combine">Rainmeter documentation</a>
 */
public class Combine implements ShapeElement<Combine> {
    private final ShapeElementBase base;
    private final List<Pair<String, ShapeElement>> actions = new ArrayList<>();

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Combine">Rainmeter documentation</a>
     */
    public Combine(ShapeElementBase base) {
        this.base = base;
    }

    public Combine union(ShapeElement element) {
        var p = new Pair<>("Union", element);
        this.actions.add(p);
        return this;
    }

    public Combine intersect(ShapeElement element) {
        var p = new Pair<>("Intersect", element);
        this.actions.add(p);
        return this;
    }

    public Combine XOR(ShapeElement element) {
        var p = new Pair<>("XOR", element);
        this.actions.add(p);
        return this;
    }

    public Combine exclude(ShapeElement element) {
        var p = new Pair<>("Exclude", element);
        this.actions.add(p);
        return this;
    }

    public String asString(Map<ShapeElement, String> shapes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Combine ").append(shapes.get(this.base));
        for (var a : this.actions) {
            sb.append("|").append(a.key).append(" ").append(shapes.get(a.value));
        }
        return sb.toString();
    }

    @Override
    public @Nullable Set<ExternalDescription> getExternalDescriptions() {
        return null;
    }

    @Override
    public Combine addModifier(Modifier modifier) {
        throw new UnsupportedOperationException();
    }
}
