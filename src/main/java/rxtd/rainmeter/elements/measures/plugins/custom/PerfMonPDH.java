package rxtd.rainmeter.elements.measures.plugins.custom;

import rxtd.rainmeter.elements.measures.plugins.ExternalPluginResource;
import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Replaces PerfMon, NomFerp and UsageMonitor plugins.<br/>
 * Covered version is 2.0.1.3
 *
 * @see <a href="https://forum.rainmeter.net/viewtopic.php?f=18&t=28937">Post on Rainmeter forum</a>
 */
public class PerfMonPDH extends PluginBase<PerfMonPDH> {
    private final static PluginResource PLUGIN = new VirtualPluginResource("PerfMonPDH", null);

    public PerfMonPDH(String name) {
        super(name, PLUGIN);
        this.manageParameter("Type", "Parent");
    }

    @Override
    protected PerfMonPDH getThis() {
        return this;
    }

    public PerfMonPDH setCategory(String category) {
        this.manageParameter("ObjectName", category);
        return getThis();
    }

    public PerfMonPDH setCounterList(String... counters) {
        return this.setCounterList(Arrays.asList(counters));
    }

    public PerfMonPDH setCounterList(List<String> counters) {
        if (counters != null && counters.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("CounterList", counters);
        return getThis();
    }

    public PerfMonPDH setSortBy(SortBy sortType) {
        this.manageParameter("SortBy", sortType);
        return getThis();
    }

    public PerfMonPDH setSortCounterIndex(Integer index) {
        this.manageParameter("SortCounterIndex", index);
        return getThis();
    }

    public PerfMonPDH setSortOrder(SortOrder category) {
        this.manageParameter("SortOrder", category);
        return getThis();
    }

    public PerfMonPDH setBlacklist(String... values) {
        return this.setBlacklist(Arrays.asList(values));
    }

    public PerfMonPDH setBlacklist(Collection<String> values) {
        if (values != null && values.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("Blacklist", values);
        return getThis();
    }

    public PerfMonPDH setWhitelist(String... values) {
        return this.setWhitelist(Arrays.asList(values));
    }

    public PerfMonPDH setWhitelist(Collection<String> values) {
        if (values != null && values.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("Whitelist", values);
        return getThis();
    }

    public PerfMonPDH setInstanceIndexOffet(Integer value) {
        this.manageParameter("InstanceIndexOffet", value);
        return getThis();
    }

    public PerfMonPDH setNameFormat(NameFormat value) {
        this.manageParameter("NameFormat", value);
        return getThis();
    }

    public PerfMonPDH setRollup(Boolean value) {
        this.manageParameter("Rollup", value);
        return getThis();
    }

    public PerfMonPDH setSortRollupFunction(RollupFunction value) {
        this.manageParameter("SortRollupFunction", value);
        return getThis();
    }

    public Child createChild(String name) {
        return this.new Child(name);
    }

    public enum SortBy {
        NONE("None"),
        INSTANCE_NAME("InstanceName"),
        RAW_COUNTER("RawCounter"),
        FORMATTED_COUNTER("FormattedCounter");

        private final String value;

        SortBy(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum SortOrder {
        ASCENDING("Ascending"),
        DESCENDING("Descending");

        private final String value;

        SortOrder(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum NameFormat {
        ORIGINAL("Original"),
        GPU_PROCESS_NAME("GpuProcessName"),
        GPU_ENG_TYPE("GpuEngType"),
        LOGICAL_DISK_DRIVE_LETTER("LogicalDiskDriveLetter"),
        LOGICAL_DISK_MOUNT_POINT("LogicalDiskMountPoint");

        private final String value;

        NameFormat(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum RollupFunction {
        SUM("Sum"),
        AVERAGE("Average"),
        MINIMUM("Minimum"),
        MAXIMUM("Maximum"),
        COUNT("Count"),
        INDEX("Index");

        private final String value;

        RollupFunction(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum ChildType {
        GET_INSTANCE_COUNT_UNFILTERED("GetInstanceCountUnfiltered"),
        GET_INSTANCE_COUNT_FILTERED("GetInstanceCountFiltered"),
        GET_INSTANCE_NAME("GetInstanceName"),
        GET_RAW_COUNTER("GetRawCounter"),
        GET_FORMATTED_COUNTER("GetFormattedCounter"),
        GET_FORMATTED_COUNTER_ALL("GetFormattedCounterAll"),
        GET_RAW_COUNTER_ALL("GetRawCounterAll");

        private final String value;

        ChildType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum ResultString {
        NUMBER("Number"),
        ORIGINAL_INSTANCE_NAME("OriginalInstanceName"),
        UNIQUE_INSTANCE_NAME("UniqueInstanceName"),
        DISPLAY_INSTANCE_NAME("DisplayInstanceName"),
        ROLLUP_INSTANCE_NAME("RollupInstanceName");

        private final String value;

        ResultString(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public class Child extends PluginBase<Child> {

        private Child(String name) {
            super(name, PLUGIN);
            this.setParent(PerfMonPDH.this.getName());

            this.addBeforeWriteListener(() -> {
                String parentLocalPath = PerfMonPDH.this.getLocalPathProvider().get();
                this.wrap(parentLocalPath);
            });
        }

        private void setParent(String measureName) {
            this.manageParameter("Parent", measureName);
        }

        public Child setType(ChildType type) {
            this.manageParameter("Type", type);
            return getThis();
        }

        public Child setCounterIndex(Integer value) {
            this.manageParameter("CounterIndex", value);
            return getThis();
        }

        public Child setInstanceName(String name) {
            this.manageParameter("InstanceName", name);
            return getThis();
        }

        public Child setInstanceIndex(Integer value) {
            this.manageParameter("InstanceIndex", value);
            return getThis();
        }

        public Child setResultString(ResultString type) {
            this.manageParameter("ResultString", type);
            return getThis();
        }

        public Child setRollupFunction(RollupFunction function) {
            this.manageParameter("RollupFunction", function);
            return getThis();
        }

        @Override
        public Child wrap(String pluginPath) {
            if (StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass() != Child.class) {
                throw new UnsupportedOperationException("This method can not be used on Child. Use it on parent measure.");
            }
            return super.wrap(pluginPath);
        }

        @Override
        public Child wrap(ExternalPluginResource plugin) {
            if (StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass() != Child.class) {
                throw new UnsupportedOperationException("This method can not be used on Child. Use it on parent measure.");
            }
            return super.wrap(plugin);
        }

        @Override
        protected Child getThis() {
            return this;
        }
    }
}
