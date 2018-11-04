package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers;

import rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers.extern.ExternalDescription;

import java.util.Set;

public abstract class ModifierBase implements Modifier {
    private String image;

    @Override
    public Set<ExternalDescription> getExternalDescriptions() {
        return null;
    }

    protected void setImage(String name, Object image) {
        this.image = name + " " + image;
    }

    @Override
    public String asString() {
        return this.image;
    }
}
