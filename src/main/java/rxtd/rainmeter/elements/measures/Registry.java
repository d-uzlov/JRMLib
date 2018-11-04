package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.formulas.Formula;

public class Registry extends MeasureBase<Registry> {

    public Registry(String name) {
        super(name, "Registry");
    }

    public Registry() {
        this(null);
    }

    @Override
    protected Registry getThis() {
        return this;
    }

    public Registry setRegHKey(String value) {
        this.manageParameter("RegHKey", value);
        return getThis();
    }

    public Registry setRegHKey(Formula value) {
        this.manageParameter("RegHKey", value);
        return getThis();
    }

    public Registry setRegKey(String value) {
        this.manageParameter("RegKey", value);
        return getThis();
    }

    public Registry setRegKey(Formula value) {
        this.manageParameter("RegKey", value);
        return getThis();
    }

    public Registry setRegValue(String value) {
        this.manageParameter("RegValue", value);
        return getThis();
    }

    public Registry setRegValue(Formula value) {
        this.manageParameter("RegValue", value);
        return getThis();
    }

    public static final class HKeys {
        public final static String CLASSES_ROOT = "HKEY_CLASSES_ROOT";
        public final static String CURRENT_USER = "HKEY_CURRENT_USER";
        public final static String LOCAL_MACHINE = "HKEY_LOCAL_MACHINE";
        public final static String USERS = "HKEY_USERS";
        public final static String CURRENT_CONFIG = "HKEY_CURRENT_CONFIG";
        public final static String PERFORMANCE_DATA = "HKEY_PERFORMANCE_DATA";

        private HKeys() {
        }
    }
}
