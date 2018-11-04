package rxtd.rainmeter.elements.meters.shape;

import java.util.Arrays;
import java.util.List;

public class ShapeUtils {

    public static String createImage(boolean skipNulls, Object... values) {
        return createImage(skipNulls, Arrays.asList(values));
    }

    public static String createImage(boolean skipNulls, List values) {
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (var v : values) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }

            String value;
            if (v == null) {
                if (skipNulls) {
                    value = "*";
                } else {
                    break;
                }
            } else {
                value = v.toString();
            }
            sb.append(value);
        }
        return sb.toString();
    }

}
