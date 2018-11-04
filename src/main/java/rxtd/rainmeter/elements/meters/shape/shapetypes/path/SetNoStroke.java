package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

public enum SetNoStroke implements PathModifier {
    WITH_STROKE("0"),
    NO_STROKE("1");

    private final String value;

    SetNoStroke(String value) {
        this.value = "SetNoStroke " + value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
