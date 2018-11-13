package rxtd.rainmeter.elements.measures.plugins.custom;

import rxtd.rainmeter.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.elements.measures.plugins.PluginResource;
import rxtd.rainmeter.elements.measures.plugins.VirtualPluginResource;

/**
 * A plug-in that allows you to put MSI Afterburner hardware monitoring information into the Rainmeter.<br/>
 * Covered version is 2.0<br/>
 * Old dlls (versions 2.0 and prior) does not support wrapping in any form. At least until LocalPluginLoader is updated to support old plugin API.
 *
 * @see <a href="https://forums.guru3d.com/threads/rainmeter-plugin-for-msi-afterburner.319558/page-2#post-3806691">Documentation in original post on forum</a>
 */
public class MSIAfterburner extends PluginBase<MSIAfterburner> {
    private final static PluginResource PLUGIN = new VirtualPluginResource("MSIAfterburner", null);

    public MSIAfterburner(String name) {
        super(name, PLUGIN);
    }

    @Override
    protected MSIAfterburner getThis() {
        return this;
    }

    public MSIAfterburner setDataSource(String value) {
        this.manageParameter("DataSource", value);
        return this.getThis();
    }

    public MSIAfterburner setGpuNumber(Integer value) {
        this.manageParameter("GPU", value);
        return this.getThis();
    }

    public MSIAfterburner setSource(Source source) {
        this.manageParameter("SourceId", source.getId());
        return this.getThis();
    }

    public MSIAfterburner setSource(Integer value) {
        this.manageParameter("SourceId", value);
        return this.getThis();
    }

    public MSIAfterburner setGpuDescriptor(String value) {
        this.manageParameter("GpuDescriptor", value);
        return this.getThis();
    }

    /**
     * List of the available sources can be found here: c:\Program Files (x86)\MSI Afterburner\SDK\Include\MAHMSharedMemory.h
     */
    public static final class Source {
        public final static Source GPU_TEMPERATURE = new Source(0x00000000);
        public final static Source PCB_TEMPERATURE = new Source(0x00000001);
        public final static Source MEM_TEMPERATURE = new Source(0x00000002);
        public final static Source VRM_TEMPERATURE = new Source(0x00000003);
        public final static Source FAN_SPEED = new Source(0x00000010);
        public final static Source FAN_TACHOMETER = new Source(0x00000011);
        public final static Source CORE_CLOCK = new Source(0x00000020);
        public final static Source SHADER_CLOCK = new Source(0x00000021);
        public final static Source MEMORY_CLOCK = new Source(0x00000022);
        public final static Source GPU_USAGE = new Source(0x00000030);
        public final static Source MEMORY_USAGE = new Source(0x00000031);
        public final static Source FB_USAGE = new Source(0x00000032);
        public final static Source VID_USAGE = new Source(0x00000033);
        public final static Source BUS_USAGE = new Source(0x00000034);
        public final static Source GPU_VOLTAGE = new Source(0x00000040);
        public final static Source AUX_VOLTAGE = new Source(0x00000041);
        public final static Source MEMORY_VOLTAGE = new Source(0x00000042);
        public final static Source AUX2_VOLTAGE = new Source(0x00000043);
        public final static Source FRAMERATE = new Source(0x00000050);
        public final static Source FRAMETIME = new Source(0x00000051);
        public final static Source GPU_POWER = new Source(0x00000060);
        public final static Source GPU_TEMP_LIMIT = new Source(0x00000070);
        public final static Source GPU_POWER_LIMIT = new Source(0x00000071);
        public final static Source GPU_VOLTAGE_LIMIT = new Source(0x00000072);
        public final static Source GPU_UTIL_LIMIT = new Source(0x00000074);
        public final static Source GPU_SLI_SYNC_LIMI = new Source(0x00000075);
        public final static Source CPU_TEMPERATURE = new Source(0x00000080);
        public final static Source CPU_USAGE = new Source(0x00000090);
        public final static Source RAM_USAGE = new Source(0x00000091);
        public final static Source PAGEFILE_USAGE = new Source(0x00000092);
        public final static Source CPU_CLOCK = new Source(0x000000A0);
        public final static Source GPU_TEMPERATURE2 = new Source(0x000000B0);
        public final static Source PCB_TEMPERATURE2 = new Source(0x000000B1);
        public final static Source MEM_TEMPERATURE2 = new Source(0x000000B2);
        public final static Source VRM_TEMPERATURE2 = new Source(0x000000B3);
        public final static Source GPU_TEMPERATURE3 = new Source(0x000000C0);
        public final static Source PCB_TEMPERATURE3 = new Source(0x000000C1);
        public final static Source MEM_TEMPERATURE3 = new Source(0x000000C2);
        public final static Source VRM_TEMPERATURE3 = new Source(0x000000C3);
        public final static Source GPU_TEMPERATURE4 = new Source(0x000000D0);
        public final static Source PCB_TEMPERATURE4 = new Source(0x000000D1);
        public final static Source MEM_TEMPERATURE4 = new Source(0x000000D2);
        public final static Source VRM_TEMPERATURE4 = new Source(0x000000D3);
        public final static Source GPU_TEMPERATURE5 = new Source(0x000000E0);
        public final static Source PCB_TEMPERATURE5 = new Source(0x000000E1);
        public final static Source MEM_TEMPERATURE5 = new Source(0x000000E2);
        public final static Source VRM_TEMPERATURE5 = new Source(0x000000E3);
        public final static Source PLUGIN_GPU = new Source(0x000000F0);
        public final static Source PLUGIN_CPU = new Source(0x000000F1);
        public final static Source PLUGIN_MOBO = new Source(0x000000F2);
        public final static Source PLUGIN_RAM = new Source(0x000000F3);
        public final static Source PLUGIN_HDD = new Source(0x000000F4);
        public final static Source PLUGIN_NET = new Source(0x000000F5);
        public final static Source PLUGIN_PSU = new Source(0x000000F6);
        public final static Source PLUGIN_UPS = new Source(0x000000F7);
        public final static Source PLUGIN_MISC = new Source(0x000000FF);

        private final int id;

        private Source(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    /**
     * List of available descriptors from here: https://forums.guru3d.com/threads/rainmeter-plugin-for-msi-afterburner.319558/page-2#post-3806691
     */
    public static final class GpuDescriptor {
        public final static String FAMILY = "Family";
        public final static String DISPLAY_NAME = "DisplayName";
        public final static String DRIVER = "Driver";
        public final static String BIOS = "BIOS";
        public final static String MEMORY = "Memory";
        public final static String GPU_OD = "GpuId";
        public final static String VENDOR_ID = "VendorId";
        public final static String DEVICE_ID = "DeviceId";
        public final static String SUB_SYSTEM = "SubSystem";
        public final static String REVISION = "Revision";
        public final static String BUS = "Bus";
        public final static String DEVICE_NUM = "DeviceNum";
        public final static String FUNCTION_NUM = "FunctionNum";
    }
}
