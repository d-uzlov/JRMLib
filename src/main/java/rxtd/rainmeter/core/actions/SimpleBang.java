package rxtd.rainmeter.core.actions;

import java.util.Arrays;
import java.util.List;

public class SimpleBang implements Action {
    private final String value;

    public SimpleBang(String command, List<String> args) {
        if (command == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[!").append(command);
        if (args != null) {
            for (String arg : args) {
                sb.append(" ").append(arg);
            }
        }
        sb.append("]");
        this.value = sb.toString();
    }

    public SimpleBang(String command, String... args) {
        this(command, Arrays.asList(args));
    }

    @Override
    public String toString() {
        return this.value;
    }
}
