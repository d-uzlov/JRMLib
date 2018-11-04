package rxtd.rainmeter;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueNamePrefixProvider implements NamePrefixProvider {
    private final String measurePrefixName;
    private final AtomicInteger measuresUID;

    private final String meterPrefixName;
    private final AtomicInteger metersUID;

    private final String variablePrefixName;
    private final AtomicInteger variablesUID;

    private final String includePrefixName;
    private final AtomicInteger includesUID;

    private DecimalFormat decimalFormat = new DecimalFormat();

    public UniqueNamePrefixProvider() {
        this("Measure", "Meter", "Variable", "", true);
    }

    public UniqueNamePrefixProvider(String measurePrefixName, String meterPrefixName, String variablePrefixName, String includePrefixName, boolean separateUIDs) {
        this.measurePrefixName = measurePrefixName;
        this.meterPrefixName = meterPrefixName;
        this.variablePrefixName = variablePrefixName;
        this.includePrefixName = includePrefixName;

        if (separateUIDs) {
            this.measuresUID = new AtomicInteger();
            this.metersUID = new AtomicInteger();
            this.variablesUID = new AtomicInteger();
            this.includesUID = new AtomicInteger();
        } else {
            if (this.measurePrefixName.equals(this.meterPrefixName) || this.measurePrefixName.equals(this.variablePrefixName)) {
                throw new IllegalArgumentException("if name prefixes are equal, then IDs must be separated");
            }
            this.measuresUID = new AtomicInteger();
            this.metersUID = this.measuresUID;
            this.variablesUID = this.measuresUID;
            this.includesUID = this.measuresUID;
        }
    }

    private static boolean isAllASCII(String input) {
        boolean isASCII = true;
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i);
            if (c > 0x7F) {
                isASCII = false;
                break;
            }
        }
        return isASCII;
    }

    public void setZeroPaddingSize(int size) {
        if (size < 1) {
            this.decimalFormat = new DecimalFormat();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("0");
        }
        this.decimalFormat = new DecimalFormat(sb.toString());
    }

    private String next(String prefixName, AtomicInteger uidGenerator, String suggest) {
        String prefix = prefixName + this.decimalFormat.format(uidGenerator.getAndIncrement());
        if (suggest == null) {
            return prefix;
        }
        if (!isAllASCII(suggest) || !suggest.chars().allMatch(value -> Character.isLetter(value) || value == '_' || Character.isDigit(value))) {
            throw new RuntimeException("only digits, ASCII letters and '_' are allowed in names: " + suggest);
        }
        return prefix + "_" + suggest;
    }

    @Override
    public String nextVariablesPrefix(String suggest) {
        return next(this.variablePrefixName, this.variablesUID, suggest);
    }

    @Override
    public String nextMeasuresPrefix(String suggest) {
        return next(this.measurePrefixName, this.measuresUID, suggest);
    }

    @Override
    public String nextMetersPrefix(String suggest) {
        return next(this.meterPrefixName, this.metersUID, suggest);
    }

    @Override
    public String nextIncludePrefix(String suggest) {
        return this.next(this.includePrefixName, this.includesUID, suggest);
    }
}
