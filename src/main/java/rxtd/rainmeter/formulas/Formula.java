package rxtd.rainmeter.formulas;

import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.elements.meters.Meter;
import rxtd.rainmeter.variables.Variable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Formula {
    private String value;
    private String calcValue;
    private String escapedValue;

    protected Formula(String value, String calcValue, String escapedValue) {
        this.setValue(value, calcValue, escapedValue);
    }

    public Formula(int number) {
        this(Integer.toString(number), Integer.toString(number), Integer.toString(number));
    }

    public Formula(double number) {
        this(SkinUtils.print(number), SkinUtils.print(number), SkinUtils.print(number));
    }

    public Formula(BigDecimal number) {
        this(number.toPlainString(), number.toPlainString(), number.toPlainString());
    }

    public Formula(Variable variable) {
        String value = variable.getUsage();
        String escapedValue = variable.getEscaped();
        this.setValue(value, value, escapedValue);
    }

    public Formula(Measure measure) {
        String value = "[&" + measure.getName() + "]";
        String calcValue = measure.getName();
        String escapedValue = "[&*" + measure.getName() + "*]";
        this.setValue(value, calcValue, escapedValue);
    }

    public Formula(Measure measure, MeasureParameters parameter) {
        String value = "[&" + measure.getName() + ":" + parameter.getValue() + "]";
        String escapedValue = "[&*" + measure.getName() + ":" + parameter.getValue() + "*]";
        if (parameter == MeasureParameters.NUMBER_VALUE) {
            this.setValue(value, measure.getName(), escapedValue);
        } else {
            this.setValue(value, value, escapedValue);
        }
    }

    public Formula(Meter meter, MeterParameters parameter) {
        String value = "[&" + meter.getName() + ":" + parameter.getValue() + "]";
        String escapedValue = "[&*" + meter.getName() + ":" + parameter.getValue() + "*]";
        this.setValue(value, value, escapedValue);
    }

    public Formula(Measure measure, Integer decimals, Double divisor, boolean percentual, Boolean useMin) {
        StringBuilder builder = new StringBuilder(measure.getName());
        List<String> suffixes = new ArrayList<>();
        if (decimals != null) {
            suffixes.add(decimals.toString());
        }
        if (divisor != null) {
            suffixes.add("/" + SkinUtils.print(divisor));
        }
        if (percentual) {
            suffixes.add(MeasureParameters.PERCENTUAL.getValue());
        }
        if (useMin != null) {
            suffixes.add(useMin ? MeasureParameters.MIN.getValue() : MeasureParameters.MAX.getValue());
        }
        for (int i = 0; i < suffixes.size(); i++) {
            String s = i == 0 ? ":" : ",";
            builder.append(s).append(suffixes.get(i));
        }
        String value = "[&" + builder.toString() + "]";
        String escapedValue = "[&*" + builder.toString() + "*]";
        this.setValue(value, value, escapedValue);
    }

    public Formula(String manuallyConstructed) {
        String value = manuallyConstructed;
        this.setValue(value, value, value);
    }

    public Formula(String functionName, Formula... args) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(functionName);
        builder.append("(");
        if (args != null && args.length > 0) {
            builder.append(args[0]);
            for (int i = 1; i < args.length; i++) {
                builder.append(",").append(args[i]);
            }
        }
        builder.append(")");
        builder.append(")");
        String value = builder.toString();
        this.setValue(value, value, value);
    }

    public BooleanFormula equals(Formula formula) {
        String s = "=";
        String value = this.cover(this.toString() + s + formula.toString());
        String calcValue = this.cover(this.toCalcString() + s + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + s + formula.toEscapedString());
        return new BooleanFormula(value, calcValue, escapedValue);
    }

    public BooleanFormula notEqual(Formula formula) {
        String s = "<>";
        String value = this.cover(this.toString() + s + formula.toString());
        String calcValue = this.cover(this.toCalcString() + s + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + s + formula.toEscapedString());
        return new BooleanFormula(value, calcValue, escapedValue);
    }

    public BooleanFormula moreOrEqual(Formula formula) {
        String s = ">=";
        String value = this.cover(this.toString() + s + formula.toString());
        String calcValue = this.cover(this.toCalcString() + s + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + s + formula.toEscapedString());
        return new BooleanFormula(value, calcValue, escapedValue);
    }

    public BooleanFormula moreThan(Formula formula) {
        String s = ">";
        String value = this.cover(this.toString() + s + formula.toString());
        String calcValue = this.cover(this.toCalcString() + s + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + s + formula.toEscapedString());
        return new BooleanFormula(value, calcValue, escapedValue);
    }

    public BooleanFormula lessOrEqual(Formula formula) {
        String s = "<=";
        String value = this.cover(this.toString() + s + formula.toString());
        String calcValue = this.cover(this.toCalcString() + s + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + s + formula.toEscapedString());
        return new BooleanFormula(value, calcValue, escapedValue);
    }

    public BooleanFormula lessThan(Formula formula) {
        String s = "<";
        String value = this.cover(this.toString() + s + formula.toString());
        String calcValue = this.cover(this.toCalcString() + s + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + s + formula.toEscapedString());
        return new BooleanFormula(value, calcValue, escapedValue);
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String toCalcString() {
        return this.calcValue;
    }

    public String toEscapedString() {
        return this.escapedValue;
    }

    protected void setValue(String value, String calcValue, String escapedValue) {
        this.value = value;
        this.calcValue = calcValue;
        this.escapedValue = escapedValue;
    }

    private Formula binaryOperator(Formula formula, String operator) {
        String value = this.cover(this.toString() + operator + formula.toString());
        String calcValue = this.cover(this.toCalcString() + operator + formula.toCalcString());
        String escapedValue = this.cover(this.toEscapedString() + operator + formula.toEscapedString());
        return new Formula(value, calcValue, escapedValue);
    }

    public Formula add(Formula formula) {
        return this.binaryOperator(formula, "+");
    }

    public Formula add(double number) {
        return this.binaryOperator(new Formula(number), "+");
    }

    public Formula add(int number) {
        return this.binaryOperator(new Formula(number), "+");
    }

    public Formula subtract(Formula formula) {
        return this.binaryOperator(formula, "-");
    }

    public Formula subtract(double number) {
        return this.binaryOperator(new Formula(number), "-");
    }

    public Formula subtract(int number) {
        return this.binaryOperator(new Formula(number), "-");
    }

    public Formula multiply(Formula formula) {
        return this.binaryOperator(formula, "*");
    }

    public Formula multiply(double number) {
        return this.binaryOperator(new Formula(number), "*");
    }

    public Formula multiply(int number) {
        return this.binaryOperator(new Formula(number), "*");
    }

    public Formula divide(Formula formula) {
        return this.binaryOperator(formula, "/");
    }

    public Formula divide(double number) {
        return this.binaryOperator(new Formula(number), "/");
    }

    public Formula divide(int number) {
        return this.binaryOperator(new Formula(number), "/");
    }

    public Formula power(Formula formula) {
        return this.binaryOperator(formula, "**");
    }

    public Formula power(double number) {
        return this.binaryOperator(new Formula(number), "**");
    }

    public Formula power(int number) {
        return this.binaryOperator(new Formula(number), "**");
    }

    public Formula inverse() {
        String s = "-";
        String value = this.cover(s + this.toString());
        String calcValue = this.cover(s + this.toCalcString());
        return new Formula(value, calcValue, value);
    }

    protected String cover(String string) {
        return "(" + string + ")";
    }

    public enum MeterParameters {
        X("X"),
        Y("Y"),
        W("W"),
        H("H");

        private final String value;

        MeterParameters(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum MeasureParameters {
        NUMBER_VALUE(""),
        PERCENTUAL("%"),
        MIN("MinValue"),
        MAX("MaxValue"),
        ESCAPE_REG_EXP("EscapeRegExp"),
        ENCODE_URL("EncodeURL");

        private final String value;

        MeasureParameters(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static final class BuiltIn {
        public static Formula cos(Formula arg) {
            return new Formula("Cos", arg);
        }

        public static Formula sin(Formula arg) {
            return new Formula("Sin", arg);
        }

        public static Formula round(Formula arg) {
            return new Formula("Round", arg);
        }

        public static Formula abs(Formula arg) {
            return new Formula("Abs", arg);
        }
    }
}
