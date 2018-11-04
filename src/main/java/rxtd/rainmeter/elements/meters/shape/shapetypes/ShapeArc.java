package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;

public class ShapeArc extends ShapeElementBase {
    private final double startX;
    private final double startY;
    private final double endX;
    private final double endY;
    private final double radiusX;
    private final double radiusY;
    private final double rotationAngle;
    private final SweepDirection sweepDirection;
    private final ArcSize arcSize;
    private final ShapeEnding shapeEnding;

    public ShapeArc(double startX, double startY,
                    double endX, double endY,
                    double radiusX, double radiusY,
                    double rotationAngle,
                    SweepDirection sweepDirection,
                    ArcSize arcSize,
                    ShapeEnding shapeEnding) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.rotationAngle = rotationAngle;
        this.sweepDirection = sweepDirection;
        this.arcSize = arcSize;
        this.shapeEnding = shapeEnding;

        this.setImage("Arc", ShapeUtils.createImage(false,
                this.startX, this.startY,
                this.endX, this.endY,
                this.radiusX, this.radiusY,
                this.rotationAngle,
                this.sweepDirection,
                this.arcSize,
                this.shapeEnding));
    }

    public enum SweepDirection {
        CLOCKWISE("0"),
        COUNTER_CLOCKWISE("1");

        private final String value;

        SweepDirection(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ArcSize {
        SMALL("0"),
        LARGE("1");

        private final String value;

        ArcSize(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
