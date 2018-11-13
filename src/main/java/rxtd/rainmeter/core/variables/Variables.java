package rxtd.rainmeter.core.variables;

import rxtd.rainmeter.core.NamePrefixProvider;
import rxtd.rainmeter.core.SkinUtils;

public class Variables {
    public static Variable create(String name, String initialValue) {
        NamePrefixProvider prefixProvider = SkinUtils.getNamePrefixProvider();
        String varName = prefixProvider.nextVariablesPrefix(name);
        return new VariableImpl(varName, initialValue);
    }

    public static Variable create(String name, double initialValue) {
        return create(name, SkinUtils.print(initialValue));
    }

    public static Variable create(String name, int initialValue) {
        return create(name, Integer.toString(initialValue));
    }

    public final static class Path {
        public final static Variable PROGRAM_DRIVE = new VariableImpl("PROGRAMDRIVE");
        public final static Variable PROGRAM = new VariableImpl("PROGRAMPATH");
        public final static Variable SETTINGS = new VariableImpl("SETTINGSPATH");
        public final static Variable SKINS = new VariableImpl("SKINSPATH");
        public final static Variable PLUGINS = new VariableImpl("PLUGINSPATH");
        public final static Variable ADDONS = new VariableImpl("ADDONSPATH");

        private Path() {
        }
    }

    public final static class Skin {
        public final static Variable RESOURCES_FOLDER = new VariableImpl("@");
        public final static Variable CURRENT_PATH = new VariableImpl("CURRENTPATH");
        public final static Variable CURRENT_FILE = new VariableImpl("CURRENTFILE");
        public final static Variable ROOT_CONFIG_PATH = new VariableImpl("ROOTCONFIGPATH");
        public final static Variable ROOT_CONFIG = new VariableImpl("ROOTCONFIG");
        public final static Variable CURRENT_CONFIG = new VariableImpl("CURRENTCONFIG");
        public final static Variable CURRENT_CONFIG_X = new VariableImpl("CURRENTCONFIGX");
        public final static Variable CURRENT_CONFIG_Y = new VariableImpl("CURRENTCONFIGY");
        public final static Variable CURRENT_CONFIG_WIDTH = new VariableImpl("CURRENTCONFIGWIDTH");
        public final static Variable CURRENT_CONFIG_HEIGHT = new VariableImpl("CURRENTCONFIGHEIGHT");

        private Skin() {
        }
    }

    public final static class Miscellaneous {
        public final static Variable CRLF = new VariableImpl("CRLF");
        public final static Variable CURRENT_SECTION = new VariableImpl("CURRENTSECTION");
        public final static Variable CONFIG_EDITOR = new VariableImpl("CONFIGEDITOR");

        private Miscellaneous() {
        }
    }

    public final static class Monitor {
        public final static Variable WORK_AREA_X = new VariableImpl("WORKAREAX");
        public final static Variable WORK_AREA_Y = new VariableImpl("WORKAREAY");
        public final static Variable WORK_AREA_WIDTH = new VariableImpl("WORKAREAWIDTH");
        public final static Variable WORK_AREA_HEIGHT = new VariableImpl("WORKAREAHEIGHT");

        public final static Variable SCREEN_AREA_X = new VariableImpl("SCREENAREAX");
        public final static Variable SCREEN_AREA_Y = new VariableImpl("SCREENAREAY");
        public final static Variable SCREEN_AREA_WIDTH = new VariableImpl("SCREENAREAWIDTH");
        public final static Variable SCREEN_AREA_HEIGHT = new VariableImpl("SCREENAREAHEIGHT");

        public final static Variable P_WORK_AREA_X = new VariableImpl("PWORKAREAX");
        public final static Variable P_WORK_AREA_Y = new VariableImpl("PWORKAREAY");
        public final static Variable P_WORK_AREA_WIDTH = new VariableImpl("PWORKAREAWIDTH");
        public final static Variable P_WORK_AREA_HEIGHT = new VariableImpl("PWORKAREAHEIGHT");

        public final static Variable P_SCREEN_AREA_X = new VariableImpl("PSCREENAREAX");
        public final static Variable P_SCREEN_AREA_Y = new VariableImpl("PSCREENAREAY");
        public final static Variable P_SCREEN_AREA_WIDTH = new VariableImpl("PSCREENAREAWIDTH");
        public final static Variable P_SCREEN_AREA_HEIGHT = new VariableImpl("PSCREENAREAHEIGHT");

        public final static Variable V_SCREEN_AREAX = new VariableImpl("VSCREENAREAX");
        public final static Variable V_SCREEN_AREAY = new VariableImpl("VSCREENAREAY");
        public final static Variable V_SCREEN_AREAWIDTH = new VariableImpl("VSCREENAREAWIDTH");
        public final static Variable V_SCREEN_AREAHEIGHT = new VariableImpl("VSCREENAREAHEIGHT");

        private Monitor() {
        }
    }
}
