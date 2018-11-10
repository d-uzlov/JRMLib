package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.ShapeElementBase;

import java.util.ArrayList;
import java.util.List;

public class ShapePath extends ShapeElementBase {
    private final double startX;
    private final double startY;
    private final List<Object> elements = new ArrayList<>();
    private boolean closePath = false;
    private boolean path1 = false;

    // TODO
    public ShapePath(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;

        this.addBeforeWriteListener(() -> {
            this.construct();
        });
    }

    public ShapePath setClosePath(boolean closePath) {
        this.closePath = closePath;
        return this;
    }

    public ShapePath setPath1(boolean closePath) {
        this.closePath = closePath;
        return this;
    }

    public ShapePath append(PathElement element) {
        this.elements.add(element);
        return this;
    }

    public ShapePath append(PathModifier modifier) {
        this.elements.add(modifier);
        return this;
    }

    private void construct() {
        if (this.closePath) {
            this.elements.add("ClosePath");
        }
        this.setImage("Path" + (this.path1 ? "1" : ""), SkinUtils.pipeSeparatedList(this.elements));
    }
}
