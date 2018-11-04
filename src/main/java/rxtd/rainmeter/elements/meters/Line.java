package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.elements.measures.Measure;

import java.awt.Color;

/**
 * Line displays the measure values as a series of data points connected by straight line segments.
 *
 * @see <a href="https://docs.rainmeter.net/manual/meters/line/">Rainmeter documentation</a>
 */
public class Line extends MeterBase<Line> {
    private int lineCount = 0;

    public Line(String name) {
        super(name, "Line");
    }

    public Line() {
        this(null);
    }

    @Override
    protected Line getThis() {
        return this;
    }

    /**
     * Adds a line with all of its parameters to this meter. Indices are calculated automatically.
     *
     * @param measure Measure to use as the source for a line. The measure must be able to return
     *                <a href="https://docs.rainmeter.net/manual/measures/#Percentage">percentual values</a>.
     * @param color   Color for a line.
     * @param scale   Scales (multiplies) the measure value to use for a line by the specified number.<br/>
     *                Note: If AutoScale is enabled, this option is ignored.
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#MeasureName">Rainmeter documentation</a>
     */
    public Line addLine(Measure measure, Color color, Double scale) {
        if (measure == null) {
            throw new IllegalArgumentException();
        }
        this.lineCount++;
        String suffix = "";
        if (this.lineCount > 1) {
            suffix = Integer.toString(this.lineCount);
        }
        this.getParams().put("MeasureName" + suffix, measure.getName());
        this.manageParameter("LineColor" + suffix, color);
        this.manageParameter("Scale" + suffix, scale);
        this.manageParameter("LineCount", this.lineCount);
        return getThis();
    }

    /**
     * Adds a line with all of its parameters to this meter. Indices are calculated automatically.
     *
     * @param measure Measure to use as the source for a line. The measure must be able to return
     *                <a href="https://docs.rainmeter.net/manual/measures/#Percentage">percentual values</a>.
     * @param color   Color for a line.
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#MeasureName">Rainmeter documentation</a>
     */
    public Line addLine(Measure measure, Color color) {
        this.addLine(measure, color, null);
        return getThis();
    }

    /**
     * Width of the line(s) in pixels.
     * <br/>
     * <br/>
     * Default: {@code 1}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#LineWidth">Rainmeter documentation</a>
     */
    public Line setLinesWidth(Integer width) {
        this.manageParameter("LineWidth", width);
        return getThis();
    }

    /**
     * If set to {@code true}, the lines are automatically scaled so that the largest value is visible in the meter.
     * Otherwise the largest maximum value of the all of the measures used is used as the scale.
     * <br/>
     * This behavior can ba customized with {@link rxtd.rainmeter.elements.measures.scripts.PlotManager}.
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#AutoScale">Rainmeter documentation</a>
     */
    public Line setAutoScale(Boolean value) {
        this.manageParameter("AutoScale", value);
        return getThis();
    }

    /**
     * If set to {@code true}, horizontal marker lines are displayed behind the lines.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#HorizontalLines">Rainmeter documentation</a>
     */
    public Line setHorizontalLines(Boolean value) {
        this.manageParameter("HorizontalLines", value);
        return getThis();
    }

    /**
     * Color of the horizontal marker lines.<br/>
     * <br/>
     * Default: {@link Color#BLACK}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#LineWidth">Rainmeter documentation</a>
     */
    public Line setHorizontalLineColor(Color color) {
        this.manageParameter("HorizontalLineColor", color);
        return getThis();
    }

    /**
     * Starting point of the graph.<br/>
     * <br/>
     * Default: {@link StartPlace#RIGHT}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#GraphStart">Rainmeter documentation</a>
     */
    public Line setGraphStart(StartPlace start) {
        this.manageParameter("GraphStart", start);
        return getThis();
    }

    /**
     * Orientation of the graph elements.<br/>
     * <br/>
     * Default: {@link Orientation#VERTICAL}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#LineWidth">Rainmeter documentation</a>
     */
    public Line setGraphOrientation(Orientation orientation) {
        this.manageParameter("GraphOrientation", orientation);
        return getThis();
    }

    /**
     * If set to {@code true}, the meter is flipped vertically.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#LineWidth">Rainmeter documentation</a>
     */
    public Line setFlip(Boolean value) {
        this.manageParameter("Flip", value);
        return getThis();
    }

    /**
     * Determines how the line (stroke) width is treated when {@code TransformationMatrix} is used on the meter.
     * <br/>
     * <br/>
     * Default: {@link TransformStroke#NORMAL}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#TransformStroke">Rainmeter documentation</a>
     * @see TransformStroke
     */
    public Line setTransformStroke(TransformStroke mode) {
        this.manageParameter("TransformStroke", mode);
        return getThis();
    }

    /**
     * Determines how the line (stroke) width is treated when {@code TransformationMatrix} is used on the meter.
     * <br/>
     * <br/>
     * Default: {@link TransformStroke#NORMAL}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/line/#TransformStroke">Rainmeter documentation</a>
     * @see Line#setTransformStroke
     */
    public enum TransformStroke {
        /**
         * The line width will be impacted by any scale or skew transforms from TransformationMatrix.
         *
         * @see <a href="https://docs.rainmeter.net/manual/meters/line/#TransformStroke">Rainmeter documentation</a>
         * @see Line#setTransformStroke
         */
        NORMAL("Normal"),
        /**
         * The line width will not be impacted by any transforms from {@code TransformationMatrix}. The width will be fixed to the width defined in {@code LineWidth}.
         *
         * @see <a href="https://docs.rainmeter.net/manual/meters/line/#TransformStroke">Rainmeter documentation</a>
         * @see Line#setTransformStroke
         * @see Line#setLinesWidth
         */
        FIXED("Fixed");

        private final String value;

        TransformStroke(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
