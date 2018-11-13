package rxtd.rainmeter.core.elements;

public enum BevelType {
    NO_BEVEL("0"),
    RAISED("1"),
    SUNKEN("2");

    private final String value;

    BevelType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
