package rxtd.rainmeter.core.elements.meters.shape.shapetypes;

import org.jetbrains.annotations.NotNull;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers.Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ShapeElementBase implements ShapeElement<ShapeElementBase> {
    private final Map<Class, Modifier> modifiers = new HashMap<>();
    private final List<Runnable> listeners = new ArrayList<>();
    private final Set<ExternalDescription> descriptions = new HashSet<>();
    private String name;
    private String typeImage;

    @Override
    public @NotNull Set<ExternalDescription> getExternalDescriptions() {
        return this.descriptions;
    }

    protected void setImage(String name, String typeImage) {
        this.name = name;
        this.typeImage = typeImage;
    }

    protected void addBeforeBurnListener(Runnable listener) {
        this.listeners.add(listener);
    }

    private void prepare() {
        for (var l : this.listeners) {
            l.run();
        }
    }

    protected void addExternalDescription(ExternalDescription description) {
        this.descriptions.add(description);
    }

    @Override
    public ShapeElementBase addModifier(Modifier modifier) {
        this.modifiers.put(modifier.getClass(), modifier);
        var descriptions = modifier.getExternalDescriptions();
        if (descriptions != null) {
            this.descriptions.addAll(descriptions);
        }
        return this;
    }

    @Override
    public String asString(Map<ShapeElement, String> shapes) {
        this.prepare();

        StringBuilder sb = new StringBuilder(this.name)
                .append(" ")
                .append(this.typeImage);

        for (var e : this.modifiers.entrySet()) {
            if (e.getKey() == null || e.getValue() == null) {
                continue;
            }
            sb.append("|").append(e.getValue().toString());
        }

        return sb.toString();
    }
}
