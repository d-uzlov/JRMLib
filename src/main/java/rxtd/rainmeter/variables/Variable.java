package rxtd.rainmeter.variables;

public interface Variable {
    String getUsage();

    String getName();

    String getInitialValue();

    String getEscaped();
}
