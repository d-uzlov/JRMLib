package rxtd.rainmeter.core.elements.meters;

import rxtd.rainmeter.core.elements.measures.MeasureBase;
import rxtd.rainmeter.core.elements.measures.Measure;

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
        return this.getThis();
    }

    public Roundline setStartAngle(Double value) {
        this.manageParameter("StartAngle", value);
        return this.getThis();
    }

    public Roundline setRotationAngle(Double value) {
        this.manageParameter("RotationAngle", value);
        return this.getThis();
    }

    public Roundline setLineStart(Integer value) {
        this.manageParameter("LineStart", value);
        return this.getThis();
    }

    public Roundline setLineLength(Integer value) {
        this.manageParameter("LineLength", value);
        return this.getThis();
    }

    public Roundline setLineWidth(Integer value) {
        this.manageParameter("LineWidth", value);
        return this.getThis();
    }

    public Roundline setLineColor(Color value) {
        this.manageParameter("LineColor", value);
        return this.getThis();
    }

    public Roundline setSolid(Boolean value) {
        this.manageParameter("Solid", value);
        return this.getThis();
    }

    public Roundline setControlAngle(Boolean value) {
        this.manageParameter("ControlAngle", value);
        return this.getThis();
    }

    public Roundline setControlStart(Boolean value) {
        this.manageParameter("ControlStart", value);
        return this.getThis();
    }

    public Roundline setStartShift(Integer value) {
        this.manageParameter("StartShift", value);
        return this.getThis();
    }

    public Roundline setControlLength(Boolean value) {
        this.manageParameter("ControlLength", value);
        return this.getThis();
    }

    public Roundline setLengthShift(Integer value) {
        this.manageParameter("LengthShift", value);
        return this.getThis();
    }

    public Roundline setValueRemainder(Integer value) {
        this.manageParameter("ValueRemainder", value);
        return this.getThis();
    }
}
