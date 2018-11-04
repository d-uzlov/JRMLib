package rxtd;

public class Pair<T1, T2> {
    public final T1 key;
    public final T2 value;

    public Pair(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }
}
