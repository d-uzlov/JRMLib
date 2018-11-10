package rxtd.rainmeter.elements.measures.plugins;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;
import rxtd.rainmeter.elements.measures.MeasureBase;
import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.variables.Variables;

import java.util.function.Supplier;

public abstract class PluginBase<T extends PluginBase<T>> extends MeasureBase<T> {
    private final static PluginResource LOCAL_WRAP_PLUGIN = ExternalPluginResource.fromJar("/rxtd/rainmeter/plugins/LocalPluginLoader", "LocalPluginLoader", null, false);
    private final PluginResource plugin;
    private PluginResource wrappedPlugin = null;
    private String localPluginPath = null;
    private boolean sectionVariablesExist = false;

    public PluginBase(String name, PluginResource plugin) {
        super(name, "Plugin");
        this.plugin = plugin;

        this.addBeforeWriteListener(() -> {
            if (this.localPluginPath != null) {
                this.manageParameter("Plugin", LOCAL_WRAP_PLUGIN);
                this.addResource(LOCAL_WRAP_PLUGIN);
                this.manageParameter("PluginPath", Variables.Skin.RESOURCES_FOLDER.getUsage() + this.localPluginPath);
                this.addResource(this.wrappedPlugin);
            } else {
                this.manageParameter("Plugin", plugin);
                this.addResource(this.plugin);
            }
        });
    }

    /**
     * In rainmeter all plugins are global and should be in global Plugins directory.
     * <br/>
     * I think it's inconvenient. There are times when you don't want plugin to be global.
     * <br/>
     * This function replaces plugin link to a local one, which is specified by {@code pluginPath}.
     *
     * @param pluginPath Path to a folder with two files: {@code 64-bit.dll} and {@code 32-bit.dll}, relative to Resources folder
     * @see #wrap(ExternalPluginResource)
     */
    public T wrap(String pluginPath) {
        if (this.sectionVariablesExist) {
            throw new RuntimeException("wrapping can't be changed after bang creation");
        }
        if (pluginPath == null) {
            this.localPluginPath = null;
            this.wrappedPlugin = null;
            return getThis();
        }
        this.localPluginPath = pluginPath;
        this.wrappedPlugin = new VirtualPluginResource("./" + pluginPath, null);
        return getThis();
    }

    /**
     * In rainmeter all plugins are global and should be in global Plugins directory.
     * <br/>
     * I think it's inconvenient. There are times when you don't want plugin to be global.
     * <br/>
     * This function replaces plugin link to a local one, which is specified by {@code pluginPath}.
     *
     * @throws IllegalArgumentException if {@code plugin} is not local.
     * @see #wrap(String)
     */
    public T wrap(ExternalPluginResource plugin) {
        if (plugin == null) {
            return this.wrap((String) null);
        } else {
            if (!plugin.isLocal()) {
                throw new IllegalArgumentException();
            }
            return this.wrap(plugin.getUsage());
        }
    }

    protected Supplier<String> getLocalPathProvider() {
        return () -> this.localPluginPath;
    }

    /**
     * This function should be called after every SectionVariable creation for this measure.
     */
    protected void setSectionVariablesExist() {
        this.sectionVariablesExist = true;
    }

    /**
     * @param function name of the internal plugin function
     * @param args     list of args for function. May be {@code null} if none needed.
     * @return inline formula that calls specified function with specified args.
     */
    protected Formula inline(String function, @Nullable String... args) {
        if (this.wrappedPlugin != null) {

        }
        StringBuilder value = new StringBuilder("[&" + this.getName() + ":" + function + "(");
        if (args != null) {
            value.append(String.join(",", args));
        }
        value.append(")]");
        return new Formula(value.toString());
    }
}
