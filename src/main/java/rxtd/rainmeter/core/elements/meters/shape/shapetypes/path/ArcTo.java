package rxtd.rainmeter.core.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.core.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ShapeArc;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ShapeEnding;

public class ArcTo extends PathElementBase {

    public ArcTo(double endX, double endY,
                 double radiusX, double radiusY,
                 double rotationAngle,
                 ShapeArc.SweepDirection sweepDirection,
                 ShapeArc.ArcSize arcSize,
                 ShapeEnding shapeEnding) {

        this.setImage("Arc", ShapeUtils.createImage(false,
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

