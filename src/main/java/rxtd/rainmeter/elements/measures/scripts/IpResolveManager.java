package rxtd.rainmeter.elements.measures.scripts;

import rxtd.Pair;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.resources.ResourceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Script is used for managing the list of web sites. Main intend is to automatically adapt to the death of services.
 */
public class IpResolveManager extends ScriptBase<IpResolveManager> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/scripts/ipResolveManager/IpResolveManager.lua", "IpResolveManager", true);

    public IpResolveManager(String name) {
        super(name, SCRIPT);
    }

    public IpResolveManager() {
        this(null);
    }

    @Override
    protected IpResolveManager getThis() {
        return this;
    }

    /**
     * Every source is presented as two string.
     * <br/>
     * It is meant to be http address and a regexp for parsing, but it can be anything.
     */
    public IpResolveManager setSources(List<Pair<String, String>> sources) {
        if (sources == null || sources.size() == 0) {
            this.removeParameter("Sources");
            return getThis();
        }
        ArrayList<String> table = new ArrayList<>();
        for (var v : sources) {
            ArrayList<String> list = new ArrayList<>();
            list.add(v.key);
            list.add(v.value);
            String group = ScriptBase.makeLuaTableInitializer(list, true);
            table.add(group);
        }
        this.manageParameter("Sources", ScriptBase.makeLuaTableInitializer(table, false));
        return getThis();
    }

    /**
     * Change current source.
     */
    public Action bangNext() {
        return BangUtils.commandMeasure(this.getName(), "Next()", null);
    }

    /**
     * Add an error to this source.
     */
    public Action bangError() {
        return BangUtils.commandMeasure(this.getName(), "Error()", null);
    }

    /**
     * Remove an error from this source.
     */
    public Action bangSuccess() {
        return BangUtils.commandMeasure(this.getName(), "Success()", null);
    }

    /**
     * Get value1 from current source.
     */
    public Formula formulaUrl() {
        return this.inline("GetUrl", (String[]) null);
    }

    /**
     * Get value2 from current source.
     */
    public Formula formulaRegExp() {
        return this.inline("GetRegExp", (String[]) null);
    }
}
