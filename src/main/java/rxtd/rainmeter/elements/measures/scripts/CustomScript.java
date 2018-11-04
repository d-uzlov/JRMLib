package rxtd.rainmeter.elements.measures.scripts;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.elements.CustomElement;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;

public class CustomScript extends ScriptBase<CustomScript> implements CustomElement<CustomScript> {
    public CustomScript(String name, Resource script) {
        super(name, script);
    }

    @Override
    protected CustomScript getThis() {
        return this;
    }

    @Override
    public CustomScript setOption(String name, String value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public CustomScript setOption(String name, Integer value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public CustomScript setOption(String name, Boolean value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public CustomScript setOption(String name, Double value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public CustomScript setOption(String name, Resource value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public CustomScript setOption(String name, Formula value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public CustomScript setOption(String name, Action value) {
        this.manageParameter(name, value);
        return getThis();
    }

    @Override
    public Formula inline(String variable) {
        return super.inline(variable);
    }

    @Override
    public Formula inline(String function, String... args) {
        return super.inline(function, args);
    }
}
