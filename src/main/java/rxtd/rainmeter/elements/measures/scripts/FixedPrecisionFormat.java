package rxtd.rainmeter.elements.measures.scripts;

import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.resources.ResourceFactory;
import rxtd.rainmeter.variables.Variable;

import java.util.Arrays;
import java.util.Collection;

public class FixedPrecisionFormat extends ScriptBase<FixedPrecisionFormat> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/scripts/fixedPrecisionFormat/FPF2.lua", "FPF2", true);

    public FixedPrecisionFormat(String name) {
        super(name, SCRIPT);
    }

    public FixedPrecisionFormat() {
        this(null);
    }

    public FixedPrecisionFormat setDivisor(Integer divisor) {
        this.manageParameter("Divisor", divisor);
        return getThis();
    }

    public FixedPrecisionFormat setUseUpdate(Boolean value) {
        this.manageParameter("UseUpdate", value);
        return getThis();
    }

    public FixedPrecisionFormat setDefaultPrecision(Integer value) {
        this.manageParameter("DefaultPrecision", value);
        return getThis();
    }

    public FixedPrecisionFormat setDivisor(Double divisor) {
        this.manageParameter("Divisor", divisor);
        return getThis();
    }

    public FixedPrecisionFormat setInitScale(Double divisor) {
        this.manageParameter("InitScale", divisor);
        return getThis();
    }

    public FixedPrecisionFormat setBeforeSuffix(String suffix) {
        this.manageParameter("Suffix1", suffix);
        return getThis();
    }

    public FixedPrecisionFormat setSuffixes(String... suffixes) {
        this.setSuffixes(Arrays.asList(suffixes));
        return getThis();
    }

    public FixedPrecisionFormat setSuffixes(Collection<String> suffixes) {
        this.manageParameter("Suffixes", makeLuaTableInitializer(suffixes, true));
        return getThis();
    }

    public FixedPrecisionFormat setPostfix(String postfix) {
        this.manageParameter("Postfix", postfix);
        return getThis();
    }

    public Formula formulaFormatFormula(Formula input, int precision) {
        return this.inline("FormatFormula", "'" + input.toString() + "'", Integer.toString(precision));
    }

    public Formula formulaFormatNumber(Formula input, int precision) {
        return this.inline("FormatNumber", input.toString(), Integer.toString(precision));
    }

    public Formula formulaFormatVariable(Variable input, int precision) {
        return this.inline("FormatVariable", "'" + input.getName() + "'", Integer.toString(precision));
    }

    @Override
    protected FixedPrecisionFormat getThis() {
        return this;
    }
}
