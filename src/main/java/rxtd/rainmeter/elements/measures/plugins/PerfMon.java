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
        return getThis();
    }

    public PerfMon setCounter(String counter) {
        this.manageParameter("PerfMonCounter", counter);
        return getThis();
    }

    public PerfMon setInstance(String instance) {
        this.manageParameter("PerfMonInstance", instance);
        return getThis();
    }

    public PerfMon setUseDifference(Boolean value) {
        this.manageParameter("PerfMonDifference", value);
        return getThis();
    }

    @Override
    protected PerfMon getThis() {
        return this;
    }
}
