package rxtd.rainmeter.core.elements.measures.plugins.custom;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.core.SkinUtils;
import rxtd.rainmeter.core.elements.measures.plugins.PluginBase;
import rxtd.rainmeter.core.elements.measures.plugins.ExternalPluginResource;
import rxtd.rainmeter.core.elements.measures.plugins.PluginResource;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Better version of AudioLevel. Unlike mainstream version, supports waveform output.<br/>
 * Has never been released :( Also doesn't have open source code.<br/>
 * Covered version is 2.0.0.0<br/>
 * Supports {@link PluginBase#wrap(PluginResource) autowrap}. Autowrapping is set on by default.
 *
 * @see <a href="https://forum.rainmeter.net/viewtopic.php?f=18&t=18802&start=270#p117674">Post on Rainmeter forum</a>
 */
public class AudioLevel2 extends PluginBase<AudioLevel2> {
    private final static PluginResource LOCAL = ExternalPluginResource.fromJar("AudioLevel", "v2.0.0.0", true);
    private static Boolean autowrap = true;
    private final List<Child> children = new ArrayList<>();

    public AudioLevel2(String name) {
        super(name, virtualize(LOCAL));
        if ((autowrap != null ? autowrap : SkinUtils.getDefaultAutowrap())) {
            this.wrap(LOCAL);
        }
    }

    public static void setAutowrap(Boolean autowrap) {
        AudioLevel2.autowrap = autowrap;
    }

    @Override
    protected AudioLevel2 getThis() {
        return this;
    }

    @Override
    public AudioLevel2 wrap(@Nullable PluginResource plugin) {
        for (var c : this.children) {
            c.wrap(plugin);
        }
        return super.wrap(plugin);
    }

    public Child createChild(String name) {
        return this.new Child(name);
    }

    public AudioLevel2 setPort(Port port) {
        this.manageParameter("Port", port);
        return this.getThis();
    }

    public AudioLevel2 setID(String value) {
        this.manageParameter("ID", value);
        return this.getThis();
    }

    public AudioLevel2 setRMSAttack(Integer value) {
        this.manageParameter("RMSAttack", value);
        return this.getThis();
    }

    public AudioLevel2 setRMSDecay(Integer value) {
        this.manageParameter("RMSDecay", value);
        return this.getThis();
    }

    public AudioLevel2 setRMSGain(Double value) {
        this.manageParameter("RMSGain", value);
        return this.getThis();
    }

    public AudioLevel2 disableRMSFiltering() {
        this.setRMSAttack(0);
        this.setRMSDecay(0);
        this.setRMSGain(1.0);
        return this.getThis();
    }

    public AudioLevel2 disablePeakFiltering() {
        this.setPeakAttack(0);
        this.setPeakDecay(0);
        this.setPeakGain(1.0);
        return this.getThis();
    }

    public AudioLevel2 setPeakAttack(Integer value) {
        this.manageParameter("PeakAttack", value);
        return this.getThis();
    }

    public AudioLevel2 setPeakDecay(Integer value) {
        this.manageParameter("PeakDecay", value);
        return this.getThis();
    }

    public AudioLevel2 setPeakGain(Double value) {
        this.manageParameter("PeakGain", value);
        return this.getThis();
    }

    public AudioLevel2 setFFTSize(Integer value) {
        this.manageParameter("FFTSize", value);
        return this.getThis();
    }

    public AudioLevel2 setFFTOverlap(Integer value) {
        this.manageParameter("FFTOverlap", value);
        return this.getThis();
    }

    public AudioLevel2 setFFTAttack(Integer value) {
        this.manageParameter("FFTAttack", value);
        return this.getThis();
    }

    public AudioLevel2 setFFTDecay(Integer value) {
        this.manageParameter("FFTDecay", value);
        return this.getThis();
    }

    public AudioLevel2 setBands(Integer value) {
        this.manageParameter("Bands", value);
        return this.getThis();
    }

    public AudioLevel2 setFreqMin(Integer value) {
        this.manageParameter("FreqMin", value);
        return this.getThis();
    }

    public AudioLevel2 setFreqMax(Integer value) {
        this.manageParameter("FreqMax", value);
        return this.getThis();
    }

    public AudioLevel2 setSensitivity(Double value) {
        this.manageParameter("Sensitivity", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformWidth(Integer value) {
        this.manageParameter("WaveformWidth", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformHeight(Integer value) {
        this.manageParameter("WaveformHeight", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformZoom(Integer value) {
        this.manageParameter("WaveformZoom", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformScroll(Boolean value) {
        this.manageParameter("WaveformScroll", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformColorStart(Color value) {
        this.manageParameter("WaveformColorStart", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformColorEnd(Color value) {
        this.manageParameter("WaveformColorEnd", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformFadeLength(Integer value) {
        this.manageParameter("WaveformFadeLength", value);
        return this.getThis();
    }

    public AudioLevel2 setWaveformColorCursor(Color value) {
        this.manageParameter("WaveformColorCursor", value);
        return this.getThis();
    }

    public enum Port {
        OUTPUT("Output"),
        INPUT("Input");

        private final String value;

        Port(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Type {
        RMS("RMS"),
        PEAK("Peak"),
        FFT("FFT"),
        FFT_FREQ("FFTFreq"),
        BAND("Band"),
        BAND_FREQ("BandFreq"),
        FORMAT("Format"),
        DEVICE_STATUS("DeviceStatus"),
        DEVICE_NAME("DeviceName"),
        DEVICE_ID("DeviceID"),
        DEVICE_LIST("DeviceList"),
        WAVEFORM("Waveform");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Channel {
        FRONT_LEFT("LF"),
        FRONT_RIGHT("RF"),
        FRONT_AVERAGE("AVG"),
        CENTER("C"),
        SUBWOOFER("LFE"),
        BACK_LEFT("BL"),
        BACK_RIGHT("BR"),
        SURROUND_LEFT("SL"),
        SURROUND_RIGHT("SR");

        private final String value;

        Channel(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public class Child extends PluginBase<Child> {

        private Child(String name) {
            super(name, virtualize(LOCAL));
            this.setParent(AudioLevel2.this.getName());
            AudioLevel2.this.children.add(this);
            this.wrap(AudioLevel2.this.getPluginProvider().get());
        }

        private void setParent(String measureName) {
            this.manageParameter("Parent", measureName);
        }

        public Child setChannel(Channel value) {
            if (value == Channel.FRONT_AVERAGE && Type.WAVEFORM.toString().equals(this.getParams().get("Type"))) {
                throw new RuntimeException("unsupported due to bug in plugin");
            }
            this.manageParameter("Channel", value);
            return this.getThis();
        }

        public Child setType(Type value) {
            if (value == Type.WAVEFORM && Channel.FRONT_AVERAGE.toString().equals(this.getParams().get("Channel"))) {
                throw new RuntimeException("unsupported due to bug in plugin");
            }
            this.manageParameter("Type", value);
            return this.getThis();
        }

        public Child setFFTIdx(Integer value) {
            this.manageParameter("FFTIdx", value);
            return this.getThis();
        }

        public Child setBandIdx(Integer value) {
            this.manageParameter("BandIdx", value);
            return this.getThis();
        }

        public Child setWaveformFile(String value) {
            this.manageParameter("WaveformFile", value);
            return this.getThis();
        }

        @Override
        protected Child getThis() {
            return this;
        }
    }
}
