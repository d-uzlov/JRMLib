package rxtd.rainmeter.core.elements.measures;

import rxtd.rainmeter.core.formulas.Formula;

public class Time extends MeasureBase<Time> {
    public Time(String name) {
        super(name, "Time");
    }

    public Time() {
        this(null);
    }

    @Override
    protected Time getThis() {
        return this;
    }

    public Time setFormat(FormatBuilder format) {
        if (format != null) {
            this.manageParameter("Format", format.getString());
        } else {
            this.removeParameter("Format");
        }
        return this.getThis();
    }

    public Time setFormat(String constructedFormat) {
        this.manageParameter("Format", constructedFormat);
        return this.getThis();
    }


    public Time setTimeStamp(Formula formula) {
        this.manageParameter("TimeStamp", formula);
        return this.getThis();
    }

    public Time setTimeStampFormat(String value) {
        this.manageParameter("TimeStampFormat", value);
        return this.getThis();
    }

    public Time setTimeStampLocale(String value) {
        this.manageParameter("TimeStampLocale", value);
        return this.getThis();
    }

    public Time setFormatLocale(String locale) {
        this.manageParameter("FormatLocale", locale);
        return this.getThis();
    }

    public Time setTimeZoneLocal() {
        this.manageParameter("TimeZone", "local");
        return this.getThis();
    }

    public Time setTimeZoneGMT(Double offset) {
        this.manageParameter("TimeZone", offset);
        return this.getThis();
    }

    public Time setDaylightSavingTime(Boolean value) {
        this.manageParameter("DaylightSavingTime", value);
        return this.getThis();
    }

    public enum FormatCode {
        /**
         * Year in format: 2018
         */
        YEAR("Y", true),
        /**
         * Year w/o century: 00 - 99
         */
        YEAR_SHORT("y", true),
        /**
         * Month number: 01 - 12
         */
        MONTH("m", true),
        /**
         * Month: "December"
         */
        MONTH_NAME("B", false),
        /**
         * Month: "Dec"
         */
        MONTH_NAME_SHORT("b", false),
        /**
         * Day of year: 001 - 366.
         */
        DAY_OF_YEAR("j", true),
        /**
         * Weekday as number with Sunday as first day of week: 0 - 6
         */
        DAY_OF_WEEK_SUNDAY("w", true),
        /**
         * Weekday as number with Monday  as first day of week: 1 - 7
         */
        DAY_OF_WEEK_MONDAY("u", false),
        /**
         * Full weekday name: "Saturday"
         */
        DAY_NAME("A", false),
        /**
         * Abbreviated weekday name: "Sat"
         */
        DAY_NAME_SHORT("a", false),
        /**
         * Hour: 00-23
         */
        HOUR_24("H", true),
        /**
         * Hour: 01-12
         */
        HOUR_12("I", true),
        /**
         * AM/PM indicator for 12-hour clock
         */
        HOUR_AM_PM("p", false),
        /**
         * Minute: 00-59
         */
        MINUTE("M", true),
        /**
         * Second: 00-59
         */
        SECOND("S", true),
        /**
         * Insert new line
         */
        NEWLINE("n", false),
        /**
         * Week of year as number, with first Monday as first day of week one. (00 - 53)
         */
        WEEK_SUNDAY("W", true),
        /**
         * Week of year number, with the first Sunday as first day of week one. (00 - 53)
         */
        WEEK_MONDAY("U", true);

        private final String value;
        private final boolean canTrimLeadingZeroes;

        FormatCode(String value, boolean canTrimLeadingZeroes) {
            this.value = value;
            this.canTrimLeadingZeroes = canTrimLeadingZeroes;
        }

        public String getValue() {
            return this.value;
        }

        public boolean isCanTrimLeadingZeroes() {
            return this.canTrimLeadingZeroes;
        }
    }

    public static class FormatBuilder {
        private final StringBuilder sb = new StringBuilder();

        public FormatBuilder append(String text) {
            this.sb.append(text);
            return this;
        }

        public FormatBuilder append(FormatCode formatCode) {
            this.sb.append("%").append(formatCode.getValue());
            return this;
        }

        public FormatBuilder append(FormatCode formatCode, boolean trimZeroes) {
            this.sb.append("%");
            if (trimZeroes && formatCode.isCanTrimLeadingZeroes()) {
                this.sb.append("#");
            }
            this.sb.append(formatCode.getValue());
            return this;
        }

        public FormatBuilder appendLocalDate() {
            this.sb.append("locale-date");
            return this;
        }

        public FormatBuilder appendLocalTime() {
            this.sb.append("locale-time");
            return this;
        }

        public String getString() {
            return this.sb.toString();
        }
    }
}
