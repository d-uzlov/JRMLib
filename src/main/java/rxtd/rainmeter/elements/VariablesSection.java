package rxtd.rainmeter.elements;

import rxtd.rainmeter.variables.Variable;

import java.util.HashMap;
import java.util.Map;

public class VariablesSection extends ElementBase<VariablesSection> {
    private final Map<String, Variable> variables = new HashMap<>();

    public VariablesSection() {
        super("Variables");
    }

    /**
     * @throws IllegalArgumentException if one variable name used twice
     */
    public VariablesSection addVariable(Variable variable) {
        if (this.variables.containsKey(variable.getName())
                && !this.variables.get(variable.getName()).equals(variable)) {
            throw new IllegalArgumentException("variable name used twice: " + variable.getName());
        }
        this.variables.put(variable.getName(), variable);
        this.getParams().put(variable.getName(), variable.getInitialValue());
        return this;
    }

    /**
     * @return Current number of included variables
     */
    public int count() {
        return this.variables.size();
    }

    @Override
    protected VariablesSection getThis() {
        return this;
    }
}
