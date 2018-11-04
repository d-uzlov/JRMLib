package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute;

public enum StrokeCap {
    FLAT("Flat"),
    ROUND("Round"),
    SQUARE("Square"),
    TRIANGLE("Triangle");

    private final String value;

    StrokeCap(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
