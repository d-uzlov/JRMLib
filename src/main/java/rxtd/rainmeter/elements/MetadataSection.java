package rxtd.rainmeter.elements;

/**
 * The MetadataSection of a skin describes the skin. The information is presented in the Manage window.
 *
 * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/">Rainmeter documentation</a>
 */
public class MetadataSection extends ElementBase<MetadataSection> {

    public MetadataSection() {
        super("Metadata");
    }

    /**
     * The name of the skin.
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/#Name">Rainmeter documentation</a>
     */
    public MetadataSection setSkinName(String name) {
        this.manageParameter("Name", name);
        return this;
    }

    /**
     * The author of the skin.
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/#Author">Rainmeter documentation</a>
     */
    public MetadataSection setAuthor(String author) {
        this.manageParameter("Author", author);
        return this;
    }

    /**
     * A description of the skin, setup and usage instructions, credits, or other documentation elements.<br/>
     * Use | for line breaks.
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/#Information">Rainmeter documentation</a>
     */
    public MetadataSection setInformation(String information) {
        this.manageParameter("Information", information);
        return this;
    }

    /**
     * The version of the skin.
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/#Version">Rainmeter documentation</a>
     */
    public MetadataSection setVersion(int major, int minor) {
        this.manageParameter("Version", major + "." + minor);
        return this;
    }

    /**
     * The version of the skin.
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/#Version">Rainmeter documentation</a>
     */
    public MetadataSection setVersion(String version) {
        this.manageParameter("Version", version);
        return this;
    }

    /**
     * The name of a standard license or explicit permissions and conditions for ports, mods and derivative works.
     *
     * @see <a href="https://docs.rainmeter.net/manual/skins/metadata-section/#License">Rainmeter documentation</a>
     */
    public MetadataSection setLicense(String license) {
        this.manageParameter("License", license);
        return this;
    }

    @Override
    protected MetadataSection getThis() {
        return this;
    }
}
