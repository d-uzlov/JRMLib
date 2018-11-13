package rxtd.rainmeter.core.elements;

import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.formulas.Formula;
import rxtd.rainmeter.core.resources.Resource;

public interface CustomElement<T> {
    T setOption(String name, String value);

    T setOption(String name, Integer value);

    T setOption(String name, Boolean value);

    T setOption(String name, Double value);

    T setOption(String name, Resource value);

    T setOption(String name, Formula value);

    T setOption(String name, Action value);
}
