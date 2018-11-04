package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.elements.measures.MeasureBase;

import java.awt.Color;

public class Roundline extends MeasureBase<Roundline> {
    public Roundline(String name) {
        super(name, "Roundline");
    }

    public Roundline() {
        this(null);
    }

    @Override
    protected Roundline getThis() {
        return this;
    }

    public Roundline setMeasure(Measure measure) {
        this.manageParameter("MeasureName", measure);
        return getThis();
    }

    public Roundline setStartAngle(Double value) {
        this.manageParameter("StartAngle", value);
        return getThis();
    }

    public Roundline setRotationAngle(Double value) {
        this.manageParameter("RotationAngle", value);
        return getThis();
    }

    public Roundline setLineStart(Integer value) {
        this.manageParameter("LineStart", value);
        return getThis();
    }

    public Roundline setLineLength(Integer value) {
        this.manageParameter("LineLength", value);
        return getThis();
    }

    public Roundline setLineWidth(Integer value) {
        this.manageParameter("LineWidth", value);
        return getThis();
    }

    public Roundline setLineColor(Color value) {
        this.manageParameter("LineColor", value);
        return getThis();
    }

    public Roundline setSolid(Boolean value) {
        this.manageParameter("Solid", value);
        return getThis();
    }

    public Roundline setControlAngle(Boolean value) {
        this.manageParameter("ControlAngle", value);
        return getThis();
    }

    public Roundline setControlStart(Boolean value) {
        this.manageParameter("ControlStart", value);
        return getThis();
    }

    public Roundline setStartShift(Integer value) {
        this.manageParameter("StartShift", value);
        return getThis();
    }

    public Roundline setControlLength(Boolean value) {
        this.manageParameter("ControlLength", value);
        return getThis();
    }

    public Roundline setLengthShift(Integer value) {
        this.manageParameter("LengthShift", value);
        return getThis();
    }

    public Roundline setValueRemainder(Integer value) {
        this.manageParameter("ValueRemainder", value);
        return getThis();
    }
}
