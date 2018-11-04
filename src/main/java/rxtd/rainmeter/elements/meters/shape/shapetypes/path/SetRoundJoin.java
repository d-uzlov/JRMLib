package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

public enum SetRoundJoin implements PathModifier {
    ROUND("1"),
    ANGLE("0");

    private final String value;

    SetRoundJoin(String value) {
        this.value = "SetRoundJoin " + value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
