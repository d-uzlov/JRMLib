package rxtd.rainmeter.core.elements.measures.scripts;

import rxtd.Pair;
import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.formulas.Formula;
import rxtd.rainmeter.core.resources.Resource;
import rxtd.rainmeter.core.resources.ResourceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Script is used for managing the list of web sites. Main intend is to automatically adapt to the death of services.
 */
public class IpResolveManager extends ScriptBase<IpResolveManager> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/core/scripts/ipResolveManager/IpResolveManager.lua", "IpResolveManager", true);

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
            return this.getThis();
        }
        ArrayList<String> table = new ArrayList<>();
        for (var v : sources) {
            ArrayList<String> list = new ArrayList<>();
            list.add(v.key);
            list.add(v.value);
            String group = makeLuaTableInitializer(list, true);
            table.add(group);
        }
        this.manageParameter("Sources", makeLuaTableInitializer(table, false));
        return this.getThis();
    }

    /**
     * Change current source.
     */
    public Action bangNext() {
        return this.bangCallFunction("Next");
    }

    /**
     * Add an error to this source.
     */
    public Action bangError() {
        return this.bangCallFunction("Error");
    }

    /**
     * Remove an error from this source.
     */
    public Action bangSuccess() {
        return this.bangCallFunction("Success");
    }

    /**
     * Get value1 from current source.
     */
    public Formula formulaUrl() {
        return this.inline("GetUrl");
    }

    /**
     * Get value2 from current source.
     */
    public Formula formulaRegExp() {
        return this.inline("GetRegExp");
    }
}
