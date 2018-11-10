package rxtd.rainmeter.elements.measures.plugins.custom;

import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

import java.util.Arrays;
import java.util.Collection;

/**
 * Blurs background of a meter. Needed in Win10 because it does not have Aero.<br/>
 * Covered version is 1.1.2.
 *
 * @see <a href="https://forum.rainmeter.net/viewtopic.php?f=18&t=23106">Post on Rainmeter forum</a>
 */
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
        return this.getThis();
    }

    public FrostedGlass setBorder(Border... borders) {
        return this.setBorder(Arrays.asList(borders));
    }

    public FrostedGlass setBorder(Collection<Border> borders) {
        this.manageParameter("Border", borders);
        return this;
    }

    public enum Type {
        /**
         * None will not affect anything and is mostly an option to allow you to disable the blur effect.
         */
        NONE("None"),
        /**
         * Blur will apply the regular blur effect that the plugin applied in version 1.0.
         */
        BLUR("Blur"),
        /**
         * Acrylic will apply the new windows 10 acrylic effect to the window.
         */
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
