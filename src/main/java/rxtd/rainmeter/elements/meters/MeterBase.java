package rxtd.rainmeter.elements.meters;

import rxtd.Matrix3x3;
import rxtd.rainmeter.NamePrefixProvider;
import rxtd.rainmeter.Padding;
import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.elements.BevelType;
import rxtd.rainmeter.elements.Element;
import rxtd.rainmeter.elements.MeterMeasureBase;
import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MeterBase<T extends MeterBase<T>> extends MeterMeasureBase<T> implements Meter<T> {
    private final List<Object> styles = new ArrayList<>();

    protected MeterBase(String name, String meter) {
        super(createName(name));
        this.manageParameter("Meter", meter);
        this.addBeforeWriteListener(() -> {
            this.generateStyles();
        });
    }

    private static String createName(String name) {
        NamePrefixProvider prefixProvider = SkinUtils.getNamePrefixProvider();
        return prefixProvider.nextMetersPrefix(name);
    }

    @Override
    public T setStyle(Element... origin) {
        this.styles.clear();
        if (origin != null) {
            for (var e : origin) {
                this.addStyle(e);
            }
        }
        return this.getThis();
    }

    @Override
    public T addStyle(Element origin) {
        if (origin == null) {
            return this.getThis();
        }
        this.styles.add(new Object() {
            @Override
            public String toString() {
                return origin.getName();
            }
        });
        return this.getThis();
    }

    private void generateStyles() {
        this.manageParameter("MeterStyle", this.styles);
    }

    @Override
    public T setX(String x) {
        this.manageParameter("X", x);
        return this.getThis();
    }

    @Override
    public T setX(Integer x) {
        return this.setX(Integer.toString(x));
    }

    @Override
    public T setXRelative(int x, boolean append) {
        return this.setX(x + (append ? "R" : "r"));
    }

    @Override
    public T setXRelative(Meter base, int offset, boolean append) {
        var f = new Formula(base, Formula.MeterParameters.X);
        if (append) {
            f = f.add(new Formula(base, Formula.MeterParameters.W));
        }
        f = f.add(offset);
        return this.setX(f.toString());
    }

    @Override
    public T setY(String y) {
        this.manageParameter("Y", y);
        return this.getThis();
    }

    @Override
    public T setY(Integer y) {
        return this.setY(Integer.toString(y));
    }

    @Override
    public T setYRelative(int y, boolean append) {
        return this.setY(y + (append ? "R" : "r"));
    }

    @Override
    public T setYRelative(Meter base, int offset, boolean append) {
        var f = new Formula(base, Formula.MeterParameters.Y);
        if (append) {
            f = f.add(new Formula(base, Formula.MeterParameters.H));
        }
        f = f.add(offset);
        return this.setY(f.toString());
    }

    @Override
    public T setW(String width) {
        this.manageParameter("W", width);
        return this.getThis();
    }

    @Override
    public T setW(Integer width) {
        return this.setW(Integer.toString(width));
    }

    @Override
    public T setH(String height) {
        this.manageParameter("H", height);
        return this.getThis();
    }

    @Override
    public T setH(Integer height) {
        return this.setH(Integer.toString(height));
    }

    @Override
    public T setPadding(Padding padding) {
        this.manageParameter("Padding", padding);
        return this.getThis();
    }

    @Override
    public T setHidden(String value) {
        this.manageParameter("Hidden", value);
        return this.getThis();
    }

    @Override
    public T setHidden(Boolean value) {
        this.manageParameter("Hidden", value);
        return this.getThis();
    }

    @Override
    public T setUpdateDivider(Integer divider) {
        this.manageParameter("UpdateDivider", divider);
        return this.getThis();
    }

    @Override
    public T disableUpdate() {
        return this.setUpdateDivider(-1);
    }

    @Override
    public T setSolidColor(Color color) {
        this.manageParameter("SolidColor", color);
        this.removeParameter("SolidColor2");
        this.removeParameter("GradientAngle");
        return this.getThis();
    }

    @Override
    public T setSolidColors(Color color, Color color2, Double gradientAngle) {
        this.manageParameter("SolidColor", color);
        this.manageParameter("SolidColor2", color2);
        this.manageParameter("GradientAngle", gradientAngle);
        return this.getThis();
    }

    @Override
    public T setBevelType(BevelType type) {
        this.manageParameter("BevelType", type);
        return this.getThis();
    }

    @Override
    public T setAntiAlias(Boolean value) {
        this.manageParameter("AntiAlias", value);
        return this.getThis();
    }

    @Override
    public T setToolTipText(String value) {
        this.manageParameter("ToolTipText", value);
        return this.getThis();
    }

    @Override
    public T setToolTipTitle(String value) {
        this.manageParameter("ToolTipTitle", value);
        return this.getThis();
    }

    @Override
    public T setToolTipIcon(Resource icon) {
        this.manageParameter("ToolTipIcon", icon);
        return this.getThis();
    }

    @Override
    public T setToolTipType(ToolTipType type) {
        this.manageParameter("ToolTipType", type);
        return this.getThis();
    }

    @Override
    public T setToolTipWidth(Integer value) {
        this.manageParameter("ToolTipWidth", value);
        return this.getThis();
    }

    @Override
    public T setToolTipHidden(Boolean value) {
        this.manageParameter("AntiAlias", value);
        return this.getThis();
    }

    public T setTransformationMatrix(Matrix3x3 value) {
        if (value == null) {
            this.removeParameter("TransformationMatrix");
            return this.getThis();
        }
        this.manageParameter("TransformationMatrix", SkinUtils.joinList(Arrays.asList(value.a11, value.a21, value.a12, value.a22, value.a13, value.a23), ";"));
        return this.getThis();
    }

    protected void setTextBuilder(String option, TextBuilder builder) {
        this.manageParameter(option, builder.getImage());
        for (var m : builder.getMap().entrySet()) {
            String key = "MeasureName" + this.createSuffix(m.getValue());
            this.manageParameter(key, m.getKey().getName());
        }
    }

    public T setMouseAction(MouseButton mouseButton, MouseAction mouseAction, Action action) {
        if (mouseButton == null || mouseAction == null) {
            return this.getThis();
        }
        this.manageParameter(mouseButton.toString() + "Mouse" + mouseAction.toString() + "Action", action);
        return this.getThis();
    }

    public T setMouseWheelAction(ScrollDirection direction, Action action) {
        if (direction == null) {
            return this.getThis();
        }
        this.manageParameter("MouseScroll" + direction.toString() + "Action", action);
        return this.getThis();
    }

    public T setMouseOverAction(Action action) {
        this.manageParameter("MouseOverAction", action);
        return this.getThis();
    }

    public T setMouseLeaveAction(Action action) {
        this.manageParameter("MouseLeaveAction", action);
        return this.getThis();
    }

    public enum ToolTipType {
        NORMAL("0"),
        BALLOON("1");

        private final String value;

        ToolTipType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum MouseAction {
        DOWN("Down"),
        UP("Up"),
        DOUBLE_CLICK("DoubleClick");
        private final String value;

        MouseAction(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ScrollDirection {
        LEFT("Left"),
        RIGHT("Right"),
        UP("Up"),
        DOWN("Down");
        private final String value;

        ScrollDirection(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum MouseButton {
        LEFT("Left"),
        RIGHT("Right"),
        MIDDLE("Middle"),
        X1("X1"),
        X2("X2");
        private final String value;

        MouseButton(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     * Class that is used to automatically make text values
     */
    public static class TextBuilder {
        private final Map<Measure, Integer> map = new HashMap<>();
        private final StringBuilder sb = new StringBuilder();
        private int counter = 1;

        protected Map<Measure, Integer> getMap() {
            return this.map;
        }

        protected String getImage() {
            return this.sb.toString();
        }

        /**
         * Append plaint string value.
         */
        public TextBuilder append(String string) {
            this.sb.append(string);
            return this;
        }

        /**
         * Append measure value.
         */
        public TextBuilder append(Measure measure) {
            Integer number = this.map.get(measure);
            if (number == null) {
                number = this.counter;
                this.map.put(measure, number);
                this.counter++;
            }
            this.sb.append("%").append(number);
            return this;
        }
    }
}
