package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShapeElementBase implements ShapeElement<ShapeElementBase> {
    private final Map<Class, Modifier> modifiers = new HashMap<>();
    private String name;
    private String typeImage;
    private List<Runnable> listeners = new ArrayList<>();

    @Override
    public Map<Class, Modifier> getModifiers() {
        return modifiers;
    }

    protected void setImage(String name, String typeImage) {
        this.name = name;
        this.typeImage = typeImage;
    }

    protected void addBeforeWriteListener(Runnable listener) {
        this.listeners.add(listener);
    }

    private void prepare() {
        for (var l : this.listeners) {
            l.run();
        }
    }

    @Override
    public ShapeElementBase addModifier(Modifier modifier) {
        this.modifiers.put(modifier.getClass(), modifier);
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
            Modifier modifier = e.getValue();
            sb.append("|").append(modifier.asString());
        }

        return sb.toString();
    }
}
