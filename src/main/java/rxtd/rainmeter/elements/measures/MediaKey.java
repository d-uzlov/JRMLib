package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.BangUtils;

public class MediaKey extends MeasureBase<MediaKey> {
    public MediaKey(String name) {
        super(name, "MediaKey");
    }

    public MediaKey() {
        this(null);
    }

    @Override
    protected MediaKey getThis() {
        return this;
    }

    public Action createCommand(Command command) {
        return BangUtils.commandMeasure(this.getName(), command.toString(), null);
    }

    public enum Command {
        NEXT_TRACK("NextTrack"),
        PREV_TRACK("PrevTrack"),
        STOP("Stop"),
        PLAY_PAUSE("PlayPause"),
        VOLUME_MUTE("VolumeMute"),
        VOLUME_DOWN("VolumeDown"),
        VOLUME_UP("VolumeUp");

        private final String value;

        Command(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
