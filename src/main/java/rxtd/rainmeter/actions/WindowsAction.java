package rxtd.rainmeter.actions;

public class WindowsAction implements Action {
    private final String value;

    public WindowsAction(String command, String... args) {
        if (command == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(command);
        if (args != null) {
            for (String arg : args) {
                sb.append(" ").append(arg);
            }
        }
        sb.append("]");
        this.value = sb.toString();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
