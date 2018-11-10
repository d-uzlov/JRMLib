package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

import org.jetbrains.annotations.NotNull;
import rxtd.rainmeter.SkinUtils;
import rxtd.rainmeter.elements.meters.shape.shapetypes.ShapeElementBase;

public class ShapePath extends ShapeElementBase {
    private PathDescription description = null;
    private boolean path1 = false;

    public ShapePath() {
        this.addBeforeWriteListener(this::construct);
    }
    public ShapePath setPath1(boolean path1) {
        this.path1 = path1;
        return this;
    }

    public ShapePath setPathDescription(PathDescription description) {
        this.description = description;
        return this;
    }

    private void construct() {
        if (this.description == null) {
            throw new RuntimeException("path description must be set for Path shape");
        }
        this.setImage("Path" + (this.path1 ? "1" : ""), this.description.getName());
    }

    public static class PathDescriptionImpl implements PathDescription {
        private final StringBuilder sb = new StringBuilder();
        private boolean closePath = false;
        private final String name;
        private String image = null;

        public PathDescriptionImpl(String name, double startX, double startY) {
            this.name = name;
            this.sb.append(SkinUtils.print(startX)).append(",").append(SkinUtils.print(startY));
        }

        @Override
        public PathDescriptionImpl append(PathElement element) {
            this.sb.append("|").append(element.toString());
            return this;
        }

        @Override
        public PathDescriptionImpl append(PathModifier modifier) {
            this.sb.append("|").append(modifier.toString());
            return this;
        }

        @Override
        public PathDescriptionImpl setClosePath(boolean closePath) {
            this.closePath = closePath;
            return this;
        }

        @NotNull
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            if (this.image != null) {
                return this.image;
            }
            if (this.closePath) {
                this.sb.append("|").append("ClosePath");
            }
            this.image = this.sb.toString();
            return this.image;
        }
    }
}
