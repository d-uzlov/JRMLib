package rxtd.rainmeter.core.elements;

import rxtd.Pair;
import rxtd.rainmeter.core.Padding;
import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.resources.Resource;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * The RainmeterSection of a skin defines options for the entire skin.
 *
 * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/">Rainmeter documentation</a>
 */
public class RainmeterSection extends ElementBase<RainmeterSection> {
    private final Collection<Pair<String, Action>> contextActions = new HashSet<>();

    public RainmeterSection() {
        super("Rainmeter");
        this.manageParameter("AccurateText", true);

        this.addBeforeWriteListener(() -> {
            int index = 1;
            for (var p : this.contextActions) {
                if (p.key == null || p.value == null) {
                    System.err.println("null values in context actions");
                    continue;
                }
                this.manageParameter("ContextTitle" + this.createSuffix(index), p.key);
                this.manageParameter("ContextAction" + this.createSuffix(index), p.value);
            }
        });
    }

    /**
     * Defines the update interval of the skin in milliseconds.
     * On each update, measures and meters are updated, and the skin is redrawn.<br/>
     * <br/>
     * The lowest possible effective value for {@code Update} is {@code 16}.
     * <br/>
     * Using negative values will update the skin only once on load (or on refresh).<br/>
     * <br/>
     * Note: While {@code Update=1000} will update the skin "once a second", Update is not related to the system clock,
     * and if a computer is busy or a complicated skin takes longer than the interval to complete
     * a full update of the skin, updates can be unreliable in relation to elapsed time.
     * Do not use the Update value to control clocks or other timing sensitive functions.<br/>
     * <br/>
     * Default: {@code 1000}
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#Update">Rainmeter documentation</a>
     */
    public RainmeterSection setUpdate(Integer value) {
        this.manageParameter("Update", value);
        return this.getThis();
    }

    /**
     * This option is derived. Refer to {@link #setUpdate} for original option.
     * <br/>
     * Defines the update rate of the skin.<br/>
     * <br/>
     * The highest possible effective value is {@code 60.0}.
     * <br/>
     * Using {@code 0} will update the skin only once on load (or on refresh).<br/>
     * <br/>
     * Default: {@code 1.0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#Update">Rainmeter documentation</a>
     * @see #setUpdate
     */
    public RainmeterSection setFPS(Double fps) {
        if (fps != null) {
            int update;
            if (fps == 0) {
                update = -1;
            } else {
                update = (int) Math.round(1000 / fps);
            }
            this.manageParameter("Update", Integer.toString(update));
        } else {
            this.removeParameter("Update");
        }
        return this.getThis();
    }

    /**
     * Default number of updates at which skin measure and meter values are updated. The {@link #setUpdate) option
     * is multiplied by the specified value to determine how often measures and meters are updated.<br/>
     * <br/>
     * Example: If {@code Update=1000} and {@code DefaultUpdateDivider=30}, then by default all measures and meters
     * are updated every 30 seconds.<br/>
     * <br/>
     * If {@code DefaultUpdateDivider=-1} or any negative number, then by default all measures and meters
     * are only updated once when the skin is loaded or refreshed.<br/>
     * <br/>
     * Note: This option is designed to set a default UpdateDivider for all measures and meters in the skin.
     * It can be overridden by setting UpdateDivider on individual measures or meters.
     * <br/>
     * <br/>
     * Default: {@code 1}
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#DefaultUpdateDivider">Rainmeter documentation</a>
     */
    public RainmeterSection setDefaultUpdateDivider(Integer value) {
        this.manageParameter("DefaultUpdateDivider", value);
        return this.getThis();
    }

    /**
     * If set to {@code true}, the window size is adjusted on each update to fit the meters.
     * <br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#DynamicWindowSize">Rainmeter documentation</a>
     */
    public RainmeterSection setDynamicWindowSize(Boolean value) {
        this.manageParameter("DynamicWindowSize", value);
        return this.getThis();
    }

    /**
     * Either or both may be set.
     * This will set and constrain the skin to the fixed dimensions defined, either the width, the height, or both.
     * Any meter or part of a meter positioned outside the defined boundaries will be truncated.
     * {@link #setDynamicWindowSize} will be ignored for the defined dimension(s),
     * and meters that would normally display even "outside" the skin will be truncated by the fixed skin dimension(s).
     * <br/>
     * <br/>
     * Default: {@code none}
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#SkinWidth">Rainmeter documentation</a>
     */
    public RainmeterSection setSkinWidthHeight(Integer width, Integer height) {
        this.manageParameter("SkinWidth", width);
        this.manageParameter("SkinHeight", height);
        return this.getThis();
    }

    /**
     * The values define a margin of non-draggable area.
     * It's also possible to use negative numbers in which case the margin is calculated from the opposite side.
     * {@code E.g. DragMargins=0,-100,0,0}.
     * <br/>
     * <br/>
     * Default: {@code 0,0,0,0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#DragMargins">Rainmeter documentation</a>
     */
    public RainmeterSection setDragMargins(Padding padding) {
        this.manageParameter("DragMargins", padding);
        return this.getThis();
    }

    public RainmeterSection setOnRefreshAction(Action action) {
        this.manageParameter("OnRefreshAction", action);
        return this.getThis();
    }

    public RainmeterSection setOnUpdateAction(Action action) {
        this.manageParameter("OnUpdateAction", action);
        return this.getThis();
    }

    public RainmeterSection setOnCloseAction(Action action) {
        this.manageParameter("OnCloseAction", action);
        return this.getThis();
    }

    public RainmeterSection setOnFocusAction(Action action) {
        this.manageParameter("OnFocusAction", action);
        return this.getThis();
    }

    public RainmeterSection setOnUnfocusAction(Action action) {
        this.manageParameter("OnUnfocusAction", action);
        return this.getThis();
    }

    public RainmeterSection setOnWakeAction(Action action) {
        this.manageParameter("OnWakeAction", action);
        return this.getThis();
    }

    public RainmeterSection setTransitionUpdate(Integer value) {
        this.manageParameter("TransitionUpdate", value);
        return this.getThis();
    }

    public RainmeterSection setToolTipHidden(Boolean value) {
        this.manageParameter("ToolTipHidden", value);
        return this.getThis();
    }

    public RainmeterSection setGroup(String... value) {
        return this.setGroup(Arrays.asList(value));
    }

    public RainmeterSection setGroup(Collection<String> value) {
        this.manageParameter("Group", value);
        return this.getThis();
    }

    public RainmeterSection setSelectedColor(Color value) {
        this.manageParameter("SelectedColor", value);
        return this.getThis();
    }

    public RainmeterSection setDragGroup(String... value) {
        return this.setDragGroup(Arrays.asList(value));
    }

    public RainmeterSection setDragGroup(Collection<String> value) {
        this.manageParameter("DragGroup", value);
        return this.getThis();
    }

    public RainmeterSection setBackground(Resource value) {
        this.manageParameter("Background", value);
        return this.getThis();
    }

    public RainmeterSection setBackgroundMode(BackgroundMode value) {
        this.manageParameter("BackgroundMode", value);
        return this.getThis();
    }

    public RainmeterSection setBackgroundMargins(Padding value) {
        this.manageParameter("BackgroundMargins", value);
        return this.getThis();
    }

    public RainmeterSection setSolidColor(Color value) {
        this.manageParameter("SolidColor", value);
        this.removeParameter("SolidColor2");
        return this.getThis();
    }

    public RainmeterSection setColors(Color value1, Color value2) {
        this.manageParameter("SolidColor", value1);
        this.manageParameter("SolidColor2", value2);
        return this.getThis();
    }

    public RainmeterSection setGradientAngle(Double value) {
        this.manageParameter("GradientAngle", value);
        return this.getThis();
    }

    public RainmeterSection setBevelType(BevelType value) {
        this.manageParameter("BevelType", value);
        return this.getThis();
    }

    public RainmeterSection setContextActions(Collection<Pair<String, Action>> actions) {
        this.contextActions.clear();
        this.contextActions.addAll(actions);
        return this.getThis();
    }

    public RainmeterSection addContextAction(Pair<String, Action> action) {
        this.contextActions.clear();
        this.contextActions.add(action);
        return this.getThis();
    }

    // TODO image options?

    @Override
    protected RainmeterSection getThis() {
        return this;
    }

    public enum BackgroundMode {
        IMAGE("0"),
        TRANSPARENT("1"),
        FILL_SOLID("2"),
        IMAGE_SCALE("3"),
        IMAGE_TILE("4");

        private final String value;

        BackgroundMode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public BackgroundMode fromValue(int value) {
            for (var e : BackgroundMode.values()) {
                if (e.toString().equals(Integer.toString(value))) {
                    return e;
                }
            }
            return null;
        }
    }
}
