package rxtd.rainmeter.elements.measures.plugins.custom;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

import java.util.ArrayList;
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
    private final List<Child> children = new ArrayList<>();

    public PerfMonPDH(String name) {
        super(name, PLUGIN);
        this.manageParameter("Type", "Parent");
    }

    @Override
    protected PerfMonPDH getThis() {
        return this;
    }

    @Override
    public PerfMonPDH wrap(@Nullable PluginResource plugin) {
        for (var c : this.children) {
            c.wrap(plugin);
        }
        return super.wrap(plugin);
    }

    public PerfMonPDH setCategory(String category) {
        this.manageParameter("ObjectName", category);
        return this.getThis();
    }

    public PerfMonPDH setCounterList(String... counters) {
        return this.setCounterList(Arrays.asList(counters));
    }

    public PerfMonPDH setCounterList(List<String> counters) {
        if (counters != null && counters.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("CounterList", counters);
        return this.getThis();
    }

    public PerfMonPDH setSortBy(SortBy sortType) {
        this.manageParameter("SortBy", sortType);
        return this.getThis();
    }

    public PerfMonPDH setSortCounterIndex(Integer index) {
        this.manageParameter("SortCounterIndex", index);
        return this.getThis();
    }

    public PerfMonPDH setSortOrder(SortOrder category) {
        this.manageParameter("SortOrder", category);
        return this.getThis();
    }

    public PerfMonPDH setBlacklist(String... values) {
        return this.setBlacklist(Arrays.asList(values));
    }

    public PerfMonPDH setBlacklist(Collection<String> values) {
        if (values != null && values.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("Blacklist", values);
        return this.getThis();
    }

    public PerfMonPDH setWhitelist(String... values) {
        return this.setWhitelist(Arrays.asList(values));
    }

    public PerfMonPDH setWhitelist(Collection<String> values) {
        if (values != null && values.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("Whitelist", values);
        return this.getThis();
    }

    public PerfMonPDH setInstanceIndexOffset(Integer value) {
        this.manageParameter("InstanceIndexOffset", value);
        return this.getThis();
    }

    public PerfMonPDH setNameFormat(NameFormat value) {
        this.manageParameter("NameFormat", value);
        return this.getThis();
    }

    public PerfMonPDH setRollup(Boolean value) {
        this.manageParameter("Rollup", value);
        return this.getThis();
    }

    public PerfMonPDH setSortRollupFunction(RollupFunction value) {
        this.manageParameter("SortRollupFunction", value);
        return this.getThis();
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
            return this.value;
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
            return this.value;
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
            return this.value;
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
            return this.value;
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
            return this.value;
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
            return this.value;
        }
    }

    public class Child extends PluginBase<Child> {

        private Child(String name) {
            super(name, PLUGIN);
            this.setParent(PerfMonPDH.this.getName());
            PerfMonPDH.this.children.add(this);
            this.wrap(PerfMonPDH.this.getPluginProvider().get());
        }

        private void setParent(String measureName) {
            this.manageParameter("Parent", measureName);
        }

        public Child setType(ChildType type) {
            this.manageParameter("Type", type);
            return this.getThis();
        }

        public Child setCounterIndex(Integer value) {
            this.manageParameter("CounterIndex", value);
            return this.getThis();
        }

        public Child setInstanceName(String name) {
            this.manageParameter("InstanceName", name);
            return this.getThis();
        }

        public Child setInstanceIndex(Integer value) {
            this.manageParameter("InstanceIndex", value);
            return this.getThis();
        }

        public Child setResultString(ResultString type) {
            this.manageParameter("ResultString", type);
            return this.getThis();
        }

        public Child setRollupFunction(RollupFunction function) {
            this.manageParameter("RollupFunction", function);
            return this.getThis();
        }

        @Override
        public Child wrap(PluginResource plugin) {
            var clazz = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
            if (clazz != PerfMonPDH.class && clazz != Child.class) {
                throw new UnsupportedOperationException("This method can not be used on Child. Use it on parent measure instead.");
            }
            return super.wrap(plugin);
        }

        @Override
        protected Child getThis() {
            return this;
        }
    }
}
