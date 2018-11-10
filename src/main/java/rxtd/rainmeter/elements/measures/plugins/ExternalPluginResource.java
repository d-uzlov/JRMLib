package rxtd.rainmeter.elements.measures.plugins;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.resources.ResourceOptions;
import rxtd.rainmeter.resources.StreamProvider;
import rxtd.rainmeter.variables.Variables;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExternalPluginResource implements PluginResource {
    private final String pluginName;
    private final String version;
    private final String usage;
    private final StreamProvider dll32;
    private final StreamProvider dll64;
    private final boolean isLocal;
    private final Path type;

    /**
     * @param pluginName Plugin folder name, also used in a rainmeter section to specify plugin.
     * @param version    String that is used to specify plugin folder.
     * @param isLocal    whether this plugin is in Rainmeter/Plugins folder or in the suite folder.
     */
    public ExternalPluginResource(String pluginName, String version, StreamProvider dll32, StreamProvider dll64, boolean isLocal) {
        this.pluginName = pluginName;
        this.version = version;
        this.dll32 = dll32;
        this.dll64 = dll64;
        this.isLocal = isLocal;
        this.type = Paths.get("plugin", this.isLocal ? "local" : "external");
        if (this.isLocal) {
            this.usage = Variables.Skin.RESOURCES_FOLDER.getUsage() + "Plugins/" + pluginName + "/" + (version == null ? "" : version);
        } else {
            this.usage = pluginName;
        }
    }

    private static void writePlugin(@NotNull Path path, @Nullable StreamProvider streamProvider, @NotNull ResourceOptions options) throws IOException {
        if (streamProvider == null) {
            return;
        }
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
        } else if (!options.overrideFiles) {
            return;
        }

        OutputStream stream = Files.newOutputStream(path);
        streamProvider.getStream().transferTo(stream);
        stream.close();
    }

    public static ExternalPluginResource fromJar(String internalPath, String pluginName, String version, boolean isLocal) {
        return new ExternalPluginResource(pluginName, version, () -> PluginBase.class.getResourceAsStream(internalPath + "/32-bit.dll"), () -> PluginBase.class.getResourceAsStream(internalPath + "/64-bit.dll"), isLocal);
    }

    public boolean isLocal() {
        return this.isLocal;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return "Plugin{external," +
                "name='" + this.pluginName + '\'' +
                ", version='" + this.version + '\'' +
                ", 32=" + (this.dll32 != null) +
                ", 64=" + (this.dll64 != null) +
                ", isLocal=" + this.isLocal +
                '}';
    }

    @Override
    public @NotNull String getUsage() {
        return this.usage;
    }

    @Override
    public Path getType() {
        return this.type;
    }

    public Path getPluginFolder(Path folder) {
        Path path = folder;
        if (this.isLocal) {
            path = path.resolve("Plugins");
        } else {
            path = path.getParent().resolve("_Plugins");
        }
        path = path.resolve(this.pluginName);

        return path;
    }

    @Override
    public @NotNull List<Path> getPaths(Path configResources, Path suiteResources) {
        Path path = this.getPluginFolder(suiteResources);

        if (this.version != null) {
            path = path.resolve(this.version);
        }
        Path p32;
        Path p64;
        if (this.isLocal) {
            p32 = path.resolve("32-bit/" + this.pluginName + ".dll");
            p64 = path.resolve("64-bit/" + this.pluginName + ".dll");
        } else {
            p32 = path.resolve("32-bit.dll");
            p64 = path.resolve("64-bit.dll");
        }

        List<Path> paths = new ArrayList<>();
        paths.add(p32);
        paths.add(p64);
        return paths;
    }

    @Override
    public boolean isAutoManaged() {
        return this.isLocal();
    }

    @Override
    public void patch(Path configResources, Path suiteResources, @NotNull ResourceOptions options) throws IOException {
        writePlugin(this.getPaths(configResources, suiteResources).get(0), this.dll32, options);
        writePlugin(this.getPaths(configResources, suiteResources).get(1), this.dll64, options);
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        ExternalPluginResource that = (ExternalPluginResource) o;
        return this.isLocal == that.isLocal &&
                Objects.equals(this.pluginName, that.pluginName) &&
                Objects.equals(this.version, that.version) &&
                Objects.equals(this.dll32, that.dll32) &&
                Objects.equals(this.dll64, that.dll64) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(this.pluginName, this.version, this.usage, this.dll32, this.dll64, this.isLocal, this.type);
    }
}
