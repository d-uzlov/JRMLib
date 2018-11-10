package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.resources.Resource;

public class Bitmap extends GeneralImage<Bitmap> {
    public Bitmap(String name) {
        super(name, "Bitmap");
    }

    public Bitmap() {
        this(null);
    }

    @Override
    protected Bitmap getThis() {
        return this;
    }

    @Override
    public Bitmap setImageCrop(Crop crop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bitmap setImageRotate(Double angle) {
        throw new UnsupportedOperationException();
    }

    public Bitmap setMeasure(Measure measure) {
        this.manageParameter("MeasureName", measure);
        return this.getThis();
    }

    public Bitmap setBitmapImage(Resource image) {
        this.manageParameter("BitmapImage", image);
        return this.getThis();
    }

    public Bitmap setBitmapFrames(Integer count) {
        this.manageParameter("BitmapFrames", count);
        return this.getThis();
    }

    public Bitmap setBitmapTransitionFrames(Integer count) {
        this.manageParameter("BitmapTransitionFrames", count);
        return this.getThis();
    }

    public Bitmap setBitmapZeroFrame(Boolean value) {
        this.manageParameter("BitmapZeroFrame", value);
        return this.getThis();
    }

    public Bitmap setBitmapExtend(Boolean value) {
        this.manageParameter("BitmapExtend", value);
        return this.getThis();
    }

    public Bitmap setBitmapDigits(Integer value) {
        this.manageParameter("BitmapDigits", value);
        return this.getThis();
    }

    public Bitmap setBitmapAlign(Align align) {
        this.manageParameter("BitmapAlign", align);
        return this.getThis();
    }

    public Bitmap setBitmapSeparation(Integer value) {
        this.manageParameter("BitmapSeparation", value);
        return this.getThis();
    }

    public enum Align {
        LEFT("Left"),
        CENTER("Center"),
        RIGHT("Right");

        private final String value;

        Align(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
