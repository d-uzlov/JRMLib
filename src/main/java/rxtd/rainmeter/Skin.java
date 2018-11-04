package rxtd.rainmeter;

import rxtd.rainmeter.elements.Element;
import rxtd.rainmeter.elements.MetadataSection;
import rxtd.rainmeter.elements.RainmeterSection;
import rxtd.rainmeter.elements.VariablesSection;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.variables.Variable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Skin {
    private final TreeMap<Integer, List<Element>> elementQueues = new TreeMap<>();
    private final VariablesSection variables = new VariablesSection();
    private final NamePrefixProvider namePrefixProvider;

    private String name;
    private MetadataSection metadata;
    private RainmeterSection rainmeterSection;

    public Skin(String name, NamePrefixProvider namePrefixProvider) {
        this.name = name;
        this.namePrefixProvider = namePrefixProvider;
    }

    /**
     * Creates skin and sets new {@link rxtd.rainmeter.NamePrefixProvider} for it.
     */
    public static Skin create(String name) {
        var provider = SkinUtils.nextNamePrefixProvider();
        return new Skin(name, provider);
    }

    public NamePrefixProvider getNamePrefixProvider() {
        return this.namePrefixProvider;
    }

    public Set<Resource> getResources() {
        Set<Resource> set = new HashSet<>();
        for (var queue : this.elementQueues.entrySet()) {
            for (Element<?> element : queue.getValue()) {
                Collection<Resource> resources = element.getResources();
                if (resources != null) {
                    set.addAll(resources);
                }
            }
        }
        return set;
    }

    public void add(Variable variable) {
        this.variables.addVariable(variable);
    }

    public VariablesSection getVariables() {
        return this.variables;
    }

    public MetadataSection getMetadata() {
        return this.metadata;
    }

    public void setMetadata(MetadataSection metadata) {
        this.metadata = metadata;
    }

    public RainmeterSection getRainmeterSection() {
        return this.rainmeterSection;
    }

    public void setRainmeterSection(RainmeterSection rainmeterSection) {
        this.rainmeterSection = rainmeterSection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void addElement(Integer queue, Element element) {
        List<Element> q = this.elementQueues.computeIfAbsent(queue, integer -> new ArrayList<>());
        q.add(element);
    }

    public void add(Element element) {
        this.add(Integer.MAX_VALUE, element, true);
    }

    public void add(Integer queue, Element element) {
        this.add(queue, element, true);
    }

    public void add(Integer queue, Element element, boolean checked) {
        if (!checked) {
            this.addElement(queue, element);
            return;
        }

        String name = element.getName();
        if (name == null) {
            throw new IllegalArgumentException("section name == null");
        }
        if (name.equalsIgnoreCase("metadata")) {
            throw new IllegalArgumentException("section name == metadata");
        }
        if (name.equalsIgnoreCase("rainmeter")) {
            throw new IllegalArgumentException("section name == rainmeter");
        }
        for (Map.Entry<Integer, List<Element>> q : this.elementQueues.entrySet()) {
            for (Element e : q.getValue()) {
                if (name.equalsIgnoreCase(e.getName())) {
                    throw new IllegalArgumentException("name " + name + " used twice");
                }
            }
        }
        this.addElement(queue, element);
    }

    public void write(PrintWriter writer) {
        if (this.metadata != null) {
            this.metadata.write(writer);
            writer.println();
        }
        if (this.rainmeterSection != null) {
            this.rainmeterSection.write(writer);
            writer.println();
        }
        if (this.variables.count() > 0) {
            this.variables.write(writer);
            writer.println();
        }
        for (var queue : this.elementQueues.entrySet()) {
            for (Element element : queue.getValue()) {
                element.prepare();
                element.write(writer);
                writer.println();
            }
        }
        writer.close();
    }
}
