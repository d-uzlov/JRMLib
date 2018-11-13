package rxtd.rainmeter.core.elements.measures;

public class FreeDiskSpace extends MeasureBase<FreeDiskSpace> {
    public FreeDiskSpace(String name) {
        super(name, "FreeDiskSpace");
    }

    public FreeDiskSpace() {
        this(null);
    }

    @Override
    protected FreeDiskSpace getThis() {
        return this;
    }

    public FreeDiskSpace setDrive(String path) {
        this.manageParameter("Drive", path);
        return this.getThis();
    }

    public FreeDiskSpace setDrive(Character driveLetter) {
        if (driveLetter == null) {
            this.removeParameter("Drive");
            return this.getThis();
        }
        this.manageParameter("Drive", driveLetter + ":");
        return this.getThis();
    }

    public FreeDiskSpace setTotal(Boolean value) {
        this.manageParameter("Total", value);
        return this.getThis();
    }

    public FreeDiskSpace setLabel(Boolean value) {
        this.manageParameter("Label", value);
        return this.getThis();
    }

    /**
     * @see Type
     */
    public FreeDiskSpace setType(Boolean value) {
        this.manageParameter("Type", value);
        return this.getThis();
    }

    public FreeDiskSpace setIgnoreRemovable(Boolean value) {
        this.manageParameter("IgnoreRemovable", value);
        return this.getThis();
    }

    public FreeDiskSpace setDiskQuota(Boolean value) {
        this.manageParameter("DiskQuota", value);
        return this.getThis();
    }

    public enum Type {
        ERROR(0),
        REMOVED(1),
        REMOVABLE(3),
        FIXED(4),
        NETWORK(5),
        CD_ROM(6),
        RAM(7);

        private final int value;
        private final String stringValue;

        Type(int value) {
            this.value = value;
            this.stringValue = Integer.toString(value);
        }

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.stringValue;
        }
    }
}
