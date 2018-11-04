package rxtd.rainmeter.elements.measures.plugins.custom;

import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

import java.util.Arrays;
import java.util.Collection;

public class FrostedGlass extends PluginBase<FrostedGlass> {
    private final static PluginResource PLUGIN = new VirtualPluginResource("FrostedGlass", null);

    public FrostedGlass(String name) {
        super(name, PLUGIN);
    }

    public FrostedGlass() {
        this(null);
    }

    @Override
    protected FrostedGlass getThis() {
        return this;
    }

    public FrostedGlass setType(Type value) {
        this.manageParameter("Type", value);
        return getThis();
    }

    public FrostedGlass setBorder(Border... borders) {
        return this.setBorder(Arrays.asList(borders));
    }

    public FrostedGlass setBorder(Collection<Border> borders) {
        this.manageParameter("Border", borders);
        return this;
    }

    public enum Type {
        NONE("None"),
        BLUR("Blur"),
        ACRYLIC("Acrylic");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Border {
        ALL("All"),
        NONE("None"),
        LEFT("Left"),
        RIGHT("Right"),
        TOP("Top"),
        BOTTOM("Bottom");

        private final String value;

        Border(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
