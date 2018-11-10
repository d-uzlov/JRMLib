package rxtd.rainmeter.elements.measures.plugins;

public class PerfMon extends PluginBase<PerfMon> {
    private final static PluginResource PLUGIN = new InternalPluginResource("PerfMon");

    public PerfMon(String name) {
        super(name, PLUGIN);
    }

    public PerfMon() {
        this(null);
    }

    public PerfMon setCategory(String category) {
        this.manageParameter("PerfMonObject", category);
        return this.getThis();
    }

    public PerfMon setCounter(String counter) {
        this.manageParameter("PerfMonCounter", counter);
        return this.getThis();
    }

    public PerfMon setInstance(String instance) {
        this.manageParameter("PerfMonInstance", instance);
        return this.getThis();
    }

    public PerfMon setUseDifference(Boolean value) {
        this.manageParameter("PerfMonDifference", value);
        return this.getThis();
    }

    @Override
    protected PerfMon getThis() {
        return this;
    }
}
