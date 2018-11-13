package rxtd.rainmeter.core.actions;

import rxtd.rainmeter.core.elements.Element;
import rxtd.rainmeter.core.formulas.Formula;
import rxtd.rainmeter.core.variables.Variable;

import java.util.ArrayList;

/**
 * Helper class for creating bangs.
 */
public class BangUtils {
    private static String escape(String string) {
        if (string == null) {
            return null;
        }
        return "\"\"\"" + string + "\"\"\"";
    }

    private static String escape(Object value) {
        if (value == null) {
            return null;
        }
        return "\"\"\"" + value.toString() + "\"\"\"";
    }

    private static String escape(Element value) {
        if (value == null) {
            return null;
        }
        return value.getName();
    }

    private static String escape(Variable value) {
        if (value == null) {
            return null;
        }
        return value.getName();
    }

    private static String escape(Formula value) {
        if (value == null) {
            return null;
        }
        return "\"\"\"" + value.toString() + "\"\"\"";
    }

    private static SimpleBang createBang(String command, String... args) {
        ArrayList<String> al = new ArrayList<>();
        for (String arg : args) {
            if (arg == null) {
                break;
            } else {
                al.add(arg);
            }
        }
        return new SimpleBang(command, al);
    }

    public static SimpleBang setClip(String value) {
        return createBang("SetClip", escape(value));
    }

    public static SimpleBang setWallpaper(String path, WallpaperPosition position) {
        return createBang("SetWallpaper", escape(path), escape(position));
    }

    public static SimpleBang about(AboutTab tab) {
        return createBang("About", escape(tab));
    }

    public static SimpleBang manage(ManageTab tab, String config, String file) {
        return createBang("Manage", escape(tab), escape(config), escape(file));
    }

    public static SimpleBang trayMenu() {
        return createBang("TrayMenu");
    }

    public static SimpleBang log(String string, ErrorType type) {
        return createBang("Log", escape(string), escape(type));
    }

    public static SimpleBang resetStats() {
        return createBang("ResetStats");
    }

    public static SimpleBang loadLayout(String name) {
        return createBang("LoadLayout", escape(name));
    }

    public static SimpleBang refreshApp() {
        return createBang("RefreshApp");
    }

    public static SimpleBang quit() {
        return createBang("Quit");
    }

    public static WindowsAction play(String path) {
        return new WindowsAction("Play", path);
    }

    public static WindowsAction playLoop(String path) {
        return new WindowsAction("PlayLoop", path);
    }

    public static WindowsAction playStop() {
        return new WindowsAction("PlayStop");
    }

    public static SimpleBang setOption(String section, String optionName, String value, String config) {
        return createBang("SetOption", escape(section), escape(optionName), escape(value), escape(config));
    }

    public static SimpleBang setVariable(String name, String value, String config) {
        return createBang("SetVariable", escape(name), escape(value), escape(config));
    }

    public static SimpleBang writeKeyValue(String sectionName, String key, String value, String path) {
        return createBang("SetVariable", escape(sectionName), escape(key), escape(value), escape(path));
    }

    public static SimpleBang setOptionGroup(String group, String option, String value, String config) {
        return createBang("SetOptionGroup", escape(group), escape(option), escape(value), escape(config));
    }

    public static SimpleBang setVariableGroup(String name, String value, String group) {
        return createBang("SetVariableGroup", escape(name), escape(value), escape(group));
    }

    public static SimpleBang show(String config) {
        return createBang("Show", escape(config));
    }

    public static SimpleBang hide(String config) {
        return createBang("Hide", escape(config));
    }

    public static SimpleBang toggle(String config) {
        return createBang("Toggle", escape(config));
    }

    public static SimpleBang showFade(String config) {
        return createBang("ShowFade", escape(config));
    }

    public static SimpleBang hideFade(String config) {
        return createBang("HideFade", escape(config));
    }

    public static SimpleBang toggleFade(String config) {
        return createBang("ToggleFade", escape(config));
    }

    public static SimpleBang fadeDuration(int milliseconds, String config) {
        return fadeDuration(Integer.toString(milliseconds), config);
    }

    public static SimpleBang fadeDuration(String value, String config) {
        return createBang("FadeDuration", escape(value), escape(config));
    }

    public static SimpleBang showBlur(String config) {
        return createBang("ShowBlur", escape(config));
    }

    public static SimpleBang hideBlur(String config) {
        return createBang("HideBlur", escape(config));
    }

    public static SimpleBang toggleBlur(String config) {
        return createBang("ToggleBlur", escape(config));
    }

    public static SimpleBang move(int x, int y, String config) {
        return move(Integer.toString(x), Integer.toString(y), config);
    }

    public static SimpleBang move(String x, String y, String config) {
        return createBang("Move", escape(x), escape(y), escape(config));
    }

    public static SimpleBang activateConfig(String config, String file) {
        return createBang("ActivateConfig", escape(config), escape(file));
    }

    public static SimpleBang toggleConfig(String config, String file) {
        return createBang("ToggleConfig", escape(config), escape(file));
    }

    public static SimpleBang deactivateConfig(String config) {
        return createBang("DeactivateConfig", escape(config));
    }

    public static SimpleBang update(String config) {
        return createBang("Update", escape(config));
    }

    public static SimpleBang redraw(String config) {
        return createBang("Redraw", escape(config));
    }

    public static SimpleBang refresh(String config) {
        return createBang("Refresh", escape(config));
    }

    public static SimpleBang delay(int milliseconds) {
        return delay(Integer.toString(milliseconds));
    }

    public static SimpleBang delay(String milliseconds) {
        return createBang("Delay", escape(milliseconds));
    }

    public static SimpleBang skinMenu(String config) {
        return createBang("SkinMenu", escape(config));
    }

    public static SimpleBang skinCustomMenu(String config) {
        return createBang("SkinCustomMenu", escape(config));
    }

    public static SimpleBang setTransparency(int alpha, String config) {
        return setTransparency(Integer.toString(alpha), config);
    }

    public static SimpleBang setTransparency(String alpha, String config) {
        return createBang("SetTransparency", escape(alpha), escape(config));
    }

    public static SimpleBang zPos(ZPosition zPosition, String config) {
        return zPos(zPosition.toString(), config);
    }

    public static SimpleBang zPos(String zPosition, String config) {
        return createBang("ZPos", escape(zPosition), escape(config));
    }

    public static SimpleBang draggable(SwitchOptions value, String config) {
        return draggable(value.toString(), config);
    }

    public static SimpleBang draggable(String value, String config) {
        return createBang("Draggable", escape(value), escape(config));
    }

    public static SimpleBang keepOnScreen(SwitchOptions value, String config) {
        return keepOnScreen(value.toString(), config);
    }

    public static SimpleBang keepOnScreen(String value, String config) {
        return createBang("KeepOnScreen", escape(value), escape(config));
    }

    public static SimpleBang clickThrough(SwitchOptions value, String config) {
        return clickThrough(value.toString(), config);
    }

    public static SimpleBang clickThrough(String value, String config) {
        return createBang("ClickThrough", escape(value), escape(config));
    }

    public static SimpleBang snapEdges(SwitchOptions value, String config) {
        return snapEdges(value.toString(), config);
    }

    public static SimpleBang snapEdges(String value, String config) {
        return createBang("SnapEdges", escape(value), escape(config));
    }

    public static SimpleBang autoSelectScreen(SwitchOptions value, String config) {
        return autoSelectScreen(value.toString(), config);
    }

    public static SimpleBang autoSelectScreen(String value, String config) {
        return createBang("AutoSelectScreen", escape(value), escape(config));
    }

    public static SimpleBang editSkin(String config, String file) {
        return createBang("EditSkin", escape(config), escape(file));
    }

    public static SimpleBang showMeter(String name, String config) {
        return createBang("ShowMeter", escape(name), escape(config));
    }

    public static SimpleBang hideMeter(String name, String config) {
        return createBang("HideMeter", escape(name), escape(config));
    }

    public static SimpleBang toggleMeter(String name, String config) {
        return createBang("ToggleMeter", escape(name), escape(config));
    }

    public static SimpleBang updateMeter(String name, String config) {
        return createBang("UpdateMeter", escape(name), escape(config));
    }

    public static SimpleBang moveMeter(int x, int y, String meterName, String config) {
        return moveMeter(Integer.toString(x), Integer.toString(y), meterName, config);
    }

    public static SimpleBang moveMeter(String x, String y, String meterName, String config) {
        return createBang("MoveMeter", escape(x), escape(y), escape(meterName), escape(config));
    }

    public static SimpleBang showMeterGroup(String group, String config) {
        return createBang("ShowMeterGroup", escape(group), escape(config));
    }

    public static SimpleBang hideMeterGroup(String group, String config) {
        return createBang("HideMeterGroup", escape(group), escape(config));
    }

    public static SimpleBang toggleMeterGroup(String group, String config) {
        return createBang("ToggleMeterGroup", escape(group), escape(config));
    }

    public static SimpleBang updateMeterGroup(String group, String config) {
        return createBang("UpdateMeterGroup", escape(group), escape(config));
    }

    public static SimpleBang enableMeasure(String name, String config) {
        return createBang("EnableMeasure", escape(name), escape(config));
    }

    public static SimpleBang disableMeasure(String name, String config) {
        return createBang("DisableMeasure", escape(name), escape(config));
    }

    public static SimpleBang toggleMeasure(String name, String config) {
        return createBang("ToggleMeasure", escape(name), escape(config));
    }

    public static SimpleBang pauseMeasure(String name, String config) {
        return createBang("PauseMeasure", escape(name), escape(config));
    }

    public static SimpleBang unpauseMeasure(String name, String config) {
        return createBang("UnpauseMeasure", escape(name), escape(config));
    }

    public static SimpleBang togglePauseMeasure(String name, String config) {
        return createBang("TogglePauseMeasure", escape(name), escape(config));
    }

    public static SimpleBang updateMeasure(String name, String config) {
        return createBang("UpdateMeasure", escape(name), escape(config));
    }

    public static SimpleBang commandMeasure(String name, String args, String config) {
        return createBang("CommandMeasure", escape(name), escape(args), escape(config));
    }

    public static SimpleBang enableMeasureGroup(String group, String config) {
        return createBang("EnableMeasureGroup", escape(group), escape(config));
    }

    public static SimpleBang disableMeasureGroup(String group, String config) {
        return createBang("DisableMeasureGroup", escape(group), escape(config));
    }

    public static SimpleBang toggleMeasureGroup(String group, String config) {
        return createBang("ToggleMeasureGroup", escape(group), escape(config));
    }

    public static SimpleBang pauseMeasureGroup(String group, String config) {
        return createBang("PauseMeasureGroup", escape(group), escape(config));
    }

    public static SimpleBang unpauseMeasureGroup(String group, String config) {
        return createBang("UnpauseMeasureGroup", escape(group), escape(config));
    }

    public static SimpleBang togglePauseMeasureGroup(String group, String config) {
        return createBang("TogglePauseMeasureGroup", escape(group), escape(config));
    }

    public static SimpleBang updateMeasureGroup(String group, String config) {
        return createBang("UpdateMeasureGroup", escape(group), escape(config));
    }

    public enum WallpaperPosition {
        CENTER("Center"),
        TILE("Tile"),
        STRETCH("Stretch"),
        FIT("Fit"),
        FILL("Fill");

        private final String value;

        WallpaperPosition(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum AboutTab {
        LOG("Log"),
        SKINS("Skins"),
        PLUGINS("Plugins"),
        VERSION("Version");

        private final String value;

        AboutTab(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ManageTab {
        SKINS("Skins"),
        LAYOUTS("Layouts"),
        SETTINGS("Settings");

        private final String value;

        ManageTab(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ErrorType {
        NOTICE("Notice"),
        ERROR("Error"),
        WARNING("Warning"),
        DEBUG("Debug");

        private final String value;

        ErrorType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum ZPosition {
        ON_DESKTOP("-2"),
        BOTTOM("-1"),
        NORMAL("0"),
        ON_TOP("1"),
        ALWAYS_ON_TOP("2");

        private final String value;

        ZPosition(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum SwitchOptions {
        TOGGLE("-1"),
        DISABLE("0"),
        ENABLE("1");

        private final String value;

        SwitchOptions(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
