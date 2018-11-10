package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.Padding;

public class Image extends GeneralImage<Image> {

    public Image(String name) {
        super(name, "Image");
    }

    public Image() {
        this(null);
    }

    @Override
    protected Image getThis() {
        return this;
    }

    public Image setImageName(TextBuilder builder) {
        this.setTextBuilder("ImageName", builder);
        return this.getThis();
    }

    public Image setImageName(String name) {
        this.manageParameter("ImageName", name);
        return this.getThis();
    }

    public Image setPreserveAspectRatio(AspectRationMode mode) {
        this.manageParameter("PreserveAspectRatio", mode);
        return this.getThis();
    }

    public Image setScaleMargins(Padding margins) {
        this.manageParameter("ScaleMargins", margins);
        return this.getThis();
    }

    public Image setTile(Boolean value) {
        this.manageParameter("Tile", value);
        return this.getThis();
    }

    public enum AspectRationMode {
        STRETCH("0"),
        FIT("1"),
        CROP("2");

        private final String value;

        AspectRationMode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
