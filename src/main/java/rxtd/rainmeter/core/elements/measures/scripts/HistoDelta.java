package rxtd.rainmeter.core.elements.measures.scripts;

import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.formulas.Formula;
import rxtd.rainmeter.core.resources.Resource;
import rxtd.rainmeter.core.resources.ResourceFactory;

/**
 * Script is used to calculate delta between the current and the oldest saved values. Delta is returned as measure numeric/string values.
 */
public class HistoDelta extends ScriptBase<HistoDelta> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/core/scripts/plotManagement/Delta.lua", "HistDelta", true);

    public HistoDelta(String name) {
        super(name, SCRIPT);
    }

    /**
     * @param width Count of input values used to determine output values.
     */
    public HistoDelta setHistWidth(int width) {
        this.manageParameter("HistWidth", Integer.toString(width));
        return this;
    }

    /**
     * @param value Input value.
     */
    public HistoDelta setFormula(Formula value) {
        this.manageParameter("CurValue", value.toString());
        return this;
    }

    @Override
    protected HistoDelta getThis() {
        return this;
    }

    /**
     * Call it before first update if you are using measure values in CurValue
     */
    public Action bangReset() {
        return this.bangCallFunction("Reset");
    }
}
