package rxtd.rainmeter.core.elements.measures.plugins;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.core.resources.ResourceOptions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class VirtualPluginResource implements PluginResource {
    private final String pluginName;
    private final String version;
    private final Path type;

    public VirtualPluginResource(String pluginName, String version) {
        if (pluginName == null) {
            throw new IllegalArgumentException();
        }
        this.pluginName = pluginName;
        this.version = version;
        this.type = Paths.get("plugin", "virtual");
    }

    @Override
    public String toString() {
        return "Plugin{virtual," +
                "file='" + this.pluginName + '\'' +
                (this.version != null ? ", version='" + this.version + '\'' : "") +
                '}';
    }

    @Override
    public String getName() {
        return this.pluginName;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public @NotNull String getUsage() {
        return this.pluginName;
    }

    @Override
    public Path getType() {
        return this.type;
    }

    @Override
    public @Nullable List<Path> getPaths(Path configResources, Path suiteResources) {
        return null;
    }

    @Override
    public boolean isAutoManaged() {
        return false;
    }

    @Override
    public void patch(Path configResources, Path suiteResources, @NotNull ResourceOptions options) {

    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        VirtualPluginResource that = (VirtualPluginResource) o;
        return Objects.equals(this.pluginName, that.pluginName) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(this.pluginName, this.type);
    }
}
