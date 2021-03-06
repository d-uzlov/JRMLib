package rxtd.rainmeter.core.elements.meters.shape;

import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ExternalDescription;
import rxtd.rainmeter.core.elements.meters.MeterBase;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ShapeElement;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code Shape} is used to create one or more vector graphic shapes.
 *
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/">Rainmeter documentation</a>
 */
public class Shape extends MeterBase<Shape> {
    private final Map<String, ExternalDescription> descriptions = new HashMap<>();
    private final Map<ShapeElement, String> shapeMap = new HashMap<>();
    private int index = 1;

    public Shape(String name) {
        super(name, "Shape");

        this.addBeforeWriteListener(() -> {
            for (var e : this.descriptions.entrySet()) {
                var desc = e.getValue();
                this.manageParameter(desc.getName(), desc);
            }
        });
    }

    @Override
    protected Shape getThis() {
        return this;
    }

    private String nextShapeID() {
        String key = "Shape";
        if (this.index > 1) {
            key += this.index;
        }
        this.index++;
        return key;
    }

    public Shape addShape(ShapeElement<?> shape) {
        if (this.shapeMap.containsKey(shape)) {
            throw new IllegalArgumentException("same complex shape used twice");
        }
        String shapeID = this.nextShapeID();
        this.shapeMap.put(shape, shapeID);

        String shapeImage = shape.asString(this.shapeMap);
        this.manageParameter(shapeID, shapeImage);

        var descriptions = shape.getExternalDescriptions();
        if (descriptions == null) {
            return this.getThis();
        }
        for (var desc : descriptions) {
            if (desc == null) {
                continue;
            }
            this.descriptions.compute(desc.getName(), (key, description) -> {
                if (description == null) {
                    return desc;
                }
                if (description.equals(desc)) {
                    throw new IllegalArgumentException("Two different descriptions have same name: " + desc.getName());
                }
                return description;
            });
        }

        return this.getThis();
    }
}
