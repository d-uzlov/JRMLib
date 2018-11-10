package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.resources.Resource;

import java.awt.Color;

/**
 * Bar displays a horizontal or vertical bar that fills
 * according to the <a href="https://docs.rainmeter.net/manual/measures/#Percentage">percentual value</a> of a measure.
 *
 * @see <a href="https://docs.rainmeter.net/manual/meters/bar/">Rainmeter documentation</a>
 */
public class Bar extends GeneralImage<Bar> {
    public Bar(String name) {
        super(name, "Bar");
    }

    public Bar() {
        this(null);
    }

    @Override
    protected Bar getThis() {
        return this;
    }

    /**
     * Name of the measure to use. The measure used must be able to return
     * <a href="https://docs.rainmeter.net/manual/measures/#Percentage">percentual values</a>.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/bar/#MeasureName">Rainmeter documentation</a>
     */
    public Bar setMeasure(Measure measure) {
        this.manageParameter("MeasureName", measure);
        return this.getThis();
    }

    /**
     * Path to an image to use for the bar instead of BarColor.
     * <br/>
     * The image is "revealed" to the percentage defined by the current value of the MeasureName bound to the meter.
     * <br/>
     * Note: The image size cannot be modified with the W or H general meter options, and will be displayed in the original image size. The bar will be constrained to the size of the image.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/bar/#MeasureName">Rainmeter documentation</a>
     */
    public Bar setBarImage(Resource image) {
        this.manageParameter("BarImage", image);
        return this.getThis();
    }

    /**
     * Color of the bar.<br/>
     * <br/>
     * Default: {@code 0,128,0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/bar/#BarColor">Rainmeter documentation</a>
     */
    public Bar setBarColor(Color color) {
        this.manageParameter("BarColor", color);
        return this.getThis();
    }

    /**
     * If {@code BarImage} is specified, defines the number of pixels on either side of the image that are always drawn
     * (i.e. top and bottom margins for vertical bars, left and right margins for horizontal bars).
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/bar/#BarBorderBarBorder">Rainmeter documentation</a>
     * @see #setBarImage
     */
    public Bar setBarBorder(Integer thickness) {
        this.manageParameter("BarBorder", thickness);
        return this.getThis();
    }

    /**
     * Orientation of the bar.<br/>
     * <br/>
     * Default: {@link Orientation#VERTICAL}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/bar/#BarOrientation">Rainmeter documentation</a>
     */
    public Bar setBarOrientation(Orientation orientation) {
        this.manageParameter("BarOrientation", orientation);
        return this.getThis();
    }

    /**
     * If set to {@code true}, the direction of the bar is flipped.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/bar/#Flip">Rainmeter documentation</a>
     */
    public Bar setFlip(Boolean value) {
        this.manageParameter("Flip", value);
        return this.getThis();
    }

}
