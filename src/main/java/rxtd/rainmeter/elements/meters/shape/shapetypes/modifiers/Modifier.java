package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers;

import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.extern.ExternalDescription;

import java.util.Set;

public interface Modifier {
    Set<ExternalDescription> getExternalDescriptions();

    String asString();
}
