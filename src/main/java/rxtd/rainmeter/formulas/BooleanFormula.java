package rxtd.rainmeter.formulas;

public class BooleanFormula extends Formula {
    protected BooleanFormula(String value, String calcValue, String escapedValue) {
        super(value, calcValue, escapedValue);
    }

    public BooleanFormula(boolean value) {
        super(value ? "1" : "0", value ? "1" : "0", value ? "1" : "0");
    }
}
