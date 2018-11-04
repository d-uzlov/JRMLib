package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.attribute;

public enum LineJoin {
    MITER("Miter"),
    BEVEL("Bevel"),
    ROUND("Round"),
    MITER_OR_BEVEL("MiterOrBevel");

    private final String value;

    LineJoin(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
