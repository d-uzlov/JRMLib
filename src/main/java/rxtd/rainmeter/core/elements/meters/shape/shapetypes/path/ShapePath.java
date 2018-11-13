package rxtd.rainmeter.core.elements.meters.shape.shapetypes.path;

import org.jetbrains.annotations.NotNull;
import rxtd.rainmeter.core.SkinUtils;
import rxtd.rainmeter.core.elements.meters.shape.shapetypes.ShapeElementBase;

import java.util.Objects;

public class ShapePath extends ShapeElementBase {
    private PathDescription description = null;
    private boolean path1 = false;

    public ShapePath() {
        this.addBeforeBurnListener(this::construct);
        this.addBeforeBurnListener(() -> this.addExternalDescription(this.description));
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
        private final String name;
        private boolean closePath = false;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            PathDescriptionImpl that = (PathDescriptionImpl) o;
            return this.closePath == that.closePath &&
                    // TODO bad :/
                    // How should we compare instances of this class?
                    // Same goes for hashCode
                    Objects.equals(this.sb, that.sb) &&
                    Objects.equals(this.name, that.name) &&
                    Objects.equals(this.image, that.image);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.sb, this.name, this.closePath, this.image);
        }
    }
}
