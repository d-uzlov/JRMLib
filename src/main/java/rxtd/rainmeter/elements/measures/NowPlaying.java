package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.formulas.Formula;

public class NowPlaying extends MeasureBase<NowPlaying> {
    public NowPlaying(String name) {
        super(name, "NowPlaying");
    }

    public NowPlaying() {
        this(null);
    }

    @Override
    protected NowPlaying getThis() {
        return this;
    }

    /**
     * Only used for specifying actual name.<br/>
     * For child measures (e.g. {@code PlayerName=[MeasurePlayer]}) see {@link #createChild}
     */
    public NowPlaying setPlayerName(String playerName) {
        this.manageParameter("PlayerName", playerName);
        return this.getThis();
    }

    /**
     * Only used for specifying actual name.<br/>
     * For child measures (e.g. {@code PlayerName=[MeasurePlayer]}) see {@link #createChild}
     */
    public NowPlaying setPlayerName(KnownPlayer player) {
        this.manageParameter("PlayerName", player);
        return this.getThis();
    }

    public NowPlaying setType(Type type) {
        this.manageParameter("Type", type);
        return this.getThis();
    }

    public NowPlaying setPlayerPath(String path) {
        this.manageParameter("PlayerPath", path);
        return this.getThis();
    }

    public NowPlaying setTrackChangeAction(Action action) {
        this.manageParameter("TrackChangeAction", action);
        return this.getThis();
    }

    public NowPlaying setDisableLeadingZeroes(Boolean value) {
        this.manageParameter("DisableLeadingZeroes", value);
        return this.getThis();
    }

    public NowPlaying createChild(String name) {
        NowPlaying child = new NowPlaying(name);
        child.setPlayerName(new Formula(this).toString());
        return child;
    }

    public NowPlaying createChild() {
        return this.createChild(null);
    }

    public Action bangCommand(ZeroArgumentBang bang) {
        return super.bangCommand(bang.toString());
    }

    public Action bangCommand(OneArgumentBang bang, int arg) {
        return super.bangCommand(bang.toString() + " " + arg);
    }

    public enum ZeroArgumentBang {
        PAUSE("Pause"),
        PLAY("Play"),
        PLAY_PAUSE("PlayPause"),
        STOP("Stop"),
        NEXT("Next"),
        PREVIOUS("Previous"),
        OPEN_PLAYER("OpenPlayer"),
        CLOSE_PLAYER("ClosePlayer"),
        TOGGLE_PLAYER("TogglePlayer");

        private final String value;

        ZeroArgumentBang(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum OneArgumentBang {
        SET_POSITION("SetPosition"),
        SET_POSITION_RELATIVE("SetPositionRelative"),
        SET_RATING("SetRating"),
        SET_SHUFFLE("SetShuffle"),
        SET_REPEAT("SetRepeat"),
        SET_VOLUME("SetVolume");

        private final String value;

        OneArgumentBang(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum Type {
        ARTIST("Artist"),
        ALBUM("Album"),
        TITLE("Title"),
        NUMBER("Number"),
        YEAR("Year"),
        GENRE("Genre"),
        COVER("Cover"),
        FILE("File"),
        DURATION("Duration"),
        POSITION("Position"),
        PROGRESS("Progress"),
        RATING("Rating"),
        REPEAT("Repeat"),
        SHUFFLE("Shuffle"),
        STATE("State"),
        STATUS("Status"),
        VOLUME("Volume");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum KnownPlayer {
        AIMP("AIMP"),
        foobar2000("CAD"),
        iTunes("iTunes"),
        J_River_Media_Center("CAD"),
        Media_Jukebox("CAD"),
        MediaMonkey("MediaMonkey"),
        MusicBee("CAD"),
        Winamp("Winamp"),
        WMP("WMP"),
        Spotify("Spotify"),
        Last_fm_Client("WLM"),
        TTPlayer("WLM"),
        OpenPandora("WLM"),
        Zune("WLM");

        private final String value;

        KnownPlayer(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
