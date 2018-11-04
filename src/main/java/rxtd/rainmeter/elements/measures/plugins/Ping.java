package rxtd.rainmeter.elements.measures.plugins;

import rxtd.rainmeter.actions.Action;

public class Ping extends PluginBase<Ping> {
    private final static PluginResource PLUGIN = new InternalPluginResource("PingPlugin");

    public Ping(String name) {
        super(name, PLUGIN);
    }

    public Ping() {
        this(null);
    }

    @Override
    protected Ping getThis() {
        return this;
    }

    public Ping setDestAddress(String value) {
        this.manageParameter("DestAddress", value);
        return getThis();
    }

    public Ping setUpdateRate(Integer value) {
        this.manageParameter("UpdateRate", value);
        return getThis();
    }

    public Ping setTimeout(Integer value) {
        this.manageParameter("Timeout", value);
        return getThis();
    }

    public Ping setTimeoutValue(Integer value) {
        this.manageParameter("TimeoutValue", value);
        return getThis();
    }

    public Ping setFinishAction(Action value) {
        this.manageParameter("FinishAction", value);
        return getThis();
    }
}
