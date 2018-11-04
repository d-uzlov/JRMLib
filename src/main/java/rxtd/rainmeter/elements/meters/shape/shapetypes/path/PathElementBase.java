package rxtd.rainmeter.elements.meters.shape.shapetypes.path;

public abstract class PathElementBase implements PathElement {
    private String name;
    private String params;

    public String getName() {
        return name;
    }

    public String getParams() {
        return params;
    }

    protected void setImage(String name, String params) {
        this.name = name;
        this.params = params;
    }

    @Override
    public String toString() {
        return this.name + " " + this.params;
    }
}
