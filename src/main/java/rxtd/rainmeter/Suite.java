package rxtd.rainmeter;

import rxtd.Pair;
import rxtd.rainmeter.elements.MetadataSection;
import rxtd.rainmeter.resources.Resource;
import rxtd.rainmeter.resources.ResourceOptions;
import rxtd.rainmeter.variables.Variables;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class Suite {
    private final String name;
    private final Map<List<String>, Map<String, Pair<Skin, Boolean>>> configs = new HashMap<>();
    private final Map<List<String>, Map<String, Skin>> resourceIncludes = new HashMap<>();
    private boolean overrideResources = true;
    private boolean overrideSkins = true;
    private boolean fillMetadata = false;
    private String license = null;
    private String author = null;
    private String version = null;
    private String information = null;
    private boolean printUnmanagedResources = false;
    private boolean printManagedResources = false;
    private boolean deduplicateResources = true;
    private Map<Path, Resource> resourceFiles = new HashMap<>();

    public Suite(String name) {
        this.name = name;
    }

    public void setOverrideResources(boolean override) {
        this.overrideResources = override;
    }

    public void setOverrideSkins(boolean overrideSkins) {
        this.overrideSkins = overrideSkins;
    }

    public String addSkin(Skin skin, String... config) {
        if (config.length > 0 && "@Resources".equalsIgnoreCase(config[0])) {
            throw new IllegalArgumentException("config.length > 0 && \"@Resources\".equalsIgnoreCase(config[0])");
        }
        List<String> configName = Arrays.asList(config);
        var map = this.configs.computeIfAbsent(configName, k -> new HashMap<>());
        if (map.containsKey(skin.getName())) {
            throw new IllegalArgumentException("name used twice: \"" + skin.getName() + "\" in config: \"" + configName.toString() + "\"");
        }
        map.put(skin.getName(), new Pair<>(skin, false));
        return Variables.Skin.RESOURCES_FOLDER.getUsage() + "../" + String.join("/", config);
    }

    public String addInclude(Skin skin, boolean global, String... config) {
        List<String> configName = Arrays.asList(config);
        if (global) {
            var map = this.resourceIncludes.computeIfAbsent(configName, k -> new HashMap<>());
            if (map.containsKey(skin.getName())) {
                throw new IllegalArgumentException("name used twice: \"" + skin.getName() + "\" in config: \"" + configName.toString() + "\"");
            }
            map.put(skin.getName(), skin);
            return Variables.Skin.RESOURCES_FOLDER.getUsage() + String.join("/", config);
        } else {
            if (config.length > 0 && "@Resources".equalsIgnoreCase(config[0])) {
                throw new IllegalArgumentException("config.length > 0 && \"@Resources\".equalsIgnoreCase(config[0])");
            }
            var map = this.configs.computeIfAbsent(configName, k -> new HashMap<>());
            if (map.containsKey(skin.getName())) {
                throw new IllegalArgumentException("name used twice: \"" + skin.getName() + "\" in config: \"" + configName.toString() + "\"");
            }
            map.put(skin.getName(), new Pair<>(skin, true));
            return Variables.Skin.RESOURCES_FOLDER.getUsage() + "../" + String.join("/", config);
        }
    }

    public void setFillMetadata(boolean fillMetadata) {
        this.fillMetadata = fillMetadata;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setPrintUnmanagedResources(boolean printUnmanagedResources) {
        this.printUnmanagedResources = printUnmanagedResources;
    }

    public void setPrintManagedResources(boolean printManagedResources) {
        this.printManagedResources = printManagedResources;
    }

    public void setDeduplicateResources(boolean deduplicateResources) {
        this.deduplicateResources = deduplicateResources;
    }

    private void fillMetadata() {
        for (var config : this.configs.entrySet()) {
            for (var skin : config.getValue().entrySet()) {
                var p = skin.getValue();
                if (p.value) {
                    continue;
                }
                MetadataSection metadata = p.key.getMetadata();
                if (this.license != null) {
                    metadata.setLicense(this.license);
                }
                if (this.author != null) {
                    metadata.setAuthor(this.author);
                }
                if (this.version != null) {
                    metadata.setVersion(this.version);
                }
                if (this.information != null) {
                    metadata.setInformation(this.information);
                }
            }
        }
    }

    private <T> void write(Map<List<String>, Map<String, T>> map, Function<T, Pair<Skin, String>> extractor) throws IOException {
        Path resourcesPath = Paths.get(this.name, "@Resources");
        ResourceOptions resourceOptions = new ResourceOptions(this.overrideResources);

        for (var e : map.entrySet()) {
            Path configPath = Paths.get(this.name, e.getKey().toArray(new String[0]));
            Files.createDirectories(configPath);
            for (var se : e.getValue().entrySet()) {
                var extRet = extractor.apply(se.getValue());
                Skin skin = extRet.key;
                Path skinPath = configPath.resolve(se.getKey() + extRet.value);
                if (Files.exists(skinPath) && !this.overrideSkins) {
                    continue;
                }
                BufferedWriter bw = Files.newBufferedWriter(skinPath, SkinUtils.getStandardCharset());
                PrintWriter pr = new PrintWriter(bw);
                skin.write(pr);
                pr.close();

                var resources = skin.getResources();
                if (resources != null) {
                    for (var re : resources) {
                        if (re == null) {
                            continue;
                        }
                        var resPaths = re.getPaths(configPath, resourcesPath);

                        AtomicBoolean writeNeeded = new AtomicBoolean(true);
                        if (resPaths != null) {
                            for (var path : resPaths) {
                                this.resourceFiles.compute(path, (key, resource) -> {
                                    if (resource == null) {
                                        return re;
                                    }
                                    if (!resource.equals(re)) {
                                        throw new RuntimeException("resources intersect: " + resource + ", " + re);
                                    }
                                    writeNeeded.set(false);
                                    return resource;
                                });
                            }
                        }
                        if (!this.deduplicateResources || writeNeeded.get()) {
                            re.patch(configPath, resourcesPath, resourceOptions);
                        }
                    }
                }
            }
        }
    }

    public void write() throws IOException {
        if (this.fillMetadata) {
            this.fillMetadata();
        }
        this.write(this.configs, skinBooleanPair -> new Pair<>(skinBooleanPair.key, (skinBooleanPair.value ? ".inc" : ".ini")));
        this.write(this.resourceIncludes, skin -> new Pair<>(skin, ".inc"));

        if (this.printUnmanagedResources) {
            this.printResources(false, "unmanaged");
        }
        if (this.printManagedResources) {
            this.printResources(true, "managed");
        }
    }

    public void printResources(boolean managed, String name) {
        boolean isAny = false;
        ResourceManager resourceManager = new ResourceManager();
        for (var c : this.configs.entrySet()) {
            for (var s : c.getValue().entrySet()) {
                Set<Resource> resources = s.getValue().key.getResources();
                for (var r : resources) {
                    if (r.isAutoManaged() == managed) {
                        isAny = true;
                        resourceManager.add(r.getType(), r);
                    }
                }
            }
        }
        if (!isAny) {
            System.out.println("## No " + name + " resources found ##");
        } else {
            System.out.println("## List of " + name + " resources:");
            resourceManager.print(0);
        }
        System.out.println("##################");
    }

    private class ResourceManager {
        private final Map<String, ResourceManager> map = new HashMap<>();
        private final Set<Resource> resources = new HashSet<>();

        public void add(Path type, Resource resource) {
            if (type == null) {
                this.resources.add(resource);
                return;
            }
            if (type.getNameCount() == 1) {
                this.map.computeIfAbsent(type.toString(), s -> new ResourceManager()).add(null, resource);
                return;
            }
            this.map.computeIfAbsent(type.subpath(0, 1).toString(), s -> new ResourceManager()).add(type.subpath(1, type.getNameCount()), resource);
        }

        public void print(int nesting) {
            final String padding = "  ";
            final String contentPrefix = "# ";

            for (var r : this.resources) {
                for (int i = 0; i < nesting; i++) {
                    System.out.print(padding);
                }
                System.out.println(contentPrefix + r);
            }
            for (var e : this.map.entrySet()) {
                for (int i = 0; i < nesting; i++) {
                    System.out.print(padding);
                }
                System.out.println(e.getKey());
                e.getValue().print(nesting + 1);
            }
        }
    }
}
