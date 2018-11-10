package rxtd.rainmeter.elements.meters.shape.shapetypes.modifiers;

import rxtd.rainmeter.elements.meters.shape.shapetypes.ExternalDescription;

import java.util.Set;

public abstract class ModifierBase implements Modifier {
    private String image;

    protected void setImage(String name, Object image) {
        this.image = name + " " + image;
    }

    @Override
    public Set<ExternalDescription> getExternalDescriptions() {
        return null;
    }

    @Override
    public String toString() {
        return this.image;
    }
}
