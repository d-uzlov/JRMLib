package rxtd.rainmeter;

import java.awt.Color;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Supplier;

public class SkinUtils {
    private final static Charset STANDARD_CHARSET = Charset.forName("x-UTF-16LE-BOM");

    private final static ThreadLocal<NamePrefixProvider> namePrefixProvider = new ThreadLocal<>();
    private final static ThreadLocal<Supplier<NamePrefixProvider>> namePrefixProviderFactory = new ThreadLocal<>();

    static {
        namePrefixProviderFactory.set(UniqueNamePrefixProvider::new);
        nextNamePrefixProvider();
    }

    public static void setNamePrefixProviderFactory(Supplier<NamePrefixProvider> factory) {
        SkinUtils.namePrefixProviderFactory.set(factory);
    }

    public static NamePrefixProvider nextNamePrefixProvider() {
        namePrefixProvider.set(namePrefixProviderFactory.get().get());
        return getNamePrefixProvider();
    }

    public static NamePrefixProvider getNamePrefixProvider() {
        return namePrefixProvider.get();
    }

    public static void setNamePrefixProvider(NamePrefixProvider namePrefixProvider) {
        SkinUtils.namePrefixProvider.set(namePrefixProvider);
    }

    public static Charset getStandardCharset() {
        return STANDARD_CHARSET;
    }

    public static String print(Color color) {
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
    }

    public static String print(Double value) {
        if (value == null) {
            return null;
        }
        // source: https://stackoverflow.com/a/25308216
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 == DecimalFormat.DOUBLE_FRACTION_DIGITS
        return df.format(value);
    }

    public static String joinList(Collection<?> values, String separator) {
        if (values == null || values.size() == 0) {
            return null;
        }
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (var s : values) {
            if (s == null) {
                continue;
            }
            if (first) {
                sb.append(s.toString());
                first = false;
            } else {
                sb.append(separator).append(s.toString());
            }
        }
        if (first) {
            return null;
        } else {
            return sb.toString();
        }
    }

    public static String joinGradient(Collection<GradientOption> values) {
        StringBuilder builder = new StringBuilder();
        for (var p : values) {
            builder.append("|").append(SkinUtils.print(p.color))
                    .append(';').append(SkinUtils.print(p.percentage));
        }
        return builder.toString();
    }

    public static String pipeSeparatedList(Collection<?> values) {
        return joinList(values, "|");
    }

    public static boolean isAllASCII(String input) {
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
}
