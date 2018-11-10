package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.elements.meters.shape.shapetypes.ExternalDescription;

public interface PathDescription extends ExternalDescription {
    PathDescription append(PathElement element);

    PathDescription append(PathModifier modifier);

    ShapePath.PathDescriptionImpl setClosePath(boolean closePath);

    String toString();
}
