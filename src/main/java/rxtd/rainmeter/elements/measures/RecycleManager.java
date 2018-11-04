package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;

public class RecycleManager extends MeasureBase<RecycleManager> {
    public RecycleManager(String name) {
        super(name, "RecycleManager");
    }

    public RecycleManager() {
        this(null);
    }

    @Override
    protected RecycleManager getThis() {
        return this;
    }

    public RecycleManager setRecycleType(Type type) {
        this.manageParameter("Interface", type);
        return getThis();
    }

    public Action createCommand(Command command) {
        return BangUtils.commandMeasure(this.getName(), command.toString(), null);
    }


    public enum Type {
        COUNT("Count"),
        SIZE("Size");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Command {
        OPEN_BIN("OpenBin"),
        EMPTY_BIN("EmptyBin"),
        EMPTY_BIN_SILENT("EmptyBinSilent");

        private final String value;

        Command(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
