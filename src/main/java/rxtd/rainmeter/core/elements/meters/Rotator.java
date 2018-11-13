package rxtd.rainmeter.core.elements.meters;

import rxtd.rainmeter.core.resources.Resource;
import rxtd.rainmeter.core.elements.measures.Measure;

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
        return this.getThis();
    }

    public Rotator setMeasure(Measure measure) {
        this.manageParameter("MeasureName", measure);
        return this.getThis();
    }

    public Rotator setOffsetX(Double value) {
        this.manageParameter("OffsetX", value);
        return this.getThis();
    }

    public Rotator setOffsetY(Double value) {
        this.manageParameter("OffsetY", value);
        return this.getThis();
    }

    public Rotator setStartAngle(Double angle) {
        this.manageParameter("StartAngle", angle);
        return this.getThis();
    }

    public Rotator setRotationAngle(Double value) {
        this.manageParameter("ImageName", value);
        return this.getThis();
    }

    public Rotator setValueRemainder(Integer value) {
        this.manageParameter("ValueRemainder", value);
        return this.getThis();
    }

}
