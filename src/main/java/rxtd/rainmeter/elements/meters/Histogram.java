package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.resources.Resource;

import java.awt.Color;

/**
 * Histogram displays a histogram for the current and past values of one or two measures.
 *
 * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/">Rainmeter documentation</a>
 */
public class Histogram extends MeterBase<Histogram> {
    public Histogram(String name) {
        super(name, "Histogram");
    }

    public Histogram() {
        this(null);
    }

    /**
     * Primary (required) measure to use for the histogram. The measure must be able to return
     * * <a href="https://docs.rainmeter.net/manual/measures/#Percentage">percentual values</a>.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setMeasure(Measure measure) {
        this.manageParameter("MeasureName", measure);
        return getThis();
    }

    /**
     * Secondary (optional) measure to use for the histogram. The measure must be able to return
     * * <a href="https://docs.rainmeter.net/manual/measures/#Percentage">percentual values</a>.
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setMeasure2(Measure measure) {
        this.manageParameter("MeasureName2", measure);
        return getThis();
    }

    /**
     * If set to {@code true}, the histogram is automatically scaled to show all the values.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setAutoscale(Boolean autoscale) {
        this.manageParameter("Autoscale", autoscale);
        return getThis();
    }

    /**
     * Starting point of the graph.<br/>
     * <br/>
     * Default: {@link StartPlace#RIGHT}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setGraphStart(StartPlace start) {
        this.manageParameter("GraphStart", start);
        return getThis();
    }

    /**
     * Orientation of the graph elements.<br/>
     * <br/>
     * Default: {@link Orientation#VERTICAL}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setGraphOrientation(Orientation orientation) {
        this.manageParameter("GraphOrientation", orientation);
        return getThis();
    }

    /**
     * If set to {@code true}, the meter is flipped vertically.<br/>
     * <br/>
     * Default: {@code false}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setFlip(Boolean value) {
        this.manageParameter("Flip", value);
        return getThis();
    }

    /**
     * Sets the color for the histogram with one measure.<br/>
     * <br/>
     * Default: {@code 0,128,0}
     *
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setColor(Color color) {
        this.removeParameter("BothColor");
        this.manageParameter("PrimaryColor", color);
        return getThis();
    }

    /**
     * Sets the colors for the histogram.
     *
     * @param primary   is the color for the primary histogram. Default is dark green {@code 0,128,0}.
     * @param secondary is the color for the secondary histogram. Default is red {@code 255,0,0}.
     * @param both      is the color used where the histogram elements overlap. Default is yellow {@code 255,255,0}.
     * @see <a href="https://docs.rainmeter.net/manual/meters/histogram/#MeasureName">Rainmeter documentation</a>
     */
    public Histogram setColors(Color primary, Color secondary, Color both) {
        this.manageParameter("PrimaryColor", primary);
        this.manageParameter("SecondaryColor", secondary);
        this.manageParameter("BothColor", both);
        return getThis();
    }

    public Histogram setImages(Resource primary, Resource secondary, Resource both) {
        this.manageParameter("PrimaryImage", primary);
        this.manageParameter("SecondaryImage", secondary);
        this.manageParameter("BothImage", both);
        return getThis();
    }

    public Histogram setImagePaths(String primary, String secondary, String both) {
        this.manageParameter("PrimaryImagePath", primary);
        this.manageParameter("SecondaryImagePath", secondary);
        this.manageParameter("BothImagePath", both);
        return getThis();
    }

    public Histogram setImageCrops(GeneralImage.Crop primary, GeneralImage.Crop secondary, GeneralImage.Crop both) {
        this.manageParameter("PrimaryImageCrop", GeneralImage.generateImageCrop(primary));
        this.manageParameter("SecondaryImageCrop", GeneralImage.generateImageCrop(secondary));
        this.manageParameter("BothImageCrop", GeneralImage.generateImageCrop(both));
        return getThis();
    }

    public Histogram setImageTints(Color primary, Color secondary, Color both) {
        this.manageParameter("PrimaryImageTint", primary);
        this.manageParameter("SecondaryImageTint", secondary);
        this.manageParameter("BothImageTint", both);
        return getThis();
    }

    public Histogram setImageAplhas(Integer primary, Integer secondary, Integer both) {
        this.manageParameter("PrimaryImageAlpha", primary);
        this.manageParameter("SecondaryImageAlpha", secondary);
        this.manageParameter("BothImageAlpha", both);
        return getThis();
    }

    public Histogram setImageFlips(GeneralImage.ImageFlip primary, GeneralImage.ImageFlip secondary, GeneralImage.ImageFlip both) {
        this.manageParameter("PrimaryImageFlip", primary);
        this.manageParameter("SecondaryImageFlip", secondary);
        this.manageParameter("BothImageFlip", both);
        return getThis();
    }

    public Histogram setImageRotates(Double primary, Double secondary, Double both) {
        this.manageParameter("PrimaryImageRotate", primary);
        this.manageParameter("SecondaryImageRotate", secondary);
        this.manageParameter("BothImageRotate", both);
        return getThis();
    }

    public Histogram setImageMatrices(double[][] primary, double[][] secondary, double[][] both) {
        GeneralImage.setColorMatrix("PrimaryImageFlip", this, primary);
        GeneralImage.setColorMatrix("SecondaryImageFlip", this, secondary);
        GeneralImage.setColorMatrix("BothImageFlip", this, both);
        return getThis();
    }

    @Override
    protected Histogram getThis() {
        return this;
    }
}
