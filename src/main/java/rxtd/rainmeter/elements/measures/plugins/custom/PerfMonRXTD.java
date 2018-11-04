package rxtd.rainmeter.elements.measures.plugins.custom;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;
import rxtd.rainmeter.elements.measures.plugins.ExternalPluginResource;
import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

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

    public PerfMonRXTD(String name) {
        super(name, PLUGIN);
        this.manageParameter("Type", "Parent");
    }

    @Override
    protected PerfMonRXTD getThis() {
        return this;
    }

    public PerfMonRXTD setCategory(String category) {
        this.manageParameter("ObjectName", category);
        return getThis();
    }

    public PerfMonRXTD setCounterList(String... counters) {
        return this.setCounterList(Arrays.asList(counters));
    }

    public PerfMonRXTD setCounterList(List<String> counters) {
        if (counters != null && counters.size() > 30) {
            throw new IllegalArgumentException("maximum 30 values");
        }
        this.manageParameter("CounterList", counters);
        return getThis();
    }

    public PerfMonRXTD setExpressionList(String... expressions) {
        return this.setExpressionList(Arrays.asList(expressions));
    }

    public PerfMonRXTD setExpressionList(List<String> expression) {
        this.manageParameter("ExpressionList", expression);
        return getThis();
    }

    public PerfMonRXTD setRollupExpressionList(String... expressions) {
        return this.setRollupExpressionList(Arrays.asList(expressions));
    }

    public PerfMonRXTD setRollupExpressionList(List<String> expression) {
        this.manageParameter("RollupExpressionList", expression);
        return getThis();
    }

    public PerfMonRXTD setSortBy(SortBy sortType) {
        this.manageParameter("SortBy", sortType);
        return getThis();
    }

    public PerfMonRXTD setSyncRawFormatted(Boolean value) {
        this.manageParameter("SyncRawFormatted", value);
        return getThis();
    }

    public PerfMonRXTD setSortIndex(Integer index) {
        this.manageParameter("SortIndex", index);
        return getThis();
    }

    public PerfMonRXTD setSortOrder(SortOrder category) {
        this.manageParameter("SortOrder", category);
        return getThis();
    }

    public PerfMonRXTD setBlacklist(String... values) {
        return this.setBlacklist(Arrays.asList(values));
    }

    public PerfMonRXTD setBlacklist(Collection<String> values) {
        this.manageParameter("Blacklist", values);
        return getThis();
    }

    public PerfMonRXTD setWhitelist(String... values) {
        return this.setWhitelist(Arrays.asList(values));
    }

    public PerfMonRXTD setWhitelist(Collection<String> values) {
        this.manageParameter("Whitelist", values);
        return getThis();
    }

    public PerfMonRXTD setBlacklistOrig(String... values) {
        return this.setBlacklistOrig(Arrays.asList(values));
    }

    public PerfMonRXTD setBlacklistOrig(Collection<String> values) {
        this.manageParameter("BlacklistOrig", values);
        return getThis();
    }

    public PerfMonRXTD setWhitelistOrig(String... values) {
        return this.setWhitelistOrig(Arrays.asList(values));
    }

    public PerfMonRXTD setWhitelistOrig(Collection<String> values) {
        this.manageParameter("WhitelistOrig", values);
        return getThis();
    }

    public PerfMonRXTD setInstanceIndexOffet(Integer value) {
        this.manageParameter("InstanceIndexOffset", value);
        return getThis();
    }

    public PerfMonRXTD setLimitIndexOffset(Boolean value) {
        this.manageParameter("LimitIndexOffset", value);
        return getThis();
    }

    public PerfMonRXTD setDisplayName(DisplayName value) {
        this.manageParameter("DisplayName", value);
        return getThis();
    }

    public PerfMonRXTD setRollup(Boolean value) {
        this.manageParameter("Rollup", value);
        return getThis();
    }

    public PerfMonRXTD setKeepDiscarded(Boolean value) {
        this.manageParameter("KeepDiscarded", value);
        return getThis();
    }

    public PerfMonRXTD setSortRollupFunction(RollupFunction value) {
        this.manageParameter("SortRollupFunction", value);
        return getThis();
    }

    @Override
    @Deprecated
    public PerfMonRXTD setDynamicVariables(Boolean value) {
        return super.setDynamicVariables(value);
    }

    public Action bangStop() {
        return BangUtils.commandMeasure(this.getName(), "Stop", null);
    }

    public Action bangResume() {
        return BangUtils.commandMeasure(this.getName(), "Resume", null);
    }

    public Action bangStopResume() {
        return BangUtils.commandMeasure(this.getName(), "StopResume", null);
    }

    public Action bangSetIndexOffset(int value) {
        return BangUtils.commandMeasure(this.getName(), "SetIndexOffset " + value, null);
    }

    public Action bangSetIndexOffset(String value) {
        return BangUtils.commandMeasure(this.getName(), "SetIndexOffset " + value, null);
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
            return value;
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
            return value;
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
            return value;
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
            return value;
        }
    }

    public class Child extends PluginBase<Child> {

        private Child(String name) {
            super(name, PLUGIN);
            this.setParent(PerfMonRXTD.this.getName());

            this.addBeforeWriteListener(() -> {
                String parentLocalPath = PerfMonRXTD.this.getLocalPathProvider().get();
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

        public Child setSearchOriginalName(Boolean value) {
            this.manageParameter("SearchOriginalName", value);
            return getThis();
        }

        public Child setDiscarded(Boolean value) {
            this.manageParameter("Discarded", value);
            return getThis();
        }

        public Child setTotal(Boolean value) {
            this.manageParameter("Total", value);
            return getThis();
        }

        public Child setRollupFunction(RollupFunction function) {
            if (function == RollupFunction.FIRST) {
                throw new IllegalArgumentException();
            }
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
