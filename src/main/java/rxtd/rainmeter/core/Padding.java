package rxtd.rainmeter.core;

public class Padding {
    public final int left;
    public final int top;
    public final int right;
    public final int bottom;

    public Padding(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public String toString() {
        return this.left + "," + this.top + "," + this.right + "," + this.bottom;
    }
}
