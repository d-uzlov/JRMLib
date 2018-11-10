package rxtd.rainmeter.elements.measures.scripts;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.elements.measures.MeasureBase;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;

import java.util.Arrays;
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
     * @param function name of the Lua function to call.
     * @return Bang that calls specified function without args.
     */
    protected Action bangCallFunction(String function) {
        return this.bangCallFunction(function, (String[]) null);
    }

    /**
     * @param function name of the Lua function to call.
     * @param args     list of the function args. May be null if none needed.
     * @return Bang that calls specified function with specified args.
     */
    protected Action bangCallFunction(String function, @Nullable String... args) {
        String command = function + "(";
        if (args != null) {
            command += SkinUtils.joinList(Arrays.asList(args), ",");
        }
        command += ")";
        return super.bangCommand(command);
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
     * @return inline formula that calls specified function with specified args.
     */
    protected Formula inline(String function, @Nullable String... args) {
        StringBuilder value = new StringBuilder("[&" + this.getName() + ":" + function + "(");
        if (args != null) {
            value.append(String.join(",", args));
        }
        value.append(")]");
        return new Formula(value.toString());
    }
}
