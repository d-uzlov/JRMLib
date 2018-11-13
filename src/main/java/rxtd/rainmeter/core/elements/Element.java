package rxtd.rainmeter.core.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.core.resources.Resource;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

/**
 * Section in skin.
 */
public interface Element<T extends Element<T>> {
    /**
     * Map used to hold parameters of section.
     * Example: Meter=Image represented as {"Meter" : "Image"} in params
     */
    @NotNull Map<String, String> getParams();

    /**
     * Name of the section
     * Example: SomeMeter
     * Will be written to file as [SomeMeter]
     */
    @NotNull String getName();

    /**
     * Function used to write skin to .ini file.
     *
     * @param writer open writer to .ini file.
     */
    void write(@NotNull PrintWriter writer);

    /**
     * Used for preparing element for writing.
     */
    void prepare();

    /**
     * @return resources used in this skin.
     */
    @Nullable Collection<Resource> getResources();

    T include(String name, String incUsage);
}
