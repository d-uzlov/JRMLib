package rxtd.rainmeter.core.elements.measures;

import rxtd.rainmeter.core.formulas.Formula;

public class MString extends MeasureBase<MString> {
    public MString(String name) {
        super(name, "String");
    }

    public MString() {
        this(null);
    }

    @Override
    protected MString getThis() {
        return this;
    }

    public MString setString(String string) {
        this.manageParameter("String", string);
        return this.getThis();
    }

    public MString setString(Formula formula) {
        this.manageParameter("String", formula);
        return this.getThis();
    }
}
