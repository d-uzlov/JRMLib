package rxtd.rainmeter.core.elements.measures;

import rxtd.rainmeter.core.actions.Action;
import rxtd.rainmeter.core.actions.ActionChain;

import java.util.LinkedHashMap;
import java.util.Map;

public class WebParser extends MeasureBase<WebParser> {
    private Map<String, String> headers = new LinkedHashMap<>();
    private ActionChain finishAction = null;
    private ActionChain onConnectErrorAction = null;
    private ActionChain onRegExpErrorAction = null;
    private ActionChain onDownloadErrorAction = null;

    public WebParser(String name) {
        super(name, "WebParser");
        this.addBeforeWriteListener(this::generateHeaders);
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
        return this.getThis();
    }

    public WebParser setRegExp(String regexp) {
        this.manageParameter("RegExp", regexp);
        return this.getThis();
    }

    public WebParser setStringIndex(Integer value) {
        this.manageParameter("StringIndex", value);
        return this.getThis();
    }

    public WebParser setStringIndex2(Integer value) {
        this.manageParameter("StringIndex2", value);
        return this.getThis();
    }

    public WebParser setUpdateRate(Integer value) {
        this.manageParameter("UpdateRate", value);
        return this.getThis();
    }

    public WebParser setDecodeCharacterReference(DecodeCharacterReference value) {
        this.manageParameter("DecodeCharacterReference", value);
        return this.getThis();
    }

    public WebParser setDebug(Boolean value) {
        this.manageParameter("Debug", value);
        return this.getThis();
    }

    public WebParser setDebug2File(String name) {
        this.manageParameter("Debug2File", name);
        return this.getThis();
    }

    public WebParser setDownload(Boolean value) {
        this.manageParameter("Download", value);
        return this.getThis();
    }

    public WebParser setDownloadFile(String path) {
        this.manageParameter("DownloadFile", path);
        return this.getThis();
    }

    public WebParser setErrorString(String value) {
        this.manageParameter("ErrorString", value);
        return this.getThis();
    }

    public WebParser setLogSubstringErrors(Boolean value) {
        this.manageParameter("LogSubstringErrors", value);
        return this.getThis();
    }

    public WebParser setForceReload(Boolean value) {
        this.manageParameter("ForceReload", value);
        return this.getThis();
    }

    public WebParser setCodePage(String value) {
        this.manageParameter("CodePage", value);
        return this.getThis();
    }

    public WebParser setProxyServer(String value) {
        this.manageParameter("ProxyServer", value);
        return this.getThis();
    }

    public WebParser setUserAgent(String value) {
        this.manageParameter("Debug", value);
        return this.getThis();
    }

    public WebParser setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
        this.generateHeaders();
        return this.getThis();
    }

    private void generateHeaders() {
        int index = 1;
        for (var h : this.headers.entrySet()) {
            if (h == null || h.getKey() == null || h.getValue() == null) {
                continue;
            }
            if (h.getKey().contains(":")) {
                throw new IllegalArgumentException();
            }
            this.manageParameter("Header" + this.createSuffix(index), h.getKey()+ ": " + h.getValue());
            index++;
        }
    }

    public WebParser addHeader(String name, String value) {
        if (name.contains(":")) {
            throw new IllegalArgumentException();
        }
        this.headers.put(name, value);
        return this.getThis();
    }

    public WebParser setFinishAction(Action action) {
        this.finishAction = new ActionChain(action);
        this.manageParameter("FinishAction", this.finishAction);
        return this.getThis();
    }

    public WebParser addFinishAction(Action action) {
        if (this.finishAction == null) {
            this.finishAction = new ActionChain();
        }
        this.finishAction.append(action);
        this.manageParameter("FinishAction", this.finishAction);
        return this.getThis();
    }

    public WebParser setOnConnectErrorAction(Action action) {
        this.onConnectErrorAction = new ActionChain(action);
        this.manageParameter("OnConnectErrorAction", this.onConnectErrorAction);
        return this.getThis();
    }

    public WebParser addOnConnectErrorAction(Action action) {
        if (this.onConnectErrorAction == null) {
            this.onConnectErrorAction = new ActionChain();
        }
        this.onConnectErrorAction.append(action);
        this.manageParameter("OnConnectErrorAction", this.onConnectErrorAction);
        return this.getThis();
    }

    public WebParser setOnRegExpErrorAction(Action action) {
        this.onRegExpErrorAction = new ActionChain(action);
        this.manageParameter("OnRegExpErrorAction", this.onRegExpErrorAction);
        return this.getThis();
    }

    public WebParser addOnRegExpErrorAction(Action action) {
        if (this.onRegExpErrorAction == null) {
            this.onRegExpErrorAction = new ActionChain();
        }
        this.onRegExpErrorAction.append(action);
        this.manageParameter("OnRegExpErrorAction", this.onRegExpErrorAction);
        return this.getThis();
    }

    public WebParser setOnDownloadErrorAction(Action action) {
        this.onDownloadErrorAction = new ActionChain(action);
        this.manageParameter("OnDownloadErrorAction", this.onDownloadErrorAction);
        return this.getThis();
    }

    public WebParser addOnDownloadErrorAction(Action action) {
        if (this.onDownloadErrorAction == null) {
            this.onDownloadErrorAction = new ActionChain();
        }
        this.onDownloadErrorAction.append(action);
        this.manageParameter("OnDownloadErrorAction", this.onDownloadErrorAction);
        return this.getThis();
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
}
