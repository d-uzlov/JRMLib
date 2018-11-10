package rxtd.rainmeter.elements.meters.string;

import rxtd.Pair;
import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.elements.meters.MeterBase;
import rxtd.rainmeter.resources.Resource;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Label is a class that represents Rainmeter's String meter.<br/>
 * String displays text.
 *
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/">Rainmeter documentation</a>
 */
public class Label extends MeterBase<Label> {
    private Collection<Pair<String, InlineSettings.InlineOption>> inlineSettings = new ArrayList<>();

    public Label(String name) {
        super(name, "String");
        this.setAntiAlias(true);
        this.addBeforeWriteListener(() -> {
            if (this.inlineSettings.size() > 0) {
                int i = 0;
                for (var p : this.inlineSettings) {
                    i++;
                    this.manageParameter("InlinePattern" + this.createSuffix(i), p.key);
                    this.manageParameter("InlineSetting" + this.createSuffix(i), p.value);
                }
            }
        });
    }

    @Override
    protected Label getThis() {
        return this;
    }

    /**
     * Text to display.
     * <br/>
     * All measures from TextBuilder are added automatically.<br/>
     * Conflicts with {@link #setMeasure}.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#MeasureName">Rainmeter documentation</a>
     */
    public Label setText(TextBuilder builder) {
        this.setTextBuilder("Text", builder);
        return this.getThis();
    }

    public Label setText(Measure measure) {
        return this.setText(new TextBuilder().append(measure));
    }

    /**
     * Text to display.<br/>
     * Use it if you want to set measures yourself or if meter should display a plain string.
     * <br/>
     * Default: {@code %1}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Text">Rainmeter documentation</a>
     */
    public Label setText(String text) {
        this.manageParameter("Text", text);
        return this.getThis();
    }

    /**
     * Measure bound to the meter.<br/>
     * Conflicts with {@link #setText(TextBuilder)}.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#MeasureName">Rainmeter documentation</a>
     * @deprecated Use {@link #setText(TextBuilder)} instead.
     * <br/>
     * <br/>
     */
    @Deprecated
    public Label setMeasure(Measure measure, int index) {
        String name = "MeasureName" + this.createSuffix(index);
        if (measure == null) {
            this.removeParameter(name);
        }
        this.manageParameter(name, measure);
        return this.getThis();
    }

    /**
     * Text displayed before Text.
     * <br/>
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Prefix">Rainmeter documentation</a>
     * @deprecated It is preferable to put the entire string in Text instead of using this option.
     * <br/>
     * Default: {@code empty}
     */
    @Deprecated
    public Label setPrefix(String prefix) {
        this.manageParameter("Prefix", prefix);
        return this.getThis();
    }

    /**
     * Text displayed after Text.
     * <br/>
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Postfix">Rainmeter documentation</a>
     * @deprecated It is preferable to put the entire string in Text instead of using this option.
     * <br/>
     * Default: {@code empty}
     */
    @Deprecated
    public Label setPostfix(String postfix) {
        this.manageParameter("Postfix", postfix);
        return this.getThis();
    }

    /**
     * Family name of the font to use for the text. The font must either be installed in Windows directly or be
     * <a href="https://docs.rainmeter.net/manual/skins/resources-folder/#Fonts">loaded at runtime</a>.
     * <br/>
     * <br/>
     * Default: {@code "Arial"}
     *
     * @see <a href="https://docs.rainmeter.net/tips/fonts-guide/"> Fonts Guide</a>
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#FontFace">Rainmeter documentation</a>
     */
    public Label setFontFace(Resource resource) {
        this.manageParameter("FontFace", resource);
        return this.getThis();
    }

    /**
     * Family name of the font to use for the text. The font must either be installed in Windows directly or be
     * <a href="https://docs.rainmeter.net/manual/skins/resources-folder/#Fonts">loaded at runtime</a>.
     * <br/>
     * <br/>
     * Default: {@code "Arial"}
     *
     * @see <a href="https://docs.rainmeter.net/tips/fonts-guide/"> Fonts Guide</a>
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#FontFace">Rainmeter documentation</a>
     */
    @Deprecated
    public Label setFontFace(String value) {
        this.manageParameter("FontFace", value);
        return this.getThis();
    }

    /**
     * Size of the font in points.
     * <br/>
     * <br/>
     * Default: {@code 10}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#FontSize">Rainmeter documentation</a>
     */
    public Label setFontSize(Integer value) {
        this.manageParameter("FontSize", value);
        return this.getThis();
    }

    /**
     * Color of the font.
     * <br/>
     * <br/>
     * Default: {@code {@link Color#BLACK}}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#FontColor">Rainmeter documentation</a>
     */
    public Label setFontColor(Color color) {
        this.manageParameter("FontColor", color);
        return this.getThis();
    }

    /**
     * The weight of the font in the range 1-999.<br/>
     * <br/>
     * The following are common values and their meanings. If the value is supported by the font family, it will be used. If not, the closest value that is supported by the font family will be used.<br/>
     * <br/>
     * 100 - Thin (Hairline)<br/>
     * 200 - Extra Light (Ultra Light)<br/>
     * 300 - Light<br/>
     * 400 - Regular (Normal)<br/>
     * 500 - Medium<br/>
     * 600 - Semi Bold (Demi Bold)<br/>
     * 700 - Bold<br/>
     * 800 - Extra Bold (Ultra Bold)<br/>
     * 900 - Black (Heavy)<br/>
     * 950 - Extra Black (Ultra Black)<br/>
     * <br/>
     * If the font does not support any additional weights, then 500 and below will use the font's normal weight, and 600 and above will simulate a bold effect.<br/>
     * <br/>
     * <br/>
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#FontWeight">Rainmeter documentation</a>
     */
    public Label setFontWeight(Integer weight) {
        this.manageParameter("FontWeight", weight);
        return this.getThis();
    }

    /**
     * Horizontal and vertical alignment of the string within the meter.
     * <br/>
     * The string will be aligned using the values of the {@code X} and {@code Y} settings as the anchor point.
     * So to align a string to {@link Align#CENTER_CENTER} within a meter with a width and height of 100,
     * set {@code X=50}, {@code Y=50} and {@code StringAlign=CenterCenter}.
     * <br/>
     * Default: {@link Align#LEFT_TOP}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#StringAlign">Rainmeter documentation</a>
     */
    public Label setStringAlign(Align value) {
        this.manageParameter("StringAlign", value);
        return this.getThis();
    }

    /**
     * Style of the string.
     * <br/>
     * Used to make font italic and bold but now bold option is deprecated.
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#StringStyle">Rainmeter documentation</a>
     */
    public Label setItalic(Boolean value) {
        if (value != null && value) {
            this.getParams().put("StringStyle", "Italic");
        } else {
            this.getParams().remove("StringStyle");
        }
        return this.getThis();
    }

    /**
     * Converts the string to a case.
     * <br/>
     * Default: {@link StringCase#NONE}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#StringCase">Rainmeter documentation</a>
     */
    public Label setStringCase(StringCase value) {
        this.manageParameter("StringCase", value);
        return this.getThis();
    }

    /**
     * Effect applied to a string.
     * <br/>
     * The size of the font used in the string has no effect on the fixed 1px size
     * of the {@link StringEffect#SHADOW} or {@link StringEffect#BORDER} effect.<br/>
     * <br/>
     * Due to how these effects are created, transparent colors on the string may have undesirable results.
     * <br/>
     * Default: {@link StringEffect#NONE}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#StringEffect">Rainmeter documentation</a>
     */
    public Label setStringEffect(StringEffect value) {
        this.manageParameter("StringEffect", value);
        return this.getThis();
    }

    /**
     * Color of the {@link #setStringEffect}.
     * <br/>
     * <br/>
     * Default: {@link Color#BLACK}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#FontEffectColor">Rainmeter documentation</a>
     */
    public Label setFontEffectColor(Color color) {
        this.manageParameter("FontEffectColor", color);
        return this.getThis();
    }

    /**
     * Controls how strings are truncated (clipped) or wrapped to fit in or expand the containing meter.
     * <br/>
     * Notes: The changing size of meters when {@link ClipString#AUTO} can cause truncation issues
     * with the overall window size of the skin, unless DynamicWindowSize=1 is set in the [Rainmeter] section of the skin.<br/>
     * <br/>
     * {@code ClipString#AUTO} will always wrap on word boundaries (spaces or tabs). Any single word that is longer
     * than the defined or maximum width will clip that line, rather than breaking the word in two.<br/>
     * <br/>
     * Default: {@link ClipString#DISABLED}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#ClipString">Rainmeter documentation</a>
     */
    public Label setClipString(ClipString value) {
        this.manageParameter("ClipString", value);
        return this.getThis();
    }

    /**
     * Sets a maximum width that the meter will expand to accommodate the string when {@link ClipString#AUTO}.
     * This setting is ignored if the {@code W} option is set..
     * <br/>
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#ClipStringW">Rainmeter documentation</a>
     */
    public Label setClipStringW(Integer value) {
        this.manageParameter("ClipStringW", value);
        return this.getThis();
    }

    /**
     * Sets a maximum height that the meter will expand to accommodate the string when {@link ClipString#AUTO}.
     * This setting is ignored if the {@code H} option is set.
     * <br/>
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#ClipStringH">Rainmeter documentation</a>
     */
    public Label setClipStringH(Integer value) {
        this.manageParameter("ClipStringH", value);
        return this.getThis();
    }

    /**
     * Defines the angle of the text in radians.<br/>
     * <br/>
     * Note: The size and position of the text are always calculated as if the text is horizontal.
     * This also means that the click-able field used for
     * <a href="https://docs.rainmeter.net/manual/mouse-actions/">Mouse Actions</a> does not change.
     * It remains where the text would be with no Angle.<br/>
     * <br/>
     * More information about using Angle can be found at
     * <a href="https://forum.rainmeter.net/viewtopic.php?p=104103">Working with Angle on a String Meter</a>.<br/>
     * <br/>
     * <br/>
     * Default: {@code 0.0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Angle">Rainmeter documentation</a>
     */
    public Label setAngle(Double value) {
        this.manageParameter("Angle", value);
        return this.getThis();
    }

    /**
     * If set to {@code true}, the value of bound measures are converted to a percentage.
     * <br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Percentual">Rainmeter documentation</a>
     */
    public Label setPercentual(Boolean value) {
        this.manageParameter("Percentual", value);
        return this.getThis();
    }

    /**
     * Number of decimals to display with numerical measure values.
     * <br/>
     * <br/>
     * Default: {@code 0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#NumOfDecimals">Rainmeter documentation</a>
     */
    public Label setNumOfDecimals(Integer value) {
        this.manageParameter("NumOfDecimals", value);
        return this.getThis();
    }

    /**
     * Scaling factor used for the measure values. The measure value is divided by the specified value.<br/>
     * <br/>
     * Note: If {@link #setAutoScale} is enabled, this option is ignored.<br/>
     * <br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Scale">Rainmeter documentation</a>
     */
    public Label setScale(Integer denominator, boolean showDecimalPoint) {
        if (denominator == null) {
            this.removeParameter("Scale");
            return this.getThis();
        }
        if (showDecimalPoint) {
            this.manageParameter("Scale", denominator + ".0");
        } else {
            this.manageParameter("Scale", denominator);
        }
        return this.getThis();
    }

    /**
     * Scaling factor used for the measure values. The measure value is divided by the specified value.<br/>
     * <br/>
     * Note: If {@link #setAutoScale} is enabled, this option is ignored.<br/>
     * <br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#Scale">Rainmeter documentation</a>
     */
    public Label setScale(Double denominator, boolean showDecimalPoint) {
        if (denominator == null) {
            this.removeParameter("Scale");
            return this.getThis();
        }
        String value = SkinUtils.print(denominator);
        if (value.contains(".")) {
            if (!showDecimalPoint) {
                throw new IllegalArgumentException();
            } else {
                this.manageParameter("Scale", value);
            }
        } else {
            if (showDecimalPoint) {
                this.manageParameter("Scale", denominator + ".0");
            } else {
                this.manageParameter("Scale", denominator);
            }

        }
        return this.getThis();
    }

    /**
     * Automatically scales the measure values. The scaled result is appended with k, M, G, etc. as appropriate.
     * <br/>
     * By default the value will be displayed with one decimal point of precision. Use {@link #setNumOfDecimals} to control this. <br/>
     * <br/>
     * Note: The value returned by AutoScale adds a space between the scaled number and the scale unit abbreviation.
     * To remove this space simply add {@link Measure#addSubstitute}(" ","") to the Measure that the AutoScale is being applied to. <br/>
     * <br/>
     * Default: {@link AutoScale#DISABLED}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/string/#AutoScale">Rainmeter documentation</a>
     */
    public Label setAutoScale(AutoScale value) {
        this.manageParameter("AutoScale", value);
        return this.getThis();
    }

    /**
     * Pair should contain &lt; regexp, inlineOption &gt;
     */
    public Label setInlineSettings(Collection<Pair<String, InlineSettings.InlineOption>> collection) {
        this.inlineSettings = new ArrayList<>(collection);
        return this.getThis();
    }

    /**
     * Pair should contain &lt; regexp, inlineOption &gt;
     */
    public Label addInlineSetting(String regExp, InlineSettings.InlineOption option) {
        this.inlineSettings.add(new Pair<>(regExp, option));
        return this.getThis();
    }

    public enum AutoScale {
        DISABLED("0"),
        /**
         * Scales by 1024.
         */
        BINARY("1"),
        /**
         * Scales by 1024 with kilo as the lowest unit.
         */
        BINARY_LIMITED("1k"),
        /**
         * Scales by 1000.
         */
        METRIC("2"),
        /**
         * Scales by 1000 with kilo as the lowest unit.
         */
        METRIC_LIMITED("2k");

        private final String value;

        AutoScale(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum StringCase {
        NONE("None"),
        UPPER("Upper"),
        LOWER("Lower"),
        PROPER("Proper");


        private final String value;

        StringCase(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum StringEffect {
        NONE("None"),
        /**
         * Makes a background copy of the string, offset 1px "right" and 1px "down", in the color defined in {@link #setFontEffectColor}
         */
        SHADOW("Shadow"),
        /**
         * Makes a background copy of the string, 2px "larger", and offset 1px "left" and 1px "up", in the color defined in {@link #setFontEffectColor}
         */
        BORDER("Border");


        private final String value;

        StringEffect(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Align {
        LEFT_TOP("LeftTop"), CENTER_TOP("CenterTop"), RIGHT_TOP("RightTop"),
        LEFT_CENTER("LeftCenter"), CENTER_CENTER("CenterCenter"), RIGHT_CENTER("RightCenter"),
        LEFT_BOTTOM("LeftBottom"), CENTER_BOTTOM("CenterBottom"), RIGHT_BOTTOM("RightBottom");

        private final String value;

        Align(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ClipString {
        /**
         * The string will not be clipped or wrapped.
         */
        DISABLED("0"),
        /**
         * The string will be clipped with an added ellipsis ... when it exceeds
         * the specified {@code W} (width) option on the meter. If the {@code H} (height) option is large enough
         * to allow multiple lines, the text is wrapped until the value of {@code H} is reached, then clipped.
         */
        ENABLED("1"),
        /**
         * The string will be clipped or wrapped based on the value of {@code W} and/or {@code H}.
         * If the width or height are not specified, the meter itself will change size to accommodate the string.
         * This setting works in conjunction with the {@code ClipStringW} and {@code ClipStringH} options,
         * to set a "maximum" size that the meter should expand to accommodate the string before clipping.
         */
        AUTO("2");

        private final String value;

        ClipString(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
