package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.resources.Resource;

import java.awt.Color;

public abstract class GeneralImage<T extends GeneralImage<T>> extends MeterBase<T> {
    protected GeneralImage(String name, String meter) {
        super(name, meter);
    }

    protected static <T extends Meter<T>> void setColorMatrix(String option, Meter<T> meter, double[][] matrix) {
        if (matrix == null) {
            for (int i = 0; i < 5; i++) {
                meter.getParams().remove(option + i);
            }
            return;
        }
        if (matrix.length != 5) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < 5; i++) {
            if (matrix[i] == null || matrix[i].length != 5) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < 5; i++) {
            StringBuilder string = new StringBuilder(SkinUtils.print(matrix[i][0]));
            for (int j = 1; j < 5; j++) {
                string.append(";").append(SkinUtils.print(matrix[i][j]));
            }
            meter.getParams().put(option + i, string.toString());
        }
    }

    protected static String generateImageCrop(Crop crop) {
        if (crop.x == 0 && crop.y == 0 && crop.w == 0 && crop.h == 0) {
            return null;
        }
        String value = crop.x + "," + crop.y + "," + crop.w + "," + crop.h;
        if (crop.origin != null) {
            value += "," + crop.origin.toString();
        }
        return value;
    }

    public T setImage(Resource image) {
        this.manageParameter("ImagePath", image);
        return getThis();
    }

    public T setImageCrop(Crop crop) {
        this.manageParameter("ImageCrop", generateImageCrop(crop));
        return getThis();
    }

    public T setGreyscale(Boolean value) {
        this.manageParameter("Greyscale", value);
        return getThis();
    }

    public T setImageTint(Color tint) {
        this.manageParameter("ImageTint", tint);
        return getThis();
    }

    public T setImageAlpha(Integer alpha) {
        this.manageParameter("ImageAlpha", alpha);
        return getThis();
    }

    public T setImageFlip(ImageFlip flip) {
        this.manageParameter("ImageFlip", flip);
        return getThis();
    }

    public T setImageRotate(Double angle) {
        this.manageParameter("ImageRotate", angle);
        return getThis();
    }

    public T setUseExifOrientation(Boolean value) {
        this.manageParameter("UseExifOrientation", value);
        return getThis();
    }

    public T setColorMatrix(double[][] matrix) {
        setColorMatrix("ColorMatrix", this, matrix);
        return getThis();
    }

    public enum CropOrigin {
        TOP_LEFT("1"),
        TOP_RIGHT("2"),
        BOTTOM_LEFT("3"),
        BOTTON_RIGHT("4"),
        CENTER("5");

        private final String value;

        CropOrigin(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum ImageFlip {
        NONE("None"),
        HORIZONTAL("Horizontal"),
        VERTICAL("Vertical"),
        BOTH("Both");

        private final String value;

        ImageFlip(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static class Crop {
        int x;
        int y;
        int w;
        int h;
        CropOrigin origin;

        public Crop(int x, int y, int w, int h, CropOrigin origin) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.origin = origin;
        }
    }
}
