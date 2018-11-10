package rxtd.rainmeter.resources;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.variables.Variables;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Resource that is represented by one file in the filesystem.
 */
public class FileResource implements Resource {
    private final Path path;
    private final String usage;
    private final Path type;
    private final boolean isShared;
    private final StreamProcessor streamProcessor;

    /**
     * @param path            will be used for resource name in file system
     * @param usage           if not null, specifies usage in rainmeter
     * @param type            human readable description of resource
     * @param isShared        determines whether resource should be in config folder of in resource folder.
     * @param streamProcessor Object that should handle writing into open OutputStream.
     */
    public FileResource(String path, @Nullable String usage, @Nullable Path type, boolean isShared, @NotNull FileResource.StreamProcessor streamProcessor) {
        this.path = Paths.get(path);
        this.streamProcessor = streamProcessor;
        this.usage = usage != null ? usage : isShared ? Variables.Skin.RESOURCES_FOLDER.getUsage() + path : path;
        this.type = type;
        this.isShared = isShared;
    }

    @NotNull
    @Override
    public String getUsage() {
        return this.usage;
    }

    @Override
    public Path getType() {
        return this.type;
    }

    @NotNull
    @Override
    public List<Path> getPaths(Path configResources, Path suiteResources) {
        if (this.isShared) {
            Path path = suiteResources.resolve(this.path);
            return Collections.singletonList(path);
        } else {
            Path path = configResources.resolve(this.path);
            return Collections.singletonList(path);
        }
    }

    @Override
    public String toString() {
        return "File{" +
                "path=" + this.path +
                ", usage='" + this.usage + '\'' +
                ", isShared=" + this.isShared +
                '}';
    }

    @Override
    public void patch(Path configResources, Path suiteResources, @NotNull ResourceOptions options) throws IOException {
        Path path = this.getPaths(configResources, suiteResources).get(0);

        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
        } else if (!options.overrideFiles) {
            return;
        }

        OutputStream stream = new BufferedOutputStream(Files.newOutputStream(path));
        this.streamProcessor.write(stream);
        stream.close();
    }

    @Override
    public boolean isAutoManaged() {
        return true;
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        FileResource that = (FileResource) o;
        return this.isShared == that.isShared &&
                Objects.equals(this.path, that.path) &&
                Objects.equals(this.usage, that.usage) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(this.path, this.usage, this.type, this.isShared);
    }

    public interface StreamProcessor {
        void write(OutputStream stream) throws IOException;
    }
}
