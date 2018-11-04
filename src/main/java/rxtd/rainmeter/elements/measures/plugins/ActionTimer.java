package rxtd.rainmeter.elements.measures.plugins;

import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionTimer extends PluginBase<ActionTimer> {
    private final static PluginResource PLUGIN = new InternalPluginResource("ActionTimer");
    private final Map<String, NamedAction> namedActions = new HashMap<>();
    private final List<ActionList> actionLists = new ArrayList<>();

    public ActionTimer(String name) {
        super(name, PLUGIN);

        this.addBeforeWriteListener(() -> {
            for (int i = 0; i < this.actionLists.size(); i++) {
                ActionList l = this.actionLists.get(i);
                for (var n : l.elements) {
                    if (n.getAction() != null) {
                        this.addNamedAction(n.getAction());
                    }
                }
                this.manageParameter("ActionList" + (i + 1), l.toString());
            }
            for (var n : this.namedActions.entrySet()) {
                NamedAction a = n.getValue();
                this.manageParameter(a.getName(), a.toString());
            }
        });
    }

    @Override
    protected ActionTimer getThis() {
        return this;
    }

    private void burnNamedActions() {
        for (var v : this.namedActions.entrySet()) {
            NamedAction a = v.getValue();
            this.manageParameter(a.getName(), a.getAction());
        }
    }

    public Map<String, NamedAction> getNamedActions() {
        return this.namedActions;
    }

    public ActionTimer addNamedAction(NamedAction action) {
        this.namedActions.put(action.getName(), action);
        return getThis();
    }

    public ActionTimer addActionList(ActionList list) {
        this.actionLists.add(list);
        return getThis();
    }

    public Action bangExecute(ActionList list) {
        int i = this.actionLists.indexOf(list);
        if (i == -1) {
            i = this.actionLists.size();
            this.actionLists.add(list);
        }
        return BangUtils.commandMeasure(this.getName(), "Execute " + (i + 1), null);
    }

    public Action bangStop(ActionList list) {
        int i = this.actionLists.indexOf(list);
        if (i == -1) {
            i = this.actionLists.size();
            this.actionLists.add(list);
        }
        return BangUtils.commandMeasure(this.getName(), "Stop " + (i + 1), null);
    }

    public interface ActionListElement {
        NamedAction getAction();

        String toString();
    }

    public static class ActionList {
        private final List<ActionListElement> elements = new ArrayList<>();

        public List<ActionListElement> getElements() {
            return this.elements;
        }

        public ActionList addElement(ActionListElement element) {
            this.elements.add(element);
            return this;
        }

        @Override
        public String toString() {
            return SkinUtils.pipeSeparatedList(this.elements);
        }
    }

    public static class NamedAction {
        private final static AtomicInteger UID = new AtomicInteger();
        private final String name;
        private final Action action;

        public NamedAction(String name, Action action) {
            String prefix = "a" + UID.getAndIncrement();
            if (name == null) {
                this.name = prefix;
            } else {
                this.name = prefix + "_" + name;
            }
            this.action = action;
        }

        public String getName() {
            return this.name;
        }

        public Action getAction() {
            return this.action;
        }

        @Override
        public String toString() {
            return this.action.toString();
        }
    }

    public static class ExecuteAction implements ActionListElement {
        private final NamedAction action;

        public ExecuteAction(NamedAction action) {
            if (action == null) {
                throw new IllegalArgumentException();
            }
            this.action = action;
        }

        public NamedAction getAction() {
            return this.action;
        }

        @Override
        public String toString() {
            return this.action.getName();
        }
    }

    public static class Repeat implements ActionListElement {
        private final NamedAction action;
        private final int waitTime;
        private final int count;

        public Repeat(NamedAction action, int waitTime, int count) {
            if (action == null) {
                throw new IllegalArgumentException();
            }
            this.action = action;
            this.waitTime = waitTime;
            this.count = count;
        }

        public NamedAction getAction() {
            return this.action;
        }

        @Override
        public String toString() {
            return "Repeat" + this.action.getName() + "," + this.waitTime + "," + this.count;
        }
    }

    public static class Wait implements ActionListElement {
        private final int time;

        public Wait(int time) {
            this.time = time;
        }

        public NamedAction getAction() {
            return null;
        }

        @Override
        public String toString() {
            return "Wait " + this.time;
        }
    }
}
