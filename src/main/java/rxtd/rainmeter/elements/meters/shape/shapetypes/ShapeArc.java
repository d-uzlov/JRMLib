package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;

public class ShapeArc extends ShapeElementBase {
    public ShapeArc(double startX, double startY,
                    double endX, double endY,
                    double radiusX, double radiusY,
                    double rotationAngle,
                    SweepDirection sweepDirection,
                    ArcSize arcSize,
                    ShapeEnding shapeEnding) {
        this.setImage("Arc", ShapeUtils.createImage(false,
                startX, startY,
                endX, endY,
                radiusX, radiusY,
                rotationAngle,
                sweepDirection,
                arcSize,
                shapeEnding));
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
