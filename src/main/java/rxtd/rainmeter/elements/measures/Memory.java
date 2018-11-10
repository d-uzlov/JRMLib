package rxtd.rainmeter.elements.measures;

public class Memory extends MeasureBase<Memory> {
    public Memory(String name, Type type) {
        super(name, type.getValue());
    }

    public Memory(Type type) {
        this(null, type);
    }

    public Memory setTotal(Boolean value) {
        this.manageParameter("Total", value);
        return this.getThis();
    }

    @Override
    protected Memory getThis() {
        return this;
    }

    public enum Type {
        RAM("PhysicalMemory"),
        COMMIT("SwapMemory");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
