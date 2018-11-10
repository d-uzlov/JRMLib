package rxtd.rainmeter.resources;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class VirtualResource implements Resource {
    private final String usage;
    private final Path type;

    public VirtualResource(String usage, Path type) {
        this.usage = usage;
        this.type = type;
    }

    @Override
    public @NotNull String getUsage() {
        return this.usage;
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
    public String toString() {
        return "VirtualResource{" +
                "usage='" + this.usage + '\'' +
                '}';
    }

    @Override
    final public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        VirtualResource that = (VirtualResource) o;
        return Objects.equals(this.usage, that.usage) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    final public int hashCode() {
        return Objects.hash(this.usage, this.type);
    }
}
