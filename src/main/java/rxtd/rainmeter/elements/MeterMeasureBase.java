package rxtd.rainmeter.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class MeterMeasureBase<T extends MeterMeasureBase<T>> extends ElementBase<T> {
    private Collection<String> groups = new ArrayList<>();

    public MeterMeasureBase(String name) {
        super(name);
        this.addBeforeWriteListener(() -> {
            if (this.groups.size() < 1) {
                return;
            }
            this.manageParameter("Group", this.groups);
        });
    }

    public T setUpdateDivider(Integer divider) {
        this.manageParameter("UpdateDivider", divider);
        return this.getThis();
    }

    public T disableUpdate() {
        return this.setUpdateDivider(-1);
    }

    public T setDynamicVariables(Boolean value) {
        this.manageParameter("DynamicVariables", value);
        return this.getThis();
    }

    public T setGroups(String... groups) {
        return this.setGroups(Arrays.asList(groups));
    }

    public T setGroups(Collection<String> groups) {
        if (groups == null) {
            this.groups.clear();
            return this.getThis();
        }
        this.groups = groups;
        return this.getThis();
    }

    public T addGroup(String group) {
        this.groups.add(group);
        return this.getThis();
    }

    public T addGroups(String... groups) {
        return this.addGroups(Arrays.asList(groups));
    }

    public T addGroups(Collection<String> groups) {
        this.groups.addAll(groups);
        return this.getThis();
    }

}
