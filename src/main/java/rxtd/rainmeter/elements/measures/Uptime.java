package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.formulas.Formula;

public class Uptime extends MeasureBase<Uptime> {
    public Uptime(String name) {
        super(name, "Uptime");
    }

    public Uptime() {
        this(null);
    }

    @Override
    protected Uptime getThis() {
        return this;
    }

    public Uptime setFormat(FormatBuilder format) {
        if (format != null) {
            this.manageParameter("Format", format.getString());
        } else {
            this.removeParameter("Format");
        }
        return getThis();
    }

    public Uptime setAddDaysToHours(Boolean value) {
        this.manageParameter("AddDaysToHours", value);
        return getThis();
    }

    public Uptime setSecondsValue(Formula formula) {
        this.manageParameter("SecondsValue", formula);
        return getThis();
    }

    public enum FormatCode {
        DAYS("%4"),
        HOURS("%3"),
        MINUTES("%2"),
        SECONDS("%1");

        private final String value;

        FormatCode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class FormatBuilder {
        private StringBuilder sb = new StringBuilder();

        public FormatBuilder append(String text) {
            this.sb.append(text);
            return this;
        }

        public FormatBuilder append(FormatCode formatCode) {
            this.sb.append(formatCode.getValue());
            return this;
        }

        public FormatBuilder append(FormatCode formatCode, Integer fixedLength) {
            if (fixedLength == null) {
                return this.append(formatCode);
            }
            this.sb.append(formatCode.getValue());
            if (fixedLength <= 0) {
                this.sb.append("!i!");
            } else {
                this.sb.append("!0").append(fixedLength).append("i!");
            }
            return this;
        }

        public String getString() {
            return this.sb.toString();
        }
    }
}