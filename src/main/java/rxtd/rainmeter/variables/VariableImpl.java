package rxtd.rainmeter.variables;

import rxtd.rainmeter.SkinUtils;

public class VariableImpl implements Variable {
    private final String name;
    private final String usage;
    private final String escaped;
    private final String initialValue;

    VariableImpl(String name, String initialValue) {
        if (name == null || !SkinUtils.isAllASCII(name)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.usage = "[#" + this.name + "]";
        this.initialValue = initialValue;
        this.escaped = "[$*" + this.name + "*]";
    }

    VariableImpl(String name) {
        this(name, null);
    }

    @Override
    public String getUsage() {
        return this.usage;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getInitialValue() {
        return this.initialValue;
    }

    @Override
    public String getEscaped() {
        return this.escaped;
    }
}
