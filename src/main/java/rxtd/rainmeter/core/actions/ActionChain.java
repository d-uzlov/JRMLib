package rxtd.rainmeter.core.actions;

public class ActionChain implements Action {
    private final StringBuilder value = new StringBuilder();

    public ActionChain(Action... actions) {
        for (var action : actions) {
            if (action != null) {
                this.append(action);
            }
        }
    }

    public ActionChain append(Action action) {
        this.value.append(action.toString());
        return this;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
