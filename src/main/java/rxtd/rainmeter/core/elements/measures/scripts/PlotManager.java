package rxtd.rainmeter.core.elements.measures.scripts;

import rxtd.rainmeter.core.formulas.Formula;
import rxtd.rainmeter.core.resources.Resource;
import rxtd.rainmeter.core.resources.ResourceFactory;

/**
 * Script is used to calculate several values that characterizes lines and histograms:
 * Maximum, Delta, Average, Sum.
 */
public class PlotManager extends ScriptBase<PlotManager> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/core/scripts/plotManagement/PlotManager.lua", "PlotManager", true);

    public PlotManager(String name) {
        super(name, SCRIPT);
    }

    public PlotManager() {
        this(null);
    }

    /**
     * @param width Count of input values used to determine output values.
     */
    public PlotManager setHistWidth(Integer width) {
        this.manageParameter("HistWidth", width);
        return this;
    }

    /**
     * @param value Input value.
     */
    public PlotManager setFormula(Formula value) {
        this.manageParameter("CurValue", value);
        return this;
    }

    /**
     * Scale in 2 * 2^N.
     */
    public PlotManager useRainmeterlikeScale() {
        this.manageParameter("UseRainlikeScale", true);
        this.removeParameter("RainlikeScaleStart");
        this.removeParameter("RainlikeScaleStep");
        return this;
    }

    /**
     * Scale in {@code start} * {@code step}^N.
     */
    public PlotManager useRainmeterlikeScale(Double start, Double step) {
        this.manageParameter("UseRainlikeScale", true);
        this.manageParameter("RainlikeScaleStart", start);
        this.manageParameter("RainlikeScaleStep", step);
        return this;
    }

    /**
     * Do not use RainmeterlikeScale. That is: use any found max value as exact max.
     * <br/>
     * Default behavior.
     */
    public PlotManager usePreciseMax() {
        this.removeParameter("UseRainlikeScale");
        this.removeParameter("RainlikeScaleStart");
        this.removeParameter("RainlikeScaleStep");
        return this;
    }

    /**
     * If found maximum is less than {@code minimum} then minimum is returned instead.
     * <br/>
     * Default {@code 1}.
     */
    public PlotManager setMinimum(Double minimum) {
        this.manageParameter("Minimum", minimum);
        return this;
    }

    /**
     * If no changes have happened in last {@code HistWidth} updates then it's safe to stop lines and histograms
     * that shown these values.
     * <br/>
     * This option sets a meter/measure group that will be disabled if no changes happened and enabled if a change occurred.
     *
     * @see #setLinkedGroupUpdateDivider
     */
    public PlotManager setLinkedGroup(String group) {
        this.manageParameter("LinkedGroup", group);
        return this;
    }

    /**
     * If no changes have happened in last {@code HistWidth} updates then it's safe to stop lines and histograms
     * that shown these values.
     * <br/>
     * This option sets update divider for a meter/measure group that will be disabled if no changes happened and enabled if a change occurred.
     *
     * @see #setLinkedGroup
     */
    public PlotManager setLinkedGroupUpdateDivider(Integer value) {
        this.manageParameter("LinkedGroupUpdateDivider", value);
        return this;
    }

    /**
     * If {@code true}, absolute values are used. This can be useful if you make a twi-sided histogram.
     */
    public PlotManager setUseAbsValue(Boolean value) {
        this.manageParameter("FindAbsMax", value);
        return this;
    }

    /**
     * @return max between saved values
     */
    public Formula formulaMax() {
        return this.inline("nMax");
    }

    /**
     * @return max between all measured values
     */
    public Formula formulaAllTimeMax() {
        return this.inline("outAllTimeMax");
    }


    /**
     * @return delta between the current and the oldest values
     */
    public Formula formulaDelta() {
        return this.inline("outDelta");
    }

    /**
     * @return sum of all saved values
     */
    public Formula formulaSum() {
        return this.inline("outSum");
    }

    /**
     * @return average of all saved values
     */
    public Formula formulaAverage() {
        return this.inline("outAverage");
    }

    /**
     * @return sum of all measured values
     */
    public Formula formulaSessionSum() {
        return this.inline("outSessionSum");
    }

    @Override
    protected PlotManager getThis() {
        return this;
    }
}
