package rxtd.rainmeter.core.elements.measures;

import rxtd.rainmeter.core.formulas.Formula;

public class Loop extends MeasureBase<Loop> {
    public Loop(String name) {
        super(name, "Loop");
    }

    public Loop() {
        this(null);
    }

    @Override
    protected Loop getThis() {
        return this;
    }

    @Override
    public Loop setAverageSize(Integer size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Loop setMaxValue(Formula value) {
        this.manageParameter("EndValue", value);
        return this.getThis();
    }

    @Override
    public Loop setMaxValue(Double value) {
        this.manageParameter("EndValue", value);
        return this.getThis();
    }

    @Override
    public Loop setMinValue(Formula value) {
        this.manageParameter("StartValue", value);
        return this.getThis();
    }

    @Override
    public Loop setMinValue(Double value) {
        this.manageParameter("StartValue", value);
        return this.getThis();
    }

    public Loop setIncrement(Double increment) {
        this.manageParameter("Increment", increment);
        return this.getThis();
    }

    public Loop setLoopCount(Integer loopCount) {
        this.manageParameter("LoopCount", loopCount);
        return this.getThis();
    }
}
