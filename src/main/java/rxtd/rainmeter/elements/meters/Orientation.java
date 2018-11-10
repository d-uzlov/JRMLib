package rxtd.rainmeter.elements.meters;

public enum Orientation {
    HORIZONTAL("Horizontal"),
    VERTICAL("Vertical");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
