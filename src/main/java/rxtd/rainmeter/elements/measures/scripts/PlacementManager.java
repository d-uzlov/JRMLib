package rxtd.rainmeter.elements.measures.scripts;

import rxtd.Pair;
import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;
import rxtd.rainmeter.elements.Element;
import rxtd.rainmeter.elements.measures.Measure;
import rxtd.rainmeter.elements.meters.Meter;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.resources.ResourceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Script is designed to be used with PerfmonPDH plugin.
 * It may not work with other plugins/measures.
 */
public class PlacementManager extends ScriptBase<PlacementManager> {
    private final static Resource SCRIPT = new ResourceFactory().jarScript("/rxtd/rainmeter/scripts/positionManager/PositionManager.lua", "PositionManager", true);

    private List<MeasureGroup> measures = null;
    private int measureElements = 0;
    private List<ManagedGroup> managed = null;
    private int managedElements = 0;
    private List<Pair<Integer, Integer>> positions = null;

    public PlacementManager(String name) {
        super(name, SCRIPT);

        this.addBeforeWriteListener(() -> {
            this.check();
            this.generateFields();
        });
    }

    public PlacementManager() {
        this(null);
    }

    @Override
    protected PlacementManager getThis() {
        return this;
    }

    private void check() {
        if (this.measures == null || this.managed == null || this.positions == null) {
            throw new RuntimeException("nothing to flash");
        }
        if (this.measureElements != this.managedElements) {
            throw new RuntimeException("measures and meters has different number of elements");
        }
        if (this.managed.size() != this.positions.size()) {
            throw new RuntimeException("meters and positions has different number of elements");
        }
    }

    private String makeTMeasures() {
        ArrayList<String> groups = new ArrayList<>();
        for (var v : this.measures) {
            ArrayList<String> list = new ArrayList<>();
            list.add(v.checkMeasure.getName());
            list.addAll(v.values);
            String group = ScriptBase.makeLuaTableInitializer(list, true);
            groups.add(group);
        }
        return ScriptBase.makeLuaTableInitializer(groups, false);
    }

    private String makeTMeters() {
        ArrayList<String> groups = new ArrayList<>();
        for (var v : this.managed) {
            ArrayList<String> list = new ArrayList<>();
            list.add(v.base.getName());
            list.add(v.group);
            for (var p : v.meterOptions) {
                list.add(p.key.getName());
                list.add(p.value);
            }
            String group = ScriptBase.makeLuaTableInitializer(list, true);
            groups.add(group);
        }
        return ScriptBase.makeLuaTableInitializer(groups, false);
    }

    private String makeTPlaceMap() {
        ArrayList<String> values = new ArrayList<>();
        for (var p : this.positions) {
            values.add(p.key.toString());
            values.add(p.value.toString());
        }
        return ScriptBase.makeLuaTableInitializer(values, false);
    }

    private void generateFields() {
        this.manageParameter("Measures", this.makeTMeasures());
        this.manageParameter("Meters", this.makeTMeters());
        this.manageParameter("Places", this.makeTPlaceMap());
    }

    public PlacementManager setMeasures(List<MeasureGroup> measures) {
        if (measures == null || measures.isEmpty()) {
            this.measures = null;
            return getThis();
        }
        int count = measures.get(0).values.size();
        for (var v : measures) {
            if (count != v.values.size()) {
                throw new RuntimeException("Not all measure groups has the same count of elements");
            }
        }
        this.measureElements = count;
        this.measures = measures;
        return getThis();
    }

    public PlacementManager setMeters(List<ManagedGroup> groups) {
        if (groups == null || groups.isEmpty()) {
            this.managed = null;
            return getThis();
        }
        int count = groups.get(0).meterOptions.size();
        for (var v : groups) {
            if (count != v.meterOptions.size()) {
                throw new RuntimeException("Not all managed groups has the same count of elements");
            }
        }
        this.managedElements = count;
        this.managed = groups;
        return getThis();
    }

    public PlacementManager setPositions(List<Pair<Integer, Integer>> positions) {
        this.positions = positions;
        return getThis();
    }

    public Action bangReset() {
        return BangUtils.commandMeasure(this.getName(), "reset()", null);
    }

    public static class MeasureGroup {
        public Measure checkMeasure;
        public List<String> values = new ArrayList<>();

        public MeasureGroup(Measure checkMeasure) {
            this.checkMeasure = checkMeasure;
        }
    }

    public static class ManagedGroup {
        public Meter base;
        public String group;
        public List<Pair<Element, String>> meterOptions = new ArrayList<>();

        public ManagedGroup(Meter base, String group) {
            this.base = base;
            this.group = group;
        }
    }
}
