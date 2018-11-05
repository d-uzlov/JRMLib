package rxtd.rainmeter.elements.measures;

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

    public CPU setProseccor(Integer vcore) {
        this.manageParameter("Processor", vcore);
        return getThis();
    }
}