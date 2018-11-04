package rxtd.rainmeter.elements.meters;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.resources.Resource;

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
        return getThis();
    }

    public Button setButtonCommand(Action action) {
        this.manageParameter("ButtonCommand", action);
        return getThis();
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
