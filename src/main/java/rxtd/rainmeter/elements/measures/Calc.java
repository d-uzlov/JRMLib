package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.formulas.Formula;

public class Calc extends MeasureBase<Calc> {
    public Calc(String name) {
        super(name, "Calc");
    }

    public Calc() {
        this(null);
    }

    @Override
    protected Calc getThis() {
        return this;
    }

    /**
     * If used formula have anything dynamic
     * other than Measures without parameters
     * then DynamicVariables flag should be set.
     * <p>
     * Note that for example :MaxValue is sometimes static
     * and hence can work without this flag
     */
    public Calc setFormula(Formula formula) {
        this.manageParameter("Formula", formula.toCalcString());
        return getThis();
    }

    public Calc setUpdateRandom(Boolean value) {
        this.manageParameter("UpdateRandom", value);
        return getThis();
    }

    public Calc setUniqueRandom(Boolean value) {
        this.manageParameter("UniqueRandom", value);
        return getThis();
    }

    public Calc setLowBound(Double value) {
        this.manageParameter("LowBound", value);
        return getThis();
    }

    public Calc setHighBound(Double value) {
        this.manageParameter("HighBound", value);
        return getThis();
    }
}