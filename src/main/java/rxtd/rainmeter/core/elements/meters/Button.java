package rxtd.rainmeter.core.elements.meters;

import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.resources.Resource;

public class Button extends GeneralImage<Button> {
    public Button(String name) {
        super(name, "Button");
    }

    public Button() {
        this(null);
    }

    @Override
    protected Button getThis() {
        return this;
    }

    public Button setButtonImage(Resource image) {
        this.manageParameter("BitmapImage", image);
        return this.getThis();
    }

    public Button setButtonCommand(Action action) {
        this.manageParameter("ButtonCommand", action);
        return this.getThis();
    }

    @Override
    public Button setImageCrop(Crop crop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Button setImageRotate(Double angle) {
        throw new UnsupportedOperationException();
    }
}
