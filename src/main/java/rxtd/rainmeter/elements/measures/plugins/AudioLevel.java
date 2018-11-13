package rxtd.rainmeter.elements.measures.plugins;

import org.jetbrains.annotations.Nullable;
import rxtd.rainmeter.elements.measures.plugins.custom.PerfMonPDH;

import java.util.ArrayList;
import java.util.List;

public class AudioLevel extends PluginBase<AudioLevel> {
    private final static PluginResource PLUGIN = new InternalPluginResource("AudioLevel");
    private final List<Child> children = new ArrayList<>();

    public AudioLevel(String name) {
        super(name, PLUGIN);
    }

    @Override
    protected AudioLevel getThis() {
        return this;
    }

    @Override
    public AudioLevel wrap(@Nullable PluginResource plugin) {
        for (var c : this.children) {
            c.wrap(plugin);
        }
        return super.wrap(plugin);
    }

    public Child createChild(String name) {
        return this.new Child(name);
    }

    public AudioLevel setPort(Port port) {
        this.manageParameter("Port", port);
        return this.getThis();
    }

    public AudioLevel setID(String value) {
        this.manageParameter("ID", value);
        return this.getThis();
    }

    public AudioLevel setRMSAttack(Integer value) {
        this.manageParameter("RMSAttack", value);
        return this.getThis();
    }

    public AudioLevel setRMSDecay(Integer value) {
        this.manageParameter("RMSDecay", value);
        return this.getThis();
    }

    public AudioLevel setRMSGain(Double value) {
        this.manageParameter("RMSGain", value);
        return this.getThis();
    }

    public AudioLevel disableRMSFiltering() {
        this.setRMSAttack(0);
        this.setRMSDecay(0);
        this.setRMSGain(1.0);
        return this.getThis();
    }

    public AudioLevel disablePeakFiltering() {
        this.setPeakAttack(0);
        this.setPeakDecay(0);
        this.setPeakGain(1.0);
        return this.getThis();
    }

    public AudioLevel setPeakAttack(Integer value) {
        this.manageParameter("PeakAttack", value);
        return this.getThis();
    }

    public AudioLevel setPeakDecay(Integer value) {
        this.manageParameter("PeakDecay", value);
        return this.getThis();
    }

    public AudioLevel setPeakGain(Double value) {
        this.manageParameter("PeakGain", value);
        return this.getThis();
    }

    public AudioLevel setFFTSize(Integer value) {
        this.manageParameter("FFTSize", value);
        return this.getThis();
    }

    public AudioLevel setFFTOverlap(Integer value) {
        this.manageParameter("FFTOverlap", value);
        return this.getThis();
    }

    public AudioLevel setFFTAttack(Integer value) {
        this.manageParameter("FFTAttack", value);
        return this.getThis();
    }

    public AudioLevel setFFTDecay(Integer value) {
        this.manageParameter("FFTDecay", value);
        return this.getThis();
    }

    public AudioLevel setBands(Integer value) {
        this.manageParameter("Bands", value);
        return this.getThis();
    }

    public AudioLevel setFreqMin(Integer value) {
        this.manageParameter("FreqMin", value);
        return this.getThis();
    }

    public AudioLevel setFreqMax(Integer value) {
        this.manageParameter("FreqMax", value);
        return this.getThis();
    }

    public AudioLevel setSensitivity(Double value) {
        this.manageParameter("Sensitivity", value);
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
        DEVICE_LIST("DeviceList");

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
            super(name, PLUGIN);
            this.setParent(AudioLevel.this.getName());
            AudioLevel.this.children.add(this);
            this.wrap(AudioLevel.this.getPluginProvider().get());
        }

        private void setParent(String measureName) {
            this.manageParameter("Parent", measureName);
        }

        public Child setChannel(Channel value) {
            this.manageParameter("Channel", value);
            return this.getThis();
        }

        public Child setType(Type value) {
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

        @Override
        protected Child getThis() {
            return this;
        }
    }
}
