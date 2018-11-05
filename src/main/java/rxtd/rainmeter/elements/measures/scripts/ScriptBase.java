package rxtd.rainmeter.elements.measures.scripts;

import rxtd.rainmeter.elements.measures.MeasureBase;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;

import java.util.Collection;

public abstract class ScriptBase<T extends ScriptBase<T>> extends MeasureBase<T> {
    public ScriptBase(String name, Resource script) {
        super(name, "Script");
        this.manageParameter("ScriptFile", script);
    }

    protected static String escape(String value) {
        return "'" + value.replace("\'", "\\'") + "'";
    }

    protected static String makeLuaTableInitializer(Collection<String> values, boolean isStringValue) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (var v : values) {
            String value = isStringValue ? escape(v) : v;
            sb.append(value).append(',');
        }
        sb.append('}');
        return sb.toString();
    }

    /**
     * @param variable name of the internal script variable
     * @return inline formula that fetches specified variable from script
     */
    protected Formula inline(String variable) {
        return new Formula("[&" + this.getName() + ":" + variable + "]");
    }

    /**
     * @param function name of the internal script function
     * @param args     list of args for function. May be {@code null} if none needed.
     * @return inline formula that calls specified formula with specified args.
     */
    protected Formula inline(String function, String... args) {
        StringBuilder value = new StringBuilder("[&" + this.getName() + ":" + function + "(");
        if (args != null) {
            value.append(String.join(",", args));
        }
        value.append(")]");
        return new Formula(value.toString());
    }
}