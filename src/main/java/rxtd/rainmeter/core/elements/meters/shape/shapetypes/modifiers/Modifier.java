package rxtd.rainmeter.core.elements.meters.shape.shapetypes.modifiers;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ExternalDescription;

import java.util.Set;

public interface Modifier {
    @Nullable
    Set<ExternalDescription> getExternalDescriptions();

    String toString();
}
