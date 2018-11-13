package rxtd.rainmeter.core.elements.measures.plugins.custom;

import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.core.elements.CustomElement;
import rxtd.rainmeter.core.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.core.formulas.Formula;
import rxtd.rainmeter.core.resources.Resource;

public class CustomPlugin extends PluginBase<CustomPlugin> implements CustomElement<CustomPlugin> {
    public CustomPlugin(String name, PluginResource plugin) {
        super(name, plugin);
    }

    public CustomPlugin(PluginResource plugin) {
        this(null, plugin);
    }

    @Override
    protected CustomPlugin getThis() {
        return this;
    }

    @Override
    public CustomPlugin setOption(String name, String value) {
        this.manageParameter(name, value);
        return this.getThis();
    }

    @Override
    public CustomPlugin setOption(String name, Integer value) {
        this.manageParameter(name, value);
        return this.getThis();
    }

    @Override
    public CustomPlugin setOption(String name, Boolean value) {
        this.manageParameter(name, value);
        return this.getThis();
    }

    @Override
    public CustomPlugin setOption(String name, Double value) {
        this.manageParameter(name, value);
        return this.getThis();
    }

    @Override
    public CustomPlugin setOption(String name, Resource value) {
        this.manageParameter(name, value);
        return this.getThis();
    }

    @Override
    public CustomPlugin setOption(String name, Formula value) {
        this.manageParameter(name, value);
        return this.getThis();
    }

    @Override
    public CustomPlugin setOption(String name, Action value) {
        this.manageParameter(name, value);
        return this.getThis();
    }
}
