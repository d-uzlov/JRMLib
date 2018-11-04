package rxtd.rainmeter.elements.meters.shape.shapetypes;

import rxtd.rainmeter.elements.meters.shape.ShapeUtils;
import rxtd.rainmeter.formulas.Formula;

/**
 * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Line">Rainmeter documentation</a>
 */
public class ShapeLine extends ShapeElementBase {
    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Line">Rainmeter documentation</a>
     */
    public ShapeLine(double startX, double startY, double endX, double endY) {
        this.setImage("Line", ShapeUtils.createImage(false,
                startX, startY,
                endX, endY));
    }

    /**
     * @see <a href="https://docs.rainmeter.net/manual/meters/shape/#Line">Rainmeter documentation</a>
     */
    public ShapeLine(Formula startX, Formula startY, Formula endX, Formula endY) {
        this.setImage("Line", ShapeUtils.createImage(false,
                startX, startY,
                endX, endY));
    }
}
