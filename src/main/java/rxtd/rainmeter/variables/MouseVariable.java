package rxtd.rainmeter.variables;

public class MouseVariable implements Variable {
    private final String name;
    private final String usage;
    private final String escaped;
    private final String initialValue;

    MouseVariable(String name, String initialValue) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.usage = "[$" + this.name + "]";
        this.initialValue = initialValue;
        this.escaped = "[$*" + this.name + "*]";
    }

    MouseVariable(String name) {
        this(name, null);
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInitialValue() {
        return initialValue;
    }

    @Override
    public String getEscaped() {
        return this.escaped;
    }
}
