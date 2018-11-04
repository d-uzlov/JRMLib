package rxtd.rainmeter.elements.measures.plugins;

public class SysInfo extends PluginBase<SysInfo> {
    private final static PluginResource PLUGIN = new InternalPluginResource("SysInfo");

    public SysInfo(String name) {
        super(name, PLUGIN);
    }

    public SysInfo() {
        this(null);
    }

    @Override
    protected SysInfo getThis() {
        return this;
    }

    public SysInfo setSysInfoType(InfoType value) {
        this.manageParameter("SysInfoType", value);
        return getThis();
    }

    public SysInfo setSysInfoData(Integer value) {
        this.manageParameter("SysInfoData", value);
        return getThis();
    }

    public SysInfo setSysInfoData(String value) {
        this.manageParameter("SysInfoData", value);
        return getThis();
    }

    public enum InfoType {
        COMPUTER_NAME,
        USER_NAME,
        USER_LOGONTIME,
        OS_VERSION,
        OS_BITS,
        PAGESIZE,
        IDLE_TIME,

        HOST_NAME,
        DOMAIN_NAME,
        DOMAINWORKGROUP,
        DNS_SERVER,
        ADAPTER_DESCRIPTION,
        ADAPTER_TYPE,
        NET_MASK,
        IP_ADDRESS,
        GATEWAY_ADDRESS,
        LAN_CONNECTIVITY,
        INTERNET_CONNECTIVITY,

        NUM_MONITORS,
        SCREEN_SIZE,
        SCREEN_WIDTH,
        SCREEN_HEIGHT,
        VIRTUAL_SCREEN_TOP,
        VIRTUAL_SCREEN_LEFT,
        VIRTUAL_SCREEN_WIDTH,
        VIRTUAL_SCREEN_HEIGHT,
        WORK_AREA,
        WORK_AREA_TOP,
        WORK_AREA_LEFT,
        WORK_AREA_WIDTH,
        WORK_AREA_HEIGHT,

        TIMEZONE_ISDST,
        TIMEZONE_BIAS,
        TIMEZONE_STANDARD_NAME,
        TIMEZONE_STANDARD_BIAS,
        TIMEZONE_DAYLIGHT_NAME,
        TIMEZONE_DAYLIGHT_BIAS;

        private final String value;

        InfoType() {
            this.value = this.name();
        }

        InfoType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
