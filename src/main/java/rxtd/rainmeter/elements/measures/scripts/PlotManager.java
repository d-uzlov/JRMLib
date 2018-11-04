package rxtd.rainmeter.elements.measures.scripts;

import rxtd.rainmeter.formulas.Formula;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.resources.ResourceFactory;

public class PlotManager extends ScriptBase<PlotManager> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/scripts/plotManagement/PlotManager.lua", "PlotManager", true);

    public PlotManager(String name) {
        super(name, SCRIPT);
    }

    public PlotManager() {
        this(null);
    }

    public PlotManager setHistWidth(Integer width) {
        this.manageParameter("HistWidth", width);
        return this;
    }

    public PlotManager setFormula(Formula value) {
        this.manageParameter("CurValue", value);
        return this;
    }

    public PlotManager useRainmeterlikeScale() {
        this.manageParameter("UseRainlikeScale", true);
        this.removeParameter("RainlikeScaleStart");
        this.removeParameter("RainlikeScaleStep");
        return this;
    }

    public PlotManager useRainmeterlikeScale(Double start, Double step) {
        this.manageParameter("UseRainlikeScale", true);
        this.manageParameter("RainlikeScaleStart", start);
        this.manageParameter("RainlikeScaleStep", step);
        return this;
    }

    public PlotManager usePreciseMax() {
        this.removeParameter("UseRainlikeScale");
        this.removeParameter("RainlikeScaleStart");
        this.removeParameter("RainlikeScaleStep");
        return this;
    }

    public PlotManager setMinimum(Double minimum) {
        this.manageParameter("Minimum", minimum);
        return this;
    }

    public PlotManager setLinkedGroup(String group) {
        this.manageParameter("LinkedGroup", group);
        return this;
    }

    public PlotManager setLinkedGroupUpdateDivider(Integer value) {
        this.manageParameter("LinkedGroupUpdateDivider", value);
        return this;
    }

    public Formula formulaMax() {
        return this.inline("nMax");
    }

    public Formula formulaAllTimeMax() {
        return this.inline("outAllTimeMax");
    }

    public Formula formulaDelta() {
        return this.inline("outDelta");
    }

    public Formula formulaSum() {
        return this.inline("outSum");
    }

    public Formula formulaAverage() {
        return this.inline("outAverage");
    }

    public Formula formulaSessionSum() {
        return this.inline("outSessionSum");
    }

    public PlotManager setUseAbsValue(Boolean value) {
        this.manageParameter("FindAbsMax", value);
        return this;
    }

    @Override
    protected PlotManager getThis() {
        return this;
    }
}
