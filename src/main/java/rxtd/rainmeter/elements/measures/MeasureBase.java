package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.NamePrefixProvider;
import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.ActionChain;
import rxtd.rainmeter.elements.MeterMeasureBase;
import rxtd.rainmeter.formulas.BooleanFormula;
import rxtd.rainmeter.formulas.Formula;

import java.util.ArrayList;

public abstract class MeasureBase<T extends MeasureBase<T>> extends MeterMeasureBase<T> implements Measure<T> {
    private ArrayList<Condition> conditions = new ArrayList<>();
    private ActionChain initialAction = null;
    private ActionChain updateAction = null;
    private ActionChain changeAction = null;
    private StringBuilder substitute = null;
    private boolean isRepeatConditionMode = false;

    public MeasureBase(String name, String measure) {
        super(createName(name));
        this.manageParameter("Measure", measure);
        this.addBeforeWriteListener(() -> {
            if (this.initialAction != null) {
                this.conditions.add(new Condition(new BooleanFormula(true), this.initialAction, null));
            }
            int index = 1;
            for (var c : this.conditions) {
                if (c == null || c.value == null || c.trueAction == null && c.falseAction == null) {
                    continue;
                }
                String suffix = this.createSuffix(index);
                index++;
                this.manageParameter("IfCondition" + suffix, c.value.toCalcString());
                this.manageParameter("IfTrueAction" + suffix, c.trueAction);
                this.manageParameter("IfFalseAction" + suffix, c.falseAction);
            }
        });
    }

    private static String createName(String name) {
        NamePrefixProvider prefixProvider = SkinUtils.getNamePrefixProvider();
        return prefixProvider.nextMeasuresPrefix(name);
    }

    public T setUpdateAction(Action action) {
        this.manageParameter("OnUpdateAction", action);
        this.updateAction = new ActionChain(action);
        return getThis();
    }

    public T addUpdateAction(Action action) {
        if (this.updateAction == null) {
            this.updateAction = new ActionChain();
        }
        this.updateAction.append(action);
        this.manageParameter("OnUpdateAction", this.updateAction);
        return getThis();
    }

    @Override
    public T setChangeAction(Action action) {
        this.manageParameter("OnChangeAction", action);
        this.changeAction = new ActionChain(action);
        return getThis();
    }

    @Override
    public T addChangeAction(Action action) {
        if (this.changeAction == null) {
            this.changeAction = new ActionChain();
        }
        this.changeAction.append(action);
        this.manageParameter("OnChangeAction", this.changeAction);
        return getThis();
    }

    @Override
    public T setInvertMeasure(Boolean value) {
        this.manageParameter("InvertMeasure", value);
        return getThis();
    }

    @Override
    public T setMaxValue(Formula value) {
        this.manageParameter("MaxValue", value);
        return getThis();
    }

    @Override
    public T setMaxValue(Double value) {
        this.manageParameter("MaxValue", value);
        return getThis();
    }

    @Override
    public T setMinValue(Formula value) {
        this.manageParameter("MinValue", value);
        return getThis();
    }

    @Override
    public T setMinValue(Double value) {
        this.manageParameter("MinValue", value);
        return getThis();
    }

    @Override
    public T setAverageSize(Integer size) {
        this.manageParameter("AverageSize", size);
        return getThis();
    }

    @Override
    public T setDisabled(Boolean value) {
        this.manageParameter("Disabled", value);
        return getThis();
    }

    @Override
    public T setPaused(Boolean value) {
        this.manageParameter("Paused", value);
        return getThis();
    }

    private void addCompareAction(String name, Formula value, Action action) {
        this.manageParameter(name + "Value", value);
        this.manageParameter(name + "Action", action);
    }

    @Override
    public T setIfAbove(Formula value, Action action) {
        this.addCompareAction("IfAbove", value, action);
        return getThis();
    }

    @Override
    public T setIfBelow(Formula value, Action action) {
        this.addCompareAction("IfBelow", value, action);
        return getThis();
    }

    @Override
    public T setIfEqual(Formula value, Action action) {
        this.addCompareAction("IfEqual", value, action);
        return getThis();
    }

    @Override
    public T setConditionMode(UpdateMode mode) {
        this.isRepeatConditionMode = mode != UpdateMode.ON_CHANGE;
        if (this.initialAction != null && this.isRepeatConditionMode) {
            throw new RuntimeException("initial action and UpdateMode.ALWAYS are incompatible");
        }
        this.manageParameter("IfConditionMode", mode);
        return getThis();
    }

    @Override
    public T setCondition(int index, BooleanFormula value, Action trueAction, Action falseAction) {
        this.conditions.set(index, new Condition(value, trueAction, falseAction));
        return getThis();
    }

    @Override
    public T addCondition(BooleanFormula value, Action trueAction, Action falseAction) {
        this.conditions.add(new Condition(value, trueAction, falseAction));
        return getThis();
    }

    @Override
    public T setInitialAction(Action action) {
        if (action == null) {
            this.initialAction = null;
        } else {
            if (this.isRepeatConditionMode) {
                throw new RuntimeException("initial action and UpdateMode.ALWAYS are incompatible");
            }
            this.initialAction = new ActionChain(action);
        }
        return getThis();
    }

    @Override
    public T addInitialAction(Action action) {
        if (this.isRepeatConditionMode) {
            throw new RuntimeException("initial action and UpdateMode.ALWAYS are incompatible");
        }
        if (this.initialAction == null) {
            this.initialAction = new ActionChain();
        }
        this.initialAction.append(action);
        return getThis();
    }

    @Override
    public T setRegExpSubstitute(Boolean value) {
        this.manageParameter("RegExpSubstitute", value);
        return getThis();
    }

    @Override
    public T addSubstitute(String pattern, String replacement) {
        if (this.substitute == null) {
            this.substitute = new StringBuilder();
        } else {
            this.substitute.append(",");
        }
        this.substitute.append("\"").append(pattern).append("\":\"").append(replacement).append("\"");
        this.manageParameter("Substitute", this.substitute.toString());
        return getThis();
    }

    private static class Condition {
        final BooleanFormula value;
        final Action trueAction;
        final Action falseAction;

        private Condition(BooleanFormula value, Action trueAction, Action falseACtion) {
            this.value = value;
            this.trueAction = trueAction;
            this.falseAction = falseACtion;
        }
    }
}
