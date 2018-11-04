package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.Padding;
import rxtd.rainmeter.elements.BevelType;
import rxtd.rainmeter.elements.Element;
import rxtd.rainmeter.resources.Resource;

import java.awt.Color;
import java.util.Collection;

/**
 * A meter is an object that defines a visual element that is displayed in a skin.
 * Meters are one of the two major kinds of objects in a skin, along with measures.
 *
 * @see <a href="https://docs.rainmeter.net/manual/meters/">Rainmeter documentation</a>
 */
public interface Meter<T extends Meter<T>> extends Element<T> {
    /**
     * Specifies one or more sections as
     * <a href="https://docs.rainmeter.net/manual/meters/general-options/meterstyles/">MeterStyles</a>
     * from which option values are inherited.
     * Multiple MeterStyles are delimited with pipes (|).
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#MeterStyle">Rainmeter documentation</a>
     */
    T setStyle(Element... origin);

    T addStyle(Element origin);

    /**
     * Specifies the x (horizontal) position of the meter in pixels
     * relative to the top-left edge of the skin.<br/>
     * <br/>
     * Relative positioning: If the value is appended with r, the position is relative to the top/left edge of the previous meter.
     * If the value is appended with R, the position is relative to the bottom/right edge of the previous meter.<br/>
     * <br/>
     * Examples:<br/>
     * {@code X=150} : Meter begins 150 horizontal pixels from the left edge of the skin window.<br/>
     * {@code X=10R} : Meter begins 10 horizontal pixels to the right of the previous meter.<br/>
     * <br/>
     * <br/>
     * Default: {@code 0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#XY">Rainmeter documentation</a>
     */
    T setX(Integer x);

    T setXRelative(int x, boolean append);

    T setXRelative(Meter base, int offset, boolean append);

    T setX(String x);

    /**
     * Specifies the y (vertical) position of the meter in pixels relative to the top-left edge of the skin.<br/>
     * <br/>
     * Relative positioning: If the value is appended with r, the position is relative to the top/left edge of the previous meter.
     * If the value is appended with R, the position is relative to the bottom/right edge of the previous meter.<br/>
     * <br/>
     * Examples:<br/>
     * {@code Y=75} : Meter begins 75 vertical pixels from the top edge of the skin window.<br/>
     * {@code Y=0r} : Meter begins at the same vertical position as the previous meter.<br/><br/>
     * <br/>
     * Default: {@code 0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#XY">Rainmeter documentation</a>
     */
    T setY(Integer y);

    T setYRelative(int y, boolean append);

    T setYRelative(Meter base, int offset, boolean append);

    T setY(String y);

    /**
     * Specifies the width of the meter in pixels. String meters and meters which display
     * an image file can automatically determine the width and height. For all other cases, W and H must be defined.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#WH">Rainmeter documentation</a>
     */
    T setW(Integer width);

    T setW(String width);

    /**
     * Specifies the height of the meter in pixels. String meters and meters which display
     * an image file can automatically determine the width and height. For all other cases, W and H must be defined.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#WH">Rainmeter documentation</a>
     */
    T setH(Integer height);

    T setH(String height);

    /**
     * Adds padding in pixels around any or all sides of a meter.
     * The width and height of the meter will dynamically be adjusted to the new size.
     * The padding will be drawn using the color defined with {@link #setSolidColor} / {@link #setSolidColors}.<br/>
     * <br/>
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#Padding">Rainmeter documentation</a>
     */
    T setPadding(Padding padding);

    /**
     * If set to {@code true}, the meter is hidden. The visibility can also be changed with the
     * <a href="https://docs.rainmeter.net/manual/bangs/#ShowHideToggleMeter">!ShowMeter and !HideMeter bangs</a>.<br/>
     * <br/>
     * Note: This is done by setting the width and height of the meter to zero, so some care should be taken when
     * using the R relative positioning suffix on X or Y on any following meters. While in effect a "singularity",
     * the hidden meter still exists and occupies a position in space.<br/><br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#Hidden">Rainmeter documentation</a>
     */
    T setHidden(Boolean value);

    T setHidden(String value);

    /**
     * Frequency at which the meter is updated.
     * The <a href="https://docs.rainmeter.net/manual/skins/rainmeter-section/#Update">Update</a> option
     * in the [Rainmeter] section of the skin is multiplied
     * by the specified value to determine how often the meter is updated.<br/>
     * <br/>
     * Example: If {@code RainmeterSection.setFPS(1.0)} is set and {@code divider = 30}, the meter is updated every 30 seconds.<br/>
     * <br/>
     * If {@code divider = -1} or any negative number, then the meter is only updated once when the skin is loaded or refreshed.<br/><br/>
     * <br/>
     * Default: {@code 1}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#UpdateDivider">Rainmeter documentation</a>
     */
    T setUpdateDivider(Integer divider);

    T disableUpdate();

    /**
     * Color of the meter background.<br/>
     * <br/>
     * Hint: {@code color = new Color(0, 0, 0, 1)} can be used to make transparent areas of the meter clickable.<br/><br/>
     * <br/>
     * Default: {@code transparent}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#SolidColor">Rainmeter documentation</a>
     */
    T setSolidColor(Color color);

    /**
     * Color of the meter background.
     * If both colors are specified, the background is a gradient composed of {@code color} and {@code color2} with the specified {@code gradientAngle}.<br/><br/>
     * <br/>
     * Default: {@code transparent}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#SolidColor">Rainmeter documentation</a>
     */
    T setSolidColors(Color color, Color color2, Double gradientAngle);

    /**
     * If enabled, draws a bevel around the edges of the rectangle specified by H and W.<br/>
     * <br/>
     * Default: {@link BevelType#NO_BEVEL}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#BevelType">Rainmeter documentation</a>
     */
    T setBevelType(BevelType type);

    /**
     * If set to {@code true}, antialising (edge smoothing) is used when the meter is drawn.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#AntiAlias">Rainmeter documentation</a>
     */
    T setAntiAlias(Boolean value);

    /**
     * If set to {@code true}, the meter is dynamic.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/general-options/#DynamicVariables">Rainmeter documentation</a>
     */
    T setDynamicVariables(Boolean value);

    T setToolTipText(String value);

    T setToolTipTitle(String value);

    T setToolTipIcon(Resource icon);

    T setToolTipType(MeterBase.ToolTipType type);

    T setToolTipWidth(Integer value);

    T setToolTipHidden(Boolean value);

    T setGroups(String... groups);

    T setGroups(Collection<String> groups);

    T addGroup(String group);

    T addGroups(String... groups);

    T addGroups(Collection<String> groups);

}
