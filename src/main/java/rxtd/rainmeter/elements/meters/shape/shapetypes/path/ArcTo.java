package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.ShapeArc;
import rxtd.rainmeter.elements.meters.shape.shapetypes.ShapeEnding;

public class ArcTo extends PathElementBase {
    private final double endX;
    private final double endY;
    private final double radiusX;
    private final double radiusY;
    private final double rotationAngle;
    private final ShapeArc.SweepDirection sweepDirection;
    private final ShapeArc.ArcSize arcSize;
    private final ShapeEnding shapeEnding;

    public ArcTo(double endX, double endY,
                 double radiusX, double radiusY,
                 double rotationAngle,
                 ShapeArc.SweepDirection sweepDirection,
                 ShapeArc.ArcSize arcSize,
                 ShapeEnding shapeEnding) {
        this.endX = endX;
        this.endY = endY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.rotationAngle = rotationAngle;
        this.sweepDirection = sweepDirection;
        this.arcSize = arcSize;
        this.shapeEnding = shapeEnding;

        this.setImage("Arc", ShapeUtils.createImage(false,
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

