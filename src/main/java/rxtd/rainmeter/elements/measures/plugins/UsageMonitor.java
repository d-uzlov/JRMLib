package rxtd.rainmeter.elements.measures.plugins;

public class UsageMonitor extends PluginBase<UsageMonitor> {
    private final static PluginResource PLUGIN = new InternalPluginResource("UsageMonitor");

    private UsageMonitor(String name) {
        super(name, PLUGIN);
    }

    private UsageMonitor() {
        this(null);
    }

    public static UsageMonitor byAlias(String alias) {
        UsageMonitor usageMonitor = new UsageMonitor();
        usageMonitor.setAlias(alias);
        return usageMonitor;
    }

    public static UsageMonitor byAlias(String name, String alias) {
        UsageMonitor usageMonitor = new UsageMonitor(name);
        usageMonitor.setAlias(alias);
        return usageMonitor;
    }

    @Override
    protected UsageMonitor getThis() {
        return this;
    }

    public UsageMonitor setAlias(String alias) {
        this.manageParameter("Alias", alias);
        return getThis();
    }

    public UsageMonitor setCategory(String category) {
        this.manageParameter("Category", category);
        return getThis();
    }

    public UsageMonitor setCounter(String counter) {
        this.manageParameter("Counter", counter);
        return getThis();
    }

    public UsageMonitor setIndex(int index) {
        this.manageParameter("Index", index);
        return getThis();
    }

    public UsageMonitor setName(String name) {
        this.manageParameter("Name", name);
        return getThis();
    }

    public UsageMonitor setUseRollup(Boolean value) {
        this.manageParameter("Rollup", value);
        return getThis();
    }

    public UsageMonitor setUsePercent(Boolean value) {
        this.manageParameter("Percent", value);
        return getThis();
    }

    public UsageMonitor setUseRawValue(Boolean value) {
        this.manageParameter("RawValue", value);
        return getThis();
    }

    public UsageMonitor setUsePIDToName(Boolean value) {
        this.manageParameter("PIDToName", value);
        return getThis();
    }

    public static final class Aliases {
        public final static String CPU = "CPU";
        public final static String RAM = "RAM";
        public final static String RAMSHARED = "RAMSHARED";
        public final static String IO = "IO";
        public final static String IOREAD = "IOREAD";
        public final static String IOWRITE = "IOWRITE";
        public final static String GPU = "GPU";
        public final static String VRAM = "VRAM";
        public final static String VRAMSHARED = "VRAMSHARED";

        private Aliases() {
        }
    }
}
