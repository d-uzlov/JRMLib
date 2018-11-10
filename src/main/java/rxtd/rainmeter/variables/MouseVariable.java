package rxtd.rainmeter.variables;

public class MouseVariable implements Variable {
    private final String name;
    private final String usage;
    private final String escaped;

    MouseVariable(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.usage = "[$" + this.name + "]";
        this.escaped = "[$*" + this.name + "*]";
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
        throw new UnsupportedOperationException();
    }

    @Override
    public String getEscaped() {
        return this.escaped;
    }
}
