package rxtd.rainmeter.elements.measures.plugins.custom;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Replaces PerfMon, NomFerp and UsageMonitor plugins.<br/>
 * Covered version is 1.2.0
 *
 * @see <a href="https://forum.rainmeter.net/viewtopic.php?f=18&t=29226&p=152316">Post on Rainmeter forum</a>
 */
public class PerfMonRXTD extends PluginBase<PerfMonRXTD> {
    private final static PluginResource PLUGIN = new VirtualPluginResource("PerfMonRxtd", null);
    private final List<Child> children = new ArrayList<>();

    public PerfMonRXTD(String name) {
        super(name, PLUGIN);
        this.manageParameter("Type", "Parent");
    }

    @Override
    protected PerfMonRXTD getThis() {
        return this;
    }

    @Override
    public PerfMonRXTD wrap(@Nullable PluginResource plugin) {
        for (var c : this.children) {
            c.wrap(plugin);
        }
        return super.wrap(plugin);
    }

    public PerfMonRXTD setCategory(String category) {
        this.manageParameter("ObjectName", category);
        return this.getThis();
    }

    public PerfMonRXTD setCounterList(String... counters) {
        return this.setCounterList(Arrays.asList(counters));
    }

    public PerfMonRXTD setCounterList(List<String> counters) {
        if (counters != null && counters.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("CounterList", counters);
        return this.getThis();
    }

    public PerfMonRXTD setExpressionList(String... expressions) {
        return this.setExpressionList(Arrays.asList(expressions));
    }

    public PerfMonRXTD setExpressionList(List<String> expression) {
        this.manageParameter("ExpressionList", expression);
        return this.getThis();
    }

    public PerfMonRXTD setRollupExpressionList(String... expressions) {
        return this.setRollupExpressionList(Arrays.asList(expressions));
    }

    public PerfMonRXTD setRollupExpressionList(List<String> expression) {
        this.manageParameter("RollupExpressionList", expression);
        return this.getThis();
    }

    public PerfMonRXTD setSortBy(SortBy sortType) {
        this.manageParameter("SortBy", sortType);
        return this.getThis();
    }

    public PerfMonRXTD setSyncRawFormatted(Boolean value) {
        this.manageParameter("SyncRawFormatted", value);
        return this.getThis();
    }

    public PerfMonRXTD setSortIndex(Integer index) {
        this.manageParameter("SortIndex", index);
        return this.getThis();
    }

    public PerfMonRXTD setSortOrder(SortOrder category) {
        this.manageParameter("SortOrder", category);
        return this.getThis();
    }

    public PerfMonRXTD setBlacklist(String... values) {
        return this.setBlacklist(Arrays.asList(values));
    }

    public PerfMonRXTD setBlacklist(Collection<String> values) {
        this.manageParameter("Blacklist", values);
        return this.getThis();
    }

    public PerfMonRXTD setWhitelist(String... values) {
        return this.setWhitelist(Arrays.asList(values));
    }

    public PerfMonRXTD setWhitelist(Collection<String> values) {
        this.manageParameter("Whitelist", values);
        return this.getThis();
    }

    public PerfMonRXTD setBlacklistOrig(String... values) {
        return this.setBlacklistOrig(Arrays.asList(values));
    }

    public PerfMonRXTD setBlacklistOrig(Collection<String> values) {
        this.manageParameter("BlacklistOrig", values);
        return this.getThis();
    }

    public PerfMonRXTD setWhitelistOrig(String... values) {
        return this.setWhitelistOrig(Arrays.asList(values));
    }

    public PerfMonRXTD setWhitelistOrig(Collection<String> values) {
        this.manageParameter("WhitelistOrig", values);
        return this.getThis();
    }

    public PerfMonRXTD setInstanceIndexOffset(Integer value) {
        this.manageParameter("InstanceIndexOffset", value);
        return this.getThis();
    }

    public PerfMonRXTD setLimitIndexOffset(Boolean value) {
        this.manageParameter("LimitIndexOffset", value);
        return this.getThis();
    }

    public PerfMonRXTD setDisplayName(DisplayName value) {
        this.manageParameter("DisplayName", value);
        return this.getThis();
    }

    public PerfMonRXTD setRollup(Boolean value) {
        this.manageParameter("Rollup", value);
        return this.getThis();
    }

    public PerfMonRXTD setKeepDiscarded(Boolean value) {
        this.manageParameter("KeepDiscarded", value);
        return this.getThis();
    }

    public PerfMonRXTD setSortRollupFunction(RollupFunction value) {
        this.manageParameter("SortRollupFunction", value);
        return this.getThis();
    }

    @Override
    @Deprecated
    public PerfMonRXTD setDynamicVariables(Boolean value) {
        return super.setDynamicVariables(value);
    }

    public Action bangStop() {
        return super.bangCommand("Stop");
    }

    public Action bangResume() {
        return super.bangCommand("Resume");
    }

    public Action bangStopResume() {
        return super.bangCommand("StopResume");
    }

    public Action bangSetIndexOffset(int value) {
        return super.bangCommand("SetIndexOffset " + value);
    }

    public Action bangSetIndexOffset(String value) {
        return super.bangCommand("SetIndexOffset " + value);
    }

    public Child createChild(String name) {
        return this.new Child(name);
    }

    public enum SortBy {
        NONE("None"),
        INSTANCE_NAME("InstanceName"),
        RAW_COUNTER("RawCounter"),
        FORMATTED_COUNTER("FormattedCounter"),
        EXPRESSION("Expression"),
        ROLLUP_EXPRESSION("RollupExpression"),
        COUNT("Count");

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

    public enum DisplayName {
        ORIGINAL("Original"),
        GPU_PROCESS_NAME("ProcessName"),
        GPU_ENG_TYPE("EngType"),
        LOGICAL_DISK_DRIVE_LETTER("DriveLetter"),
        LOGICAL_DISK_MOUNT_FOLDER("MountFolder");

        private final String value;

        DisplayName(String value) {
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
        FIRST("First");

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
        GET_COUNT("GetCount"),
        GET_RAW_COUNTER("GetRawCounter"),
        GET_FORMATTED_COUNTER("GetFormattedCounter"),
        GET_EXPRESSION("GetExpression"),
        GET_ROLLUP_EXPRESSION("GetRollupExpression");

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
        ORIGINAL_NAME("OriginalName"),
        UNIQUE_NAME("UniqueName"),
        DISPLAY_NAME("DisplayName");

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
            this.setParent(PerfMonRXTD.this.getName());
            PerfMonRXTD.this.children.add(this);
            this.wrap(PerfMonRXTD.this.getPluginProvider().get());
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

        public Child setSearchOriginalName(Boolean value) {
            this.manageParameter("SearchOriginalName", value);
            return this.getThis();
        }

        public Child setDiscarded(Boolean value) {
            this.manageParameter("Discarded", value);
            return this.getThis();
        }

        public Child setTotal(Boolean value) {
            this.manageParameter("Total", value);
            return this.getThis();
        }

        public Child setRollupFunction(RollupFunction function) {
            if (function == RollupFunction.FIRST) {
                throw new IllegalArgumentException();
            }
            this.manageParameter("RollupFunction", function);
            return this.getThis();
        }

        @Override
        public Child wrap(PluginResource plugin) {
            var clazz = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
            if (clazz != PerfMonRXTD.class && clazz != Child.class) {
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
