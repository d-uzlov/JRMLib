package rxtd.rainmeter.elements.measures.plugins;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.elements.measures.MeasureBase;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.variables.Variables;

import java.util.function.Supplier;

/**
 * Base class for all plugin measures.<br/>
 * TODO add desc
 *
 * @param <T>
 */
public abstract class PluginBase<T extends PluginBase<T>> extends MeasureBase<T> {
    private final static PluginResource LOCAL_WRAP_PLUGIN = ExternalPluginResource.fromJar("LocalPluginLoader", null, false);
    private PluginResource wrappedPlugin = null;
    private boolean sectionVariablesExist = false;

    public PluginBase(String name, PluginResource plugin) {
        super(name, "Plugin");

        this.addBeforeWriteListener(() -> {
            if (this.wrappedPlugin != null) {
                if (this.getParams().containsKey("PluginPath")) {
                    throw new RuntimeException();
                }
                this.manageParameter("Plugin", LOCAL_WRAP_PLUGIN);
                this.manageParameter("PluginPath", this.wrappedPlugin);
            } else {
                this.manageParameter("Plugin", plugin);
            }
        });
    }

    protected static VirtualPluginResource virtualize(PluginResource plugin) {
        return new VirtualPluginResource(plugin.getName(), plugin.getVersion());
    }

    /**
     * This method is a simple wrap over {@link #wrap(PluginResource)}.
     *
     * @param pluginPath Path to a folder with two files: {@code 64-bit.dll} and {@code 32-bit.dll}, relative to Resources folder
     * @see #wrap(PluginResource)
     */
    public final T wrap(@Nullable String pluginPath) {
        return this.wrap(new VirtualPluginResource(Variables.Skin.RESOURCES_FOLDER.getUsage() + pluginPath, null));
    }

    /**
     * In rainmeter all plugins are global and should be in global Plugins directory.
     * <br/>
     * It can be inconvenient. There are times when you don't want plugin to be global.
     * <br/>
     * This function replaces plugin link to a local one, which is represented as PluginResource {@code plugin}.
     */
    public T wrap(@Nullable PluginResource plugin) {
        if (this.sectionVariablesExist && this.wrappedPlugin != plugin) {
            throw new RuntimeException("wrapping can't be changed after inline function call creation");
        }
        this.wrappedPlugin = plugin;
        return this.getThis();
    }

    protected @NotNull Supplier<PluginResource> getPluginProvider() {
        return () -> this.wrappedPlugin;
    }

    /**
     * This function should be called after every SectionVariable creation for this measure, if {@link #inline} wasn't used.
     */
    protected void setSectionVariablesExist() {
        this.sectionVariablesExist = true;
    }

    /**
     * @param function name of the internal plugin function
     * @param args     list of args for function. May be {@code null} if none needed.
     * @return inline formula that calls specified function with specified args.
     */
    protected Formula inline(@NotNull String function, @Nullable String... args) {
        this.sectionVariablesExist = true;

        StringBuilder value = new StringBuilder("[&" + this.getName() + ":");
        if (this.wrappedPlugin == null) {
            value.append(function).append("(");
        } else {
            value.append("_(").append(function);
            if (args != null) {
                value.append(",");
            }
        }
        if (args != null) {
            value.append(String.join(",", args));
        }
        value.append(")]");

        return new Formula(value.toString());
    }
}
