package rxtd.rainmeter.elements.measures;

public class Net extends MeasureBase<Net> {
    public Net(String name, Type type) {
        super(name, type.getValue());
    }

    public Net(Type type) {
        this(null, type);
    }

    @Override
    protected Net getThis() {
        return this;
    }

    public Net useBestInterface() {
        this.manageParameter("Interface", "Best");
        return getThis();
    }

    public Net setInterface(Integer index) {
        this.manageParameter("Interface", index);
        return getThis();
    }

    public Net setInterfase(String name) {
        this.manageParameter("Interface", name);
        return getThis();
    }

    public Net setCumulative(Boolean value) {
        this.manageParameter("Cumulative", value);
        return getThis();
    }

    public Net setUseBits(Boolean value) {
        this.manageParameter("UseBits", value);
        return getThis();
    }


    public enum Type {
        NET_IN("NetIn"),
        NET_OUT("NetOut"),
        NET_TOTAL("NetTotal");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
