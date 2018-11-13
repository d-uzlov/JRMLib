package rxtd.rainmeter.core.elements.meters.shape.shapetypes;

public enum ShapeEnding {
    OPEN("0"),
    CLOSE("1");

    private final String value;

    ShapeEnding(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
