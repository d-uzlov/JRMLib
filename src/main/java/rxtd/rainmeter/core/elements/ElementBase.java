package rxtd.rainmeter.core.elements;

import org.jetbrains.annotations.NotNull;
import rxtd.rainmeter.core.NamePrefixProvider;
import rxtd.rainmeter.core.SkinUtils;
import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.resources.Resource;
import rxtd.rainmeter.core.elements.measures.Measure;
import rxtd.rainmeter.core.formulas.Formula;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public abstract class ElementBase<T extends ElementBase<T>> implements Element<T> {
    private final String name;
    private final LinkedHashMap<String, String> params = new LinkedHashMap<>();
    private final Collection<Resource> resources = new HashSet<>();
    private final List<Runnable> listeners = new ArrayList<>();

    /**
     * Parent class for all elements
     *
     * @param name Name of the section
     */
    public ElementBase(String name) {
        this.name = name;
    }

    protected abstract T getThis();

    @Override
    public T include(String name, String incUsage) {
        NamePrefixProvider provider = SkinUtils.getNamePrefixProvider();
        var pref = provider.nextIncludePrefix(name);
        this.manageParameter("Include" + pref, incUsage);
        return this.getThis();
    }

    @Override
    public @NotNull Map<String, String> getParams() {
        return this.params;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public void write(@NotNull PrintWriter writer) {
        writer.println("[" + this.name + "]");
        for (var param : this.params.entrySet()) {
            var key = param.getKey();
            var value = param.getValue();
            if (key == null || value == null) {
                continue;
            }
            writer.println(param.getKey() + "=" + param.getValue());
        }
    }

    @Override
    public void prepare() {
        ListIterator<Runnable> li = this.listeners.listIterator(this.listeners.size());
        while (li.hasPrevious()) {
            li.previous().run();
        }
    }

    protected void addResource(Resource resource) {
        if (resource == null) {
            return;
        }
        this.resources.add(resource);
    }

    /**
     * Adds Runnable {@code listener} to internal list. This listener will be executed in the preparation phase.
     * <br/>
     * Usually used when some work must be done once after all modifications of an object.
     * <br/>
     * Listeners are called in he reverse order so that if they are set in constructors then the downmost class listeners are executed first.
     */
    protected void addBeforeWriteListener(Runnable listener) {
        this.listeners.add(listener);
    }

    protected String createSuffix(int index) {
        if (index < 1) {
            throw new IllegalArgumentException();
        }
        if (index == 1) {
            return "";
        }
        return Integer.toString(index);
    }

    @Override
    public @NotNull Collection<Resource> getResources() {
        return this.resources;
    }

    protected void removeParameter(String name) {
        this.params.remove(name);
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Boolean values map as: true -> 1, false -> 0
     */
    protected void manageParameter(String name, Boolean value) {
        if (value != null) {
            this.getParams().put(name, value ? "1" : "0");
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Integer values are saved as is. For example: 12345.
     */
    protected void manageParameter(String name, Integer value) {
        if (value != null) {
            this.getParams().put(name, value.toString());
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Double values are saved with the smallest possible length. For example: 2.125.
     */
    protected void manageParameter(String name, Double value) {
        if (value != null) {
            this.getParams().put(name, SkinUtils.print(value));
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * String values are saved as is.
     */
    protected void manageParameter(String name, String value) {
        if (value != null) {
            this.getParams().put(name, value);
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Formulas are saved as <code>value.toSting()</code>.
     */
    protected void manageParameter(String name, Formula value) {
        if (value != null) {
            this.getParams().put(name, value.toString());
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Measures are saved as <code>value.getName()</code>.
     */
    protected void manageParameter(String name, Measure value) {
        if (value != null) {
            this.getParams().put(name, value.getName());
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Gettable values are saved as <code>value.get()</code>.
     */
    protected void manageParameter(String name, Object value) {
        if (value != null) {
            this.getParams().put(name, value.toString());
        } else {
            this.getParams().remove(name);
        }
    }

    protected void manageParameter(String name, String[] value) {
        if (value != null && value.length > 0) {
            this.getParams().put(name, SkinUtils.pipeSeparatedList(Arrays.asList(value)));
        } else {
            this.getParams().remove(name);
        }
    }

    protected void manageParameter(String name, Collection<?> value) {
        if (value != null && value.size() > 0) {
            this.getParams().put(name, SkinUtils.pipeSeparatedList(value));
        } else {
            this.getParams().remove(name);
        }
    }

    /**
     * Adds, sets or removes option of section.<br/>
     * If value is null option <code>name</code> is removed. Else pair <code>name=value</code> is added to section.<br/>
     * Color values are saved as four components 0-255, with <code>,</code> (comma) as delimiter.
     */
    protected void manageParameter(String name, Color value) {
        if (value != null) {
            this.getParams().put(name, SkinUtils.print(value));
        } else {
            this.getParams().remove(name);
        }
    }

    protected void manageParameter(String name, Action value) {
        if (value != null) {
            this.getParams().put(name, value.toString());
        } else {
            this.getParams().remove(name);
        }
    }

    protected void manageParameter(String name, Resource value) {
        if (value != null) {
            this.getParams().put(name, value.getUsage());
            this.resources.add(value);
        } else {
            this.getParams().remove(name);
        }
    }
}
