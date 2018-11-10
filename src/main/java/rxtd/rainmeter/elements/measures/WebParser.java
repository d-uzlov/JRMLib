package rxtd.rainmeter.elements.measures;

import rxtd.rainmeter.actions.Action;
import rxtd.rainmeter.actions.ActionChain;
import rxtd.rainmeter.actions.BangUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WebParser extends MeasureBase<WebParser> {
    // TODO move headers from list to map
    private List<Header> headers = null;
    private ActionChain finishAction = null;
    private ActionChain onConnectErrorAction = null;
    private ActionChain onRegExpErrorAction = null;
    private ActionChain onDownloadErrorAction = null;

    public WebParser(String name) {
        super(name, "WebParser");
    }

    public WebParser() {
        this(null);
    }

    @Override
    protected WebParser getThis() {
        return this;
    }

    public WebParser createChild(String name) {
        return new WebParser(name).setURL("[" + this.getName() + "]");
    }

    public WebParser createChild() {
        return this.createChild(null);
    }

    public WebParser setURL(String url) {
        this.manageParameter("URL", url);
        return getThis();
    }

    public WebParser setRegExp(String regexp) {
        this.manageParameter("RegExp", regexp);
        return getThis();
    }

    public WebParser setStringIndex(Integer value) {
        this.manageParameter("StringIndex", value);
        return getThis();
    }

    public WebParser setStringIndex2(Integer value) {
        this.manageParameter("StringIndex2", value);
        return getThis();
    }

    public WebParser setUpdateRate(Integer value) {
        this.manageParameter("UpdateRate", value);
        return getThis();
    }

    public WebParser setDecodeCharacterReference(DecodeCharacterReference value) {
        this.manageParameter("DecodeCharacterReference", value);
        return getThis();
    }

    public WebParser setDebug(Boolean value) {
        this.manageParameter("Debug", value);
        return getThis();
    }

    public WebParser setDebug2File(String name) {
        this.manageParameter("Debug2File", name);
        return getThis();
    }

    public WebParser setDownload(Boolean value) {
        this.manageParameter("Download", value);
        return getThis();
    }

    public WebParser setDownloadFile(String path) {
        this.manageParameter("DownloadFile", path);
        return getThis();
    }

    public WebParser setErrorString(String value) {
        this.manageParameter("ErrorString", value);
        return getThis();
    }

    public WebParser setLogSubstringErrors(Boolean value) {
        this.manageParameter("LogSubstringErrors", value);
        return getThis();
    }

    public WebParser setForceReload(Boolean value) {
        this.manageParameter("ForceReload", value);
        return getThis();
    }

    public WebParser setCodePage(String value) {
        this.manageParameter("CodePage", value);
        return getThis();
    }

    public WebParser setProxyServer(String value) {
        this.manageParameter("ProxyServer", value);
        return getThis();
    }

    public WebParser setUserAgent(String value) {
        this.manageParameter("Debug", value);
        return getThis();
    }

    public WebParser setHeaders(Header... headers) {
        return this.setHeaders(Arrays.asList(headers));
    }

    public WebParser setHeaders(Collection<Header> headers) {
        this.headers.clear();
        this.headers.addAll(headers);
        this.generateHeaders();
        return getThis();
    }

    private void generateHeaders() {
        int index = 1;
        for (var h : this.headers) {
            if (h == null || h.name == null || h.value == null) {
                continue;
            }
            if (h.name.contains(":")) {
                throw new IllegalArgumentException();
            }
            this.manageParameter("Header" + this.createSuffix(index), h.name + ": " + h.value);
            index++;
        }
    }

    public WebParser addHeader(Header header) {
        if (this.headers == null) {
            this.headers = new ArrayList<>();
        }
        this.headers.add(header);
        this.generateHeaders();
        return getThis();
    }

    public WebParser setFinishAction(Action action) {
        this.finishAction = new ActionChain(action);
        this.manageParameter("FinishAction", this.finishAction);
        return getThis();
    }

    public WebParser addFinishAction(Action action) {
        if (this.finishAction == null) {
            this.finishAction = new ActionChain();
        }
        this.finishAction.append(action);
        this.manageParameter("FinishAction", this.finishAction);
        return getThis();
    }

    public WebParser setOnConnectErrorAction(Action action) {
        this.onConnectErrorAction = new ActionChain(action);
        this.manageParameter("OnConnectErrorAction", this.onConnectErrorAction);
        return getThis();
    }

    public WebParser addOnConnectErrorAction(Action action) {
        if (this.onConnectErrorAction == null) {
            this.onConnectErrorAction = new ActionChain();
        }
        this.onConnectErrorAction.append(action);
        this.manageParameter("OnConnectErrorAction", this.onConnectErrorAction);
        return getThis();
    }

    public WebParser setOnRegExpErrorAction(Action action) {
        this.onRegExpErrorAction = new ActionChain(action);
        this.manageParameter("OnRegExpErrorAction", this.onRegExpErrorAction);
        return getThis();
    }

    public WebParser addOnRegExpErrorAction(Action action) {
        if (this.onRegExpErrorAction == null) {
            this.onRegExpErrorAction = new ActionChain();
        }
        this.onRegExpErrorAction.append(action);
        this.manageParameter("OnRegExpErrorAction", this.onRegExpErrorAction);
        return getThis();
    }

    public WebParser setOnDownloadErrorAction(Action action) {
        this.onDownloadErrorAction = new ActionChain(action);
        this.manageParameter("OnDownloadErrorAction", this.onDownloadErrorAction);
        return getThis();
    }

    public WebParser addOnDownloadErrorAction(Action action) {
        if (this.onDownloadErrorAction == null) {
            this.onDownloadErrorAction = new ActionChain();
        }
        this.onDownloadErrorAction.append(action);
        this.manageParameter("OnDownloadErrorAction", this.onDownloadErrorAction);
        return getThis();
    }

    public Action bangUpdate() {
        return super.bangCommand("Update");
    }

    public Action bangReset() {
        return super.bangCommand("Reset");
    }

    public enum DecodeCharacterReference {
        DO_NOTHING("0"),
        NUMERIC_AND_ENTITY("1"),
        NUMERIC_ONLY("2"),
        ENTITY_ONLY("3");

        private final String value;

        DecodeCharacterReference(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public enum DebugState {
        DO_NOTHING("0"),
        LOG("1"),
        SAVE_PAGE("2");

        private final String value;

        DebugState(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public static class Header {
        public final String name;
        public final String value;

        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
