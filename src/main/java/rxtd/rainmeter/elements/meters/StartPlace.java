package rxtd.rainmeter.elements.meters;

public enum StartPlace {
    RIGHT("Right"),
    LEFT("Left");

    private final String value;

    StartPlace(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
