package rxtd.rainmeter.core.elements.measures;

public class CPU extends MeasureBase<CPU> {
    public CPU(String name) {
        super(name, "CPU");
    }

    public CPU() {
        this(null);
    }

    @Override
    protected CPU getThis() {
        return this;
    }

    public CPU setProcessor(Integer vcore) {
        this.manageParameter("Processor", vcore);
        return this.getThis();
    }
}
