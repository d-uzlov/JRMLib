package rxtd.rainmeter.resources;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Something that is used in skin.
 */
public interface Resource {
    /**
     * String that should be used in skins when using this resource.
     */
    @NotNull
    String getUsage();

    /**
     * Human readable type of resource. "image/png", "plugin", etc.
     */
    Path getType();

    /**
     * Currently used for file write deduplication but may be used for other purposes in the future.
     *
     * @return all file paths that are used by this resource.
     */
    @Nullable
    List<Path> getPaths(Path configResources, Path suiteResources);

    /**
     * @return {@code false} if human action should be taken to manage this resource (e.g. to copy some file)
     */
    boolean isAutoManaged();

    /**
     * @param configResources path to folder of a config.
     * @param suiteResources  path to "@Resources" folder.
     */
    void patch(Path configResources, Path suiteResources, @NotNull ResourceOptions options) throws IOException;
}
