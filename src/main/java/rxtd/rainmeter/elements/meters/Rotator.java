package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.resources.Resource;

public class Rotator extends GeneralImage<Rotator> {
    public Rotator(String name) {
        super(name, "Rotator");
    }

    public Rotator() {
        this(null);
    }

    @Override
    protected Rotator getThis() {
        return this;
    }

    public Rotator setImage(Resource image) {
        this.manageParameter("ImageName", image);
        return getThis();
    }

    public Rotator setMeasure(Measure measure) {
        this.manageParameter("MeasureName", measure);
        return getThis();
    }

    public Rotator setOffsetX(Double value) {
        this.manageParameter("OffsetX", value);
        return getThis();
    }

    public Rotator setOffsetY(Double value) {
        this.manageParameter("OffsetY", value);
        return getThis();
    }

    public Rotator setStartAngle(Double angle) {
        this.manageParameter("StartAngle", angle);
        return getThis();
    }

    public Rotator setRotationAngle(Double value) {
        this.manageParameter("ImageName", value);
        return getThis();
    }

    public Rotator setValueRemainder(Integer value) {
        this.manageParameter("ValueRemainder", value);
        return getThis();
    }

}
