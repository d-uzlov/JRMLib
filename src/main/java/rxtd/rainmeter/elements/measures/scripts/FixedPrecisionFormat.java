package rxtd.rainmeter.elements.measures.scripts;

import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.resources.ResourceFactory;
import rxtd.rainmeter.variables.Variable;

import java.util.Arrays;
import java.util.Collection;

/**
 * Class is used to format numbers so that they always have the same number of digits.
 * <br/>
 * For example, 123 would be 123.0, and 1234567 would be 1.235M
 */
public class FixedPrecisionFormat extends ScriptBase<FixedPrecisionFormat> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/scripts/fixedPrecisionFormat/FPF2.lua", "FPF2", true);

    public FixedPrecisionFormat(String name) {
        super(name, SCRIPT);
    }

    public FixedPrecisionFormat() {
        this(null);
    }

    /**
     * @param divisor Value that is used for division to determine order.
     *                <br/>
     *                Default {@code 1024}.
     */
    public FixedPrecisionFormat setDivisor(Integer divisor) {
        this.manageParameter("Divisor", divisor);
        return this.getThis();
    }

    /**
     * @param divisor Value that is used for division to determine order.
     *                <br/>
     *                Default {@code 1024}.
     */
    public FixedPrecisionFormat setDivisor(Double divisor) {
        this.manageParameter("Divisor", divisor);
        return this.getThis();
    }

    /**
     * @param value If {@code true} script reads {@code Formula} option on update, format it and use as a return value.
     *              <br/>
     *              Default {@code false}.
     * @see #setFormula
     * @see #setDefaultPrecision
     */
    public FixedPrecisionFormat setUseUpdate(Boolean value) {
        this.manageParameter("UseUpdate", value);
        return this.getThis();
    }

    /**
     * @param value Formula to be formatted on every update. Only valid if {@code UseUpdate} option is set.
     * @see #setUseUpdate
     * @see #setDefaultPrecision
     */
    public FixedPrecisionFormat setFormula(String value) {
        this.manageParameter("Formula", value);
        return this.getThis();
    }

    /**
     * Used to determine default precision which is used when it is not specified.
     * <br/>
     * The only way to set precision for values that are formatted on update.
     * <br/>
     * Default {@code 4}.
     */
    public FixedPrecisionFormat setDefaultPrecision(Integer value) {
        this.manageParameter("DefaultPrecision", value);
        return this.getThis();
    }

    /**
     * If {@code true} then value will have the biggest suffix possible for this value and this precision. Else - the smallest.
     * <br/>
     * For example, {@code 1 000 000} can be formatted as {@code 1.000M} (greedy) or {@code 1000k} (not greedy).
     * <br/>
     * Default {@code true}.
     */
    public FixedPrecisionFormat setGreedy(Boolean value) {
        this.manageParameter("Greedy", value);
        return this.getThis();
    }

    /**
     * Values are multiplied for this value before processing. Intended to be used when determining suffixes for values < 1. Mini, micro, etc.
     * <br/>
     * Default {@code 1}.
     */
    public FixedPrecisionFormat setInitScale(Double divisor) {
        this.manageParameter("InitScale", divisor);
        return this.getThis();
    }

    /**
     * Suffix that is applied before main suffix. Use it when all of your suffixes have same first letters.
     * <br/>
     * Default {@code " "} (one space).
     */
    public FixedPrecisionFormat setBeforeSuffix(String suffix) {
        this.manageParameter("Suffix1", "\"" + suffix + "\"");
        return this.getThis();
    }

    /**
     * List of possible suffixes.
     * <br/>
     * Default {@code "", "k", "M", "G", "T", "P", "E", "Z", "Y"}.
     */
    public FixedPrecisionFormat setSuffixes(String... suffixes) {
        this.setSuffixes(Arrays.asList(suffixes));
        return this.getThis();
    }

    /**
     * List of possible suffixes.
     * <br/>
     * Default {@code "", "k", "M", "G", "T", "P", "E", "Z", "Y"}.
     */
    public FixedPrecisionFormat setSuffixes(Collection<String> suffixes) {
        this.manageParameter("Suffixes", makeLuaTableInitializer(suffixes, true));
        return this.getThis();
    }

    /**
     * Suffix that is applied after main suffix. Use it when all of your suffixes have same last letters.
     * <br/>
     * Default {@code ""} (empty).
     */
    public FixedPrecisionFormat setPostfix(String postfix) {
        this.manageParameter("Postfix", "\"" + postfix + "\"");
        return this.getThis();
    }

    /**
     * Inline Lua that formats given number.
     *
     * @param input     something that should be converted to a number
     * @param precision number of decimals
     */
    public Formula formulaFormatNumber(Formula input, int precision) {
        return this.inline("FormatNumber", input.toString(), Integer.toString(precision));
    }

    /**
     * Same as {@link #formulaFormatNumber} but the formula is evaluated inside of a script instead of autoevaluation by Rainmeter.
     * <br/>
     * This method was created due to Rainmeter nor parsing formulas correctly when used in inline Lua section variables.
     * This can be fixed with new {@code Nested Variables} syntax or with this method.
     */
    public Formula formulaFormatFormula(Formula input, int precision) {
        return this.inline("FormatFormula", "'" + input.toString() + "'", Integer.toString(precision));
    }

    /**
     * Same as {@link #formulaFormatNumber} but takes variable name which should contain number.
     * <br/>
     * This method was created because if you change the variable in the bang and then use this formula in the same bang
     * then value of the variable will not be updated until next evaluation of a bang.
     */
    public Formula formulaFormatVariable(Variable input, int precision) {
        return this.inline("FormatVariable", "'" + input.getName() + "'", Integer.toString(precision));
    }

    @Override
    protected FixedPrecisionFormat getThis() {
        return this;
    }
}
