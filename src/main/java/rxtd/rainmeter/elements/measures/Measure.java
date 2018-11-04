package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.elements.Element;
import rxtd.rainmeter.formulas.BooleanFormula;
import rxtd.rainmeter.formulas.Formula;

import java.util.Collection;

public interface Measure<T extends Measure<T>> extends Element<T> {

    T setUpdateDivider(Integer divider);

    T disableUpdate();

    T setMaxValue(Formula value);

    T setMaxValue(Double value);

    T setMinValue(Formula value);

    T setMinValue(Double value);

    T setAverageSize(Integer size);

    T setDisabled(Boolean value);

    T setPaused(Boolean value);

    T setInvertMeasure(Boolean value);

    T setGroups(String... groups);

    T setGroups(Collection<String> groups);

    T addGroup(String group);

    T addGroups(String... groups);

    T addGroups(Collection<String> groups);

    T setIfAbove(Formula value, Action action);

    T setIfBelow(Formula value, Action action);

    T setIfEqual(Formula value, Action action);

    T setConditionMode(UpdateMode mode);

    T setCondition(int index, BooleanFormula value, Action trueAction, Action falseAction);

    T addCondition(BooleanFormula value, Action trueAction, Action falseAction);

    T setInitialAction(Action action);

    T addInitialAction(Action action);

    T setChangeAction(Action action);

    T addChangeAction(Action action);

    T setUpdateAction(Action action);

    T addUpdateAction(Action action);

    T setRegExpSubstitute(Boolean value);

    T addSubstitute(String pattern, String replacement);

    T setDynamicVariables(Boolean value);

    enum UpdateMode {
        ON_CHANGE("0"),
        ALWAYS("1");

        private final String value;

        UpdateMode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
